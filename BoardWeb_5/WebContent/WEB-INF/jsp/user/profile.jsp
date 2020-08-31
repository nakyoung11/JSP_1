<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
*{
font-family: 'Nanum Gothic', sans-serif;
	outline: none;
}
body{width:100%}
img{width: 200px; height: 200px; border-radius: 50%; border:1px solid black }
.container{width:50%; margin: 30px auto;}
.profile{margin:5px auto}
</style>
<body>
  <div class="container">
  	<div class="profile">
  	<c:choose>
  		<c:when test="${data.profile_img !=null}">
  			<img src="/img/user/${loginUser.i_user}/${data.profile_img}">
  		</c:when>
  		<c:otherwise>
  		 	<img src="/img/default_profile.jpg">
  		</c:otherwise>
 	</c:choose>

  </div>
  <div>
  	<form action="/profile" method="post" enctype="multipart/form-data">
  		<div>                                <!--이미지 파일 올릴때 꼭 적기-->
  			<label>프로필 이미지 <input type="file" name="profil_img" accept="image/*"></label>
  			                                                        <!-- 이미지 파일만 보이기-->    
  			<input type="submit" value="업로드">		
  			
  		</div>
  	
  	</form>
  	
  	<div>ID:${data.user_id}</div>
  	<div>이름:${data.nm}</div>
  	<div>이메일:${data.email}</div>
  	<div>가입일시:${data.r_dt}</div>
  
  </div>
  
  </div>
  
</body>
</html>