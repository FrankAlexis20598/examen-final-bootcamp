package com.microservicio.fingerprints.dtos;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
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
public class ValidateIn implements Serializable {

    @NotEmpty
    private String document;
}
