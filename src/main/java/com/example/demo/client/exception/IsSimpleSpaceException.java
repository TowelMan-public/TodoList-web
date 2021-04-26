package com.example.demo.client.exception;

public class IsSimpleSpaceException extends RuntimeException{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L; 
	
	public IsSimpleSpaceException(String message){
		super(message);
	}
}