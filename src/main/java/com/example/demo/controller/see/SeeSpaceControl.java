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
import com.example.demo.form.delete.DeleteUserInSpaceForm;
import com.example.demo.form.update.UpdateSpaceForm;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.SpaceService;
import com.example.demo.service.UserInSpaceService;

@Controller
@RequestMapping(UrlConfig.ROOT_URL)
public class SeeSpaceControl {
	@Autowired
	SpaceService spaceService;
	@Autowired
	UserInSpaceService userInSpaceService;
	
	@GetMapping("see/space/{spaceId}")
	public String showPage(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("spaceId") Integer spaceId, Model model) {
		return new ModelSetter(model,ModelSetter.PAGE_SEE_SPACE)
				
					.setSpaceId(spaceId)
					
					.setDeleteNoneUserInSpaceForm(
							new DeleteNoneUserInSpaceForm())
					
					.setDeleteUserInSpaceForm(
							new DeleteUserInSpaceForm())
					
					.setUpdateSpaceForm(
							spaceService.getSpace(user,spaceId))
					
					.setShowForm(
							userInSpaceService.getUsersInSpace(user, spaceId))
					
					.buildAndReturnUrl();
	}
	
	@PostMapping("update/space")
	public String updateSpace(@AuthenticationPrincipal UserDetailsImp user,
			@Validated UpdateSpaceForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.UpdateSpaceForm", result);
			redirect.addFlashAttribute("UpdateSpaceForm", form);
			return "redirect:" + UrlConfig.ROOT_URL + "/see/space/" + form.getSpaceId();
		}
		
		//処理
		spaceService.updateSpace(user,form);
		
		//リダイレクト
		return "redirect:" + UrlConfig.ROOT_URL + "/see/space/" + form.getSpaceId();
	}
	
	@PostMapping("delete/space")
	public String deleteSpace(@AuthenticationPrincipal UserDetailsImp user,
			@Validated DeleteNoneUserInSpaceForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.DeleteNoneUserInSpaceForm", result);
			redirect.addFlashAttribute("DeleteNoneUserInSpaceForm", form);
			return "redirect:" + UrlConfig.ROOT_URL + "/see/space/" + form.getId();
		}
		
		//処理
		spaceService.deleteSpace(user,form);
		
		//リダイレクト
		return "redirect:" + UrlConfig.ROOT_URL + "/see/space/day";
	}
	
	@GetMapping("exit/space/{spaceId}")
	public String exitSpace(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("spaceId") Integer spaceId, Model model) {
		//処理
		spaceService.exitSpace(user,spaceId);
		
		//リダイレクト
		return "redirect:" + UrlConfig.ROOT_URL + "/see/space/day";
	}
	
	@PostMapping("delete/space/user")
	public String deleteUserInSpace(@AuthenticationPrincipal UserDetailsImp user,
			@Validated DeleteUserInSpaceForm form, BindingResult result, RedirectAttributes redirect) {
		//入力ﾁｪｯｸ
		if(result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.DeleteUserInSpaceForm", result);
			redirect.addFlashAttribute("DeleteUserInSpaceForm", form);
			return "redirect:" + UrlConfig.ROOT_URL + "/see/space/" + form.getId();
		}
		
		//処理
		userInSpaceService.deleteUserInSpace(user,form);
		
		//リダイレクト
		return "redirect:" + UrlConfig.ROOT_URL + "/see/space/" + form.getId();
	}
}
