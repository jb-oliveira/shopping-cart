package com.jb.api.domain.exception;

import java.util.function.Supplier;

public class InvalidItemException extends DomainException  {
    public InvalidItemException(String message) {
        super(message);
    }
}
