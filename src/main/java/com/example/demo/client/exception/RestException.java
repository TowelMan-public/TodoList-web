package com.example.demo.client.exception;

import lombok.Getter;

public class RestException  extends RuntimeException{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L;
	
	@Getter
	private final String errorCord;
	
	public RestException(String errorCord, String message){
		super(message);
		this.errorCord = errorCord;
	}
}
