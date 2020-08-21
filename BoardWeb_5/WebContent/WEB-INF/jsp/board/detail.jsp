<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	background-color: rgb(247, 246, 245)
}
*{
	outline: none;
}

.container {
	width: 600px;
	margin: 50px auto;
	background: #CCCEE4;
	padding: 10px
}

.hits, .dt {
	margin-left: 20px;
	font-size: smaller
}

hr {
	border: 1px solid #4d4d4d;
}

.ctnt {
	margin-top: 20px;
}

.btn {
	margin-left: 46%; wdith:30px; display:inline-block;
}


button {
	display: inline-block;
	width: 50px;
	height: 50px;
	background: #645574;
	border-radius: 50%;
	border: none;
	margin: 5px
}

#btn1{margin-left:17%}
button:hover{background-color: #5C1D75;}

a {
	text-decoration: none;
	color: #F2EBF5; width:20px;
	font-size: smaller
}
#delFrm{display: inline-block;}
</style>


<meta charset="UTF-8">
<title>상세글</title>
</head>
<body>
	<div class="container">
		<div class="title">
			<h3>${data.title}<h3>
		</div>
		<div class="user">
			<span class="nm">${data.nm}</span> 
			
			<span class="dt"> ${data==null? data.r_dt : data.m_dt}&nbsp;  &nbsp; ${data.r_dt==data.m_dt? '' : '수정'}</span>		
            
		
			<span class="hits">${data.hits}</span>
			
			
		</div>
		<hr>
		<div class="ctnt">${data.ctnt}</div>

	</div>
		<button id="btn1"><a href="/board/list">목록</a></button>	<div class=btn>
	

		<c:if test="${loginUser.i_user==data.i_user}">
			<button id="btn2">
				<a href="/board/regmod?i_board=${data.i_board}">수정</a>
			           <!-- 쿼리스트링: 값을 보내는 역할 -->
			</button>
			<form id="delFrm" action="/board/del" method="post">
				<input type="hidden" name="i_board" value="${data.i_board}">
				<button id="btn2"><a href=# onclick="submiDel()">삭제</a></button>
			</form>	
		
		</c:if>	
	
	

   </div>
	<script>
	 function submiDel(){
		 delFrm.submit()
	 }
	</script>
</body>
</html>