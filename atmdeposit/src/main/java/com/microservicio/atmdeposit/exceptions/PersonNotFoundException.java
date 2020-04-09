package com.microservicio.atmdeposit.exceptions;

/**
 * @author Frank
 */
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException() {
        super("Persona no encontrada.");
    }

}
