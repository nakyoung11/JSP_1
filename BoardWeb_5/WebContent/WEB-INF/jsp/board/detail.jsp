<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>상세글</title>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<style>
body {
	background-color: rgb(247, 246, 245)
}

* {
	font-family: 'Nanum Gothic', sans-serif;
	outline: none;
}

.incmtlist{margin-top: 10px;}
#in{
    padding-left: 5px;
    border-radius: 5px;
    margin: 10px 5px 5px 5px ; width: 500px; height: 25px; outline: none; border:none}

#cmtbtn{
    
outline: none; border:none ;background: rgb(255, 242, 129); width: 35px; height: 25px; border-radius: 10%;
font-size: 11px;}

.container {
	width: 800px;
	height: 800px;
	margin: 50px auto;
	background: rgb(247, 246, 245);
	padding: 10px;
}

.cntcon {
	width: 700px;
	padding: 20px;
	margin: 20px;
	background: #CED5F5;
	border-radius: 5px;
}

.hits, .dt {
	margin-left: 15px;
	font-size: smaller
}

hr {
	border: 1px solid #4d4d4d;
}

.nm {
	font-weight: bold;
}

.ctnt {
	margin-top: 20px;
	margin-bottom: 30px;
	padding: 10px;
}

#btn2 {
	display: inline-block;
	margin-left: 68%;
	wdith: 35px;
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

#btn1 {
	margin-left: 3%;
	display: inline-block;
}

button:hover {
	background-color: #5C1D75;
}

a {
	text-decoration: none;
	color: #F2EBF5;
	width: 20px;
	font-size: smaller
}

#delFrm {
	display: inline-block;
}

.count {
	
	font-size: 7px;

}

.material-icons {
   margin-left:46%;
	color: red;
	cursor: pointer
}
.cmtlist{margin-top:15px}


#cmtMod{all: unset; padding: 15px; font-size: 13px}
.cmtNm{width: 55px; font-size: 13px; padding-left: 10px; font-weight: bold;}
.cmtCmt{font-size:15px}
.m_dt{padding-left:20px; font-size:15px}
#cmtMod, #cmtdel{color: rgb(150, 148, 146);}
#cmtdel{font-size: 13px}
#cmtMod:hover{color: rgb(252, 231, 41) ;}
#cmtdel:hover{color: rgb(182, 34, 14) ;}


</style>
<body>
	<div class="container">
		<div class="cntcon">
			<div class="title">
				<h2>${data.title}<h2>
			</div>
			<div class="user">
				<span class="nm">${data.nm}</span>
				<span class="dt"> ${data==null? data.r_dt : data.m_dt}&nbsp;
					&nbsp; ${data.r_dt==data.m_dt? '' : '수정'}</span> <span class="hits">${data.hits}</span>
					
					
			<c:if test="${data.yn_like==0}">
					<span class="material-icons" onclick="like(0)">favorite_border</span>
				</c:if>

				<c:if test="${data.yn_like==1}">
					<span class="material-icons" onclick="like(1)">favorite</span>
				</c:if>
				<span class="count">종아요 ${data.count}</span> 


			</div>
			<hr>
			<div class="ctnt">${data.ctnt}</div>
			
			<div class="cmt">
		
		<form id="cmtFrm" action="/board/cmt" method="post">
		<input type="hidden" name="i_cmt" value="0">
		                          <!-- 등록과 수정은 value의 값으로 구분(0등록 1수정 -->
		<input type="hidden" name="i_board" value="${data.i_board}">
			<div class="incmtlist">
				<input id="in" type="text" name="cmt" placeholder="욕설이나 비방은 삼가해주세요">
				<input id="cmtbtn" type="submit" value="전송">
			</div>
		</form>
			<div class="cmtlist">
			<table>
			<c:forEach items="${cmtList}" var="item">
			  <tr>
			     <td class="cmtNm">${item.nm}</td>
			     <td class="cmtCmt">${item.cmt}</td>
			     <td class="m_dt" width=150px>${item.m_dt}</td>
			     <c:if test="${loginUser.i_user==item.i_user}">
			     <td width=60px><button id= "cmtMod"onclick="cmtMod(${item.i_cmt})">수정</button></td>
			     <td width=40px><a id="cmtdel" href="/board/cmt?i_board=${data.i_board}&i_cmt=${item.i_cmt}">삭제</a></td>
			     </c:if>
			     
		     </tr>
		   </c:forEach>  
		   </table>
		   
		   </div>
		</div>
		
		</div>
		
		<button id="btn1">
			<a href="/board/list">목록</a>
		</button>



		<c:if test="${loginUser.i_user==data.i_user}">
			<button id="btn2">
				<a href="/board/regmod?i_board=${data.i_board}">수정</a>
				<!-- 쿼리스트링: 값을 보내는 역할 -->
			</button>
			<form id="delFrm" action="/board/del" method="post">
				<input type="hidden" name="i_board" value="${data.i_board}">
				<button id="btn3">
					<a href=# onclick="submiDel()">삭제</a>
				</button>
			</form>

		</c:if>




	</div>
	<script>
		function submiDel() {
			delFrm.submit()
		}

		function like(yn_like) {
			location.href = "/board/toggleLike?i_board=${data.i_board}&yn_like="
					+ yn_like
		}
		
		function cmtMod(i_cmt){
		 	var modInput = document.createElement('input')
		 	cmtMod.setAttribute()
		 	
		}
		
		
	</script>
</body>
</html>