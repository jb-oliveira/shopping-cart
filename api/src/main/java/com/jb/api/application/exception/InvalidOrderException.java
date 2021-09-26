package com.jb.api.application.exception;

import com.jb.api.domain.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidOrderException extends BaseException {

    public InvalidOrderException(String code) {
        super("Invalid order code = " + code);
    }

}
