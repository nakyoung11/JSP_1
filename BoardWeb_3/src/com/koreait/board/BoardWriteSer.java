package com.koreait.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.common.Utils;
import com.koreait.board.vo.BoardVO;
import com.koreait.voard.db.BoardDAO;


@WebServlet("/boardWrite")
public class BoardWriteSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsp="/WEB-INF/view/boardRegmod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //숫자를 보내더라도 문자로 날라가 ! HTML로 보낸것은 모두 문자 
		String title=request.getParameter("title");
		String ctnt=request.getParameter("ctnt");
		String strI_student=request.getParameter("i_student");
		
  //값이 잘 넘어왔는지 ! 단위 테스트
		System.out.println("title : "+ title);
		System.out.println("ctnt : "+ ctnt);
		System.out.println("i_student : "+Utils.parseStrToint(strI_student, 0));	
		
		BoardVO param= new BoardVO();
		param.setTitle(title);
		param.setCtnt(ctnt);
        param.setI_student(Utils.parseStrToint(strI_student, 0));	
        
        		
		int result= BoardDAO.insBoardWrite(param);
		//값이 잘 넘어왔다면 1출력! 
		
		if(result==1) {response.sendRedirect("/boardList");
		}else {
			response.sendRedirect("/err");
			//request.setAttribute("msg", "에러가 발생하였습니다.");
			doGet(request,response);
		}
		
		
		
		
		
		System.out.println("result : "+result);
		
		
		
		
		

	}

}
