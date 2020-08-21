<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>  
<%@ page import="com.koreait.board.vo.BoardVO" %>  

<% 
  
  List<BoardVO> list= (List)request.getAttribute("data");
	
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
.itemRow:hover {background-color: rgb(233, 217, 203); cursor:pointer}

.head {
	width: 10%;
	text-align: center;
}

.ctnt {
	padding-left: 30px;}


</style>

</head>
<body>
	<div>
		<h3>게시판 리스트</h3>
		<a href="/boardWrite"> <!--화면띄우기  /2개의 jsp파일이 필요하고/-->
		<button id="btn">글쓰기</button>
	</a>
	</div>
	<table>
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<% for(BoardVO vo : list){ %>
		<tr class="itemRow" onclick="moveToDetail(<%=vo.getI_board()%>)">
			<td class="head">
			<%=vo.getI_board()%></td>
			<td class="ctnt">
		    <%=vo.getTitle()%></td>
		    <td>
		    <%=vo.getI_student() %></td>
		</tr>
		<%}%>
	</table>
	<script>
	function moveToDetail(i_board){
		console.log('moveToDetail-i_board:'+i_board)
		location.href='boardDetail?i_board='+i_board
	}
	
	</script> 
	
	
</body>
</html>