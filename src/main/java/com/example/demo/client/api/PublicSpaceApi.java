package com.example.demo.client.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.client.api.entity.SpaceEntity;
import com.example.demo.client.api.entity.VoidEntity;
import com.example.demo.client.rest.RestTemplateAdapter;
import com.example.demo.security.UserDetailsImp;

import lombok.Data;

@Component
public class PublicSpaceApi {
	private static final String ROOT_URL = "/space/me";
	
	@Autowired
	RestTemplateAdapter restTemplateAdapter;
	
	public List<SpaceEntity> getPublicSpace(UserDetailsImp user){
		final String URL = ROOT_URL + "/get";
		
		return restTemplateAdapter.getForObjectsWhenLogined(URL, new VoidEntity(), SpaceEntity.class, user);
	}
	
	public void joinSpace(UserDetailsImp user,int spaceId){
		final String URL = ROOT_URL + "/join";
		
		Dto dto = new Dto();
		dto.setSpaceId(spaceId);
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, VoidEntity.class, user);
	}
	
	@Data
	public class Dto{
		private Integer spaceId;
	}
}
