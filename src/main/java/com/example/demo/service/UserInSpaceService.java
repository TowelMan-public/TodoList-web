package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.UserInSpaceApi;
import com.example.demo.form.insert.InsertUserInSpaceForm;
import com.example.demo.security.UserDetailsImp;

@Service
public class UserInSpaceService {
	@Autowired
	UserInSpaceApi userInSpaceApi;

	public void insertUserInSpace(UserDetailsImp user, Integer spaceId, InsertUserInSpaceForm form) {
		userInSpaceApi.insertUserInSpace(user, userInSpaceApi. new DtoBuilder()
															 .setSpaceId(spaceId)
															 .setUsername(form.getUsername())
															 .setAuthortyId(
																	 Integer.parseInt(form.getAuthortyId())));
	}
}
