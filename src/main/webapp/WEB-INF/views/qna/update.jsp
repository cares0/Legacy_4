<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="../template/header_css.jsp"></c:import>
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<h1>질문 수정 페이지</h1>

	<form action="./update" method="POST">
		<input type="hidden" name="num" value="${detail.num}">
		<h3>질문 제목 수정</h3>
		<div><input type="text" name="title" value="${detail.title}"></div>
		<h3>질문 내용 수정</h3>
		<div><textarea name="contents" rows="10" cols="30">${detail.contents}</textarea></div>
		<br>
		<h3>작성자 정보 : ${detail.writer}</h3>
		<br>
		<button type="submit">수정하기</button>
	</form>

</body>
</html>