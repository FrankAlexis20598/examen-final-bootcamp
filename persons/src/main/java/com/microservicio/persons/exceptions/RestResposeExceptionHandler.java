package com.microservicio.persons.exceptions;

import com.microservicio.persons.services.dto.ErrorResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Frank
 */
@RestControllerAdvice
public class RestResposeExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(PersonNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handlerPersonNotFoundException(HttpServletRequest request, PersonNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
}
