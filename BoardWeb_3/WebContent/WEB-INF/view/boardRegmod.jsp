<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<div>글쓰기</div>
</head>
<body>
	<form id="frm" method="post" action="/boardWrite" onsubmit="return chk()">
	<div>
		<label>제목 <input type="text" name="title"></label>
	</div>
			
	<div>
		<label>내용 <textarea name="ctnt"></textarea></label>
	</div>
	<div>
		<label>작성자 <input type="text" name="i_student"></label>
	</div>
	<div>
		<input type="submit" value="글등록">
	</div>
	</form>
	
	<script>
	
	function chk() { //문제가 생겼을 때만 안날아 가게
		//console.log(`title:\${frm.title.value}`) // \! 가독성이 좋음 
		if (frm.title.value == "") {
			alert('제목을 입력해주세요.')
			frm.title.focus()
			return false
		} else if (frm.ctnt.value.length == 0) {
			alert('내용을 입력해주세요.')
			frm.ctnt.focus()
			return false
		} else if (frm.i_student.value == "") {
			alert('작성자를 입력해주세요.')
			frm.i_student.focus()
			return false
		}

	}
	
	</script>


</body>
</html>