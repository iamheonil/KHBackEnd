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
	<div class="text-center">
		<ul class="pagination">
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
				<li><a href="/board/list?curPage=${i }">${i }</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
<!-- div.container -->

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>