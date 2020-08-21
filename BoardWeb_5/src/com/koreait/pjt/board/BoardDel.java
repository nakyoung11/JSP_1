package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.db.BoardDAO;
import com.koreait.vo.BoardVO;

/**
 * Servlet implementation class BoardDel
 */
@WebServlet("/board/del")
public class BoardDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strI_board= request.getParameter("i_board");
		int i_board=Integer.parseInt(strI_board);
		
		BoardVO param= new BoardVO();
		param.setI_board(i_board);
		
		int result = BoardDAO.DelBoard(param);
		
		if(result==1) {
			response.sendRedirect("/board/list");
		}
	}

}
