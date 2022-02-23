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
	<h1>My Page</h1>
	
	<div>
		<h3>${mypage.id}</h3>
		<h3>${mypage.name}</h3>
		<h3>${mypage.phone}</h3>
		<h3>${mypage.email}</h3>
	</div>
	
	
</body>
</html>