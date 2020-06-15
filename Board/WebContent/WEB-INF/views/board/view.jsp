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

<h1>게시글 상세보기</h1>
<hr>

<table class="table table-bordered">
<tr>
<td class="info">글번호</td><td colspan="3">${viewBoard.boardno }</td>
</tr>

<tr>
<td class="info">제목</td><td colspan="3">${viewBoard.title }</td>
</tr>

<tr>
<td class="info">아이디</td><td>${userid }</td>
<td class="info">닉네임</td><td>${usernick }</td>
</tr>

<tr>
<td class="info">조회수</td><td>${viewBoard.hit }</td>
<td class="info">추천수</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="3">${viewBoard.writtendate }</td>
</tr>

<tr><td class="info"  colspan="4">본문</td></tr>

<tr><td colspan="4">${viewBoard.content }</td></tr>

</table>

<a href="/board/list">목록으로 돌아가기</a>
	
</div> <!-- div.container -->

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>