<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<style>
*{
	outline: none;
}
.container{width:400px; height: 400px; margin: 80px auto ;background:#645574; 
border-radius: 50%;border:2px solid #645574 }
h1{text-align: center;}
.err{color: #e74c3c; font-weight:bold ; margin-left:80px; font-size:smaller}

input{height: 25px ;border:none; margin-left: 78px; margin-bottom:15px} 
form{margin:30px ;padding: 5px}
#btn{display:inline-block; margin: 30px 20px 0px 115px;width:100px; height:35px;
background: #FFFFFF; border-radius: 20px; border:none;}
a{text-decoration: none;display:inline-block; margin: 0px 0px 20px 165px; color:#F2EBF5}
body{background-color: rgb(247, 246, 245)}</style>


<body>

<div class="container">
<div class="pa">

</div>
<form action="/login" method="post" >
<h1>로그인</h1>
<div><input type="text" name= "user_id" id="urid" placeholder="아이디" value="${user_id}"></div>
<div><input type="password" name= "user_pw" id="urpw" placeholder="비밀번호"></div>
<div class="err">${msg}</div>

<div><input type="submit" value="로그인" id=btn></div>
</form>
<a href="/join">회원가입</a>
</div>
</body>
</html>