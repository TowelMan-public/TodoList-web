package com.example.demo.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.client.api.entity.SpaceEntity;
import com.example.demo.client.rest.RestTemplateAdapter;
import com.example.demo.security.UserDetailsImp;

import lombok.Data;

@Component
public class SpaceApi {
	private static final String ROOT_URL = ApiUrlRootConfing.ROOT_URL + "/space";
	
	@Autowired
	RestTemplateAdapter restTemplateAdapter;
	
	public void deleteSpace(UserDetailsImp user, int spaceId) {
		final String URL = ROOT_URL + "/delete";
		
		Dto dto = new Dto();
		dto.setSpaceId(spaceId);
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, Void.class, user);
	}
	
	public SpaceEntity getSpace(UserDetailsImp user, int spaceId) {
		final String URL = ROOT_URL + "/get";
		
		Dto dto = new Dto();
		dto.setSpaceId(spaceId);
		
		return restTemplateAdapter.getForObjectWhenLogined(URL, dto, SpaceEntity.class, user);
	}
	
	public void makeSpace(UserDetailsImp user, String spaceName, int scopeId) {
		final String URL = ROOT_URL + "/make";
		
		Dto dto = new Dto();
		dto.setSpaceName(spaceName);
		dto.setScopeId(scopeId);
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, Void.class, user);
	}
	
	public void secessionSpace(UserDetailsImp user, int spaceId) {
		final String URL = ROOT_URL + "/secession";
		
		Dto dto = new Dto();
		dto.setSpaceId(spaceId);
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, Void.class, user);
	}
	
	public void updateScope(UserDetailsImp user, int spaceId, int scopeId) {
		final String URL = ROOT_URL + "/scope/update";
		
		Dto dto = new Dto();
		dto.setSpaceId(spaceId);
		dto.setScopeId(scopeId);
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, Void.class, user);
	}
	
	@Data
	public class Dto{
		private Integer spaceId;
		private Integer scopeId;
		private String spaceName;
	}
}
