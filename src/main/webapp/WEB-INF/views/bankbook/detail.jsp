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
	<h1>BankBook Detail Page</h1>
	<table>
		<thead>
			<tr>
				<th>Number</th>
				<th>Name</th>
				<th>Contents</th>
				<th>Rate</th>
				<th>Sale</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${detail.bookNumber}</td>
				<td>${detail.bookName}</td>
				<td>${detail.bookContents}</td>
				<td>${detail.bookRate}</td>
				<td>${detail.bookSale}</td>
			</tr>
		</tbody>
	</table>
	
	<br>
	<a href="./list">통장 목록 가기</a>
	<a href="./update?bookNumber=${detail.bookNumber}">통장 수정하기</a>
	<a href="./delete?bookNumber=${detail.bookNumber}">통장 지우기</a>
	
	
</body>
</html>