package com.koreait.matzip;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/") // 모든 요청을 다잡아. 다만 res로 시작하는 모든 요청은 stict
@MultipartConfig(
		fileSizeThreshold = 10_485_760, // 10 MB
		maxFileSize = 52_428_800, // 50 MB
		maxRequestSize = 104_857_600 // 100 MB
	)
public class Container extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HandlerMapper mapper;

	// 싱글톤
	public Container() {
		mapper = new HandlerMapper();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		proc(request, response);
	}

	private void proc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String routerCheckResult= LoginChkInerceptor.routerChk(request);
		if(routerCheckResult!=null) {
			response.sendRedirect(routerCheckResult);
			return;
		}
		

		String temp = mapper.nav(request); //보통 템플릿 파일명이 넘어옴. 

		if(temp.indexOf(":") >= 0){
			String prefix=temp.substring(0, temp.indexOf(":"));	
			String value=temp.substring(temp.indexOf(":")+1);
			// 다시 보기 !!  0	
				if("redirect".equals(prefix)) {
					response.sendRedirect(value);
					return;
				}else if("ajax".equals(prefix)) {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json");
					PrintWriter out =response.getWriter();
					
					System.out.println("value: "+ value);
					out.print(value);
					return;
				}	
	
		}

		switch (temp) {
		case "405":
			temp = "/WEB-INF/view/error.jsp";
			break;
		case "404":
			temp = "/WEB-INF/view/notFound.jsp";
			break;

		}

		request.getRequestDispatcher(temp).forward(request, response);
	}

}
