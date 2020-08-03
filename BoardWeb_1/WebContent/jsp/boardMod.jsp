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
	}
%>
<%


	

%>





<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
</head>
<body>
<form action="/jsp/boardWriteProc.jsp" method="post" id="frm"
			onsubmit="return chk()">
			<div id=msg></div>
			<div>
				<label>제목 <input type="text" name="title"><%=  %>></label>
			</div>
		
			<div>
				<label>내용 <textarea name="ctnt"></textarea></label>
			</div>
			<div>
				<label>작성자 <input type="text" name="i_student"></label>
			</div>
			<div>
				<input type="submit" value="글등록">
			</div>
		</form>


</body>
</html>