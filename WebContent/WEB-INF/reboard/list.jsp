<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<title>글목록</title>
</head>
<body>
	<div align="center">
		<b>글 목 록</b>
		<table width="500">
			<tr bgcolor="yellow">
				<td align="right"><a href="write_board.re"> 글쓰기 </a></td>
			</tr>
		</table>
		<table border="1" width="500">
			<tr bgcolor="green">
				<th align="center">번호</th>
				<th align="center" width="30%">제목</th>
				<th align="center">작성자</th>
				<th align="center" width="20%">작성일</th>
				<th align="center">조회</th>
				<th align="center" width="10%">IP</th>
			</tr>
			<c:if test="${listBoard == null }">
				<tr>
					<td colspan="6">게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="dto" items="${listBoard }">
				<tr>
					<td>${dto.num}</td>
					<td><c:if test="${dto.re_level > 0 }">
							<img src="img/level.gif" width="${dto.re_level*10 }">
							<img src="img/re.gif">
						</c:if> <a href="content_reboard.re?num=${dto.num }">${dto.subject }</a> 
					 <c:if test="${dto.readcount >10 }">
							<img src="img/hot.gif">
						</c:if> 
 </td>
					<td>${dto.writer }</td>
					<td>${dto.reg_date }</td>
					<td>${dto.readcount }</td>
					<td>${dto.ip }</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test = "${startPage > pageBlock }">
		[<a href="list_reboard.re?pageNum=${startPage - pageBlock }">이전</a>]
	</c:if>
		<c:forEach var = "i" begin = "${startPage }" end = "${endPage }">
		[<a href="list_reboard.re?pageNum=${i }">${i }</a>]</c:forEach>
		<c:if test = "${endPage < pageCount }">
		[<a href="list_reboard.re?pageNum=${startPage + pageBlock }">다음</a>]
		</c:if>
		
	</div>
</body>
</html>
