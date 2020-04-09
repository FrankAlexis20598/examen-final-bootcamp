package com.microservicio.accounts.controllers;

import com.microservicio.accounts.services.IAccountService;
import com.microservicio.accounts.services.dtos.AccountResponse;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Frank
 */
@RestController
@RequestMapping(value = "/core/accounts")
public class AccountController {
    
    @Autowired
    private IAccountService accountService;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<AccountResponse> findByCardNumber(@RequestParam String cardNumber) {
        return accountService.findByCardNumber(cardNumber);
    }
}
