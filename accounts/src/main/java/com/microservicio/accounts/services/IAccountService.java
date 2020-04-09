package com.microservicio.accounts.services;

import com.microservicio.accounts.services.dtos.AccountResponse;
import io.reactivex.Single;

/**
 *
 * @author Frank
 */
public interface IAccountService {
    
    Single<AccountResponse> findByCardNumber(String cardNumber);
}
