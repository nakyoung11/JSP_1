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
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>
*{
font-family: 'Nanum Gothic', sans-serif;
	outline: none;
}
body{background-color: rgb(247, 246, 245)}
.container{width:1000px;  margin: 50px auto}
table {
	width: 800px;
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
.ff{font-size: 7px;}
p{font-size: 15px; font-weight:bold}
.page>a{color:#1C1F24; font-size: 15px; }
.page>a:active{font-size: 18px}
.page{width:800px;margin:15px ; text-align: center}
#pageColor{color:red}
	
#btn{display:inline-block; margin-left:75%; margin-bottom: 20px;width:50px; height:50px; 
background: #645574; border-radius: 50%; border:none;}
#btn:hover{background-color: #5C1D75;}
.click{cursor:pointer}
.img{background-image:url()}
#selFrm{margin:5px 0px 10px 5px; width: 250px; display:inline-block;}
.searchFrm{display: inline-block ;margin:15px 10px 10px 0px;width: 400px;}
#searchText{width: 250px;height: 25px;border-radius: 10px;  border: 1px solid #aea3b3 ;}
#seaSubmit{border-radius: 10px; width: 50px; height: 25px; border: none; background:#aea3b3 ;color: white;}

</style>
</head>

<body>
	<div class="container">
		 <!--화면띄우기  /2개의 jsp파일이 필요하고/-->
		<p>${loginUser.nm}님 환영합니다👏👏&nbsp;&nbsp;<a id="logOut" href="/logout">로그아웃</a></p>
		<button id="btn"><a href="/profile"> 프로필 </a></button>
	
	
		<div>

		
		<button id="btn"><a  href="/board/regmod">글쓰기</a></button>
		<!-- /의 차이 :  처음부터 시작하고 싶다면 앞에 /를 붙여야하고 안붙인다면 제일 마지막에 있는 주소만 바뀜 
		     만약 안붙였다면 /board/board/reg-->
		<form id="selFrm"  method="get" action="/board/list" >
		<!-- param.page가 가능한 이유는? String pagedd=request.getParameter("page")와 같기 때문에 -->
		<input type="hidden" name="page" value="${page}"> <!--페이지수-->
		<input type="hidden" name="searchText" value="${param.searchText}"> 
		 레코드수 :  
		 <select name="record_cnt" onchange="changeReCord()">
		  <c:forEach begin="10" end="30" step="10" var="item">
		  	<c:choose> 
				  <c:when test="${param.record_cnt==item}">
				    	<option value="${item}" SELECTED>${item}개</option>
				  </c:when>
			
				  <c:otherwise>	<!-- else처럼 사용 -->	  
				  	<option value="${item}">${item}개</option>
				  </c:otherwise>
		  		</c:choose>
		  	</c:forEach>
		  </select>
		
		</form>
		</div>
		


	

	<table>
		<tr>
			<th>NO</th>
			<th width=400px>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th width=200px>작성일</th>
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
		<form action="/board/list" class="searchFrm">
			<input type="search" name="searchText"  id="searchText" value="${param.searchText==''? '':param.searchText}">
			<input type="submit" id="seaSubmit" value="검색">
		</form>
		
	
	<div class="page">
	    <a href="/board/list?page=1&record_cnt=${param.record_cnt==null? 10:param.record_cnt}&searchText=${param.searchText}" class="ff">처음</a>
		<c:forEach begin="1"  end="${pagingCnt}" var="item">
		<c:choose>
		 <c:when test="${page == item}">
			<span id="pageColor">&nbsp; ${item}&nbsp;&nbsp;</span>
		 </c:when>
		 <c:otherwise>
			<a href="/board/list?page=${item}&record_cnt=${param.record_cnt}&searchText=${param.searchText}">&nbsp;${item}&nbsp;&nbsp;</a> 
		 </c:otherwise>	
		</c:choose>	
		</c:forEach>
		<a href="/board/list?page=${pagingCnt}&record_cnt=${param.record_cnt}&searchText=${param.searchText}" class="ff">끝</a>
	</div>
	</div>



</body>
<script>

function changeReCord(){
	selFrm.submit()
}

function detail(i_board, hits){
	location.href='/board/detail?i_board='+i_board+'&page=${page}&record_cnt=${param.record_cnt}&searchText=${param.searchText}'
			                     // 키     = value
}




</script>

</html>