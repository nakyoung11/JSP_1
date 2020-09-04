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




.cmtbtn{
	 cursor: pointer; 
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



.material-icons {
   margin-left:30%;
	color: red;
	cursor: pointer
}

.material-icons:hover ~ #likeList{
	display: block;

}


.cmtlist{margin:5px}

td{padding: 5px}

#cmtCnt{  padding-left: 5px; 
    border-radius: 5px;
    margin: 10px 5px 5px 5px ; margin-bottom: 10px;width: 500px; height: 25px; outline: none; border:none}

#cmtMod{all: unset; padding-left: 15px; font-size: 13px;	cursor: pointer}
.cmtNm{display:inline-block;width: 80px; font-size: 13px; font-weight: bold; padding-top: 15px} 
.cmtCmt{font-size:15px}
.m_dt{padding-left:20px; font-size:15px}
#cmtMod, #cmtdel{color: rgb(150, 148, 146);}
#cmtdel{font-size: 13px}
#cmtMod:hover{color: rgb(252, 231, 41) ;}
#cmtdel:hover{color: rgb(182, 34, 14) ;}
.pImg{width: 50px; height: 50px; border-radius: 50%; border: 2px solid #aea3b3; margin-right:13px}
.c_pImg{width: 30px; height: 30px; border-radius: 50%; border: 2px solid #aea3b3; }
.highlight{font-style:italic;color:red}
.count {	
	font-size: 7px;
	cursor: pointer;

}
.count:hover + #likeList{
	display: block;

}
#likeList:hover{
	display: block;
}


#likeList{

background-color: #DDECFF;
border-radius: 30px;
position: absolute;
width: 150px;
height: 300px;
left:53%;
display:none;
padding:15px;

}

#userLike{display: flex; 
 align-items: center;}

#userLike img{width: 30px; height: 30px ;margin: 5px}

.like_nm{font-size: smaller;}

</style>
<body>
	<div class="container">
		<div class="cntcon">
			<div class="title">
				<h2>${data.title}<h2>
			</div>
			<div class="user">
				<c:choose>
		  			<c:when test="${data.profile_img !=null}">
		  				<img class="pImg" src="/img/user/${data.i_user}/${data.profile_img}">
		  			</c:when>
		  			<c:otherwise>
		  		 		<img class="pImg"  src="/img/default_profile.jpg">
		  			</c:otherwise>
	 			</c:choose>
				<span class="nm">${data.nm}</span>
				<span class="dt"> ${data==null? data.r_dt : data.m_dt}&nbsp;
					&nbsp; ${data.r_dt==data.m_dt? '' : '수정'}</span> <span class="hits">${data.hits}</span>
					
					
			<c:if test="${data.yn_like==0}">
					<span class="material-icons" onclick="like(0)">favorite_border</span>
				</c:if>

				<c:if test="${data.yn_like==1}">
					<span class="material-icons" onclick="like(1)">favorite</span>
				</c:if>
				
				
				<span class="count">종아요 ${data.like_cnt}</span>
				
				<div id="likeList">	
					<c:forEach items="${list}" var="item">
					<div id="userLike">
					<c:choose>
				  		<c:when test="${item.profile_img !=null}">
				  			<img class="pImg" src="/img/user/${item.i_user}/${item.profile_img}">
				  		</c:when>
				  		<c:otherwise>
				  		 	<img class="pImg"  src="/img/default_profile.jpg">
				  		</c:otherwise>
	 				</c:choose>
					
					
					
					 <span class="like_nm"> ${item.nm}</span></div>	
					</c:forEach>
					</div>
				</div>
		
			<hr>
			<div class="ctnt">${data.ctnt}</div>
			
				
			<div class="cmt">
		<span> </span> 
		<form id="cmtFrm" action="/board/cmt" method="post">
		<input type="hidden" name="i_cmt" value="0"><!--수정: i_cmt값주고,  -->
		                          <!-- 등록과 수정은 value의 값으로 구분(0등록 1수정 -->
		<input type="hidden" name="i_board" value="${data.i_board}">
			<div class="incmtlist">
				<input id="cmtCnt" type="text" name="cmt" placeholder="욕설이나 비방은 삼가해주세요" value=""><!-- 수정 내용 -->
				<input class="cmtbtn" id="cmtSubmit" type="submit" value="전송"> <!-- 수정으로 버튼 바꾸기  -->
			    <input class="cmtbtn" type="button" value="취소" onclick="clkCmtCancle()">
			 </div>
		</form>
			<div class="cmtlist">
			<table>
			<c:forEach items="${cmtList}" var="item">
			  <tr id="cmtTr">
				<td>
			 	<c:choose>
				  		<c:when test="${item.profile_img !=null}">
				  			<img class="c_pImg" src="/img/user/${item.i_user}/${item.profile_img}">
				  		</c:when>
				  		<c:otherwise>
				  		 	<img class="c_pImg"  src="/img/default_profile.jpg">
		  				 </c:otherwise>
	 	        </c:choose>
			     </td>
			      <td class="cmtNm">${item.nm}</td>
			     <td class="cmtCmt">${item.cmt}</td>
			     <td class="m_dt" width=150px>${item.r_dt==item.m_dt? item.r_dt:item.m_dt}</td>
			     <c:if test="${loginUser.i_user==item.i_user}">
			     <td width=60px><button id= "cmtMod" onclick="clkCmtMod(${item.i_cmt},'${item.cmt}')">수정</button></td>
			     <td width=40px><a id="cmtdel" onclick="clkDel(${item.i_cmt})">삭제</a></td>
	   
			     </c:if>
			     
		     </tr>
		   </c:forEach>  
		   </table>
		   
		   </div>
		</div>
		
		</div>
		
		
		<button id="btn1">
			<a href="/board/list?page=${param.page}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&searchType=${param.searchType}">목록</a>
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
		location.href='/board/toggleLike?page=${param.page}&record_cnt=${param.record_cnt}&searchType=${param.searchType}&searchText=${param.searchText}&i_board=${data.i_board}&yn_like=' + yn_like
    	}
    
        function submitDel() {

			
		}
		
		function clkCmtMod(i_cmt, cmt){
		 	console.log(i_cmt)
		 	console.log(cmt)
			cmtFrm.i_cmt.value=i_cmt
			cmtFrm.cmt.value= cmt
			cmtSubmit.value='수정'
		  
		    
		 	
		}
		
		function clkCmtCancle(){
			cmtFrm.i_cmt.value=0
			cmtFrm.cmt.value=''
			cmtSubmit.value='전송'
		}
		
		function clkDel(i_cmt){
			if(confirm('삭제 하겠습니까?')){
				location.href='/board/cmt?i_board=${data.i_board}&i_cmt='+i_cmt
			}
		}
		
		
	</script>
</body>
</html>