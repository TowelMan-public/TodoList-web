package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.PublicSpaceApi;
import com.example.demo.client.api.SpaceApi;
import com.example.demo.client.api.entity.SpaceEntity;
import com.example.demo.form.delete.DeleteNoneUserInSpaceForm;
import com.example.demo.form.insert.InsertSpaceForm;
import com.example.demo.form.insert.JoinPublicSpaceForm;
import com.example.demo.form.update.UpdateSpaceForm;
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

	public List<SpaceEntity> getPublicSpace(UserDetailsImp user) {
		return publicSpaceApi.getPublicSpace(user);
	}

	public UpdateSpaceForm getSpace(UserDetailsImp user, Integer spaceId) {
		SpaceEntity entity = spaceApi.getSpace(user, spaceId);
		
		//データセット
		UpdateSpaceForm form = new UpdateSpaceForm();
		form.setSpaceId(entity.getSpaceId().toString());
		form.setSpaceName(entity.getSpaceName());
		form.setScopeId(entity.getScopeId().toString());
		
		return form;
	}

	public void updateSpace(UserDetailsImp user, UpdateSpaceForm form) {
		spaceApi.updateScope(user, 
				Integer.parseInt(form.getSpaceId()),
				Integer.parseInt(form.getScopeId()));
	}

	public void deleteSpace(UserDetailsImp user, DeleteNoneUserInSpaceForm form) {
		spaceApi.deleteSpace(user, Integer.parseInt(form.getId()));
	}

	public void exitSpace(UserDetailsImp user, Integer spaceId) {
		spaceApi.secessionSpace(user, spaceId);
	}

	public void joinPublicSpace(UserDetailsImp user, JoinPublicSpaceForm form) {
		publicSpaceApi.joinSpace(user, form.getSpaceId());
	}
}
