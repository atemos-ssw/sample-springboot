package com.atemos.sample.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404,"9000","DATA NOT FOUND"),
    DATA_DUPLICATION(400,"9001","DATA IS DUPLICATED"),
    INTER_SERVER_ERROR(500,"9002","INTER SERVER ERROR")   
    ;

    private int status;
    private String errorCode;
    private String message;
    
	int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
	String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	
	String getErrorCode() {
		return errorCode;
	}
	
}