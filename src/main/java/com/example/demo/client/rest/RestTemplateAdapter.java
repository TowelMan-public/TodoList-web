package com.example.demo.client.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateAdapter {
	
	private final RestTemplate restTemplate;
	
	//RestTemplateの設定
	public RestTemplateAdapter() {
		restTemplate = new RestTemplateBuilder()
							.errorHandler(new RestTemplateResponseErrorHandler())
							.build();
	}
	
	
	public <R,T> ResponseEntity<T> postForObjectWhenLogined(String url, R requestBody, Class<T> responseBodyClass,String token) {
		//リクエスト作成
		RequestEntity<R> requestEntity = 
		        RequestEntity
		          .post(url)
		          .header("X-AUTH-TOKEN",token)
		          .body(requestBody);
		
		//実行
		return restTemplate.exchange(requestEntity, responseBodyClass);
	}
	
	public <R,T> ResponseEntity<T> getForObjectWhenLogined(String url, R requestBody, Class<T> responseBodyClass,String token) {
		//変換
		Map<String, String> requestBodyMap;
		try {
			requestBodyMap = BeanUtils.describe(requestBody);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			requestBodyMap = new HashMap<>();
		}
		
		//URLの作成
		StringBuilder bld = new StringBuilder(url);
		for(Map.Entry<String, ?> entry : requestBodyMap.entrySet()) {
			bld.append(entry.getKey() + "=" + entry.getValue().toString() + "&");
		}
		bld.setLength(bld.length()-1);
		url = bld.toString();
		
		//リクエスト作成
		RequestEntity<Void> requestEntity = 
		        RequestEntity
		          .get(url)
		          .header("X-AUTH-TOKEN",token)
		          .build();
		
		//実行
		return restTemplate.exchange(requestEntity, responseBodyClass);
	}
	
	public <R ,T> ResponseEntity<T> postForObject(String url,R requestBody, Class<T> responseBodyClass) {		
		//リクエスト作成
		RequestEntity<R> requestEntity = 
		        RequestEntity
		          .post(url)
		          .body(requestBody);
		
		//実行
		return restTemplate.exchange(requestEntity, responseBodyClass);
	}
	
	public <R,T>  ResponseEntity<T> gettForObject(String url, R requestBody, Class<T> responseBodyClass) {
		//変換
		Map<String, String> requestBodyMap;
		try {
			requestBodyMap = BeanUtils.describe(requestBody);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			requestBodyMap = new HashMap<>();
		}
		
		//URLの作成
		StringBuilder bld = new StringBuilder(url);
		for(Map.Entry<String, ?> entry : requestBodyMap.entrySet()) {
			bld.append(entry.getKey() + "=" + entry.getValue().toString() + "&");
		}
		bld.setLength(bld.length()-1);
		url = bld.toString();
		
		//リクエスト作成
		RequestEntity<Void> requestEntity = 
		        RequestEntity
		          .get(url)
		          .build();
		
		//実行
		return restTemplate.exchange(requestEntity, responseBodyClass);
	}
}
