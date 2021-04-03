package com.example.demo.client.rest;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler{
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR ||
				response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
	}
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		
	}
}