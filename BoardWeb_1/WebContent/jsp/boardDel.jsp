<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.koreait.web.BoardVO"%>
=

<%!
	Connection getCon() throws Exception{
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String username="hr";
	String password="koreait2020";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	Connection con = DriverManager.getConnection(url, username, password);
	System.out.println("접속성공!");
	return con;
	
    } 

	//int dr;
	//String massage;
%>


<%	
	String strI_board =request.getParameter("i_board"); 
	int i_board= Integer.parseInt(strI_board); //null이 들어오더라도 예외가 안되도록 처리하기!
	BoardVO vo = new BoardVO();
	Connection con= null; 
	PreparedStatement ps =null;
	
	
	String sql=" DELETE FROM t_board WHERE i_board= ? "; 
	
	int result=-1;
	
   	try{
    	con=getCon();//* 접속
    	ps= con.prepareStatement(sql);//* 명령어 전달
    	ps.setInt(1,i_board); //1은 물음표의 위치값 뒤에는 i_board는 물음표에 들어갈 값
      	result= ps.executeUpdate();
    	//ps.setString(1,strI_board);
 		//massage= (dr+1) + "개의 행이 삭제 되었습니다.";
 		//자바에서 하는 것은 자동커밋! 
  	
   	}
    catch(Exception e){
        	e.printStackTrace();
        //	massage= "삭제할 수 없습니다.";
        	
        }finally{  
        	
          //닫을때 반대로 !                                                         
          if(ps!=null){try{ps.close();}catch(Exception e){}}
          if(con!=null){try{con.close();}catch(Exception e){}} 	
        }
   	
   	System.out.println("result:" + result);
   	
   	switch(result){
   	case -1:
   		response.sendRedirect("/jsp/boardDetail.jsp?err=-1&i_board="+i_board); //바로 주소이동 //상세페이지를 보여주기 위해서 디테일 값에 i)board
   		break;
   	case 0:
   		response.sendRedirect("/jsp/boardDetail.jsp?err=0&i_board="+i_board);
   		break;
   	case 1:
   		response.sendRedirect("/jsp/boardList.jsp");
   		break;
   		
   	}
   	
   	
    	
%>





<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div> </div>
</body>
</html>