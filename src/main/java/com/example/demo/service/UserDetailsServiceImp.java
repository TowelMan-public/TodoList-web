package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.UserApi;
import com.example.demo.client.api.entity.UserEntity;
import com.example.demo.form.insert.SignupForm;
import com.example.demo.form.update.UpdatePassword;
import com.example.demo.form.update.UpdateUsername;
import com.example.demo.security.UserDetailsImp;

@Service
public class UserDetailsServiceImp{
	@Autowired
	UserApi userApi;
	
	public String loginAndReturnToken(String username,String password) throws AuthenticationException {
		return userApi.login(username, password);
	}

	public void logout(UserDetailsImp user) {
		userApi.logout(user);
	}
	
	public void deleteUser(UserDetailsImp user) {
		userApi.deleteUser(user);
	}
	
	public UserEntity getUser(UserDetailsImp user) {
		return userApi.getUser(user);
	}

	public UpdateUsername getUsernameToUpdateUsernameForm(UserDetailsImp user) {
		return new UpdateUsername(
					userApi.getUser(user)
						.getUsername());
	}

	public void updateUsername(UserDetailsImp user, UpdateUsername form) {
		userApi.updateUsername(user, form.getUsername());
	}

	public void updatePassword(UserDetailsImp user, UpdatePassword form) {
		userApi.updatePassword(user, form.getNewPassword(), form.getOneMorePassword());
	}

	public void withdrawal(UserDetailsImp user) {
		userApi.deleteUser(user);
	}

	public void insertUser(SignupForm form) {
		userApi.insertUser(userApi. new InsertDtobBuilder()
								  .setUsername(form.getUsername())
								  .setPassword(form.getPassword())
								  .setOneMorePassword(form.getOneMorePassword()));
	}
	
}
