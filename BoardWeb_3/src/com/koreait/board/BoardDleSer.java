package com.koreait.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.koreait.board.common.Utils;
import com.koreait.voard.db.BoardDAO;

@WebServlet("/boardDel")
public class BoardDleSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String strI_board = request.getParameter("i_board");
		int i_board = Utils.parseStrToint(strI_board, 0);

		// System.out.println(i_board);

		int result = BoardDAO.delBoard(i_board);
		
		if(i_board==0) {
			response.sendRedirect("/boardList");
			return;
		}

		System.out.println(result);

		if (result == 1) {
			response.sendRedirect("/boardList");
		}else {
			response.sendRedirect("/err?err=1&target=boardList");
		}

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
