package com.koreait.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.vo.UserVO;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Receiver;


@WebServlet("/changePw")
public class ChangePw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ViewResolver.forward("user/changePw", request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	 String type=request.getParameter("type");
	 String pw= request.getParameter("pw");

     UserVO loginUser=MyUtils.getLoginUser(request); 
	 String encryptpw=MyUtils.encryptString(pw);  	 
	 
	 System.out.println(pw);
	 System.out.println(encryptpw);
	 
	UserVO param= new UserVO();	 
	 switch(type) {
	 
	 case "1": //현재비밀번호
	
		param.setUser_id(loginUser.getUser_id());
		param.setUser_pw(encryptpw);
		
		int result=UserDAO.login(param);
		 
		 System.out.println("result"+result);
		 
		 if(result==1) {
			 request.setAttribute("isAuth", true);
		 }else {
			 request.setAttribute("msg","비밀번호를 확인하세요" );
		 }
		 
		 doGet(request, response);
		 break;
		 
	 case "2": //비밀번호 변경
				
			param.setI_user(loginUser.getI_user());
			param.setUser_pw(encryptpw);
		 
		   UserDAO.updUser(param);
		   response.sendRedirect("/profile");
		   break;
		
		 
	 
	 }
	 
	}
}
