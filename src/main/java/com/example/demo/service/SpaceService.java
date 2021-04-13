package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.PublicSpaceApi;
import com.example.demo.client.api.SpaceApi;
import com.example.demo.form.insert.InsertSpaceForm;
import com.example.demo.security.UserDetailsImp;

@Service
public class SpaceService {
	@Autowired
	SpaceApi spaceApi;
	@Autowired
	PublicSpaceApi publicSpaceApi;
	
	public void insertSpace(UserDetailsImp user, InsertSpaceForm form) {
		spaceApi.makeSpace(user,
				form.getSpaceName(),
				Integer.parseInt(form.getScopeId()));
	}	
}
