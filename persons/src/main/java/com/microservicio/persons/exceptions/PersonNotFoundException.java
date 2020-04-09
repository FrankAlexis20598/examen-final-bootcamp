package com.microservicio.persons.exceptions;

/**
 *
 * @author Frank
 */
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException() {
        super("Persona no encontrada.");
    }
    
}
