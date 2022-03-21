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
	<h1>${board} 등록 페이지</h1>

	<form action="./add" method="POST" id="frm" enctype="multipart/form-data">
		<h3>글 이름 입력</h3>
		<div><input type="text" name="title" id="title"></div>
		<h3>글 내용 입력</h3>
		<div><textarea name="contents" rows="10" cols="30" id="contents"></textarea></div>
		<h3>작성자 입력</h3>
		<div><input type="text" readonly="readonly" name="writer" value="${member.id}"></div>	
		<br>
	
		<div id="fileResult">
			<!--감싸고있는 부모div를 지워버리자!-->
			<div>
				<input type="file" name="files"> <button type="button">DEL</button>
			</div>
			<!-- <input type="file" name="files">
			<input type="file" name="files"> -->
		</div>
		<div>
			<button type="button" id="fileAdd">FileAdd</button>
			<button type="button" class="del">Sample DEL</button>
		</div>

		<br>
		
			<button type="button" id="btn">등록하기</button>
		
	</form>
	<br>
	<script src="../resources/js/add.js"></script>
	<script src="../resources/js/file.js"></script>
</body>
</html>