<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import = "board.*"%>
<!-- updateForm.jsp -->
<link rel="stylesheet" type="text/css" href="../style.css">
<script type = "text/javascript">
function editCheck(){
	if(f.subject.value == ""){
		alert("제목을 입력하세요 !")
		f.focus.subject
		return
	}
	if(f.content.value == ""){
		alert("내용을 입력하세요 !")
		f.focus.content
		return
	}
	if(f.passwd.value ==""){
		alert("비밀번호를 입력 하세요 !")
		f.focus.passwd
		return
	}
	document.f.submit()
}

</script>
<div align="center">
<h3>글수정</h3>
<form name="f" action = "update_board.do?num=${dto.num }" method = "post"  >
	<table border="1" width="500">
		<tr>
			<td bgcolor="yellow" align="center" width="20%">이름</td>
			<td width="80%"><input type="text" name = "writer" value = "${dto.writer }" readonly></td>
		</tr>
		<tr>
			<td bgcolor="yellow" align="center" width="20%">제목</td>
			<td width=80%><input type="text" name = "subject" value = "${dto.subject }" size = "50" >
		</tr>
		<tr>
		<td bgcolor = "yellow" align = "center" width = "20%" >Email</td>
		<td>
		<input type = "text"  name = "email" value = "${dto.email }" size="50" >
		</td>
		</tr>
		<tr>
		<td bgcolor = "yellow" align = "center" width = "20%" height = "50%">
		내용
		</td>
		<td>
		<textarea rows="10" cols="50" name = "content" value = "${dto.content }"></textarea>
		</td>
		</tr>
		<tr>
		<td bgcolor = "yellow" align = "center" width = "20%">
		비밀번호 </td>
		<td>
		<input type = "password" name = "passwd">
		</td>
		</tr>
		<tr>
		<td bgcolor = "yellow" colspan = "2" align = "center">
		<input type = "button" value = "글수정"  onclick = "location.href = 'javascript:editCheck()'">
		<input type = "reset" value = "다시작성">
		<input type = "button" value = "목록보기" onclick = "location.href = 'list_board.do'">
		</td>
		</tr>
	</table>
	</form>
</div>
