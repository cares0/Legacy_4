<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>글내용</th>
				<th>작성자</th>
				<th>작성일자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${detail.num}</td>
				<td>${detail.title}</td>
				<td>${detail.contents}</td>
				<td>${detail.writer}</td>
				<td>${detail.regDate}</td>
				<td>${detail.hit}</td>
			</tr>
		</tbody>
	</table>
	
	<br>
	<a href="./list">목록 보기</a>
</body>
</html>