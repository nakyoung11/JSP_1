<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<style>
*{
	outline: none;
}
.container{width:500px; margin: 50px auto ;}
h1{text-align: center;}
.frcont{background:#645574; padding:10px}
form{margin:30px ;padding: 5px 5px 5px 55px}
label{display:inline-block; width:100px; height: 25px; margin:10px; color:#F2EBF5}
input{height: 25px ;border-bottonm:1px solid #000000;}
#info{margin:10px; padding-left:70px;font-size:smaller}
#btn{display:inline-block; margin: 50px 20px 20px 28px;width:250px; height:35px;
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
					<input type="text" name="user_id" placeholder="아이디" id="urid" required  value="${data.user_id}">
					
				</div>
				<div>
					<label for="urpw">비밀번호</label>
					<input type="password" name="user_pw" placeholder="비밀번호" id="urid" required>
				</div>
				<div>
					 <label for="urpwr">비밀번호확인</label>
					<input type="password" name="user_pwre" placeholder="비밀번호확인" id="urpwr">
				</div>
				<div><label for="urnm">이름</label>
					<input type="text" name="nm" placeholder="이름" required  value="${data.nm}">
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
			} else if (frm.nm.value.length > 2) {
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