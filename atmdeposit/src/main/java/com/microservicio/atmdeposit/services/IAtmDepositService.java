package com.microservicio.atmdeposit.services;

import com.microservicio.atmdeposit.services.dtos.AtmDepositResponse;
import com.microservicio.atmdeposit.services.dtos.DepositIn;
import io.reactivex.Single;

/**
 * @author Frank
 */
public interface IAtmDepositService {

    Single<AtmDepositResponse> depositAmount(DepositIn depositIn);
}
