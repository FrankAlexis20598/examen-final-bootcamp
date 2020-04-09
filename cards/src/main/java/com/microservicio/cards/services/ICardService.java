package com.microservicio.cards.services;

import com.microservicio.cards.services.dtos.CardResponse;
import io.reactivex.Single;

/**
 *
 * @author Frank
 */
public interface ICardService {
    
    Single<CardResponse> findAllByDocumentNumber(String documentNumber);
}
