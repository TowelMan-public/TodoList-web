package com.example.demo.service.ListInSpace;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.api.ListInSpaceApi;
import com.example.demo.form.see.ListInSpace.HeaderSpaceForm;
import com.example.demo.form.see.ListInSpace.ListInSpaceInDayForm;
import com.example.demo.form.see.ListInSpace.ListInSpaceInMonthForm;
import com.example.demo.form.see.ListInSpace.ListInSpaceInMonthForm.ListCountInDay;
import com.example.demo.form.see.ListInSpace.ListInSpaceInYearForm;
import com.example.demo.logic.ListInSpace.ListInSpaceLogicSharedService;
import com.example.demo.security.UserDetailsImp;

@Service
public class LitsInSpaceService {
	@Autowired
	ListInSpaceLogicSharedService listInSpaceLogicSharedService;
	@Autowired
	ListInSpaceApi listInSpaceApi;
	
	public List<ListInSpaceInMonthForm> getListInSpaceEachMonth(UserDetailsImp user, HeaderSpaceForm headerSpaceForm, Integer spaceId) {
		List<ListInSpaceInMonthForm> listentity = new ArrayList<>();
		
		//各種必要なデータを求める
		int year = headerSpaceForm.getYearNum();
		int month = headerSpaceForm.getMonthNum();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		int lastDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		calendar.add(Calendar.DAY_OF_MONTH, -(lastDay - 1));
		int nowDay = 1 - (calendar.get(Calendar.DAY_OF_WEEK) - 1);
		
		//データ取得・データセット
		for(;nowDay <= lastDay;) {
			List<ListInSpaceInMonthForm.ListCountInDay> listCountInDayList = new ArrayList<>();
			
			//一週間分のデータ取得
			for(int i=0;i < 7;i++,nowDay++) {
				if(nowDay >= 1 && nowDay <= lastDay) {
					listCountInDayList.add(
							listInSpaceLogicSharedService.toListCountInDay(
									nowDay,
									headerSpaceForm,
									listInSpaceApi.getCountListInDay(user, spaceId, year, month, nowDay)));
				}else {
					listCountInDayList.add(ListInSpaceInMonthForm.emptyListCountDay);
				}
			}
			
			listentity.add(new ListInSpaceInMonthForm(listCountInDayList));
		}
		
		return listentity;
	}

	public List<ListInSpaceInDayForm> getListInSpaceEachDay(UserDetailsImp user, HeaderSpaceForm headerSpaceForm, Integer spaceId) {
		return listInSpaceLogicSharedService.getListInSpaceInDayForm(
				user,
				listInSpaceApi.getListInDayInSpace(user,
						spaceId,
						headerSpaceForm.getYearNum(),
						headerSpaceForm.getMonthNum(),
						headerSpaceForm.getDayNum()));
	}

	public List<ListInSpaceInYearForm> getListInSpaceEachYear(UserDetailsImp user, HeaderSpaceForm headerSpaceForm, Integer spaceId) {
		List<ListInSpaceInYearForm> listentity = new ArrayList<>();
		
		for(int i=1;i<=12;i++) {
			ListInSpaceInYearForm entity = listInSpaceLogicSharedService.toListInSpaceInYearForm(
				listInSpaceApi.getCountListInMonth(user,
						spaceId,
						headerSpaceForm.getYearNum(),
						i));
			
			entity.setAssignmentDate(headerSpaceForm.getYearNum().toString() + "-" + i);
			listentity.add(entity);
		}
				
		return listentity;
	}

}