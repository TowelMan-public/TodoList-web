package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.ModelSetter;
import com.example.demo.UrlConfig;
import com.example.demo.client.exception.AlreadyUsedException;
import com.example.demo.form.update.UpdatePassword;
import com.example.demo.form.update.UpdateUsername;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserDetailsServiceImp;

@Controller
@RequestMapping(UserConfingControl.URL)
public class UserConfingControl {
	public static final String URL = UrlConfig.ROOT_URL + "/user/confing";
	@Autowired
	UserDetailsServiceImp userDetailsService;
	
	@GetMapping
	public String showPage(@AuthenticationPrincipal UserDetailsImp user, Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_USER_CONFING)
				
					.setUpdateUsername(
							userDetailsService.getUsernameToUpdateUsernameForm(user))
					
					.setUpdatePassword(
							new UpdatePassword())
					
					.buildAndReturnUrl();
	}
	
	@PostMapping("/update/username")
	public String updateUsername(@AuthenticationPrincipal UserDetailsImp user,
			@Validated UpdateUsername form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.UpdateUsername", result);
			redirect.addFlashAttribute("UpdateUsername", form);
			return "redirect:" + URL;
		}
		
		//処理
		try {
			userDetailsService.updateUsername(user,form);
		}
		catch(AlreadyUsedException e) {
			FieldError error = new FieldError(result.getObjectName(), "newUsername", "既に使用されているユーザー名です");
			result.addError(error);
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.UpdateUsername", result);
			redirect.addFlashAttribute("UpdateUsername", form);
			return "redirect:" + URL;
		}
		
		//リダイレクト
		return "redirect:" + URL;
	}
	
	@PostMapping("/update/username")
	public String updatePassword(@AuthenticationPrincipal UserDetailsImp user,
			@Validated UpdatePassword form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.UpdatePassword", result);
			redirect.addFlashAttribute("UpdatePassword", form);
			return "redirect:" + URL;
		}
		
		//処理
		userDetailsService.updatePassword(user,form);
		
		//リダイレクト
		return "redirect:" + URL;
	}
}
