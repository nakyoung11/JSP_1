package com.koreait.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import com.koreait.board.common.Utils;
import com.koreait.board.vo.BoardVO;
import com.koreait.voard.db.BoardDAO;

@WebServlet("/boardDetail")
public class boardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	
//		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/boardDetail.jsp");
//		rd.forward(request, response);
		
		String strI_board=request.getParameter("i_board");
		int i_board=Utils.parseStrToint(strI_board,0);
	
		if(i_board==0) {
			// 창을 띄우고 싶다면 자바스크립으로 만들어주기
			response.sendRedirect("/boardList");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		
		 BoardVO data=BoardDAO.selBoard(param);
		 request.setAttribute("data", data);
		 //setAttribute는 한 번만 쓸 수 있어. 
		 
		 //request.setAttribute("data",BoardDAO.selBoard(param))" 위에 두줄을 1줄로도 가능
		
		String jsp="/WEB-INF/view/boardDetail.jsp";
		
		request.getRequestDispatcher(jsp).forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
