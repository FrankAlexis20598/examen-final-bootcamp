package com.microservicio.accounts.services.dtos;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Frank
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse implements Serializable {

    private String accountNumber;
    private double amount;
    
    public AccountResponse(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
