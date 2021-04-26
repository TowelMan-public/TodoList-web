package com.example.demo.controller.insert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.ModelSetter;
import com.example.demo.UrlConfig;
import com.example.demo.client.exception.UserAleadyJoinSpaceException;
import com.example.demo.form.insert.InsertUserInSpaceForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserInSpaceService;

@Controller
@RequestMapping(InsertUserInSpaceControl.URL)
public class InsertUserInSpaceControl {
	public static final String URL = UrlConfig.ROOT_URL + "/insert/space/user/{spaceId}";
	
	@Autowired
	UserInSpaceService userInSpaceService;
	
	@GetMapping
	public String showPage(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("spaceId") Integer spaceId , Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_INSERT_USER_IN_SPACE)
				
					.setSpaceId(spaceId)
					
					.setInsertUserInSpaceForm(
							new InsertUserInSpaceForm())
					
					.buildAndReturnUrl();
	}
	
	@PostMapping
	public String insertUserInSpace(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("spaceId") Integer spaceId,
			@Validated InsertUserInSpaceForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.InsertUserInSpaceForm", result);
			redirect.addFlashAttribute("InsertUserInSpaceForm", form);
			return "redirect:" + URL.replace("spaceId",spaceId.toString());
		}
		
		//処理
		try {
			userInSpaceService.insertUserInSpace(user,spaceId,form);
		}
		catch(UserAleadyJoinSpaceException e) {
			FieldError error = new FieldError(result.getObjectName(), "username", "既にプロジェクトに参画済みです");
			result.addError(error);
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.InsertUserInSpaceForm", result);
			redirect.addFlashAttribute("InsertUserInSpaceForm", form);
			return "redirect:" + URL.replace("spaceId",spaceId.toString());
		}
		
		//リダイレクト
		return "redirect:" + URL.replace("spaceId",spaceId.toString());
	}
}
