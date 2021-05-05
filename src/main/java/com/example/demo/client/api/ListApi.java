package com.example.demo.client.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.client.api.entity.ContentEntity;
import com.example.demo.client.api.entity.ListEntity;
import com.example.demo.client.rest.RestTemplateAdapter;
import com.example.demo.security.UserDetailsImp;

import lombok.Data;

@Component
public class ListApi {
	private static final String ROOT_URL = ApiUrlRootConfing.ROOT_URL + "/list";
	
	@Autowired
	RestTemplateAdapter restTemplateAdapter;
	
	public void deleteList(UserDetailsImp user, int listId) {
		final String URL = ROOT_URL + "/delete";
		
		Dto dto = new Dto();
		dto.setListId(listId);
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, Void.class, user);
	}
	
	public ListEntity getList(UserDetailsImp user, int listId) {
		final String URL = ROOT_URL + "/get";
		
		Dto dto = new Dto();
		dto.setListId(listId);
		
		return restTemplateAdapter.getForObjectWhenLogined(URL, dto, ListEntity.class, user);
	}
	
	public List<ContentEntity> getContentsInList(UserDetailsImp user, int listId) {
		final String URL = ROOT_URL + "/get/content";
		
		Dto dto = new Dto();
		dto.setListId(listId);
		
		return restTemplateAdapter.getForObjectsWhenLogined(URL, dto, ContentEntity.class, user);
	}
	
	public void insertList(UserDetailsImp user, InsertDtoBuilder builder) {
		final String URL = ROOT_URL + "/insert";
		
		Dto dto = builder.build();
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, Void.class, user);
	}
	
	public void updateList(UserDetailsImp user, UpdateDtoBuilder builder) {
		final String URL = ROOT_URL + "/update";
		
		Dto dto = builder.build();
		
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, Void.class, user);
	}
	
	public class InsertDtoBuilder{
		private Dto dto;
		
		public InsertDtoBuilder() {
			dto = new Dto();
		}
		
		public InsertDtoBuilder setSpaceId(int spaceId) {
			dto.setSpaceId(spaceId);
			return this;
		}
		
		public InsertDtoBuilder setlistName(String listName) {
			dto.setListName(listName);
			return this;
		}
		
		public InsertDtoBuilder setListDate(Date listDate) {
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
	
	public class UpdateDtoBuilder{
		private Dto dto;
		
		public UpdateDtoBuilder() {
			dto = new Dto();
		}
		
		public UpdateDtoBuilder setSpaceId(int listId) {
			dto.setListId(listId);
			return this;
		}
		
		public UpdateDtoBuilder setlistName(String listName) {
			dto.setListName(listName);
			return this;
		}
		
		public UpdateDtoBuilder setListDate(Date listDate) {
			dto.setListDate(listDate);
			return this;
		}
		
		public UpdateDtoBuilder setListTime(Date listTime) {
			dto.setListTime(listTime);
			return this;
		}
		
		public Dto build() {
			return dto;
		}
	}
	
	@Data
	public class Dto{
		private Integer listId;
		private String listName;
		private Date listDate;
		private Date listTime;
		private Integer spaceId;
	}
}
