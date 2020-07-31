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

	String title= request.getParameter("title");
	String ctnt= request.getParameter("ctnt");
	String strI_student= request.getParameter("i_student");
	
	Connection con= null; 
 	PreparedStatement ps =null; 
	String sql= " INSERT INTO t_board (i_board, title, ctnt, i_student) "
			 +" SELECT nvl(max(i_board),0)+1,?,?,? FROM t_board ";
	

	
	try{
		con=getCon();//* 접속
    	ps= con.prepareStatement(sql);//* 명령어 전달
    	ps.setString(1, title);
    	ps.setString(2, ctnt);
    	ps.setString(3, strI_student);
        ps.executeUpdate();
    	
		
	}catch(Exception e){
    	e.printStackTrace();
    //	massage= "삭제할 수 없습니다.";
    	
    }finally{  
    	
      //닫을때 반대로 !                                                         
      if(ps!=null){try{ps.close();}catch(Exception e){}}
      if(con!=null){try{con.close();}catch(Exception e){}} 	
    }
	response.sendRedirect("/jsp/boardList.jsp");
	
%>

<!-- 한글 입력시 글이 깨짐  -->
<div>title: <%=title %></div>
<div>ctnt: <%=ctnt %></div>
<div>strI_student: <%=strI_student%></div>