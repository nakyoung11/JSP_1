<!-- 고객으로 부터 값을 받음  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<%
	String msg = "";
String err = request.getParameter("err");
if (err != null) {
	switch (err) {
		case "10" :
	msg = "글을 등록 할 수 없습니다.";
	break;
		case "20" :
	msg = "DB에러 발생";
	break;
	}
}
%>

<style> /*가장 위에 써요~ 밑에 쓰면 번쩍임 현상이 나타나  자바스크립도 선언은 위에 */
#msg {
	color: red
}
</style>
<body>
	<div>
		<!-- 값입력을 받기 위한 것 
	 값입력과 처리를 나누는게 소스관리가 편하기 때문에 한파일에 작성하기 보다는 파일을 나눠서 함-->
		<form action="/jsp/boardWriteProc.jsp" method="post" id="frm"
			onsubmit="return ch k()">
			<!-- 주소창에 액션만 나타나 -->
			<!-- on으로 시작하는 것은 다 이벤트! 이벤트란 내가 동작하는 것 ! ~~ 할때! onsubmit 에서"return false"하면 못날아가~
             값이 없거나 참이면 날아가요~-->
			<div id=msg><%=msg%></div>
			<div>
				<label>제목 <input type="text" name="title"></label>
			</div>
			<!-- name은 키값  id class는 안됨 
			id는 유일한 값  1파일에 1개만 ! 유니크! 
			class는 그룹 여러개 넣는 것도 가능 class="bb jj oo" -->
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
	// 제일 밑에 적여요~
	
/* 	function eleValid(ele, nm){
		if(ele.value.length==0){
			alert(nm+'을(를) 입력해주세요.')
			ele.focus()
			return true;
		}
	}
	
	function chk(){
		if (eleValid(frm.title,'제목')){
			return false;
		}else if (eleValid(frm.ctnt,'내용')){
			return false;
		}else if (eleValid(frm.i_studuent,'작성자')){
			return false;	
	    }
	  }
	 */
	
	function chk(){ //문제가 생겼을 때만 안날아 가게
		//console.log(`title:\${frm.title.value}`) // \! 가독성이 좋음 
		if(frm.title.value==""){
			alert('제목을 입력해주세요.')
			frm.title.focus()
			return false
		}else if(frm.ctnt.value.length==0){
			alert('내용을 입력해주세요.')
			frm.ctnt.focus()
			return false
		}else if(frm.i_student.value==""){
			alert('작성자를 입력해주세요.')
			frm.i_student.focus()
			return false
		}
		
	} 
	</script>



	</div>

</body>
</html>