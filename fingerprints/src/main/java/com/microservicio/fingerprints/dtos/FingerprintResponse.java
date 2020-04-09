package com.microservicio.fingerprints.dtos;

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
public class FingerprintResponse implements Serializable {

    private String entityName;
    private boolean success;
}
