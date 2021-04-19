package com.example.demo.logic.ListInSpace;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.client.api.ListApi;
import com.example.demo.client.api.entity.ContentEntity;
import com.example.demo.client.api.entity.CountlistInSpaceEntity;
import com.example.demo.client.api.entity.TodoListEachUserEntity;
import com.example.demo.form.see.ListInSpace.HeaderSpaceForm;
import com.example.demo.form.see.ListInSpace.ListInSpaceInDayForm;
import com.example.demo.form.see.ListInSpace.ListInSpaceInMonthForm.ListCountInDay;
import com.example.demo.form.see.ListInSpace.ListInSpaceInYearForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.utility.DateUtility;

@Component
public class ListInSpaceLogicSharedService {
	@Autowired
	ListApi listApi;
	@Autowired
	DateUtility dateUtility;
	
	public List<ListInSpaceInDayForm> getListInSpaceInDayForm(UserDetailsImp user, List<TodoListEachUserEntity> listInDayInSpace) {
		List<ListInSpaceInDayForm> listEntity = new ArrayList<>();
		
		//データの取得・セット
		for(TodoListEachUserEntity entity : listInDayInSpace) {
			ListInSpaceInDayForm form = new ListInSpaceInDayForm();
			form.setAffterDateList(
					entity.getListDate()
						.after(new Date()));
			form.setListId(entity.getListId());
			form.setListName(entity.getListName());
			form.setListTime(
					dateUtility.dateTypeToTimeFormatString(
							entity.getListDate()));
			
			//Contentの取得・セット
			List<ContentEntity> contentList = listApi.getContentsInList(user, entity.getListId());
			form.setContentCount(contentList.size());
			
			List<ListInSpaceInDayForm.ContentForm> contentFormList = new ArrayList<>();
			for(ContentEntity content : contentList) {
				ListInSpaceInDayForm.ContentForm contentForm = new ListInSpaceInDayForm.ContentForm();
				contentForm.setContentId(content.getContentId());
				contentForm.setContentTitle(content.getTitle());
				
				contentFormList.add(contentForm);
			}
			
			listEntity.add(form);
		}
		return listEntity;
	}

	public ListInSpaceInYearForm toListInSpaceInYearForm(CountlistInSpaceEntity countListInMonth) {
		ListInSpaceInYearForm form = new ListInSpaceInYearForm();
		form.setListCount(countListInMonth.getCount());
		return form;
	}

	public ListCountInDay toListCountInDay(Integer day, HeaderSpaceForm headerSpaceForm, CountlistInSpaceEntity countListInDay) {
		ListCountInDay listCountInDay = new ListCountInDay();
		listCountInDay.setGetListCount(countListInDay.getCount());
		listCountInDay.setDay(day.toString());
		listCountInDay.setAssignmentDate(
				dateUtility.dateTypeToDateFormatString(headerSpaceForm.getAssignmentDate()));
		return listCountInDay;
	}
	
}
