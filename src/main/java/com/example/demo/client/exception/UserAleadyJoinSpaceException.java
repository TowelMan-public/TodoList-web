package com.example.demo.client.exception;

public class UserAleadyJoinSpaceException extends RuntimeException{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L;
	
	public UserAleadyJoinSpaceException(String message){
		super(message);
	}
}
