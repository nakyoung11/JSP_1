package com.koreait.pjt.board;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.vo.BoardCmtVO;
import com.koreait.vo.UserVO;


@WebServlet("/board/cmt")
public class BoardCmt extends HttpServlet {

	//삭제
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser= (UserVO)hs.getAttribute(Const.LOGIN_USER);
		String strI_board =request.getParameter("i_board");
		String strI_cmt=request.getParameter("i_cmt");
		int i_cmt= MyUtils.parseStrToInt(strI_cmt);
		
		BoardCmtVO param= new BoardCmtVO();
		param.setI_cmt(i_cmt);
		param.setI_user(loginUser.getI_user());
		
		
		int result= BoardCmtDAO.delcmt(param);
		response.sendRedirect("/board/detail?i_board="+strI_board);
		
	}
	//등록 수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser= (UserVO)hs.getAttribute(Const.LOGIN_USER);
		
		String strI_cmt= request.getParameter("i_cmt");
		String strI_board =request.getParameter("i_board");
		String cmt=request.getParameter("cmt");
		
		String searchType= request.getParameter("searchType");
		searchType=(searchType==null)? "a":searchType;
		int page = MyUtils.getIntParameter(request, "page");
		page = (page == 0 ? 1 : page);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);	
			
			
		String searchText=request.getParameter("searchText");
		searchText= searchText==null? "":searchText;
		
		
		System.out.println(strI_cmt);
		
		
		int i_board=MyUtils.parseStrToInt(strI_board);
		
		BoardCmtVO param =new BoardCmtVO();
		param.setCmt(cmt);
		param.setI_user(loginUser.getI_user());
		
		System.out.println(param.getCmt());
		
       		
		switch(strI_cmt) {
		
		case "0": //등록
			param.setI_board(i_board);
			BoardCmtDAO.insCmt(param);			
			break;
			
		 default: //수정
			int i_cmt=MyUtils.parseStrToInt(strI_cmt);
			param.setI_cmt(i_cmt);
			BoardCmtDAO.updCmt(param);
			break;
			
		
		}
		
		
		
		String target = String.format("/board/detail?i_board=%s&page=%s&record_cnt=%s&searchText=%s&searchType=%s"
				, strI_board, page, recordCnt, searchText, searchType);

		response.sendRedirect(target);
		
		
	}

}
