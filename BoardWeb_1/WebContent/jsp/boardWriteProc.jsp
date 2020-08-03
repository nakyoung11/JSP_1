<!-- 고객의 글을 데이터 베이스에 넣음 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.koreait.web.BoardVO"%>

<%!//전역변수, 메소드 만들기  
	Connection getCon() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String username = "hr";
		String password = "koreait2020";

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println("접속성공!");
		return con;
	}%>


<%
	String title = request.getParameter("title"); //외부로 부터 받은 값을 풀어봄 타이틀은 키값!
// 주소값:HttpServletRequest request 
String ctnt = request.getParameter("ctnt");
String strI_student = request.getParameter("i_student");
int i_student = Integer.parseInt(strI_student);
//integer.parseInt는 문자가 1개라도 있다면 무조건 에러!
//에러터지면 밑으로 실행이 안되용. 
//서브릿에서 체크해주는 걸로 !

Connection con = null;
PreparedStatement ps = null;

String sql = " INSERT INTO t_board (i_board, title, ctnt, i_student) "
		+ " SELECT nvl(max(i_board),0)+1,?,?,? FROM t_board ";
//i_student,  title, ctnt, i_student
//?는 값삽입
//현재 구조에서는 지금 들어간 pk값을 볼 수 가 없어. 그래서 디테일 화면을 으로 볼 수는 없어..  	
//
		

		int result=-1;
		try {
		con = getCon();//* 접속
		ps = con.prepareStatement(sql);//* 명령어 전달
		ps.setNString(1, title);
		ps.setNString(2, ctnt);
		ps.setInt(3, i_student);
		
		if("".equals(title)||"".equals(ctnt)||"".equals(i_student)){
			response.sendRedirect("/jsp/boardWrite.jsp?err=10");
		}
		
		result = ps.executeUpdate(); //값에 따라 페이비 이동 
		
	        
	} catch (Exception e) {
		e.printStackTrace();
		//	massage= "삭제할 수 없습니다.";
	
	} finally {
	
		//닫을때 반대로 !                                                         
		if (ps != null) {
			try {
		ps.close();
			} catch (Exception e) {
			}
		}
		if (con != null) {
			try {
		con.close();
			} catch (Exception e) {
			}
		}
}

int err=0;		
   switch(result){
   case 1: //레코드 1개가 들어감 
	   response.sendRedirect("/jsp/boardList.jsp");
	   return; // 메소드 자체를 완전 종료
   case 0:
	   err=10;
	   break; 
	   
   case -1:
	   err=20;
	   break; 
	   
    }
   
   response.sendRedirect("/jsp/boardWrite.jsp?err="+err); // 너무 많은 내용을 적어서 글등록하면 
   //주소값이 달라져있어 err=20 or err=10으로 
  
%>