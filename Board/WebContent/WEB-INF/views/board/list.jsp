<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="web.dto.Board"%>

<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>
<div class="container">

	<h1>글 목록</h1>

<!-- 로그인상태 -->
<div style="text-align:right;">
	<c:if test="${login }">
		<strong>${usernick } 님</strong><br>
		<button onclick='location.href="/board/write";'>글쓰기</button>
	</c:if>
	
	<c:if test="${not login }">
		<strong>로그인이 필요합니다</strong><br>
		<button onclick='location.href="/member/login";'>로그인</button>
		<button onclick='location.href="/member/join";'>회원가입</button>
	</c:if>
</div>	
	<hr>

	<table class="table table-striped table-hover table-condensed">

		<tr class="success">
			<th style="width: 15%">글번호</th>
			<th style="width: 45%">제목</th>
			<th style="width: 15%">아이디</th>
			<th style="width: 10%">조회수</th>
			<th style="width: 15%">작성일</th>
		</tr>

		<c:forEach items="${BoardList }" var="b">
			<tr>
				<td>${b.boardno }</td>
				<td><a href="/board/view?boardno=${b.boardno }">${b.title }</a></td>
				<td>${b.id }</td>
				<!-- <td>${b.content }</td> -->
				<td>${b.hit }</td>
				<td>${b.writtendate }</td>
			</tr>
		</c:forEach>

	</table>
	
	<c:import url="/WEB-INF/views/layout/paging.jsp"></c:import>
	
</div> <!-- div.container -->

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>