<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
</head>
<style>
<style>
*{ 
font-family: 'Nanum Gothic', sans-serif;
	outline: none;
}
.container{width:600px; height:600px; margin: 50px auto ; }
h1{text-align: center;}
.frcont{background:#645574; padding:30px;width:550px; height:550px;border-radius: 50%}
form{margin:30px ;padding: 5px 5px 5px 90px}
label{display:inline-block; width:100px; height: 25px; margin:10px; color:#F2EBF5}
input{height: 25px ; border: none;background: #645574;  border-bottom: 1px solid rgb(239, 232, 241);}
#info{margin:10px; padding-left:155px;font-size:smaller}
#btn{display:inline-block; margin: 50px 20px 15px 24px;width:260px; height:35px;
background: #FFFFFF; border-radius: 20px; border:none}
.err{color: #e74c3c;}




</style>
<body>

	<div class="err">${msg}</div>
	
	<div class="container">
	
		<div class="frcont">
		<h1>회원가입</h1>
		<div id="info">회원가입을 위한 정보를 입력해주세요.</div>
			<form action="/join" method="post" id="frm" onsubmit="return chk()">
				<!-- 네임은 키값 -->
				
				<div>
					<label for="urid"> 아이디</label>
					<input type="text" name="user_id" placeholder="아이디(5글자 이하)" id="urid" required  value="${data.user_id}">
					
				</div>
				<div>
					<label for="urpw">비밀번호</label>
					<input type="password" name="user_pw" placeholder="비밀번호 (5글자 이상)" id="urid" required>
				</div>
				<div>
					 <label for="urpwr">비밀번호확인</label>
					<input type="password" name="user_pwre" placeholder="비밀번호확인" id="urpwr">
				</div>
				<div><label for="urnm">이름</label>
					<input type="text" name="nm" placeholder="이름(2글자 이상 5글자 미만)" required  value="${data.nm}">
				</div>
				<div><label for="uremail">이메일</label>
					<input type="email" name="email" placeholder="이메일" id="uremail"  value="${data.email}">
				</div>
				<div>
					<input type="submit" value="회원가입" id=btn>				
				</div>



			</form>

		</div>

	</div>

	<script>
		function chk() {
			if (frm.user_id.value.length < 5) {
				alert("아이디는 5글자 이상이어야 합니다.")
				frm.user_id.focus()
				return false
			} else if (frm.user_pw.value.length < 5) {
				alert("비밀번호는 5글자 이상이어야 합니다.")
				frm.user_pw.focus()
				return false
			} else if (frm.user_pw.value != frm.user_pwre.value) {
				alert("비밀번호를 확인해주세요.")
				frm.user_pw.focus()
				return false
			} else if (frm.nm.value.length > 2&&frm.nm.value.length<5) {
				const korean = /[^가-힣]/;
				//const result = korean.test(frm.nm.value)
				if (korean.test(frm.nm.value) {
					alert("이름을 다시 입력해주세요.(초성 입력X)")
					frm.nm.focus()
					return false
				}
			}
			if (frm.email.value.length > 0) { 
				const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i
				if (!email.test(frm.email.value)) {
					alert("이메일을 확인해 주세요.")
					frm.nm.focus()
					return false
				}
			} 

		}
	</script>
</body>
</html>