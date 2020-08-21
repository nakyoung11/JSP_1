package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.koreait.vo.BoardVO;
import com.koreait.vo.UserVO;
import com.sun.deploy.uitoolkit.impl.fx.Utils;
import com.koreait.pjt.Const;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;

/**
 * Servlet implementation class BoardRegmodSer
 */
@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//화면띄우기 : 등록창 수정창
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board= request.getParameter("i_board");
		
		if(strI_board!=null) {
		int i_board=Integer.parseInt(strI_board);	
		BoardVO param=new BoardVO();
		param.setI_board(i_board);
		BoardVO data=BoardDAO.selBoard(param);
		request.setAttribute("data", data);
		}
	
		ViewResolver.forwardLoginChk("board/regmod", request, response);
		                      //jsp파일담당이기 때문에 파일이 있는 곳을 적어줘요. 
			
	}

	
	//처리용~(DB에 등록/수정 실시)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession hs = request.getSession();
		
		UserVO loginUser= (UserVO)hs.getAttribute(Const.LOGIN_USER);
		
		String strI_board=request.getParameter("i_board");
		
		
		//int i_board=Integer.parseInt(strI_board);
		System.out.println(strI_board);
		String title=request.getParameter("title");
		String ctnt=request.getParameter("ctnt");
		BoardVO param= new BoardVO();
		param.setTitle(title);
		param.setCtnt(ctnt);
		param.setI_user(loginUser.getI_user());
		
		if(strI_board.equals("")) {

		int result=BoardDAO.insBoardWrite(param);		
		response.sendRedirect("/board/list");
		
		}else{
//수정			
			int i_board=Integer.parseInt(strI_board);		
			param.setI_board(i_board);
			int result=BoardDAO.updateBoard(param);
			response.sendRedirect("/board/detail?i_board="+strI_board);
		}
				
			
		}
			
	}


