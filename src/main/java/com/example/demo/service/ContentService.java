package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.ContentApi;
import com.example.demo.client.api.entity.ContentEntity;
import com.example.demo.form.delete.DeleteNoneUserInSpaceForm;
import com.example.demo.form.insert.InsertContentForm;
import com.example.demo.form.update.UpdateContentForm;
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

	public UpdateContentForm getContent(UserDetailsImp user, Integer contentId) {
		ContentEntity entity = contentApi.getContent(user, contentId);
		
		//データセット
		UpdateContentForm form = new UpdateContentForm();
		form.setContentId(
				entity.getContentId().toString());
		form.setContentText(entity.getContentText());
		form.setContentTitle(entity.getTitle());
		return form;
	}

	public void updateContent(UserDetailsImp user, UpdateContentForm form) {
		contentApi.updateContent(user, contentApi. new UpdateDtoBuilder()
												 .setContentId(
														 Integer.parseInt(form.getContentId()))
												 .setContentText(form.getContentText())
												 .setTitle(form.getContentTitle()));
	}

	public void deleteContent(UserDetailsImp user, DeleteNoneUserInSpaceForm form) {
		contentApi.deleteContent(user, form.getId());
	}
}
