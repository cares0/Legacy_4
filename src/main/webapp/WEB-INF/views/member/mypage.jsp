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
		<img src="../resources/upload/member/${mypage.memberFileDTO.fileName}">
		<a href="./photoDown?fileNum=${mypage.memberFileDTO.fileNum}">${mypage.memberFileDTO.oriName}</a>
		<h3>${mypage.memberFileDTO.oriName}</h3>
	</div>
	
	<a href="./update?id=${mypage.id}">정보수정하기</a>
	
	
</body>
</html>