package com.example.demo.controller.serch;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.ModelSetter;
import com.example.demo.UrlConfig;
import com.example.demo.form.insert.JoinPublicSpaceForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.SpaceService;
import com.example.demo.utility.DateUtility;

@Controller
@RequestMapping(UrlConfig.ROOT_URL)
public class SerchPublicSpaceControl {
	@Autowired
	SpaceService spaceService;
	@Autowired
	DateUtility dateUtility;
	
	@GetMapping("serch/space/public")
	public String showPage(@AuthenticationPrincipal UserDetailsImp user, Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_SERCH_SPACE_PUBLIC)
				
				.setShowForm(
						spaceService.getPublicSpace(user))
				
				.setJoinPublicSpaceForm(
						new JoinPublicSpaceForm())
				
				.buildAndReturnUrl();
	}
	
	@PostMapping("join/space/public")
	public String joinPublicSpace(@AuthenticationPrincipal UserDetailsImp user,
			@Validated JoinPublicSpaceForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.JoinPublicSpaceForm", result);
			redirect.addFlashAttribute("JoinPublicSpaceForm", form);
			return "redirect:" + UrlConfig.ROOT_URL + "/serch/space/public";
		}
		
		//処理
		spaceService.joinPublicSpace(user,form);
		
		//リダイレクト
		return "redirect:" + UrlConfig.ROOT_URL +
				("/see/space/{spaceId}/month/{assignmentDate}"
						.replace("{spaceId}", form.getSpaceId().toString())
						.replace("{assignmentDate}", dateUtility.dateTypeToDateFormatString(new Date(), '-')));
	}
}
