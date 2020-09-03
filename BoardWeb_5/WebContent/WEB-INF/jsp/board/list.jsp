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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<style>
*{
font-family: 'Nanum Gothic', sans-serif;
	outline: none;
}
body{background-color: rgb(247, 246, 245)}
.container{width:1000px;  margin: 50px auto}
table {
	width: 900px;
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
	
#btn{display:inline-block; margin-left:86%; margin-bottom: 20px;width:50px; height:50px; 
background: #645574; border-radius: 50%; border:none;}
#btn:hover{background-color: #5C1D75;}
#pro_btn{border:none; background:none; margin-left:5px; }
#pro_btn>a{color:#A360EB; font-weight:bold; font-size:13px}
.click{cursor:pointer}
.img{background-image:url()}
#selFrm{margin:5px 0px 10px 5px; width: 200px; display:inline-block;}
.searchFrm{display: inline-block ;margin:15px 10px 10px 0px;width: 400px;}
#searchText{width: 250px;height: 25px;border-radius: 10px;  border: 1px solid #aea3b3 ;}
#subSubmit{border-radius: 10px; width: 50px; height: 25px; border: none; background:#aea3b3 ;color: white;}
.pImg{width: 30px; height: 30px; border-radius: 50%; border: 1px solid #aea3b3; }
#nm{padding: 5px}
.p_td{display: inline-block; width: 40px}
#user_nm{display: inline-block;}
.material-icons{width: 100%;	color: #5C1D75;}
.highlight{font-weight: bold;  color:red}
select{ height:25px; border-radius: 10px ;padding: 3px }

</style>
</head>

<body>
	<div class="container">
		 <!--화면띄우기  /2개의 jsp파일이 필요하고/-->
		<p>${loginUser.nm}님 환영합니다👏👏&nbsp;&nbsp;<a id="logOut" href="/logout">로그아웃</a><button id="pro_btn"><a href="/profile"> 프로필 </a></button></p>

	
	
		<div>

		
		<button id="btn"><a  href="/board/regmod">글쓰기</a></button>
		<!-- /의 차이 :  처음부터 시작하고 싶다면 앞에 /를 붙여야하고 안붙인다면 제일 마지막에 있는 주소만 바뀜 
		     만약 안붙였다면 /board/board/reg-->
		<form id="selFrm"  method="get" action="/board/list" >
		<!-- param.page가 가능한 이유는? String pagedd=request.getParameter("page")와 같기 때문에 -->
		<input type="hidden" name="page" value="${page}"> <!--페이지수-->
		<input type="hidden" name="searchText" value="${param.searchText}"> 
		<input type="hidden" name="searchType" value="${param.searchType==null? 'a':param.searchType}"> 
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
			<th width="350px">제목</th>
			<th id="nm">작성자</th>
			<th>조회수</th>
			<th>좋아요</th>
			<th>댓글</th>
			<th width="38x">관심</th>
			<th width="200px">작성일</th>
		</tr>

		<c:forEach items="${list}" var="item">
		<tr class="click" onclick="detail(${item.i_board})">
       <!-- 내장객체에 담겨있는 것을 사용 EL식 -->
		<td>${item.i_board}</td>
		<td>${item.title}</td>
		<td>
		<div class="p_td">
		<c:choose>
		  		<c:when test="${item.profile_img !=null}">
		  			<img class="pImg" src="/img/user/${item.i_user}/${item.profile_img}">
		  		</c:when>
		  		<c:otherwise>
		  		 	<img class="pImg"  src="/img/default_profile.jpg">
		  		</c:otherwise>
	 	</c:choose>
		</div>
			<div id="user_nm">${item.nm}</div></td>
		<td>${item.hits}</td>
		<td>${item.cnt}</td>
		<td>${item.cntCmt}</td>
		<td>	
		       <c:if test="${item.yn_like==0}">
					<span class="material-icons" >favorite_border</span>
				</c:if>

				<c:if test="${item.yn_like==1}">
					<span class="material-icons">favorite</span>
				</c:if>
		</td>
		<td>${item.m_dt}</td>

		</tr>
		</c:forEach>
	</table>	
	
		
	
		<form action="/board/list" class="searchFrm" >		
		<select name="searchType" >
				<option value="a" ${searchType=='a' ? 'SELECTED':''}>제목</option>
				<option value="b" ${searchType=='b' ? 'SELECTED':''}>내용</option>
				<option value="c" ${searchType=='c' ? 'SELECTED':''}>제목+내용</option>
			</select>
			<input type="search" name="searchText"  id="searchText" value="${param.searchText==''? '':param.searchText}">
			<input type="submit" id="subSubmit" value="검색">
			
		</form>
		
	
	<div class="page">
	    <a href="/board/list?page=1&record_cnt=${param.record_cnt==null? 10:param.record_cnt}&searchText=${param.searchText}&searchType=${searchType}" class="ff">처음</a>
		<c:forEach begin="1"  end="${pagingCnt}" var="item">
		<c:choose>
		 <c:when test="${page == item}">
			<span id="pageColor">&nbsp; ${item}&nbsp;&nbsp;</span>
		 </c:when>
		 <c:otherwise>
			<a href="/board/list?page=${item}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&searchType=${searchType}">&nbsp;${item}&nbsp;&nbsp;</a> 
		 </c:otherwise>	
		</c:choose>	
		</c:forEach>
		<a href="/board/list?page=${pagingCnt}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&searchType=${searchType}" class="ff">끝</a>
	</div>
	</div>



</body>
<script>

function changeReCord(){
	selFrm.submit()
}
function changeType(){
	typeFrm.submit()
}

function detail(i_board, hits){
	location.href='/board/detail?i_board='+i_board+'&page=${page}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&searchType=${searchType}'
			                     // 키     = value
}




</script>

</html>