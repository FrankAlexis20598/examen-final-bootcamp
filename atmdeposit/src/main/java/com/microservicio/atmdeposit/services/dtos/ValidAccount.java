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
public class ValidAccount implements Serializable {
    
    private String accountNumber;
}
