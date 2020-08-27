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
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.vo.BoardVO;


@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		int page=MyUtils.getIntParameter(request, "page");
	
		
		page = page==0? 1:page; //3항식! 
		
		int eIdx= page*Const.RECORD_CNT;
		int sIdx= eIdx-Const.RECORD_CNT;
		
		
		request.setAttribute("inpage", page);
		
		
		
		BoardVO param= new BoardVO();
		param.setRecord_cnt(Const.RECORD_CNT);
		param.setSidx(sIdx);
		param.setEidx(eIdx);
		List<BoardVO> list= BoardDAO.selBoardList(param);
		request.setAttribute("pagingCnt",BoardDAO.selPageinCnt(param));
		
		request.setAttribute("list", list);
		
		
		
		//hs.getAttribute("/login");
		
		ViewResolver.forwardLoginChk("board/list", request, response);
}
}

	