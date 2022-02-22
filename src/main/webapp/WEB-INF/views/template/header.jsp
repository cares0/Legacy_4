<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!--Header 시작-->
	<header class="header">
		<nav class="header_nav_main">
			<ul>
				<li><a href="/s1/">HOME</a></li>
				<li><a href="/s1/notice/list">Notice</a></li>
				<li><a href="/s1/qna/list">QnA</a></li>
				<li><a href="/s1/bankbook/list">Product</a></li>
			</ul>
		</nav>

		<nav class="header_nav_sub">
			<ul>
				<c:choose>
					<c:when test="${empty member}"> 
					<li><a href="./member/login">Login</a></li>
					<li><a href="./member/join">Join</a></li>
					</c:when>
				 	<c:otherwise>
					<li><a href="./member/mypage">MyPage</a></li>
					<li><a href="./member/logout">Logout</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</header>
    <!--Header 끝-->