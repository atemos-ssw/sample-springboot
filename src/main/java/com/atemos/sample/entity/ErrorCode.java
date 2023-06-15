package com.atemos.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {


	BAD_REQUEST(HttpStatus.BAD_REQUEST, "ERROR-002","request parameters are not good"),
	CONFLICT(HttpStatus.CONFLICT, "ERROR-900", "데에터 충돌하였습니다"),
    GATEWAY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR-001", "게이트웨이 에러");
    // ...
    
    private final HttpStatus httpStatus;	// HttpStatus
    private final String code;				// ACCOUNT-001
    private final String message;			// 설명
}
