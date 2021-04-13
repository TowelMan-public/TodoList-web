package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.ModelSetter;
import com.example.demo.UrlConfig;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserDetailsServiceImp;

@Controller
@RequestMapping(WithdrawalControl.URL)
public class WithdrawalControl {
	public static final String URL = UrlConfig.ROOT_URL + "/user/withdrawal";
	
	@Autowired
	UserDetailsServiceImp userDetailsService;
	
	@GetMapping
	public String showPage(@AuthenticationPrincipal UserDetailsImp user, Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_WITHDRAWAL)
					.buildAndReturnUrl();
	}
	
	@PostMapping
	public String withdrawal(@AuthenticationPrincipal UserDetailsImp user){
		//処理
		userDetailsService.withdrawal(user);
		
		//ログアウトさせる
		return "redirect:" + UrlConfig.ROOT_URL + "/logout";
	}
}
