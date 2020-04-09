package com.microservicio.atmdeposit.services.impl;

import com.microservicio.atmdeposit.apiclients.apis.*;
import com.microservicio.atmdeposit.apiclients.dtos.*;
import com.microservicio.atmdeposit.exceptions.BlacklistedPersonException;
import com.microservicio.atmdeposit.exceptions.PersonNotFoundException;
import com.microservicio.atmdeposit.services.dtos.AtmDepositResponse;
import com.microservicio.atmdeposit.services.dtos.DepositIn;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import retrofit2.Response;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Frank
 */
@RunWith(MockitoJUnitRunner.class)
public class AtmDepositServiceImplTest {

    @InjectMocks
    private AtmDepositServiceImpl service;

    @Mock
    private DepositIn depositIn;

    @Mock
    private IApiAccounts apiAccounts;

    @Mock
    private IApiCards apiCards;

    @Mock
    private IApiFingerprints apiFingerprints;

    @Mock
    private IApiPersons apiPersons;

    @Mock
    private IApiReniec apiReniec;

    private List<PersonResponse> personResponseList;

    @Before
    public void setUp() {
        personResponseList = getPersonResponseList();

        when(apiFingerprints.validateFingerprint(any())).thenReturn(getValidationFingerprintResponse());
        when(apiReniec.validateDocumentNumber(any())).thenReturn(getValidationReniecRespose());
        when(apiCards.getCards(any())).thenReturn(getCardsResponse());
        when(apiAccounts.getAccount(any())).thenReturn(
                getAccountRespose("1111222233334441"),
                getAccountRespose("1111222233334442"),
                getAccountRespose("1111222233334443")
        );
    }

    private List<PersonResponse> getPersonResponseList() {
        return Arrays.asList(
                new PersonResponse(1, "10000000", true, false),
                new PersonResponse(2, "10000001", false, false),
                new PersonResponse(3, "10000002", true, true)
        );
    }

    @Test
    public void testDepositAmount() {
        when(depositIn.getDocumentNumber()).thenReturn("10000000");
        when(depositIn.getAmount()).thenReturn(100D);
        when(apiPersons.getPerson(any())).thenReturn(getPersonResponse("10000000"));

        TestObserver<AtmDepositResponse> testObserver = new TestObserver<>();
        service.depositAmount(depositIn).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertSubscribed()
                .assertComplete()
                .assertValue(response -> response.getFingerprintEntityName().equals("Core"))
                .assertValue(response -> response.getValidAccounts().size() == 3)
                .assertValue(response -> response.getCustomerAmount() == 3100D)
                .assertNoErrors();
    }

    @Test
    public void testDepositAmountValidateWithReniec() {
        when(depositIn.getDocumentNumber()).thenReturn("10000001");
        when(depositIn.getAmount()).thenReturn(500D);
        when(apiPersons.getPerson(any())).thenReturn(getPersonResponse("10000001"));

        TestObserver<AtmDepositResponse> testObserver = new TestObserver<>();
        service.depositAmount(depositIn).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertSubscribed()
                .assertComplete()
                .assertValue(response -> response.getFingerprintEntityName().equals("Reniec"))
                .assertValue(response -> response.getValidAccounts().size() == 3)
                .assertValue(response -> response.getCustomerAmount() == 3500D)
                .assertNoErrors();
    }

    @Test
    public void testDepositAmountBlacklistedPersonException() {
        when(depositIn.getDocumentNumber()).thenReturn("10000002");
        when(apiPersons.getPerson(any())).thenReturn(getPersonResponse("10000002"));

        service.depositAmount(depositIn).test()
                .assertSubscribed()
                .assertNotComplete()
                .assertValueCount(0)
                .assertError(BlacklistedPersonException.class);
    }

    @Test
    public void testDepositAmountPersonNotFoundException() {
        when(depositIn.getDocumentNumber()).thenReturn("10000003");
        when(apiPersons.getPerson(any())).thenReturn(getPersonResponse("10000003"));

        service.depositAmount(depositIn).test()
                .assertSubscribed()
                .assertNotComplete()
                .assertValueCount(0)
                .assertError(PersonNotFoundException.class);
    }

    private Single<Response<PersonResponse>> getPersonResponse(String documentNumber) {
        Response<PersonResponse> response = personResponseList.stream()
                .filter(person -> person.getDocument().equals(documentNumber))
                .findFirst()
                .map(Response::success)
                .orElse(Response.error(HttpStatus.NOT_FOUND.value(), getResponseBody()));
        return Single.just(response);
    }

    private ResponseBody getResponseBody() {
        return ResponseBody.create(MediaType.parse("application/json"), "");
    }

    private Single<ValidationResponse> getValidationFingerprintResponse() {
        return Single.just(new ValidationResponse("Core", true));
    }

    private Single<ValidationResponse> getValidationReniecRespose() {
        return Single.just(new ValidationResponse("Reniec", true));
    }

    private Single<CardResponse> getCardsResponse() {
        return Single.just(new CardResponse(Arrays.asList(
                new Card("1111222233334441", true),
                new Card("1111222233334442", true),
                new Card("1111222233334443", true),
                new Card("1111222233334444", false),
                new Card("1111222233334445", false),
                new Card("1111222233334446", false)
        )));
    }

    private Single<AccountResponse> getAccountRespose(String cardNumber) {
        return Single.just(new AccountResponse(getAccountNumber(cardNumber), getAmount(cardNumber)));
    }

    private String getAccountNumber(String cardNumber) {
        return cardNumber.concat("XXX");
    }

    private double getAmount(String cardNumber) {
        switch (cardNumber) {
            case "1111222233334441":
                return 1000;
            case "1111222233334442":
                return 500;
            case "1111222233334443":
                return 1500;
            default:
                return 200;
        }
    }
}
