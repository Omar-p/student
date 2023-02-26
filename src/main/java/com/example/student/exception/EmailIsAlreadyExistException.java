package com.example.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailIsAlreadyExistException extends RuntimeException {

    public EmailIsAlreadyExistException(String message) {
      super(message);
    }
}
