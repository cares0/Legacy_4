<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="../template/header_css.jsp"></c:import>
	<link rel="stylesheet" type="text/css" href="../resources/css/table.css">
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	
	<br>
	<div class="notice_title">${board} List Page</div>
	<br>
	<table class="notice_table">
		<thead>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.num}</td>
					<td><a href="./detail?num=${dto.num}">
						<c:catch var="message">
							<c:forEach begin="1" end="${dto.depth}"> &nbsp; </c:forEach>
						</c:catch>
						<!-- forEach문법 상 조건문이 <=이기 때문에 , begin이 0이면, depth가 0이어도 1번은 반복을 함 -->
						<!-- 따라서 begin은 1이 되어야 함, 0일때 한번도 안돌아야 하기 때문 -->
						${dto.title}
						</a>
					</td>
					<td>${dto.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<br>
	<a href="./add">공지사항 등록</a>
	
</body>
</html>