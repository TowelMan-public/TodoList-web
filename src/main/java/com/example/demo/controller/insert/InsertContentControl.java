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
import com.example.demo.form.insert.InsertContentForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.ContentService;

@Controller
@RequestMapping(InsertContentControl.URL)
public class InsertContentControl {
	public static final String URL = UrlConfig.ROOT_URL + "/insert/content/{listId}";
	
	@Autowired
	ContentService contentService;
	
	@GetMapping
	public String showPage(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("listId") Integer listId , Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_INSERT_CONTENT)
					.setListId(listId)
					
					.setInsertContentForm(
							new InsertContentForm())
					
					.buildAndReturnUrl();
	}
	
	@PostMapping
	public String insertList(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("listId") Integer listId,
			@Validated InsertContentForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.InsertContentForm", result);
			redirect.addFlashAttribute("InsertContentForm", form);
			return "redirect:" + URL.replace("listid",listId.toString());
		}
		
		//処理
		contentService.insertContent(user,form,listId);
		
		//リダイレクト
		return "redirect:" + URL.replace("listid",listId.toString());
	}
}
