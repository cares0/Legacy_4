<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Member Login Page</h1>
	
	<div>
	
	<form action="./login" method="post">
		<fieldset>
			<legend>ID입력</legend>
			<input type="text" name="id" value="${cookie.remember.value}">
		</fieldset>
		
		<fieldset>
			<legend>PW입력</legend>
			<input type="password" name="pw">
		</fieldset>
		
		<fieldset>
			<legend>Remember me</legend>
			<input type="checkbox" name="remember" value="1">
		</fieldset>

		<fieldset>
			<button type="submit">Login</button>
		</fieldset>
	</form>
	
	</div>
</body>
</html>