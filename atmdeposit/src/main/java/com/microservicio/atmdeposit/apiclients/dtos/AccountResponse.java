package com.microservicio.atmdeposit.apiclients.dtos;

import lombok.*;

import java.io.Serializable;

/**
 *
 * @author Frank
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse implements Serializable {

    private String accountNumber;
    private double amount;
}
