package com.example.demo.client.exception;

public class SpaceIsnotPublicException extends RuntimeException{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L;
	
	public SpaceIsnotPublicException(String message){
		super(message);
	}
}