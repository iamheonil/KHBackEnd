<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>계산이 응답 페이지</h1>
<hr>

<c:set var="n1" value="${param.num1 }" />
<c:set var="n2" value="${param.num2 }" />
<c:set var="op" value="${param.oper }" />

<c:choose>
	<c:when test="${op eq 'add' }">
		${n1 } + ${n2 } = ${n1 + n2 }<br>
	</c:when>
	<c:when test="${op eq 'sub' }">
		${n1 } - ${n2 } = ${n1 - n2 }<br>
	</c:when>
	<c:when test="${op eq 'mul' }">
		${n1 } * ${n2 } = ${n1 * n2 }<br>
	</c:when>
	<c:when test="${op eq 'div' }">
		${n1 } / ${n2 } = ${n1 / n2 }<br>
	</c:when>
</c:choose>

<hr>
<h3><a href="/ajax/ajax_01.jsp">입력 폼으로</a></h3>

</body>
</html>












