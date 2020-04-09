package com.microservicio.persons.services.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Frank
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {

    private String message;
    private int statusCode;
    private String path;
}
