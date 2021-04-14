package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.MySpaceApi;
import com.example.demo.client.api.entity.SpaceEntity;
import com.example.demo.security.UserDetailsImp;

@Service
public class MySpaceService {
	@Autowired
	MySpaceApi mySpaceApi;

	public List<SpaceEntity> getJoinedSpace(UserDetailsImp user) {
		return mySpaceApi.getMySpace(user);
	}
}
