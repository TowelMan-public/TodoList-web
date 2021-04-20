package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.form.insert.SignupForm;
import com.example.demo.service.UserDetailsServiceImp;

@Controller
@RequestMapping(UrlConfig.ROOT_URL)
public class UserControl {	
	@Autowired
	UserDetailsServiceImp userDetailsService;
	
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_LOGIN)
				
				.buildAndReturnUrl();
	}
	
	@GetMapping("/signup")
	public String showSignupPage(Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_SIGNUP)
				
				.setSignupForm(
						new SignupForm())
				
				.buildAndReturnUrl();
	}
	
	@PostMapping("/signup")
	public String InsertUser(@Validated SignupForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.SignupForm", result);
			redirect.addFlashAttribute("SignupForm", form);
			return "redirect:" + UrlConfig.ROOT_URL + "/signup";
		}
		
		//処理
		userDetailsService.insertUser(form);
		
		//リダイレクト
		return "redirect:" + UrlConfig.ROOT_URL + "/login";
	}
}