package com.microservicio.atmdeposit.apiclients.apis;

import com.microservicio.atmdeposit.apiclients.dtos.ValidateIn;
import com.microservicio.atmdeposit.apiclients.dtos.ValidationResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 *
 * @author Frank
 */
public interface IApiReniec {

    @POST("reniec/validate")
    Single<ValidationResponse> validateDocumentNumber(@Body ValidateIn validateIn);
}
