package com.microservicio.atmdeposit.apiclients.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable {

    private String cardNumber;
    private boolean active;
}
