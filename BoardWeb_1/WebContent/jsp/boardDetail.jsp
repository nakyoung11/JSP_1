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
    String strI_board =request.getParameter("i_board"); 
  	if(strI_board==null){
%>
    <script>
	alert('잘 못 된 접근입니다.')
	location.href='/jsp/boardList.jsp'
	</script>

<%	
	return;  //잘못된 접근일때는 메소드를 종료시킴 안해준다면 밑에 실행이 다됨 
	}
    


 
    Connection con= null; 
	PreparedStatement ps =null;
	ResultSet rs=null; 
    int i_board= Integer.parseInt(strI_board);
	String sql=" SELECT title, ctnt, i_student FROM t_board WHERE i_board= ? "; 
   	BoardVO vo = new BoardVO(); 
   	
   	try{
    	con=getCon();//* 접속
    	ps= con.prepareStatement(sql);//* 명령어 전달
    	ps.setInt(1,i_board); //1은 물음표의 위치값 뒤에는 i_board는 물음표에 들어갈 값
    	
    	rs= ps.executeQuery();// 무조건 SELECT만!!!! *값전달  /위에서 값이 잘 들어가고 문장을 완성하고 executeQuery!
    	//ps.setString(1,strI_board);
    
    	if(rs.next()){ 
 		
    	String title= rs.getNString("title");
    	String ctnt= rs.getNString("ctnt");
    	int i_student=rs.getInt("i_student");
    	
    	
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

a {display: inline;
  text-align: center;
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
		
	<div> <a href="/jsp/boardList.jsp">목록</a></div>
	<a href="#" onclick="procDel(<%=i_board%>)">삭제</a>
	<div>제목: <%=vo.getTitle() %></div>
	<div>내용: <%=vo.getCtnt() %></div>
	<div>작성자:<%=vo.getI_student() %></div>
	<script>
	function procDel(i_board){
		//alert('i_board:'+i_board)
		if(confirm('삭제하시겠습니까?')){
		   location.href='/jsp/boardDel.jsp?i_board='+i_board
		}
	}
	</script>



</body>
</html>