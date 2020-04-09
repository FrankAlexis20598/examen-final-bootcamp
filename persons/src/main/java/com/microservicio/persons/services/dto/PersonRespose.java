package com.microservicio.persons.services.dto;

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
public class PersonRespose implements Serializable {
    
    private int id;
    private String document;
    private boolean fingerprint;
    private boolean blacklist;
}
