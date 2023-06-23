package com.atemos.sample.utils;

import org.springframework.stereotype.Component;

public class Utils {

	public static final Utils  instance = new Utils();
	
	public Utils() {
		
	}
	
	public static Utils getInstnace() {
		return instance;
	}
	
	
}
