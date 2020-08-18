//로직담당
//자료담아서 주소값만 보내주는 담당임
package com.koreait.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.vo.BoardVO;
import com.koreait.voard.db.BoardDAO;
import com.koreait.voard.db.DBCon;

@WebServlet("/boardList") // 주소값이고 맵핑은 이미 끝 !, "/"이렇게 하면 최초페이지 , 이 주소로 오면 아래의 메소드가 실행
public class BoardListSer extends HttpServlet { // extends: 상속 받음
	private static final long serialVersionUID = 1L; // 멤버필드

	public BoardListSer() { // 기본생성자 : 클래스 이름, 리턴타입 X
		super(); // 부모의 기본생성자 호출

	}
	// 둘중에 필요한 메소드만 가지고 있어도 됨

	@Override
	// 컨테이너에서 호출해서 사용! get 방식으로 보내면 실행됨 (get은 속도가 빠르지만 주소창에 쿼리문이 들어가기 때문에 한계가 있어~ 너무
	// 많은 글을 쓸 수 는 없어 ~ 화면 띄울때 많이 사용~ 검색같은 경우 )
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 이곳에서만 작업을 해주면 되지요. //requst가 중요 //response에 모든 정보가 들어있어요!
		// request는 연결고리 ! 데이터베이스의 결과물을 담아 보냄

		List<BoardVO> list = BoardDAO.selBoardList(); // BoardDAO의 데이터를 받기위해 리스트선언

		request.setAttribute("data", list); // 오브젝트 타입은 실수, 정수, 문자 모두 다 받음.
											// getAttribute는 오브젝트가 결과가됨

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/boardList.jsp");
		// 결과 리턴! //web-inf아래에 있다면 외부에소 주소값으로 접근이 불가능하기 때문에 dispatcher로 연다
		rd.forward(request, response);
		// 응답이후에는 소멸~ (메모리 관리를 위해서)
		// requestDispatcher 이동하는것이기 하지만 데이터를 유지! 주소값 이 변하지 않음! 샌드~는 무조건 겟방식으로 날아가~
		// dis는 메소드에 따라 달라.

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// //고객에게 응답으로가

	}

	@Override
	// post 방식으로 보내면 실행됨 (post는 form으로 만!! 속도는 쪼끔 느리지만 많은 양의 데이터를 보낼 수 있어~ 보안이 필요하고
	// 내용이 많다면 post)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
