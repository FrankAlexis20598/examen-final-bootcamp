package com.microservicio.atmdeposit.apiclients.apis;

import com.microservicio.atmdeposit.apiclients.dtos.PersonResponse;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * @author Frank
 */
public interface IApiPersons {
    
    @GET("persons")
    Single<Response<PersonResponse>> getPerson(@Query("documentNumber") String documentNumber);
}
