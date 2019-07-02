package com.ebi.personrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String errorMsg) {
        super(errorMsg);
    }

    public PersonNotFoundException(String errorMsg, Throwable rootcause) {
        super(errorMsg, rootcause);
    }
}
