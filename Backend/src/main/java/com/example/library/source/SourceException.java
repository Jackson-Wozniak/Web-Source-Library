package com.example.library.source;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SourceException extends RuntimeException{
    public SourceException(String message){
        super(message);
    }
}
