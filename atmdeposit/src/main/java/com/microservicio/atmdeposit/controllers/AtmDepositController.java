package com.microservicio.atmdeposit.controllers;

import com.microservicio.atmdeposit.services.IAtmDepositService;
import com.microservicio.atmdeposit.services.dtos.AtmDepositResponse;
import com.microservicio.atmdeposit.services.dtos.DepositIn;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 *
 * @author Frank
 */
@RestController
@RequestMapping(value = "/atm/deposits")
public class AtmDepositController implements Serializable {

    @Autowired
    private IAtmDepositService atmDepositService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<AtmDepositResponse> depositAmount(@RequestBody DepositIn depositIn) {
        return atmDepositService.depositAmount(depositIn);
    }

}
