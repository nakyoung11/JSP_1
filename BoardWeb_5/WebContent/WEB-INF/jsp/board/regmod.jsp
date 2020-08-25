<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data==null? '등록' : '수정'}</title>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>
*{
font-family: 'Nanum Gothic', sans-serif;
	outline: none;
}
body{background-color: rgb(247, 246, 245)}
.container{width: 500px; margin: 50px auto; padding: 20px; height: 400px; }
.title{margin-bottom: 10px; color:#4d4d4d; font-size:15px}
.title>input{width: 450px; height: 20px;}
.ctnt{margin-bottom: 20px; color:#4d4d4d}
textarea{width: 450px; height: 300px;margin-left: 39px; margin-bottom:15px; color:#4d4d4d}
#sub{float:right; margin-right: 5px; background: #645574; border-radius: 5px; border:none; width: 50px; 
     height: 25px; color:#F2EBF5; font-size:smaller}
#sub:hover{background-color: #5C1D75;}



</style>
</head>
<body>
<div class="container">

	<form id="frm" action="/board/regmod" method="post">
        <div class="title">제목 <input type="text" name="title" value="${data.title}"></div>
    	<input type="hidden" name="i_board" value="${data.i_board}">
        <textarea name="ctnt" placeholder="내용을 입력해주세요">${data.ctnt}</textarea>
		<input id="sub" type="submit" value="${data==null? '등록' : '수정'}">
	
	
	</form>

</div>

</body>
</html>