<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data==null? '글등록' : '글수정'}</title>
<style>
.err{color: #e74c3c;}
</style>
</head>
<body>
<div>${data==null? '글등록' : '글수정'}</div>
<!-- 글 수정에서는 i_board가 필요해 ! -->
<div class="err">${msg }</div>
	<form id="frm" method="post" action="/${data==null? 'boardWrite':'boardMod'}" onsubmit="return chk()">
	<input type="hidden" name="i_board" value="${data.i_board}">
		<div>
			<label>제목 <input type="text" name="title" value="${data.title}"></label>
		</div>
				
		<div>
			<label>내용 <textarea name="ctnt" >${data.ctnt}</textarea></label>
		</div>
		<div>
			<label>작성자 <input type="text" name="i_student"  value="${data.i_student}" ${data==null ? '':'readonly'}></label>
		</div>
		<div>
			<input type="submit" value="${data==null? '글등록' : '글수정'}">
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