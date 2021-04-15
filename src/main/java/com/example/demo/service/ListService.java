package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.ListApi;
import com.example.demo.client.api.entity.ContentEntity;
import com.example.demo.client.api.entity.ListEntity;
import com.example.demo.form.delete.DeleteNoneUserInSpaceForm;
import com.example.demo.form.insert.InsertListForm;
import com.example.demo.form.update.UpdateListForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.utility.DateUtility;

@Service
public class ListService {
	@Autowired
	ListApi listApi;
	@Autowired
	DateUtility dateUtility;

	public void insertList(UserDetailsImp user, InsertListForm form, Integer spaceId) {
		listApi.insertList(user, listApi. new InsertDtoBuilder()
										.setSpaceId(spaceId)
										.setListTime(form.getListTimeToDate())
										.setListDate(form.getListDateToDate())
										.setlistName(form.getListName()));
	}

	public UpdateListForm getList(UserDetailsImp user, Integer listId) {
		ListEntity entity = listApi.getList(user, listId);
		
		//データセット
		UpdateListForm form = new UpdateListForm();
		form.setListId(entity.getListId().toString());
		form.setListName(entity.getListName());
		form.setListDate(
				dateUtility.dateTypeToDateFormatString(entity.getListDate()));
		form.setListTime(
				dateUtility.dateTypeToTimeFormatString(entity.getListDate()));
		
		return form;
	}

	public List<ContentEntity> getContentInList(UserDetailsImp user, Integer listId) {
		return listApi.getContentsInList(user, listId);
	}

	public void updateList(UserDetailsImp user, UpdateListForm form) {
		listApi.updateList(user, listApi. new UpdateDtoBuilder()
										.setListDate(form.getListDateToDate())
										.setlistName(form.getListName())
										.setSpaceId(Integer.parseInt(form.getListId()))
										.setListTime(form.getListTimeToDate()));
	}

	public void deleteList(UserDetailsImp user, DeleteNoneUserInSpaceForm form) {
		listApi.deleteList(user, Integer.parseInt(form.getId()));
	}
}
