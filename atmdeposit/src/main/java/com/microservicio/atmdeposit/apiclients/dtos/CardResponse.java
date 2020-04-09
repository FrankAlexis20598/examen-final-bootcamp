package com.microservicio.atmdeposit.apiclients.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Frank
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse implements Serializable {

    private List<Card> cards;
}
