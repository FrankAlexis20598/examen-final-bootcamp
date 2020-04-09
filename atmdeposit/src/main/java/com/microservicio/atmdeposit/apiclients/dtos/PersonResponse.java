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
public class PersonResponse implements Serializable {

    private int id;
    private String document;
    private boolean fingerprint;
    private boolean blacklist;
}
