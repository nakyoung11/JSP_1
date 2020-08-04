<!-- 고객의 글을 데이터 베이스에 업데이트 -->
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
<!-- 위에까지는 공통 -->

<%
String title = request.getParameter("title"); 
String ctnt = request.getParameter("ctnt");
String strI_student = request.getParameter("i_student");
String strI_board =request.getParameter("i_board"); 

//정수형으로 변환
int i_board = Integer.parseInt(strI_board);
int i_student = Integer.parseInt(strI_student);


Connection con = null;
PreparedStatement ps = null;

String sql = " UPDATE t_board SET title=?, ctnt=?, i_student=? WHERE i_board=? ";

		int result=-1;
		try {
		con = getCon();//* 접속
		ps = con.prepareStatement(sql);//* 명령어 전달
		ps.setNString(1, title);
		ps.setNString(2, ctnt);
		ps.setInt(3, i_student);
		ps.setInt(4, i_board);
		
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
				try {ps.close();} 
				catch (Exception e) {}
			}
			if (con != null) {
				try {con.close();}
				catch (Exception e) {}
			}
	}

   int err=0;		
   switch(result){
   case 1: //레코드 1개가 들어감 
	   response.sendRedirect("/jsp/boardList.jsp"); //주소바로 가기  연결고리 노노~
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