<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<style>



</style>
<body>
	<div>
	<!-- 값입력을 받기 위한 것 
	 값입력과 처리를 나누는게 소스관리가 편하기 때문에 한파일에 작성하기 보다는 파일을 나눠서 함-->
		<form action="/jsp/boardWriteProc.jsp" method="post"><!-- 주소창에 액션만 나타나 -->
			<div>
				<label>제목 <input type="text" name="title"></label>
			</div>
			<!-- name은 키값  id class는 안됨 
			id는 유일한 값  1파일에 1개만 ! 유니크! 
			class는 그룹 여러개 넣는 것도 가능 class="bb jj oo" -->
			<div>
				<label>내용 <input type="text" name="ctnt"></label>
			</div>
			<div>
				<label>작성자 <input type="text" name="i_student"></label>
			</div>
			<div>
				<input type="submit" value="글등록">
			</div>
		</form>

	</div>

</body>
</html>