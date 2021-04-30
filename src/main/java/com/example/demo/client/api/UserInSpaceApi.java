package com.example.demo.client.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.client.api.entity.UserInSpaceEntity;
import com.example.demo.client.api.entity.VoidEntity;
import com.example.demo.client.rest.RestTemplateAdapter;
import com.example.demo.security.UserDetailsImp;

import lombok.Data;

@Component
public class UserInSpaceApi {
	private static final String ROOT_URL = ApiUrlRootConfing.ROOT_URL + "/space/user";
	
	@Autowired
	RestTemplateAdapter restTemplateAdapter;
	
	public void deleteUserInSpace(UserDetailsImp user, int spaceId, String username) {
		final String URL = ROOT_URL + "/delete";
		
		Dto dto = new Dto();
		dto.setSpaceId(spaceId);
		dto.setUsername(username);
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, VoidEntity.class, user);
	}
	
	public List<UserInSpaceEntity> getUserInSpace(UserDetailsImp user, int spaceId){
		final String URL = ROOT_URL + "/get";
		
		Dto dto = new Dto();
		dto.setSpaceId(spaceId);
		
		return restTemplateAdapter.getForObjectsWhenLogined(URL, new VoidEntity(), UserInSpaceEntity.class, user);
	}
	
	public void insertUserInSpace(UserDetailsImp user, DtoBuilder builder) {
		final String URL = ROOT_URL + "/insert";
		
		Dto dto = builder.build();
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, VoidEntity.class, user);
	}
	
	public void updateUserAuthortyInSpace(UserDetailsImp user, DtoBuilder builder) {
		final String URL = ROOT_URL + "/update";
		
		Dto dto = builder.build();
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, VoidEntity.class, user);
	}
	
	public class DtoBuilder{
		private Dto dto;
		
		public DtoBuilder() {
			dto = new Dto();
		}
		
		public DtoBuilder setSpaceId(int spaceId) {
			dto.setSpaceId(spaceId);
			return this;
		}
		
		public DtoBuilder setUsername(String username) {
			dto.setUsername(username);
			return this;
		}
		
		public DtoBuilder setAuthortyId(int authortyId) {
			dto.setAuthortyId(authortyId);
			return this;
		}
		
		public Dto build() {
			return dto;
		}
	}
	
	@Data
	public class Dto{
		private Integer spaceId;
		private String username;
		private Integer authortyId;
	}
}
