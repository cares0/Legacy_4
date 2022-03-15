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
	<h1>${board} detail page</h1>
	<table>
		<thead>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>글내용</th>
				<th>작성자</th>
				<th>작성일자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${detail.num}</td>
				<td>${detail.title}</td>
				<td>${detail.contents}</td>
				<td>${detail.writer}</td>
				<td>${detail.regDate}</td>
				<td>${detail.hit}</td>
			</tr>
		</tbody>
	</table>
	<div>
		<c:forEach items="${detail.fileDTOs}" var="f" >
			<a href="../resources/upload/${board}/${f.fileName}">${f.oriName}</a>
			<a href="./fileDown?fileNum=${f.fileNum}">${f.oriName}</a>
		</c:forEach>
	</div>
	
	<br>
	<a href="./list">목록 보기</a>
	<a href="./delete?num=${detail.num}">글 삭제</a>
	<a href="./update?num=${detail.num}">글 수정</a>
	<c:if test="${board!='notice'}">
		<a href="./reply?num=${detail.num}">답글 달기</a>
	</c:if>
	<!-- 부모글의 NUM을 보내서 부모글의 REF, STEP, DEPTH를 참고하게 만들자 -->
</body>
</html>