package com.ebi.personrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException(String errorMsg){
        super(errorMsg);
    }

    public InvalidTokenException(String errorMsg, Throwable exception){
        super(errorMsg,exception);
    }
}
