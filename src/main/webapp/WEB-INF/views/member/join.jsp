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
	
	
    <form action="./join" method="post" id="frm">
        
        <fieldset>
            <legend>아이디 입력</legend>
            <input type="text" name="id" id="id">
            <div id="idResult"></div>
        </fieldset>

        <fieldset>
            <legend>비밀번호 입력</legend>
            <input type="password" name="pw" id="pw" placeholder="8글자 이상 12글자 이하로 작성하세요">
            <div id="pwResult"></div>
        </fieldset>

        <fieldset>
            <legend>비밀번호 확인</legend>
            <input type="password" name="pw2" id="pw2" placeholder="8글자 이상 12글자 이하로 작성하세요">
            <div id="pw2Result"></div>
        </fieldset>
        
        <fieldset>
            <legend>이름 입력</legend>
            <input type="text" id="name" name="name">
        </fieldset>

        <fieldset>
            <legend>전화번호 입력</legend>
            <input type="text" id="phone" name="phone">
        </fieldset>

        <fieldset>
            <legend>이메일 입력</legend>
            <input type="text" id="email" name="email">
        </fieldset>
        
        <fieldset>
			<button type="button" id="btn">회원가입하기</button>
        </fieldset>
        

    </form>
	<script src="../resources/js/join2.js"></script>
	
</body>
</html>