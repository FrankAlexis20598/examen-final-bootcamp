package com.microservicio.cards.services.impl;

import com.microservicio.cards.services.ICardService;
import com.microservicio.cards.services.dtos.Card;
import com.microservicio.cards.services.dtos.CardResponse;
import io.reactivex.Single;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Frank
 */
@Service
public class CardServiceImpl implements ICardService {

    @Override
    public Single<CardResponse> findAllByDocumentNumber(String documentNumber) {
        return Single.fromCallable(getCards).map(CardResponse::new);
    }

    private final Callable<List<Card>> getCards = () -> {
        return Arrays.asList(
            new Card("1111222233334441", true),
            new Card("1111222233334442", true),
            new Card("1111222233334443", true),
            new Card("1111222233334444", false),
            new Card("1111222233334445", false),
            new Card("1111222233334446", false)
        );
    };
}
