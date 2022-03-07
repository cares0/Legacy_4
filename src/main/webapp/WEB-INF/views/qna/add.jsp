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
	<h1>질문 등록 페이지</h1>

	<form action="./add" method="POST">
		<h3>질문 제목 입력</h3>
		<div><input type="text" name="title"></div>
		<h3>질문 내용 입력</h3>
		<div><textarea name="contents" rows="10" cols="30"></textarea></div>
		<h3>작성자 입력</h3>
		<div><input type="text" name="writer"></div>
		
		<br>
		<button type="submit">등록하기</button>
	
	</form>

</body>
</html>