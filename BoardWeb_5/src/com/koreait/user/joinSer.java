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


@WebServlet("/join")
public class joinSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	// 화면띄우기 전용으로 해보기 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ViewResolver.forward("user/join", request, response);
	}

	
	//업무처리 , 값넣기, 업데이트하기 등등 //모든처리는 post로 해보고~
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		String encrypt_pw=MyUtils.encryptString(user_pw);
		String nm=request.getParameter("nm");
		String email=request.getParameter("email");
		 
		UserVO param= new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		param.setNm(nm);
		param.setEmail(email);
		
		int result=UserDAO.insUser(param);
		
		System.out.println(result);
	
		if(result!=1) {
			//에러가 발생하였습니다. 계속된다면 관리자에게  문의 하십시오.
			request.setAttribute("msg","에러가 발생하였습니다.");
            request.setAttribute("data",param);
        	doGet(request,response);	
			return;
		}
		
		response.sendRedirect("/login");
		//ViewResolver.forward("user/login", request, response);
		
		
	}

}
