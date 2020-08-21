package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.vo.BoardVO;
import com.koreait.vo.UserVO;


@WebServlet("/board/detail")
public class BoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserVO loginUser=MyUtils.getLoginUser(request);
		if(loginUser==null) {
			response.sendRedirect("/login");
			return;
		}
		
		String strI_board= request.getParameter("i_board");
		int i_board=Integer.parseInt(strI_board);
	
		//단독으로 조회수 올리기 방지
		ServletContext application=getServletContext(); //어플리케이션 객체 얻기 (이 방법뿐!)
		//서버가 켜질때 1개만 만들어져!  //내장객체 4개는 get // set !
		Integer readI_user=(Integer)application.getAttribute("read_"+strI_board);
		//int에서 null을 담는 기능 추가                                      // read_(구분용)+strI_baord(의미있는 번호) 키값
		//내가 read_10이라는 것에 무엇있는지  받아옴,(읽었다면 나의 pk값이 있을것,) 결과를 가지고 if로 감
		if(readI_user==null||readI_user!=loginUser.getI_user()) {
		   //한번도 읽은 사람이 없거나 서버를 올렸다 내렸을때! 가저온 키값과 loginUser를 비교! 
			BoardDAO.addHits(i_board); //조회수를 올리고
			application.setAttribute("read_"+strI_board, loginUser.getI_user());
		    //같은 키값(read_10)으로 나의 Pk를 받아! 그리고 새로고침했을때 넘어가는 것은 나의pK값이 넘어가서 null이 아님
		}
		//끝 :누가 내글을 마지막으로 읽었는지를 기록(취약점: 두사람이 새로고침을 한다면 조회수가 올라감)
				
		BoardVO param= new BoardVO();
		param.setI_board(i_board);

		
		BoardVO items= BoardDAO.selBoard(param);

		
		request.setAttribute("data", items);
		
	
		
		ViewResolver.forward("board/detail", request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}