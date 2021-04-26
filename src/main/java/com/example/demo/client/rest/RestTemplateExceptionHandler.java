package com.example.demo.client.rest;

import java.util.regex.Matcher;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

import com.example.demo.client.exception.*;

import lombok.Data;

@Component
public class RestTemplateExceptionHandler {
	
	public void handlError(RestClientResponseException e) {
		ErrorResponse errorResponse = getErrorResponse(e);
		
		//各種具体的にわかる例外を投げる・分別ができないときは他のやつ投げる
		switch(errorResponse.getErrorCode()) {
		case "AlreadyUsedException":
			throw new AlreadyUsedException(errorResponse.getMessage());
		case "HaveNotAuthorityInSpaceException":
			throw new HaveNotAuthorityInSpaceException(errorResponse.getMessage());
		case "NotFoundException":
			throw new NotFoundException(errorResponse.getMessage());
		case "SpaceIsnotPublicException":
			throw new SpaceIsnotPublicException(errorResponse.getMessage());
		case "UserAleadyJoinSpaceException":
			throw new UserAleadyJoinSpaceException(errorResponse.getMessage());
		case "IsSimpleSpaceException":
			throw new IsSimpleSpaceException(errorResponse.getMessage());
		default:
			switch(HttpStatus.valueOf(e.getRawStatusCode())) {
			case UNAUTHORIZED:
				throw new InvalidLoginException(errorResponse.getMessage());
			case FORBIDDEN:
				throw new LoginFailureException(errorResponse.getMessage());
			default:
				throw e;
			}
		}
	}
	
	private ErrorResponse getErrorResponse(RestClientResponseException e) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^.*\"errorCode\":\"(.*)\".*\"message\":\"(.*)\"$");
		Matcher matcher = pattern.matcher(e.getResponseBodyAsString());
		if(matcher.matches()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorCode(matcher.group(1));
			errorResponse.setMessage(matcher.group(2));
			return errorResponse;
		}else {
			return new ErrorResponse();
		}
	}
	
	@Data
	private class ErrorResponse {
		private String errorCode;
	    private String message;
	    
	    public ErrorResponse() {
	    	errorCode = "NULL";
	    }
	}
}
