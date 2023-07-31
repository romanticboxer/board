<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- content.jsp -->
<link rel="stylesheet" type="text/css" href="../style.css">
<div align="center">
	<h4>글내용 보기</h4>
	<table border="1" width="500" height="300">
		<tr>
			<td bgcolor="yellow" align="center" width="25%">글번호</td>
			<td align="center" width="25%">${dto.num }</td>
			<td bgcolor="yellow" align="center" width="25%">조회수</td>
			<td width="25%" align="center">${dto.readcount }</td>
		</tr>
		<tr>
			<td bgcolor="yellow" align="center" width="25%">작성자</td>
			<td align="center" width=25%>${dto.writer}</td>
			<td bgcolor="yellow" align="center" width="25%">작성일</td>
			<td align="center" width="25%">${dto.reg_date}</td>
		</tr>
		<tr>
			<td width="25%" align="center" bgcolor="yellow">글제목</td>
			<td colspan="3" align="center">${dto.subject }</td>
		</tr>
		<tr height="25%">
			<td bgcolor="yellow" align="center" width="25%">글 내용</td>
			<td colspan="3" align="center">${dto.content }</td>
		</tr>
		<tr>
			<td colspan="4" bgcolor="yellow" align="right" height="20%">
			&nbsp &nbsp &nbsp &nbsp
			<input type="button" value="글수정" onclick="location.href = 'update_board.do?num=${dto.num}'">
			&nbsp &nbsp &nbsp &nbsp
				<input type="button" value="글삭제"
				onclick="location.href = 'delete_board.do?num=${dto.num}'"> 
				&nbsp &nbsp &nbsp &nbsp<input
				type="button" value="글목록" onclick="location.href = 'list_board.do'">
	</table>
</div>
