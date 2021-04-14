package com.example.demo.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.client.api.entity.ContentEntity;
import com.example.demo.client.api.entity.VoidEntity;
import com.example.demo.client.rest.RestTemplateAdapter;
import com.example.demo.security.UserDetailsImp;

import lombok.Data;

@Component
public class ContentApi {
	private static final String ROOT_URL = "/content";
	
	@Autowired
	RestTemplateAdapter restTemplateAdapter;
	
	public void deleteContent(UserDetailsImp user, int contentId) {
		//URL
		final String URL = ROOT_URL + "/delete";
		
		//Dto作成
		Dto dto = new Dto();
		dto.setContentId(contentId);
		
		//処理
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, VoidEntity.class, user);
	}
	
	public ContentEntity getContent(UserDetailsImp user, int contentId) {
		//URL
		final String URL = ROOT_URL + "/get";
		
		//Dto作成
		Dto dto = new Dto();
		dto.setContentId(contentId);
		
		//処理
		return restTemplateAdapter.getForObjectWhenLogined(URL, dto, ContentEntity.class, user);
	}
	
	public void insertContent(UserDetailsImp user,InsertDtoBuilder builder) {
		//URL
		final String URL = ROOT_URL + "/insert";
		
		//Dto作成
		Dto dto = builder.build();
		
		//処理
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, VoidEntity.class, user);
	}
	
	public void updateContent(UserDetailsImp user,UpdateDtoBuilder builder) {
		//URL
		final String URL = ROOT_URL + "/update";
		
		//Dto作成
		Dto dto = builder.build();
		
		//処理
		restTemplateAdapter.postForObjectWhenLogined(URL, dto, VoidEntity.class, user);
	}

	public class InsertDtoBuilder{
		private Dto dto;
		
		public InsertDtoBuilder() {
			dto = new Dto();
		}
		
		public InsertDtoBuilder setListId(int listId) {
			dto.setListId(listId);
			return this;
		}
		
		public InsertDtoBuilder setTitle(String title) {
			dto.setTitle(title);
			return this;
		}
		
		public InsertDtoBuilder setContentText(String contentText) {
			dto.setContentText(contentText);
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
		
		public UpdateDtoBuilder setContentId(int contentId) {
			dto.setContentId(contentId);
			return this;
		}
		
		public UpdateDtoBuilder setTitle(String title) {
			dto.setTitle(title);
			return this;
		}
		
		public UpdateDtoBuilder setContentText(String contentText) {
			dto.setContentText(contentText);
			return this;
		}
		
		public UpdateDtoBuilder setContentText(Integer listId) {
			dto.setListId(listId);
			return this;
		}
		
		public Dto build() {
			return dto;
		}
	}
	
	@Data
	public class Dto{
		private Integer contentId;
		private String title;
		private String contentText;
		private Integer listId;
	}
}