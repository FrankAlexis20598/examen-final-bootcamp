package com.microservicio.atmdeposit.services.dtos;

import java.io.Serializable;

import lombok.*;

/**
 *
 * @author Frank
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepositIn implements Serializable {
    
    private String documentNumber;
    private double amount;
}
