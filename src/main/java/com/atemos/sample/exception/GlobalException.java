package com.atemos.sample.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class GlobalException extends RuntimeException{

    private ErrorCode errorCode;

    public GlobalException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
        
    }
}
