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
import com.example.demo.service.MySpaceService;

@Controller
@RequestMapping(SerchJoinedSpaceControl.URL)
public class SerchJoinedSpaceControl {
	public static final String URL = UrlConfig.ROOT_URL + "/serch/space/joined";
	
	@Autowired
	MySpaceService mySpaceService;
	
	@GetMapping
	public String showPage(@AuthenticationPrincipal UserDetailsImp user, Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_SERCH_SPACE_JOINED)
					
					.setShowForm(
							mySpaceService.getJoinedSpace(user))
					
					.buildAndReturnUrl();
	}
}
