package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.client.api.SimpleApi;
import com.example.demo.client.api.entity.SimpleTodoListEntity;
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

	public List<SimpleTodoListEntity> getSimpleTodoList(UserDetailsImp user) {
		return simpleApi.getSimpleTodoLists(user);
	}
}
