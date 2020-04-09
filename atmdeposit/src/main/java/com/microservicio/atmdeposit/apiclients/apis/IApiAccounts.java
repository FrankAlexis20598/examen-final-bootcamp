package com.microservicio.atmdeposit.apiclients.apis;

import com.microservicio.atmdeposit.apiclients.dtos.AccountResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * @author Frank
 */
public interface IApiAccounts {

    @GET("accounts")
    Single<AccountResponse> getAccount(@Query("cardNumber") String cardNumber);
}
