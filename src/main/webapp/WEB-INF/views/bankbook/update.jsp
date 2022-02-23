<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>BankBook Update Page</h1>
	
	<form action="./update" method="POST">
		<h3>${detail.bookNumber}번 통장 수정</h3>
		<input type="hidden" name="bookNumber" value="${detail.bookNumber}">
		<h3>통장 이름 수정</h3>
		<div><input type="text" name="bookName" value="${detail.bookName}"></div>
		<h3>통장 내용 수정</h3>
		<div><textarea name="bookContents" rows="10" cols="30">${detail.bookContents}</textarea></div>
		<h3>통장 이율 수정</h3>
		<div><input type="text" name="bookRate" value="${detail.bookRate}"></div>
		<h3>판매 여부 수정</h3>
		<div>판매<input type="radio" name="bookSale" value="1"> 판매중지<input type="radio" name="bookSale" value="0"></div>		
		
		<br>
		<button type="submit">등록하기</button>
	
	</form>
	
</body>
</html>