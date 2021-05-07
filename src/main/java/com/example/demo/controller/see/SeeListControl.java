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
import com.example.demo.form.update.UpdateListForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.ListService;

@Controller
@RequestMapping(UrlConfig.ROOT_URL)
public class SeeListControl {	
	@Autowired
	ListService listService;
	
	@GetMapping("see/list/{listId}")
	public String showPage(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("listId") Integer listId, Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_SEE_LIST)
				
					.setListId(listId)
					
					.setUpdateListForm(
							listService.getList(user,listId))
					
					.setDeleteNoneUserInSpaceForm(
							new DeleteNoneUserInSpaceForm())
					
					.setShowForm(
							listService.getContentInList(user,listId))
					
					.buildAndReturnUrl();
	}
	
	@PostMapping("update/list")
	public String updateList(@AuthenticationPrincipal UserDetailsImp user,
			@Validated UpdateListForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.UpdateListForm", result);
			redirect.addFlashAttribute("UpdateListForm", form);
			return "redirect:" + UrlConfig.ROOT_URL + "/see/list/" + form.getListId();
		}
		
		//処理
		listService.updateList(user,form);
		
		//リダイレクト
		return "redirect:" + UrlConfig.ROOT_URL + "/see/list/" + form.getListId();
	}
	
	@PostMapping("delete/list")
	public String deleteList(@AuthenticationPrincipal UserDetailsImp user,
			@Validated DeleteNoneUserInSpaceForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.DeleteNoneUserInSpaceForm", result);
			redirect.addFlashAttribute("DeleteNoneUserInSpaceForm", form);
			return "redirect:" + UrlConfig.ROOT_URL + "/see/list/" + form.getId();
		}
		
		//処理
		listService.deleteList(user,form);
		
		//リダイレクト
		return "redirect:" + UrlConfig.ROOT_URL + "/see/space/day";
	}
	
	@GetMapping("delete/list")
	public String test(@AuthenticationPrincipal UserDetailsImp user){
		return "helow";
	}
}
