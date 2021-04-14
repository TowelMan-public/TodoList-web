package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.client.api.SimpleApi;
import com.example.demo.form.insert.InsertSimpleForm;
import com.example.demo.security.UserDetailsImp;

public class SimpleTodoService {
	@Autowired
	SimpleApi simpleApi;

	public void insertSimpleTodo(UserDetailsImp user, InsertSimpleForm form) {
		simpleApi.insertSimpleTodoList(user, simpleApi. new InsertDtoBuilder()
													  .setListName(form.getListName())
													  .setListdate(form.getListDateToDate())
													  .setListTime(form.getListTimeToDate()));
	}
}
