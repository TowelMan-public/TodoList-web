package com.example.demo.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.client.api.entity.UserEntity;
import com.example.demo.client.api.entity.VoidEntity;
import com.example.demo.client.rest.RestTemplateAdapter;
import com.example.demo.security.UserDetailsImp;

import lombok.Data;

@Component
public class UserApi {
	private static final String ROOT_URL = ApiUrlRootConfing.ROOT_URL + "/user";
	
	@Autowired
	RestTemplateAdapter restTemplateAdapter;
	
	public String login(String username,String password) {
		final String URL = ApiUrlRootConfing.ROOT_URL + "/login";
		
		LoginForm form = new LoginForm();
		form.setUsername(username);
		form.setPassword(password);
		
		return restTemplateAdapter.postForObject(URL, form, String.class)
				.getBody();
	}
	
	public void logout(UserDetailsImp user) {
		final String URL = ApiUrlRootConfing.ROOT_URL + "/logout";
		
		restTemplateAdapter.postForObjectWhenLogined(URL, new VoidEntity(), VoidEntity.class, user);
	}
	
	public void deleteUser(UserDetailsImp user) {
		final String URL = ROOT_URL + "/delete";
		
		restTemplateAdapter.postForObjectWhenLogined(URL, new VoidEntity(), VoidEntity.class, user);
	}
	
	public UserEntity getUser(UserDetailsImp user) {
		final String URL = ROOT_URL + "/get";
		
		return restTemplateAdapter.getForObjectWhenLogined(URL, new VoidEntity(), UserEntity.class, user);
	}
	
	public void insertUser(InsertDtobBuilder builder) {
		final String URL = ROOT_URL + "/insert";
		
		Dto dto = builder.build();
		
		restTemplateAdapter.postForObject(URL, dto, VoidEntity.class);
	}
	
	public void updatePassword(UserDetailsImp user,String newPassword, String oneMorePassword) {
		final String URL = ROOT_URL + "/password/update";
		
		Dto dto = new Dto();
		dto.setNewPassword(newPassword);
		dto.setOneMorePassword(oneMorePassword);
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, VoidEntity.class, user);
	}
	
	public void updateUsername(UserDetailsImp user,String newUsername) {
		final String URL = ROOT_URL + "/username/update";
		
		Dto dto = new Dto();
		dto.setNewUsername(newUsername);
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, VoidEntity.class, user);
	}
	
	public class InsertDtobBuilder{
		private Dto dto;
		
		public InsertDtobBuilder(){
			dto = new Dto();
		}
		
		public InsertDtobBuilder setUsername(String username) {
			dto.setUsername(username);
			return this;
		}
		
		public InsertDtobBuilder setPassword(String password) {
			dto.setPassword(password);
			return this;
		}
		
		public InsertDtobBuilder setOneMorePassword(String oneMorePassword) {
			dto.setOneMorePassword(oneMorePassword);
			return this;
		}
		
		public Dto build() {
			return dto;
		}
	}
	
	@Data
	public class Dto {
		private String username;
		private String newUsername;
		private String password;
		private String newPassword;
		private String oneMorePassword;
	}
	
	@Data
	public class LoginForm{
		private String username;
		private String password;
	}
}
