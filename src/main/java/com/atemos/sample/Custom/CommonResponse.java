package com.atemos.sample.custom;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data; 

@Data
public class CommonResponse<T> extends BasicResponse {
 
	String message=null;
	HttpStatus httpCode = null;
	Object data = null;
//	private int count;
//	private T data;
//
//	public CommonResponse(T data) {
//		this.data = data;
//		if(data instanceof List) {
//			this.count = ((List<?>)data).size();
//		} else {
//			this.count = 1;
//		}
//	}
}
