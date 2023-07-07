package com.atemos.sample.exception;

import org.springframework.http.HttpStatus;

import com.atemos.sample.custom.BasicResponse;

import lombok.Data;

@SuppressWarnings("rawtypes")
@Data
public class ErrorResponse extends BasicResponse {
	private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }
}
