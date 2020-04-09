package com.microservicio.accounts.services.impl;

import com.microservicio.accounts.services.IAccountService;
import com.microservicio.accounts.services.dtos.AccountResponse;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

/**
 *
 * @author Frank
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Override
    public Single<AccountResponse> findByCardNumber(String cardNumber) {
        return Single.fromCallable(() -> getAccount(cardNumber));
    }

    private AccountResponse getAccount(String cardNumber) {
        try {
            Thread.sleep(5000);
            return new AccountResponse(getAccountNumber(cardNumber), getAmount(cardNumber));
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
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
