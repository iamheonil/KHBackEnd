<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/board/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		location.href = "/board/update?boardno=${viewBoard.boardno }";
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
		location.href = "/board/delete?boardno=${viewBoard.boardno }";
	});
	
});
</script>

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
<td class="info">아이디</td><td>${viewBoard.id }</td>
<td class="info">닉네임</td><td>${nick }</td>
</tr>

<tr>
<td class="info">조회수</td><td>${viewBoard.hit }</td>
<td class="info">추천수</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="3">${viewBoard.writtendate }</td>
</tr>

<tr><td class="info" colspan="4">본문</td></tr>

<tr><td colspan="4">${viewBoard.content }</td></tr>


<tr>
<td class="info">첨부 파일</td><td colspan="3"><a href="/upload/${boardFile.storedname }" download="${boardFile.originname }">${boardFile.originname }</a></td>
</tr>

</table>

<%-- <div>
<a href="/upload/${boardFile.storedname }"
 download="${boardFile.originname }">${boardFile.originname }</a>
</div> --%>

<div class="text-center">	
	<button id="btnList" class="btn btn-primary">목록</button>
	
	<c:if test="${userid eq viewBoard.id }">
		<button id="btnUpdate" class="btn btn-info">수정</button>
		<button id="btnDelete" class="btn btn-danger">삭제</button>
	</c:if>
</div>

</div>
<br>
<c:import url="/WEB-INF/views/layout/footer.jsp" />
