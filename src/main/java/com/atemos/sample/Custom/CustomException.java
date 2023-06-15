package com.atemos.sample.Custom;

import com.atemos.sample.entity.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

	ErrorCode errorCode;
}
