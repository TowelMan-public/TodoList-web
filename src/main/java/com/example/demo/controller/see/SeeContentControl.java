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
import com.example.demo.form.delete.DeleteNoneUserInSpaceForm;
import com.example.demo.form.update.UpdateContentForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.ContentService;

@Controller
@RequestMapping(UrlConfig.ROOT_URL)
public class SeeContentControl {
	@Autowired
	ContentService contentService;
	
	@GetMapping("/see/content/{contentId}")
	public String showPage(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("contentId") Integer contentId, Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_SEE_CONTENT)
					
					.setContentId(contentId)
					
					.setUpdateContentForm(
							contentService.getContent(contentId))
					
					.setDeleteNoneUserInSpaceForm(
							new DeleteNoneUserInSpaceForm())
					
					.buildAndReturnUrl();
	}
	
	@PostMapping("/update/content")
	public String updateList(@AuthenticationPrincipal UserDetailsImp user,
			@Validated UpdateContentForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.UpdateContentForm", result);
			redirect.addFlashAttribute("UpdateContentForm", form);
			return "redirect:" + UrlConfig.ROOT_URL + "/see/content/" + form.getContentId();
		}
		
		//処理
		contentService.updateContent(user,form);
		
		//リダイレクト
		return "redirect:" + UrlConfig.ROOT_URL + "/see/content/" + form.getContentId();
	}
	
	@PostMapping("/delete/content")
	public String updateList(@AuthenticationPrincipal UserDetailsImp user,
			@Validated DeleteNoneUserInSpaceForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.DeleteNoneUserInSpaceForm", result);
			redirect.addFlashAttribute("DeleteNoneUserInSpaceForm", form);
			return "redirect:" + UrlConfig.ROOT_URL + "/see/content/" + form.getId();
		}
		
		//処理
		contentService.deleteContent(user,form);
		
		//リダイレクト
		return "redirect:" + UrlConfig.ROOT_URL + "/see/space/day";
	}
}
