package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.ListApi;
import com.example.demo.form.insert.InsertListForm;
import com.example.demo.security.UserDetailsImp;

@Service
public class ListService {
	@Autowired
	ListApi listApi;

	public void insertList(UserDetailsImp user, InsertListForm form, Integer spaceId) {
		listApi.insertList(user, listApi. new InsertDtoBuilder()
										.setSpaceId(spaceId)
										.setListTime(form.getListTimeToDate())
										.setListDate(form.getListDateToDate())
										.setlistName(form.getListName()));
	}
}
