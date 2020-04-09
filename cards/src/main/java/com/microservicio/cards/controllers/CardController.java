package com.microservicio.cards.controllers;

import com.microservicio.cards.services.ICardService;
import com.microservicio.cards.services.dtos.CardResponse;
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
@RequestMapping(value = "/core/cards")
public class CardController {
    
    @Autowired
    private ICardService cardService;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<CardResponse> findAllByDocumentNumber(@RequestParam String documentNumber) {
        return cardService.findAllByDocumentNumber(documentNumber);
    }
}
