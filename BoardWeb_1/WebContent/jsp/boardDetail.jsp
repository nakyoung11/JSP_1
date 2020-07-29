<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.koreait.web.BoardVO"%>

<%!//전역변수, 메소드 만들기  
	    Connection getCon() throws Exception{
    	String url="jdbc:oracle:thin:@localhost:1521:orcl";
    	String username="hr";
    	String password="koreait2020";
    	
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    	Connection con = DriverManager.getConnection(url, username, password);
    	System.out.println("접속성공!");
    	return con;
    }  
    %>


<%  
    Connection con= null; 
	PreparedStatement ps =null;
	ResultSet rs=null; 
    String strI_board =request.getParameter("i_board"); 
	String sql=" SELECT title, ctnt, i_student FROM t_board WHERE i_board= " + strI_board;
   	BoardVO vo = new BoardVO(); 
   	
   	try{
    	con=getCon();//* 접속
    	ps= con.prepareStatement(sql);//* 명령어 전달
    	rs= ps.executeQuery(); // 무조건 SELECT만!!!! *값전달 
    
    	if(rs.next()){ 
    		
    	
    	String title= rs.getNString("title");
    	String ctnt= rs.getNString("ctnt");
    	int i_student= rs.getInt("i_student");
    
		vo.setI_board(Integer.parseInt(strI_board));
    	vo.setTitle(title); 
    	vo.setCtnt(ctnt);
    	vo.setI_student(i_student);
    	
    	}
   	}
    catch(Exception e){
        	
        	e.printStackTrace(); 
        	
        }finally{  
        	
          //닫을때 반대로 !
          if(rs!=null){try{rs.close();}catch(Exception e){}} 
                                                             
          if(ps!=null){try{ps.close();}catch(Exception e){}}
          
          if(con!=null){try{con.close();}catch(Exception e){}} 	
        }
    	
    	
    
    
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	font-size: smaller;
	margin: 10px;
}

table {
	border: 1px solid black;
	border-collapse: collapse;
}

tr, th, td {
	border: 1px solid black;
	height: 30px;
	width: 120px;
}

p {
	text-align: center;
	margin: 0px;
}

th {
	background-color: burlywood;
}
</style>
</head>
<body>
	<div>
		상세페이지:
		<%=strI_board%></div>
	<table>
		<tr>
			<th>title</th>
			<th>Ctnt</th>
			<th>i_student</th>
		</tr>
		<tr>
			<td><p><%=vo.getTitle() %></p></td>
			<td><p><%=vo.getCtnt() %></p></td>
			<td><p><%=vo.getI_student() %><p></td>
			
		</tr>
	</table>
	<a href="/jsp/boardDetail.jsp?i_board=<%=vo.getI_board()-1%>"> 
					
			이전글</a>
			<a href="/jsp/boardDetail.jsp?i_board=<%=vo.getI_board()+1%>"> 
					
			다음글</a>
				


</body>
</html>