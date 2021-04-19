package com.example.demo.client.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.client.api.entity.CountlistInSpaceEntity;
import com.example.demo.client.api.entity.SpaceEntity;
import com.example.demo.client.api.entity.TodoListEachUserEntity;
import com.example.demo.client.api.entity.VoidEntity;
import com.example.demo.client.rest.RestTemplateAdapter;
import com.example.demo.security.UserDetailsImp;

import lombok.Data;

@Component
public class MySpaceApi {
	private static final String ROOT_URL = "/space/me";
	
	@Autowired
	RestTemplateAdapter restTemplateAdapter;
	
	public CountlistInSpaceEntity getCountListInDay(UserDetailsImp user, int year, int month, int day) {
		final String URL = ROOT_URL + "/count/day/get";
		
		Dto dto = new Dto();
		dto.setYear(year);
		dto.setMonth(month);
		dto.setDay(day);
		
		return restTemplateAdapter.getForObjectWhenLogined(URL, dto, CountlistInSpaceEntity.class, user);
	}
	
	public CountlistInSpaceEntity getCountListInMonth(UserDetailsImp user, int year, int month) {
		final String URL = ROOT_URL + "/count/month/get";
		
		Dto dto = new Dto();
		dto.setYear(year);
		dto.setMonth(month);
		
		return restTemplateAdapter.getForObjectWhenLogined(URL, dto, CountlistInSpaceEntity.class, user);
	}
	
	public CountlistInSpaceEntity getCountListInWeek(UserDetailsImp user, int year, int month, int weekCount) {
		final String URL = ROOT_URL + "/count/week/get";
		
		Dto dto = new Dto();
		dto.setYear(year);
		dto.setMonth(month);
		dto.setWeekCount(weekCount);
		
		return restTemplateAdapter.getForObjectWhenLogined(URL, dto, CountlistInSpaceEntity.class, user);
	}
	
	public List<TodoListEachUserEntity> getListInDayInSpace(UserDetailsImp user, int year, int month, int day) {
		final String URL = ROOT_URL + "/day/get";
		
		Dto dto = new Dto();
		dto.setYear(year);
		dto.setMonth(month);
		dto.setDay(day);
		
		return restTemplateAdapter.getForObjectsWhenLogined(URL, dto, TodoListEachUserEntity.class, user);
	}
	
	public List<SpaceEntity> getMySpace(UserDetailsImp user){
		final String URL = ROOT_URL + "/day/get";
		
		return restTemplateAdapter.getForObjectsWhenLogined(URL, new VoidEntity(), SpaceEntity.class, user);
	}
	
	@Data
	public class Dto{
		private Integer year;
		private Integer month;
		private Integer weekCount;
		private Integer day;
	}
}
