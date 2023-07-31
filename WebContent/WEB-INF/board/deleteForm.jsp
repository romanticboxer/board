<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- deleteForm.jsp -->
<link rel="stylesheet" type="text/css" href="../style.css">
<div align="center">
<b>글삭제</b>
<br><br>
	<form id="f" action="delete_board.do?num=${num}" method="post">
		<table border="1" width="400">
			<tr>
				<th bgcolor="yellow" align="center">비밀번호를 입력해 주세요.</th>
			</tr>
			<tr>
				<td align="center">비밀번호:<input type="password" name="passwd">
				</td>
			</tr>
			<tr>
				<td bgcolor="yellow" align="center"><input type="submit"
					value="글삭제"> &nbsp <input type="button" value="글목록"
					onclick="location.href = 'list_board.do'"></td>
			</tr>
		</table>
	</form>
</div>
