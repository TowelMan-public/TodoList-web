package com.example.demo.client.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.client.api.entity.SimpleTodoListEntity;
import com.example.demo.client.api.entity.VoidEntity;
import com.example.demo.client.rest.RestTemplateAdapter;
import com.example.demo.security.UserDetailsImp;

import lombok.Data;

@Component
public class SimpleApi {
	private static final String ROOT_URL = ApiUrlRootConfing.ROOT_URL + "/simple";
	
	@Autowired
	RestTemplateAdapter restTemplateAdapter;
	
	public List<SimpleTodoListEntity> getSimpleTodoLists(UserDetailsImp user){
		final String URL = ROOT_URL + "/get";
		
		return restTemplateAdapter.getForObjectsWhenLogined(URL, new VoidEntity(), SimpleTodoListEntity.class, user);
	}
	
	public void insertSimpleTodoList(UserDetailsImp user,InsertDtoBuilder builder) {
		final String URL = ROOT_URL + "/insert";
		
		Dto dto = builder.build();
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, VoidEntity.class, user);
	}
	
	public class InsertDtoBuilder{
		private Dto dto;
		
		public InsertDtoBuilder setListName(String listName) {
			dto.setListName(listName);
			return this;
		}
		
		public InsertDtoBuilder setListdate(Date listDate) {
			dto.setListDate(listDate);
			return this;
		}
		
		public InsertDtoBuilder setListTime(Date listTime) {
			dto.setListTime(listTime);
			return this;
		}
		
		public Dto build() {
			return dto;
		}
	}
	
	@Data
	public class Dto {
		private String listName;
		private Date listDate;
		private Date listTime;
	}
}
