<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<div class="container">

<h1>글쓰기 페이지</h1>
<hr>

<form action="/board/write" method="post">

제목 : <input type="text" name="title" id="title"> <br><br>
아이디: <input type="text" name="writer" id="writer" readonly value="${usernick }"> <br><br>
내용:<br> <textarea rows="5" cols="45" id="content" name="content"></textarea> <br><br>

<br><br>

첨부파일 #1 : <input type="file" name="file" id="file">
<br><br>


<button id="btnWrite">작성하기</button>
</form>

<br>
</div>
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>