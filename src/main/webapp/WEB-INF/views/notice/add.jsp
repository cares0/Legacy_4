<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지사항 등록 페이지</h1>

	<form action="./add" method="POST">
		<h3>공지사항 이름 입력</h3>
		<div><input type="text" name="title"></div>
		<h3>공지사항 내용 입력</h3>
		<div><textarea name="contents" rows="10" cols="30"></textarea></div>
		<h3>작성자 입력</h3>
		<div><input type="text" name="writer"></div>
		
		<br>
		<button type="submit">등록하기</button>
	
	</form>

</body>
</html>