<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
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

<%  //메소드 내에 들어있는 것!!
    //읽기 할때 쓰는 것 //요거는 다 쓸줄 알아요해 .     	
    List <BoardVO> boardList = new ArrayList();
    Connection con= null; //finally에서도 사용하고 싶어서. ~ try에서만 하면 try{}에서만 살아있어요.   연결
   	PreparedStatement ps =null;//실행 명령 , 문장 완성(printf)    실행
   	ResultSet rs=null;// 하드웨어의 사양만큼 데이터를 담음    	    담음    //SELECT문에서만
   	
   	String sql= " SELECT i_board, title FROM t_board ORDER BY i_board DESC"; //양쪽에 빈칸주기 
	    
    try{    	
    	con=getCon();//* 접속
    	ps= con.prepareStatement(sql);//* 명령어 전달
    	rs= ps.executeQuery(); // 무조건 SELECT만!!!! *값전달 
    	
    	//list에 값넣어주시 (parsing)
    	while(rs.next()){ //rs.next()는 행을 가르키고  결과값은 참(레코드가 있다면), 거짓! 레코드가 있다면 반복! 레코드 없이면 끝!
    		
    		int i_board=rs.getInt("i_board");//괄호안에 "컬럼명" 
			
    		String title=rs.getNString("title"); //get현재 가르키고 있는 레코드에서 가져오고 싶은 컬럼값을 가져옴.  	//스트링 스트링
    		
			BoardVO vo = new BoardVO(); //********whille밖에 있다면 같은 값으로 리턴됨. 
    		
			vo.setI_board(i_board); //가져온 값 넣기 
    		
			vo.setTitle(title);    		
    		
			boardList.add(vo); //boardList에 담아요.
    		
    	}
    	
    }catch(Exception e){
    	//이걸 맨밑에 주고 예상가능한 에러들을 catch(){}형태로 추가 가능
    	e.printStackTrace(); //에러메세지 출력 //구체적인 예외처리 따로 공부하기  
    	
    }finally{  //닫는 이유는 안해주면 서버가 죽어서.. 
    	
      //닫을때 반대로 !
      if(rs!=null){try{rs.close();}catch(Exception e){}} //하나가 실패하더라도 다른 곳은 닫히도록 해줌. 희박하지만 혹시 모르는 상황에 
                                                         //대비해서 FM
      if(ps!=null){try{ps.close();}catch(Exception e){}}
      
      if(con!=null){try{con.close();}catch(Exception e){}} 	
    }
   
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
table {
	width: 60%;
	border: 1px solid black;
	background-color: blanchedalmond;
	border-collapse: collapse;
}

th {
	background-color: rgb(233, 217, 203)
}

th, tr, td {
	border: 1px solid black;
	height: 25px;
}

.head {
	width: 10%;
	text-align: center;
}

.ctnt {
	padding-left: 30px;
}
</style>

</head>
<body>
	<div>
		<h3>게시판 리스트</h3>

	</div>
	<table>
		<tr>
			<th>NO</th>
			<th>제목</th>
		</tr>
		<%for(BoardVO vo : boardList){ %>
		<tr>
			<td class="head"><%=vo.getI_board()%></td>
			<td class="ctnt">
			<a href="/jsp/boardDetail.jsp?i_board=<%=vo.getI_board()%>"> <%=vo.getTitle()%>
			</a></td>
		</tr>
		<%}%>
	</table>
	<a href="/jsp/boardWrite.jsp"> <!--화면띄우기  /2개의 jsp파일이 필요하고/-->
		<button>글쓰기</button>
	</a>
</body>
</html>