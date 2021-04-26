package com.example.demo.client.exception;

public class AlreadyUsedException extends RuntimeException{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L; 
	
	public AlreadyUsedException(String message){
		super(message);
	}
}