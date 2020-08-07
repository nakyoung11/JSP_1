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
		
		String strI_board=request.getParameter("i_board");
		int i_board=Utils.parseStrToint(strI_board, 0);
		String ctnt=request.getParameter("ctnt");
		String strI_student=request.getParameter("i_student");
		int i_student=Utils.parseStrToint(strI_student, 0);
		String title=request.getParameter("title");
		
		BoardVO param= new BoardVO();
		param.setCtnt(ctnt);
		param.setI_board(i_board);
		param.setI_student(i_student);
		param.setTitle(title);
		BoardDAO.selBoardWrite(param);
		response.sendRedirect("/boardList");
		
		
		
		
		;
	}

}
