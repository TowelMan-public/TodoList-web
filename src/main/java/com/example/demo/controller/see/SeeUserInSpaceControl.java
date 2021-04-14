package com.example.demo.controller.see;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.ModelSetter;
import com.example.demo.UrlConfig;
import com.example.demo.form.update.UpdateUserInSpaceForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserInSpaceService;

@Controller
@RequestMapping(SeeUserInSpaceControl.URL)
public class SeeUserInSpaceControl {
	public static final String URL = UrlConfig.ROOT_URL + "/see/space/user";
	
	@Autowired
	UserInSpaceService userInSpaceService;
	
	@GetMapping("/{spaceId}/{username}")
	public String showPage(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("spaceId") Integer spaceId ,@PathVariable("username") String username, Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_SEE_USER_IN_SPACE)
					
					.setUpdateUserInSpaceForm(
							userInSpaceService.getUserInSpace(user,spaceId,username))
					
					.buildAndReturnUrl();
	}
	
	@PostMapping
	public String updateUserInSpace(@AuthenticationPrincipal UserDetailsImp user,
			@Validated UpdateUserInSpaceForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.InsertListForm", result);
			redirect.addFlashAttribute("InsertListForm", form);
			return "redirect:" + UrlConfig.ROOT_URL + "see/space/day";
		}
		
		//処理
		userInSpaceService.updateUserInSpace(user,form);
		
		//リダイレクト
		return "redirect:" + URL + form.getSpaceId() + "/" + form.getUsername();
	}
}
