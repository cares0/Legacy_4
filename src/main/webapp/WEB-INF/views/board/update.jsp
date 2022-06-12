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
	<h1>공지사항 수정 페이지</h1>

	<form action="./update" method="POST">
		<input type="hidden" name="num" value="${detail.num}">
		<h3>공지사항 이름 수정</h3>
		<div><input type="text" name="title" value="${detail.title}"></div>
		<h3>공지사항 내용 수정</h3>
		<div><textarea name="contents" rows="10" cols="30">${detail.contents}</textarea></div>
		<br>
		<h3>작성자 정보 : ${detail.writer}</h3>
		<br>
		<!-- 첨부파일이 파라미터로 넘어가야되는데, DB의 fileNum도 넘어가야됨 -->
		<!-- 새로 올릴 파일 자체는 전송이 됨, 근데 누구를 수정할 것이냐를 위해 fileNum을 넘겨야 함 -->
<%-- 		<div>
		<c:forEach items="${detail.fileDTOs}" var="f">
			<input type="file" name="file" value="${f.fileName}">
			<input type="hidden" name="fileNum" value="${f.fileNum}">
		</c:forEach>
		</div> --%>
		<!-- 근데 수정을 안하면 fileNum을 보내면 안됨 -->
		<!-- 그래서 나중에 JS로 수정 안한 파일태그는 hidden태그를 지우던지 해야함 -->
		<!-- 굉장히 복잡해버리기 때문에 그냥 방식을 바꿔버림 -->
		<!-- 수정의 개념을 기존파일 제거 -> 새로운 파일 새로 삽입 으로 해버리는 것임 -->
		<div id="files">
		<c:forEach items="${detail.fileDTOs}" var="f">
			<div>
				${f.oriName} <button class="fileDeleteBtn" data-fileNum="${f.fileNum}" type="button">X</button>
			</div>
		</c:forEach>
		</div>
		
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
		</div>
		
		<button type="submit">수정하기</button>
		


		<script type="text/javascript" src="../resources/js/file.js"></script>
	</form>

</body>
</html>