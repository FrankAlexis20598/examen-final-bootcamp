package com.microservicio.atmdeposit.services.impl;

import com.microservicio.atmdeposit.apiclients.apis.*;
import com.microservicio.atmdeposit.apiclients.dtos.*;
import com.microservicio.atmdeposit.exceptions.BlacklistedPersonException;
import com.microservicio.atmdeposit.exceptions.PersonNotFoundException;
import com.microservicio.atmdeposit.services.IAtmDepositService;
import com.microservicio.atmdeposit.services.dtos.AtmDepositResponse;
import com.microservicio.atmdeposit.services.dtos.DepositIn;
import com.microservicio.atmdeposit.services.dtos.ValidAccount;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import retrofit2.Response;

/**
 * @author Frank
 */
@Service
@Log4j2
public class AtmDepositServiceImpl implements IAtmDepositService {

    @Autowired
    private IApiAccounts apiAccounts;
    @Autowired
    private IApiCards apiCards;
    @Autowired
    private IApiFingerprints apiFingerprints;
    @Autowired
    private IApiPersons apiPersons;
    @Autowired
    private IApiReniec apiReniec;

    @Override
    public Single<AtmDepositResponse> depositAmount(DepositIn depositIn) {
        return apiPersons.getPerson(depositIn.getDocumentNumber())
                .filter(this::validateIfPersonNotFound)
                .map(Response::body)
                .filter(personResponse -> validateBlacklist(personResponse.isBlacklist()))
                .flatMap(this::validateDocumentNumber)
                .filter(ValidationResponse::isSuccess)
                .map(ValidationResponse::getEntityName)
                .zipWith(
                        getAccounts().apply(depositIn.getDocumentNumber()),
                        (entityName, accountResponses) -> parseAtmDepositResponse(entityName, accountResponses, depositIn.getAmount()))
                .toSingle();
    }

    private boolean validateIfPersonNotFound(Response<PersonResponse> response) throws PersonNotFoundException {
        if (response.code() == HttpStatus.NOT_FOUND.value()) {
            throw new PersonNotFoundException();
        }
        return true;
    }

    private boolean validateBlacklist(boolean isBlacklist) throws BlacklistedPersonException {
        if (isBlacklist) {
            throw new BlacklistedPersonException();
        }
        return true;
    }

    private Maybe<ValidationResponse> validateDocumentNumber(PersonResponse personResponse) {
        ValidateIn validateIn = new ValidateIn(personResponse.getDocument());
        if (personResponse.isFingerprint()) {
            return apiFingerprints.validateFingerprint(validateIn)
                    .map(response -> parseValidationResponse(response.getEntityName(), response.isSuccess()))
                    .toMaybe();
        } else {
            return apiReniec.validateDocumentNumber(validateIn)
                    .map(response -> parseValidationResponse(response.getEntityName(), response.isSuccess()))
                    .toMaybe();
        }
    }

    private ValidationResponse parseValidationResponse(String entityName, boolean success) {
        return new ValidationResponse(entityName, success);
    }

    private Function<String, Maybe<List<AccountResponse>>> getAccounts() {
        return documentNumber -> apiCards.getCards(documentNumber)
                .doOnSuccess(cardResponse -> log.info("Cards: {}", cardResponse.getCards().size()))
                .toObservable()
                .flatMapIterable(CardResponse::getCards)
                .filter(Card::isActive)
                .flatMap(card -> apiAccounts.getAccount(card.getCardNumber())
                        .toObservable()
                        .subscribeOn(Schedulers.io()))
                .toList()
                .toMaybe();
    }

    private AtmDepositResponse parseAtmDepositResponse(String entityName, List<AccountResponse> accountResponses, double amount) {
        return new AtmDepositResponse(
                entityName,
                getValidAccounts(accountResponses),
                getCustomerAmount(accountResponses, amount)
        );
    }

    private List<ValidAccount> getValidAccounts(List<AccountResponse> accountResponses) {
        return accountResponses.stream()
                .map(account -> new ValidAccount(account.getAccountNumber()))
                .collect(Collectors.toList());
    }

    private double getCustomerAmount(List<AccountResponse> accountResponses, double amount) {
        double sumAmount = accountResponses.stream()
                .map(AccountResponse::getAmount)
                .mapToDouble(Double::doubleValue)
                .sum();
        return sumAmount + amount;
    }
}
