<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>  
<%@ page import="com.koreait.vo.BoardVO" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
*{
	outline: none;
}
body{background-color: rgb(247, 246, 245)}
.container{width:800px;  margin: 50px auto}
table {
	width: 600px;
	border: 1px solid black;
	border-collapse: collapse;
}

.click:hover {background-color: #CCCFEB; cursor:pointer ; color:#CDDCDC}

th {
	color:#EDEDED; font-size: smaller
}

th, tr, td {
	border: 1px solid black;
	height: 25px;
	color:#1C1F24
}
td{text-align:center}


.head {
	width: 5%;
	text-align: center;
}

.ctnt {
	padding-left: 30px; 
}
#logOut{color:#E02A11; letter-spacing :-2.5px; border:#E02A11 }
a{text-decoration: none; color:#F2EBF5; font-size:smaller}
p{font-size: 15px}

	
#btn{display:inline-block; margin-left:555px; margin-bottom: 20px;width:50px; height:50px; 
background: #645574; border-radius: 50%; border:none;}
#btn:hover{background-color: #5C1D75;}
.click{cursor:pointer}
</style>
</head>

<body>
	<div class="container">
		 <!--화면띄우기  /2개의 jsp파일이 필요하고/-->
		<p>${loginUser.nm}님 환영합니다. &nbsp;&nbsp;<a id="logOut" href="/logout">로그아웃</a></p>
		
		
		<button id="btn"><a  href="/board/regmod">글쓰기</a></button>
		<!-- /의 차이 :  처음부터 시작하고 싶다면 앞에 /를 붙여야하고 안붙인다면 제일 마지막에 있는 주소만 바뀜 
		     만약 안붙였다면 /board/board/reg-->

	<table>
		<tr>
			<th>NO</th>
			<th width=280px>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th width=160px>작성일</th>
		</tr>

		<c:forEach items="${list}" var="item">
		<tr class="click" onclick="detail(${item.i_board})">
       <!-- 내장객체에 담겨있는 것을 사용 EL식 -->
		<td>${item.i_board}</td>
		<td>${item.title}</td>
		<td>${item.nm}</td>
		<td>${item.hits}</td>
		<td>${item.m_dt}</td>
		</tr>
		</c:forEach>
	</table>
	</div>
</body>
<script>
function detail(i_board, hits){
	location.href='/board/detail?i_board='+i_board
			                     // 키     = value
}




</script>

</html>