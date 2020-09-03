<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
<div>${msg}</div>
<c:if test="${isAuth==false||isAuth==null}"><!-- 이전비밀번호 확인 -->
	<div>
	<form action="/changePw" method="post">
		<input type="hidden" name="type" value="1">
		<div>
		    <label><input type="password" name="pw" placeholder="현재 비밀번호"></label>
		</div>
		<div>
			<input type="submit" value="확인">
		</div>
	</form>
	</div>

</c:if>

<c:if test="${isAuth==true}"><!--  비밀번호변경 -->
	<div>비밀번호 변경</div>
	<div>
		<form action="/changePw" method="post" onsubmit="retun pwChk()" id="pwFrm">
			<input type="hidden" name="type" value="2">
		<div>
		    <label><input type="password" name="pw" placeholder="변경할 비밀번호"></label>
		</div>
		<div>
		    <label><input type="password" name="repw" placeholder="변경할 비밀번호 확인"></label>
		</div>
		<div>
			<input type="submit" value="확인">
		</div>
	</form>
	</div>
</c:if>

<script >
function pwChk() {
	if (pwFrm.pw.value.length < 5) {
		alert("비밀번호는 5글자 이상이어야 합니다.")
		frm.pw.focus()
		return false
	} else if (pwFrm.pw.value != pwFrm.repw.value) {
		alert("비밀번호를 확인해주세요.")
		frm.repw.focus()
		return false

	}
}


</script>

</body>
</html>