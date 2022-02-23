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
	
	
    <form action="./join" method="post">
        
        <fieldset>
            <legend>아이디 입력</legend>
            <input type="text" name="id" >
        </fieldset>

        <fieldset>
            <legend>비밀번호 입력</legend>
            <input type="password" name="pw">
        </fieldset>
        
        <fieldset>
            <legend>이름 입력</legend>
            <input type="text" name="name">
        </fieldset>

        <fieldset>
            <legend>전화번호 입력</legend>
            <input type="text" name="phone">
        </fieldset>

        <fieldset>
            <legend>이메일 입력</legend>
            <input type="text" name="email">
        </fieldset>
        
        <fieldset>
			<button type="submit">회원가입하기</button>
        </fieldset>
        

    </form>
	
	
</body>
</html>