package com.microservicio.atmdeposit.exceptions;

import com.microservicio.atmdeposit.services.dtos.ErrorResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestResponseExceptionHandlerTest {

    @InjectMocks
    private RestResponseExceptionHandler handler;

    @Mock
    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        when(request.getRequestURI()).thenReturn("/atm/deposits");
    }

    @Test
    public void testHandlerBlacklistedPersonException() {
        String errorMessage = "La persona se encuentra en la lista negra.";
        ResponseEntity<ErrorResponse> response = handler.handlerBlacklistedPersonException(request, new BlacklistedPersonException());
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getMessage(), errorMessage);
    }

    @Test
    public void testHandlerPersonNotFoundException() {
        String errorMessage = "Persona no encontrada.";
        ResponseEntity<ErrorResponse> response = handler.handlerPersonNotFoundException(request, new PersonNotFoundException());
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getMessage(), errorMessage);
    }
}