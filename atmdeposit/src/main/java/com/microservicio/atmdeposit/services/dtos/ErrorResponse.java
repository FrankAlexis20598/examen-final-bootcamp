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
public class ErrorResponse implements Serializable {
    
    private String message;
    private int statusCode;
    private String path;
}
