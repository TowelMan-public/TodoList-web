package com.example.demo.controller.advice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.client.api.entity.SimpleTodoListEntity;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.SimpleTodoService;

@ControllerAdvice
public class SeeSimpleTodoControllerAdvice {
	@Autowired
	SimpleTodoService simpleTodoService;
	
	@ModelAttribute("SimpleForm")
	public List<SimpleTodoListEntity> addSimpleForm(@AuthenticationPrincipal UserDetailsImp user) {
		return simpleTodoService.getSimpleTodoList(user);
	}
}
