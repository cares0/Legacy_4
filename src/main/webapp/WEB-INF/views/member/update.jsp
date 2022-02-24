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
	<h1>Member Join Page</h1>
	
	
    <form action="./update" method="post">
        
		<h3>${mypage.id} 회원님 정보 수정</h3>

		<input type="hidden" name="id" value="${mypage.id}">
        <fieldset>
            <legend>이름 수정</legend>
            <input type="text" name="name" value="${mypage.name}">
        </fieldset>

        <fieldset>
            <legend>전화번호 수정</legend>
            <input type="text" name="phone" value="${mypage.phone}">
        </fieldset>

        <fieldset>
            <legend>이메일 수정</legend>
            <input type="text" name="email" value="${mypage.email}">
        </fieldset>
        
        <fieldset>
			<button type="submit">정보수정하기</button>
        </fieldset>
        

    </form>

	
</body>
</html>