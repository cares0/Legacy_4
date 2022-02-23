<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="../template/header_css.jsp"></c:import>
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<h1>BankBook Add Page</h1>
	
	<form action="./add" method="POST">
		<h3>통장 이름 입력</h3>
		<div><input type="text" name="bookName"></div>
		<h3>통장 내용 입력</h3>
		<div><textarea name="bookContents" rows="10" cols="30"></textarea></div>
		<h3>통장 이율 입력</h3>
		<div><input type="text" name="bookRate"></div>
		<h3>판매 여부 입력</h3>
		<div>판매<input type="radio" name="bookSale" value="1"> 판매중지<input type="radio" name="bookSale" value="0"></div>
	
		<br>
		<button type="submit">등록하기</button>
	
	</form>
	
</body>
</html>