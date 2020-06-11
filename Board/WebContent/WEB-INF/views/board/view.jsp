<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="web.dto.Board"%>

<%
	Board b = (Board) request.getAttribute("Board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<body>
	<div class="container">

		<h1>글 상세 페이지</h1>
		<hr>

		<table border="1">

			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>아이디</th>
				<th>본문</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>


			<tr>
				<td><%=b.getBoardno()%></td>
				<td><%=b.getTitle()%></td>
				<td><%=b.getId()%></td>
				<td><%=b.getContent()%></td>
				<td><%=b.getHit()%></td>
				<td><%=b.getWrittendate()%></td>
			</tr>


		</table>

		<a href="/board/list">목록으로 돌아가기</a>
	</div>
	<!-- div container -->
</body>
</html>