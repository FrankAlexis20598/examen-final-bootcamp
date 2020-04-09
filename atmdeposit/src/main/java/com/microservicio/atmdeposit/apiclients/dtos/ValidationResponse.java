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
public class ValidationResponse implements Serializable {

    private String entityName;
    private boolean success;
}
