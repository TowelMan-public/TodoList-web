package com.example.demo;

import java.util.List;

import org.springframework.ui.Model;

import com.example.demo.form.delete.*;
import com.example.demo.form.see.*;
import com.example.demo.form.see.ListInSpace.HeaderSpaceForm;
import com.example.demo.form.update.*;
import com.example.demo.form.insert.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

//ビルダー
public class ModelSetter {
	//セットするもの母体
	private final Model model;
	//このModelを渡すべきURL
	private final String url;
	
	//WebSeeContentType群
	public static final WebSeeContentType PAGE_SEE_LIST_IN_SPACE_DAY = new WebSeeContentType ("see_list_in_space_day",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_SEE_LIST_IN_SPACE_MONTH = new WebSeeContentType ("see_list_in_space_month",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_SEE_LIST_IN_SPACE_YEAR = new WebSeeContentType ("see_list_in_space_year",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_SEE_LIST = new WebSeeContentType ("see_list",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_SEE_CONTENT = new WebSeeContentType ("see_content",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_SEE_SPACE = new WebSeeContentType ("see_space",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_SEE_USER_IN_SPACE = new WebSeeContentType ("user_in_space",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_INSERT_CONTENT = new WebSeeContentType ("insert_content",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_INSERT_SPACE = new WebSeeContentType ("insert_space",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_INSERT_LIST = new WebSeeContentType ("insert_list",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_INSERT_USER_IN_SPACE = new WebSeeContentType ("insert_user_in_space",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_INSERT_SIMPLE = new WebSeeContentType ("simple",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_SERCH_SPACE_JOINED = new WebSeeContentType ("serch_space_joined",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_SERCH_SPACE_PUBLIC = new WebSeeContentType ("serch_space_public",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_USER_CONFING = new WebSeeContentType ("user_confing",WebSeeContentType.EXAMPLE_URL);
	public static final WebSeeContentType PAGE_LOGIN = new WebSeeContentType (null,"/login");
	public static final WebSeeContentType PAGE_SIGNUP = new WebSeeContentType (null,"/signup");
	public static final WebSeeContentType PAGE_WITHDRAWAL = new WebSeeContentType (null,"/withdrawal");
	
	public ModelSetter(Model pageModel,WebSeeContentType webSeeContentType) {
		model = pageModel; 
		model.addAttribute("WebSeeContentType", webSeeContentType.getWebSeeContentTypeName());
		
		url=webSeeContentType.getPageUrl();
	}
	
	public String buildAndReturnUrl() {
		return UrlConfig.ROOT_URL + url;
	}
	
	public ModelSetter setShowForm(List<?> showForm){
		model.addAttribute("ShowForm", showForm);
		return this;
	}
	
	public ModelSetter setSignupForm(SignupForm form) {
		model.addAttribute("SignupForm", form);
		return this;
	}
	
	public ModelSetter setSimpleForm(List<SimpleForm> simpleForm){
		model.addAttribute("SimpleForm", simpleForm);
		return this;
	}
	
	public ModelSetter setSpaceId(int spaceId){
		model.addAttribute("spaceId", spaceId);
		return this;
	}
	
	public ModelSetter setHeaderSpaceForm(HeaderSpaceForm headerSpaceForm){
		model.addAttribute("HeaderSpaceForm", headerSpaceForm);
		return this;
	}
	
	public ModelSetter setDeleteNoneUserInSpaceForm(DeleteNoneUserInSpaceForm deleteNoneUserInSpaceForm){
		model.addAttribute("DeleteNoneUserInSpaceForm", deleteNoneUserInSpaceForm);
		return this;
	}
	
	public ModelSetter setUpdateListForm(UpdateListForm updateListForm){
		model.addAttribute("UpdateListForm", updateListForm);
		return this;
	}
	
	public ModelSetter setListId(int listId){
		model.addAttribute("listId", listId);
		return this;
	}
	
	public ModelSetter setContentId(int contentId){
		model.addAttribute("contentId", contentId);
		return this;
	}
	
	public ModelSetter setUpdateContentForm(UpdateContentForm updateContentForm){
		model.addAttribute("UpdateContentForm", updateContentForm);
		return this;
	}
	
	public ModelSetter setDeleteUserInSpaceForm(DeleteUserInSpaceForm deleteUserInSpaceForm){
		model.addAttribute("DeleteUserInSpaceForm", deleteUserInSpaceForm);
		return this;
	}
	
	public ModelSetter setUpdateSpaceForm(UpdateSpaceForm updateSpaceForm){
		model.addAttribute("UpdateSpaceForm", updateSpaceForm);
		return this;
	}
	
	public ModelSetter setUpdateUserInSpaceForm(UpdateUserInSpaceForm updateUserInSpaceForm){
		model.addAttribute("UpdateUserInSpaceForm", updateUserInSpaceForm);
		return this;
	}
	
	public ModelSetter setInsertContentForm(InsertContentForm insertContentForm){
		setOther("InsertContentForm", insertContentForm);
		return this;
	}

	public ModelSetter setInsertSpaceForm(InsertSpaceForm insertSpaceForm){
		setOther("InsertSpaceForm", insertSpaceForm);
		return this;
	}
	
	public ModelSetter setInsertListForm(InsertListForm insertListForm){
		setOther("InsertListForm", insertListForm);
		return this;
	}
	
	public ModelSetter setInsertUserInSpaceForm(InsertUserInSpaceForm insertUserInSpaceForm){
		setOther("InsertUserInSpaceForm", insertUserInSpaceForm);
		return this;
	}
	
	public ModelSetter setInsertSimpleForm(InsertSimpleForm insertSimpleForm){
		setOther("InsertSimpleForm", insertSimpleForm);
		return this;
	}
	
	public ModelSetter setUpdatePassword(UpdatePassword UpdatePassword){
		setOther("UpdatePassword", UpdatePassword);
		return this;
	}
	
	public ModelSetter setUpdateUsername(UpdateUsername updateUsername){
		setOther("UpdateUsername", updateUsername);
		return this;
	}
	
	public ModelSetter setOther(String keyName,Object object){
		if(!model.containsAttribute(keyName))
			model.addAttribute(keyName, object);
		return this;
	}
	
	@Getter
	@AllArgsConstructor
	private static class WebSeeContentType{
		public static final String EXAMPLE_URL = "/example";
		private String pageUrl;
		private String webSeeContentTypeName;
	}
}
