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
	<h1>${board} 답글 페이지</h1>

	<form action="./reply" method="POST">
		<input type="hidden" value="${dto.num}" name="num">
		<!-- 부모글의 num이 들어가 있는 dto을 받아서 또 넘겨주어야 함 -->
		<h3>답글 제목 입력</h3>
		<div><input type="text" name="title"></div>
		<h3>답글 내용 입력</h3>
		<div><textarea name="contents" rows="10" cols="30"></textarea></div>
		<h3>작성자 입력</h3>
		<div><input type="text" name="writer"></div>
		
		<br>
		<button type="submit">답글달기</button>
	
	</form>

</body>
</html>