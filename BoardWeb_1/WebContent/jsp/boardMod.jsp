<!-- 디테일 + Write -->

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
	BoardVO vo = new BoardVO();
	String strI_board = request.getParameter("i_board");
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	int i_board = Integer.parseInt(strI_board);
	String sql = " SELECT title, ctnt, i_student FROM t_board WHERE i_board= ? ";
	//" UPDATE t_board SET title=?, ctnt=?  WHERE i_board=? "; 
	//무언가 보기 위해서 는 PK 값이 필요해 

	try {
		con = getCon();//* 접속
		ps = con.prepareStatement(sql);//* 명령어 전달
		ps.setInt(1, i_board); //1은 물음표의 위치값 뒤에는 i_board는 물음표에 들어갈 값
	
		rs = ps.executeQuery();// 무조건 SELECT만!!!! *값전달  /위에서 값이 잘 들어가고 문장을 완성하고 executeQuery!
		//ps.setString(1,strI_board);
	
		if (rs.next()) {
	
			String title = rs.getNString("title");
			String ctnt = rs.getNString("ctnt");
			int i_student = rs.getInt("i_student");
	
			vo.setTitle(title);
			vo.setCtnt(ctnt);
			vo.setI_student(i_student);
	
		}
	} catch (Exception e) {
	
		e.printStackTrace();
	
	} finally {
	
		//닫을때 반대로 !
		if (rs != null) {
			try {
		rs.close();
			} catch (Exception e) {
			}
		}
	
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
%>





<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
</head>
<body>
	<div>
		상세페이지:
		<%=i_board%></div>

	<div>
		<a href="/jsp/boardList.jsp">목록</a>
	</div>
	<a href="#" onclick="procDel(<%=i_board%>)">삭제</a>
	<!-- 수정할값을 같이 넘겨주기  -->

	<form action="/jsp/boardModProc.jsp" method="post">
		<input type="hidden" name="i_board" value="<%=i_board%>">
		 <!--위에 hidden은  값 감추기 -->
		<label>제목:<input type="text" name="title" value=<%=vo.getTitle()%>></label>

		<div>
			<label>내용:<textarea name="ctnt"><%=vo.getCtnt()%></textarea></label>
		</div>
		<div>
			<label>작성자:<input type="text" name="i_student"
				value=<%=vo.getI_student()%>></label>
		</div>
		<div>
			<!--화면띄우기  /2개의 jsp파일이 필요하고/-->
			<input type="submit" value="수정">
		</div>
	</form>
	<script>
	
	
	
	</script>


</body>
</html>