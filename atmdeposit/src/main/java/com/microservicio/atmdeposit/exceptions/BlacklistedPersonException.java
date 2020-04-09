package com.microservicio.atmdeposit.exceptions;

public class BlacklistedPersonException extends Exception {

    public BlacklistedPersonException() {
        super("La persona se encuentra en la lista negra.");
    }

}
