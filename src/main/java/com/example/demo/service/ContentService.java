package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.ContentApi;
import com.example.demo.form.insert.InsertContentForm;
import com.example.demo.security.UserDetailsImp;

@Service
public class ContentService {
	@Autowired
	ContentApi contentApi;

	public void insertContent(UserDetailsImp user, InsertContentForm form, Integer listId) {
		contentApi.insertContent(user, contentApi. new InsertDtoBuilder()
												 .setContentText(form.getContentText())
												 .setTitle(form.getContentTitle())
												 .setListId(listId));
	}
}
