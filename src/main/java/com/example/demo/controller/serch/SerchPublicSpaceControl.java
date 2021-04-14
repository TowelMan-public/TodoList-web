package com.example.demo.controller.serch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.ModelSetter;
import com.example.demo.UrlConfig;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.SpaceService;

@Controller
@RequestMapping(SerchPublicSpaceControl.URL)
public class SerchPublicSpaceControl {
	public static final String URL = UrlConfig.ROOT_URL + "/serch/space/public";
	
	@Autowired
	SpaceService spaceService;
	
	@GetMapping
	public String showPage(@AuthenticationPrincipal UserDetailsImp user, Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_SERCH_SPACE_PUBLIC)
				
					.setShowForm(
							spaceService.getPublicSpace(user))
					
					.buildAndReturnUrl();
	}
}
