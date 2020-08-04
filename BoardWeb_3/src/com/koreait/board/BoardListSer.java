//로직담당
//자료담아서 주소값만 보내주는 담당임
package com.koreait.board;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/boardList") //주소값 ! 맵핑은 이미 끝 !  "/"이렇게 하면 최초페이지 
public class BoardListSer extends HttpServlet { //extends 상속 받음 
	private static final long serialVersionUID = 1L; //멤버필드
       
   
    public BoardListSer() { //기본생성자 : 클래스 이름, 리턴타입 X
        super(); //부모의 기본생성자 호출  
       
    }

	@Override   
	//컨테이너에서 호출해서 사용! get 방식으로 보내면 실행됨  (get은 속도가 빠르지만 주소창에 쿼리문이 들어가기 때문에 한계가 있어~ 너무 많은 글을 쓸 수 는 없어 ~ 화면 띄울때 많이 사용~ 검색같은 경우 )
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이곳에서만 작업을 해주면 되지요.                                       //response에 모든 정보가 들어있어요!                                
	
		String strI_board= request.getParameter("i_board");
		System.out.println("Servlet i_board:" + strI_board);		
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/boardList.jsp");
		rd.forward(request,response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath()); //고객에게 응답으로가 
		//request.getDispatcher 데이터를 유지해서 보냄  
		                                                  
	}

	@Override
	// post 방식으로 보내면 실행됨 (post는 form으로 만!! 속도는 쪼끔 느리지만 많은 양의 데이터를 보낼 수 있어~ 보안이 필요하고 내용이 많다면 post)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       		
		doGet(request, response);
		
	}

}
