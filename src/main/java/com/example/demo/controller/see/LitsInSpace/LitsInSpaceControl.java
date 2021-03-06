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
import com.example.demo.service.SpaceService;
import com.example.demo.service.ListInSpace.LitsInSpaceService;
import com.example.demo.utility.DateUtility;

@Controller
@RequestMapping(LitsInSpaceControl.URL)
public class LitsInSpaceControl {
	@Autowired
	LitsInSpaceService litsInSpaceService;
	@Autowired
	SpaceService spaceService;
	@Autowired
	DateUtility dateUtility;
	
	public static final String URL = UrlConfig.ROOT_URL + "/see/space/{spaceId}";
	
	@GetMapping("month/{assignmentDate}")
	public String showPageEachMonth(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("spaceId") Integer spaceId ,
			@PathVariable("assignmentDate") String assignmentDate, Model model) throws ParseException {
		
		//MySpace管轄
		if(spaceId == -1) {
			return "redirect:" + UrlConfig.ROOT_URL +
					("/see/space/month/{assignmentDate}"
							.replace("{assignmentDate}", assignmentDate));
		}
		
		HeaderSpaceForm headerSpaceForm = HeaderSpaceForm.newInstanceByMonth(
				assignmentDate,
				spaceService
					.getSpace(user, spaceId)
					.getSpaceName());
		
		return new ModelSetter(model,ModelSetter.PAGE_SEE_LIST_IN_SPACE_MONTH)
				
				.setSpaceId(spaceId)
				
				.setHeaderSpaceForm(
						headerSpaceForm)
				
				.setShowForm(
						litsInSpaceService.getListInSpaceEachMonth(user,headerSpaceForm,spaceId))
				
				.buildAndReturnUrl();
	}
	
	@GetMapping("day/{assignmentDate}")
	public String showPageEachDay(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("spaceId") Integer spaceId ,
			@PathVariable("assignmentDate") String assignmentDate, Model model) throws ParseException {
		
		//MySpace管轄
		if(spaceId == -1) {
			return "redirect:" + UrlConfig.ROOT_URL +
					("/see/space/day/{assignmentDate}"
							.replace("{assignmentDate}", assignmentDate));
		}
		
		HeaderSpaceForm headerSpaceForm = HeaderSpaceForm.newInstanceByDay(
				assignmentDate,
				spaceService
					.getSpace(user, spaceId)
					.getSpaceName());
		
		return new ModelSetter(model,ModelSetter.PAGE_SEE_LIST_IN_SPACE_DAY)
				
				.setSpaceId(spaceId)
				
				.setHeaderSpaceForm(
						headerSpaceForm)
				
				.setShowForm(
						litsInSpaceService.getListInSpaceEachDay(user,headerSpaceForm,spaceId))
				
				.buildAndReturnUrl();
	}
	
	@GetMapping("year/{assignmentDate}")
	public String showPageEachYear(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("spaceId") Integer spaceId ,
			@PathVariable("assignmentDate") String assignmentDate, Model model) throws ParseException {
		
		//MySpace管轄
		if(spaceId == -1) {
			return "redirect:" + UrlConfig.ROOT_URL +
					("/see/space/year/{assignmentDate}"
							.replace("{assignmentDate}", assignmentDate));
		}
		
		HeaderSpaceForm headerSpaceForm = HeaderSpaceForm.newInstanceByYear(
				assignmentDate,
				spaceService
					.getSpace(user, spaceId)
					.getSpaceName());
		
		return new ModelSetter(model,ModelSetter.PAGE_SEE_LIST_IN_SPACE_YEAR)
				
				.setSpaceId(spaceId)
				
				.setHeaderSpaceForm(
						headerSpaceForm)
				
				.setShowForm(
						litsInSpaceService.getListInSpaceEachYear(user,headerSpaceForm,spaceId))
				
				.buildAndReturnUrl();
	}
	
	@GetMapping("day")
	public String showPageEachDay(@AuthenticationPrincipal UserDetailsImp user,	@PathVariable("spaceId") Integer spaceId)
			throws ParseException {
		return "redirect:" + UrlConfig.ROOT_URL +
				(("/see/space/" + spaceId.toString() + "/day/{assignmentDate}"
						.replace("{assignmentDate}", 
								dateUtility.dateTypeToDateFormatString(new Date(), '-'))));
	}
}