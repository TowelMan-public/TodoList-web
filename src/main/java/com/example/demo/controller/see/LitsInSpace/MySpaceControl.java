package com.example.demo.controller.see.LitsInSpace;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.ModelSetter;
import com.example.demo.UrlConfig;
import com.example.demo.form.see.ListInSpace.HeaderSpaceForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.ListInSpace.ListInMySpaceService;
import com.example.demo.utility.DateUtility;

@Controller
@RequestMapping(MySpaceControl.URL)
public class MySpaceControl {
	@Autowired
	ListInMySpaceService listInMySpaceService;
	@Autowired
	DateUtility dateUtility;
	
	public static final String URL = UrlConfig.ROOT_URL + "/see/space";
	public static final String MY_SPACE_NAME = "自分の総合";
	
	@GetMapping("month/{assignmentDate}")
	public String showPageEachMonth(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("assignmentDate") String assignmentDate, Model model) throws ParseException {
		
		HeaderSpaceForm headerSpaceForm = HeaderSpaceForm.newInstanceByMonth(
					assignmentDate,
					MY_SPACE_NAME);
		
		return new ModelSetter(model,ModelSetter.PAGE_SEE_LIST_IN_SPACE_MONTH)
				
				.setHeaderSpaceForm(
						headerSpaceForm)
				
				.setShowForm(
						listInMySpaceService.getListInSpaceEachMonth(user,headerSpaceForm))
				
				.buildAndReturnUrl();
	}
	
	@GetMapping("day/{assignmentDate}")
	public String showPageEachDay(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("assignmentDate") String assignmentDate, Model model) throws ParseException {
		
		HeaderSpaceForm headerSpaceForm = HeaderSpaceForm.newInstanceByDay(
				assignmentDate,
				MY_SPACE_NAME);
		
		return new ModelSetter(model,ModelSetter.PAGE_SEE_LIST_IN_SPACE_DAY)
				
				.setHeaderSpaceForm(
						headerSpaceForm)
				
				.setShowForm(
						listInMySpaceService.getListInSpaceEachDay(user,headerSpaceForm))
				
				.buildAndReturnUrl();
	}
	
	@GetMapping("day")
	public String showPageEachDay(@AuthenticationPrincipal UserDetailsImp user,	Model model) throws ParseException {		
		return "redirect:" + UrlConfig.ROOT_URL +
				("/see/space/day/{assignmentDate}"
						.replace("{assignmentDate}", dateUtility.dateTypeToDateFormatString(new Date(), '-')));
	}
	
	@GetMapping("year/{assignmentDate}")
	public String showPageEachYear(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("assignmentDate") String assignmentDate, Model model) throws ParseException {
		
		HeaderSpaceForm headerSpaceForm = HeaderSpaceForm.newInstanceByYear(
				assignmentDate,
				MY_SPACE_NAME);
		
		return new ModelSetter(model,ModelSetter.PAGE_SEE_LIST_IN_SPACE_YEAR)
				
				.setHeaderSpaceForm(
						headerSpaceForm)
				
				.setShowForm(
						listInMySpaceService.getListInSpaceEachYear(user,headerSpaceForm))
				
				.buildAndReturnUrl();
	}
}