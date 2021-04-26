package com.example.demo.controller.advice;

import java.text.ParseException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.UrlConfig;
import com.example.demo.client.exception.*;

//エラーページを表示しないといけないようなエラー
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final String ERROR_PAGE_LINK = "/error";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String LINK_URL = "linkUrl";
	private static final String LINK_MESSAGE = "linkMessage";
	
	//パブリックプロジェクトじゃない
	@ExceptionHandler({SpaceIsnotPublicException.class})
	public ModelAndView handleSpaceIsnotPublicException(SpaceIsnotPublicException e) {
		ModelAndView modelAndView = new ModelAndView();
		
		//セットして返却
		modelAndView.addObject(ERROR_MESSAGE,"指定されたプロジェクトが不適切です。スコープがパブリックじゃありません。");
		modelAndView.setViewName(ERROR_PAGE_LINK);
		return modelAndView;
	}
	
	//プロジェクトの任意の操作の権限が無い
	@ExceptionHandler({HaveNotAuthorityInSpaceException.class})
	public ModelAndView handleHaveNotAuthorityInSpaceException(HaveNotAuthorityInSpaceException e) {
		ModelAndView modelAndView = new ModelAndView();
		
		//セットして返却
		modelAndView.addObject(ERROR_MESSAGE,"あなたにはこのプロジェクトに対する、あなたが行おうとした操作を許される権限がありません。");
		modelAndView.setViewName(ERROR_PAGE_LINK);
		return modelAndView;
	}
	
	//URLに不備も　各種IDが不正
	@ExceptionHandler({NotFoundException.class})
	public ModelAndView handleNotFoundException(NotFoundException e) {
		ModelAndView modelAndView = new ModelAndView();
		
		//セットして返却
		modelAndView.addObject(ERROR_MESSAGE,"URL等に指定されたIDのうち、いずれかが不適切なものです。");
		modelAndView.setViewName(ERROR_PAGE_LINK);
		return modelAndView;
	}
	
	//URLに問題も 不適切なプロジェクト
	@ExceptionHandler({IsSimpleSpaceException.class})
	public ModelAndView handleIsSimpleSpaceException(IsSimpleSpaceException e) {
		ModelAndView modelAndView = new ModelAndView();
		
		//セットして返却
		modelAndView.addObject(ERROR_MESSAGE,"URL等に指定されたプロジェクトは不適切です。");
		modelAndView.setViewName(ERROR_PAGE_LINK);
		return modelAndView;
	}
	
	//URLに問題も  日付
	@ExceptionHandler({ParseException.class})
	public ModelAndView handleParseException(ParseException e) {
		ModelAndView modelAndView = new ModelAndView();
		
		//セットして返却
		modelAndView.addObject(ERROR_MESSAGE,"URL等に指定された日付が不正です。");
		modelAndView.setViewName(ERROR_PAGE_LINK);
		return modelAndView;
	}
	
	//ログイン状態なし　再ログイン求む TODO
	@ExceptionHandler({InvalidLoginException.class})
	public ModelAndView handleInvalidLoginException(InvalidLoginException e) {
		ModelAndView modelAndView = new ModelAndView();
		
		//セットして返却
		modelAndView.addObject(ERROR_MESSAGE,"お手数をおかけしますが、もう一度ログインページにてログインしてください。");
		modelAndView.addObject(LINK_URL, UrlConfig.ROOT_URL + "/logout");
		modelAndView.addObject(LINK_MESSAGE,"ログインページに行きます");
		modelAndView.setViewName(ERROR_PAGE_LINK);
		return modelAndView;
	}
	
	//予期できないAPI呼び出しエラー
	@ExceptionHandler({RestClientResponseException.class})
	public ModelAndView handleRestClientResponseException(RestClientResponseException  e) {
		ModelAndView modelAndView = new ModelAndView();
		
		//セットして返却
		modelAndView.addObject(ERROR_MESSAGE,"サーバ側で、予期せぬことが発生いたしました。");
		modelAndView.setViewName(ERROR_PAGE_LINK);
		return modelAndView;
	}
	
	//API呼び出し時のIOエラー 予期できないもの扱い
	@ExceptionHandler({ResourceAccessException.class})
	public ModelAndView handleParseException(ResourceAccessException e) {
		ModelAndView modelAndView = new ModelAndView();
		
		//セットして返却
		modelAndView.addObject(ERROR_MESSAGE,"Webサーバ側で、予期せぬことが発生いたしました。");
		modelAndView.setViewName(ERROR_PAGE_LINK);
		return modelAndView;
	}
	
	//予期できないもの
	@ExceptionHandler({RuntimeException.class})
	public ModelAndView handleRuntimeException(RuntimeException  e) {
		ModelAndView modelAndView = new ModelAndView();
		
		//セットして返却
		modelAndView.addObject(ERROR_MESSAGE,"Webサーバ側で、予期せぬことが発生いたしました。");
		modelAndView.setViewName(ERROR_PAGE_LINK);
		return modelAndView;
	}
}