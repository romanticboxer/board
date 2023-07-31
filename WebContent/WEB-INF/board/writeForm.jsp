<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- writeForm.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>글쓰기</title>
</head>
<body>
<script type = "text/javascript">
function check(){
	if(f.writer.value==""){
		alert("작성자를 입력해 주세요")
		f.writer.focus()
	}
	if(f.subject.value==""){
		alert("제목을 입력해 주세요")
		f.subject.focus()
	}
	if(f.content.value==""){
		alert("내용을 입력해 주세요")
		f.content.focus()
	}
	if(f.passwd.value==""){
		alert("비밀번호를 입력해 주세요")
		return false
	}
	return true
}

</script>
	<div align="center">
		<form name="f" action="write_board.do" method="post" onsubmit = "return check()">
			<table border="1" width="40%">
				<tr>
					<td colspan="2" bgcolor="yellow" align="center" width=600>글쓰기</td>
				</tr>
				<tr>
					<td bgcolor="yellow" align="center">이름</td>
					<td width=80%>
					
					<input type="text" name="writer"  ></td>
				</tr>
				<tr>
					<td bgcolor="yellow" align="center">제목</td>
					<td width=80%><input type="text" style="width: 500;"
						name="subject"></td>
				</tr>
				<tr>
					<td bgcolor="yellow" align="center">Email</td>
					<td><input type="text" style="width: 500" name="email"></td>
				</tr>
				<tr>
					<td height="300" width=20% bgcolor="yellow" align="center">내용</td>
					<td height="300"><textarea cols="55" rows="18" name="content">
</textarea>
				</tr>
				<tr>
					<td width="20%" bgcolor="yellow" align="center">비밀번호</td>
					<td><input type="password" name="passwd"></td>
				</tr>
				<tr>
					<td colspan="2" bgcolor="yellow" align="center">
					<input
						type="submit" value="글쓰기"> <input type="reset"
						value="다시작성"> <input type="button"
						onclick="location.href = 'list_board.do'" value="목록보기"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
