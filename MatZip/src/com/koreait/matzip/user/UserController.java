package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;


	// /user/login 
public class UserController {

	public String login(HttpServletRequest request) {
		
		request.setAttribute(Const.TITLE, "로그인");
		request.setAttribute(Const.VIEW, "user/login");	//템플릿에서 어떤파일을 열까?	
		return ViewRef.TEMP_DEFAULT;//어떤 템플릿을 열까?
	}
	
	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "회원가입");
		request.setAttribute(Const.VIEW, "user/join");	//템플릿에서 어떤파일을 열까?	
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String joinProc(HttpServletRequest request) {
		String user_id= request.getParameter("user_id");
		String user_pw= request.getParameter("user_pw");
		String user_nm= request.getParameter("nm");
		
		return "redirect:/user/login";
	}
	
}
