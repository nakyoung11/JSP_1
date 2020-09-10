<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<link rel="stylesheet" type="text/css" href="/res/css/common.css">
<body>
	<div id="container">
		<header>
			
			<h1 class="hidden">MatZip</h1>
			<nav id=menu>
			<a href="restarant/reg" class="round">등록</a>
			<a href="/user/favorite" class="round">찜</a>
			
			
			</nav>
			<div id="userinfo">
				<div class="containerPImg">
					<c:choose>
						<c:when test="${loiginUser.profile_img !=null}">
							<img class="pImg"
								src="/res/img/user/${loiginUser.i_user}/${loiginUser.profile_img}">
						</c:when>
						<c:otherwise>
							<img class="pImg" src="/res/img/default_profile.jpg">
						</c:otherwise>
					</c:choose>
				</div>
				<div id="userNm">${loginUser.nm}님 환영합니다.</div>
				<div id="logOut">
					<a href="/user/logout">로그아웃</a>
				</div>
			</div>
		</header>
		<section>
			<jsp:include page="/WEB-INF/view/${view}.jsp"></jsp:include>
		</section>
		<footer> 회사정보 </footer>
	</div>

</body>
</html>