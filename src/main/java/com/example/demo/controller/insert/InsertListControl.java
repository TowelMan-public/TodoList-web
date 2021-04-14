package com.example.demo.controller.insert;

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
import com.example.demo.form.insert.InsertListForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.ListService;

@Controller
@RequestMapping(InsertListControl.URL)
public class InsertListControl {
	public static final String URL = UrlConfig.ROOT_URL + "/insert/list/{spaceId}";
	
	@Autowired
	ListService listService;
	
	@GetMapping
	public String showPage(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("spaceId") Integer spaceId , Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_INSERT_LIST)
					
					.setSpaceId(spaceId)
					
					.setInsertListForm(
							new InsertListForm())
					
					.buildAndReturnUrl();
	}
	
	@PostMapping
	public String insertList(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("spaceId") Integer spaceId,
			@Validated InsertListForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.InsertListForm", result);
			redirect.addFlashAttribute("InsertListForm", form);
			return "redirect:" + URL.replace("spaceId",spaceId.toString());
		}
		
		//処理
		listService.insertlist(user,form,spaceId);
		
		//リダイレクト
		return "redirect:" + URL.replace("spaceId",spaceId.toString());
	}
}
