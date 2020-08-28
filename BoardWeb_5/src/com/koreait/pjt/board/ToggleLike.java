package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.vo.BoardLikeVO;
import com.koreait.vo.BoardVO;
import com.koreait.vo.UserVO;

@WebServlet("/board/toggleLike")
public class ToggleLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	UserVO loginUser=MyUtils.getLoginUser(request);
		if(loginUser==null) {
			response.sendRedirect("/login");
			return;
		}
				
	String strI_board=request.getParameter("i_board");
	String strYn_like=request.getParameter("yn_like");
	
	
	
	String searchText=request.getParameter("searchText");
	searchText= searchText==null? "":searchText;
	System.out.println(searchText);
	
	
	
	int page = MyUtils.getIntParameter(request, "page");
	page = (page == 0 ? 1 : page);
	
	int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
	recordCnt = (recordCnt == 0 ? 10 : recordCnt);
	
		

	//System.out.println(strI_board);
	//System.out.println(strYn_like);
	
	int i_board=MyUtils.parseStrToInt(strI_board);
	int yn_like=MyUtils.parseStrToInt(strYn_like, 3);
	
	BoardVO param=new BoardVO();
	param.setI_board(i_board);
	param.setI_user(loginUser.getI_user());


	
	if(yn_like==0) {
		BoardDAO.insLike(param);
	}else if(yn_like==1) {
		BoardDAO.dleLike(param);
	}
		

	request.setAttribute("page", page); 
	
	

	response.sendRedirect("/board/detail?i_board="+i_board+"&page="+page+"&record_cnt="+
	recordCnt+"&searchText="+searchText);
	
	//BoardVO result=BoardDAO.
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
