<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="../template/header_css.jsp"></c:import>
	<link rel="stylesheet" type="text/css" href="../resources/css/table.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/list.css">
	
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	
	<div class="table_container">
		<h1 class="bankbook_title">BankBook List Page</h1>
	
		<!-- 검색 창 -->
		<div class="search_container">
			<form action="./list" method="get">
				<fieldset>
					<select name="kind">
						<option value="col1">제목</option>
						<option value="col2">내용</option>
						<option value="col3">작성자</option>
					</select>
					<input type="text" name="search" value="${pager.search}">
					<button type="submit">검색</button>
				</fieldset>
			</form>
		</div>


		<table class="bankbook_table">
			<thead>
				<tr>
					<th>Num</th>
					<th>Name</th>
					<th>Rate</th>
					<th>Sale</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.bookNumber}</td>
						<td><a href="./detail?bookNumber=${dto.bookNumber}">${dto.bookName}</a></td>
						<td>${dto.bookRate}</td>
						<c:if test="${dto.bookSale!=0}">
						<td>판매중</td>
						</c:if>
						<c:if test="${dto.bookSale==0}">
						<td>사용불가</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class = "notice_block">
			<c:if test="${pager.pre}">
				<a href="./list?page=${pager.startNum-1}">PREVIEW</a>
			</c:if>
		<!-- 제일 작은 수에 -1 -->
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i" >
				<a href="./list?page=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a>
			</c:forEach>
				
			<c:if test="${pager.next}">
				<a href="./list?page=${pager.lastNum+1}">NEXT</a>
			</c:if>
				<!-- 제일 큰 수에 +1 -->
		</div>
		<br>

		<a href="./add">통장 추가</a>
	</div>
</body>
</html>