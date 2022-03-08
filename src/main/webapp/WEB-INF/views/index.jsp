<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="./template/header_css.jsp"></c:import>
	<script type="text/javascript">
		
	</script>
	
</head>
<body>
	<c:import url="./template/header.jsp"></c:import>
	<h1>Index Page</h1>
	<c:if test="${not empty member}">
	<h3>${member.name}님 환영합니다.</h3>
	</c:if>
	
</body>
</html>