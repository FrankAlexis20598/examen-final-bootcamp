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
public class ValidateIn implements Serializable {

    private String document;
}
