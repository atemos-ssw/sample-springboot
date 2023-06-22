package com.atemos.sample.Custom;

import org.springframework.http.HttpStatus;

@SuppressWarnings("rawtypes")
public class ErrorResponse extends BasicResponse {
	private String errorMessage;
//	private String errorCode;
	private HttpStatus errorCode;
	 
	public ErrorResponse(String errorMessage, HttpStatus errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
}
