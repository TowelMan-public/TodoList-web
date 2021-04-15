package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.UserInSpaceApi;
import com.example.demo.client.api.entity.UserInSpaceEntity;
import com.example.demo.form.delete.DeleteUserInSpaceForm;
import com.example.demo.form.insert.InsertUserInSpaceForm;
import com.example.demo.form.update.UpdateUserInSpaceForm;
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

	public UpdateUserInSpaceForm getUserInSpace(UserDetailsImp user, Integer spaceId, String username) {
		UpdateUserInSpaceForm form = new UpdateUserInSpaceForm();
		List<UserInSpaceEntity> list = userInSpaceApi.getUserInSpace(user, spaceId);
		list.forEach(entity -> {
			if(entity.getUsername().equals(username)) {
				form.setUsername(entity.getUsername());
				form.setSpaceId(entity.getSpaceId().toString());
				form.setAuthortyId(entity.getAuthorityId().toString());
			}
		});
		return form;//TODO
	}

	public void updateUserInSpace(UserDetailsImp user, UpdateUserInSpaceForm form) {
		userInSpaceApi.updateUserAuthortyInSpace(user, userInSpaceApi. new DtoBuilder()
															 .setSpaceId(
																	 Integer.parseInt(form.getSpaceId()))
															 .setUsername(form.getUsername())
															 .setAuthortyId(
																	 Integer.parseInt(form.getAuthortyId())));
	}

	public List<UserInSpaceEntity> getUsersInSpace(UserDetailsImp user, Integer spaceId) {
		return userInSpaceApi.getUserInSpace(user, spaceId);
	}

	public void deleteUserInSpace(UserDetailsImp user, DeleteUserInSpaceForm form) {
		userInSpaceApi.deleteUserInSpace(user,
				Integer.parseInt(form.getId()),
				form.getUsername());
	}
}
