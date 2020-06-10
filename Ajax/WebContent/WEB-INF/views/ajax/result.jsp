<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br>


<table border="1">
	<tr>
		<th>아이디</th>
		<th>패스워드</th>
	</tr>

	<c:forEach var="m" items="${list }">

		<tr>
			<td>${m.id }</td>
			<td>${m.pw }</td>
		</tr>
	</c:forEach>
</table>