package com.example.demo.client.exception;

public class NotFoundException extends RuntimeException{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L; 
	
	public NotFoundException(String message){
		super(message);
	}
}
