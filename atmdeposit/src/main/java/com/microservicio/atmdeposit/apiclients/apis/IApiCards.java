package com.microservicio.atmdeposit.apiclients.apis;

import com.microservicio.atmdeposit.apiclients.dtos.CardResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * @author Frank
 */
public interface IApiCards {

    @GET("cards")
    Single<CardResponse> getCards(@Query("documentNumber") String documentNumber);
}
