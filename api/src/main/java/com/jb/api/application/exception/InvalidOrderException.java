package com.jb.api.application.exception;

import com.jb.api.domain.exception.DomainException;

public class InvalidOrderException extends DomainException {
    public InvalidOrderException(String code) {
        super("Invalid order code = " + code);
    }
}
