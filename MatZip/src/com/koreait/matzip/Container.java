package com.koreait.matzip;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/") //모든 요청을 다잡아. 다만 res로 시작하는 모든 요청은 stict
public class Container extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HandlerMapper mapper;
	
	//싱글톤
	public Container() {
		mapper = new HandlerMapper();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("uri : "+request.getRequestURI());
//		//첫번째 값이 res인것 뽑아내기
//		String[] uriArr=request.getRequestURI().split("/");
//		
//		for(int i=0; i<uriArr.length;i++) {
//			System.out.println("uriArr["+i+"] : " +uriArr[i]);
//		}

//		if(uriArr.length > 1) {
//			request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
//		}
			
		proc(request, response);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proc(request, response);
	}
	
	private void proc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp = mapper.nav(request);
		
		String isRedirect = temp.substring(0,temp.indexOf("/"));
		
		if(temp.indexOf("/") >=0) {
		
			if("redirect:".equals(isRedirect)){
				response.sendRedirect(temp.substring(temp.indexOf("/")));	
				return;
				}
		}
		
		switch(temp) {
		case "405":
			temp ="/WEB-INF/view/error.jsp";
			break;
		case "404":
			temp="/WEB-INF/view/notFound.jsp";
			break;
		
		}
		
		request.getRequestDispatcher(temp).forward(request, response);
	}

}
