package com.jb.api.domain.exception;

import com.fasterxml.jackson.databind.ser.Serializers;

public class InvalidTaxException extends BaseException {

    public InvalidTaxException() {
        super("Invalid taxtable");
    }
}
