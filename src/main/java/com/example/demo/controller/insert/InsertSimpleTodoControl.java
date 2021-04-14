package com.example.demo.controller.insert;

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
import com.example.demo.form.insert.InsertSimpleForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.SimpleTodoService;

@Controller
@RequestMapping(InsertSimpleTodoControl.URL)
public class InsertSimpleTodoControl {
	public static final String URL = UrlConfig.ROOT_URL + "/insert/simple";
	
	@Autowired
	SimpleTodoService simpleTodoService;
	
	@GetMapping
	public String showPage(@AuthenticationPrincipal UserDetailsImp user, Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_INSERT_SIMPLE)
					
					.setInsertSimpleForm(
							new InsertSimpleForm())
					
					.buildAndReturnUrl();
	}
	
	@PostMapping
	public String insertUserInSpace(@AuthenticationPrincipal UserDetailsImp user,
			@Validated InsertSimpleForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.InsertContentForm", result);
			redirect.addFlashAttribute("InsertContentForm", form);
			return "redirect:" + URL;
		}
		
		//処理
		simpleTodoService.insertSimpleTodo(user,form);
		
		//リダイレクト
		return "redirect:" + URL;
	}
}
