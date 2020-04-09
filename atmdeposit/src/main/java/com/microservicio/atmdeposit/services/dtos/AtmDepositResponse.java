package com.microservicio.atmdeposit.services.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.*;

/**
 *
 * @author Frank
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtmDepositResponse implements Serializable {
    
    private String fingerprintEntityName;
    private List<ValidAccount> validAccounts;
    private double customerAmount;
}
