<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>계산기 01</h1>
<h3>기본 HTTP 통신 (동기식)</h3>
<hr>

<form action="/ajax/ajax_01_ok.jsp" method="post">
	<input type="text" name="num1" />
	<select name="oper">
		<option value="add">더하기</option>
		<option value="sub">빼기</option>
		<option value="mul">곱하기</option>
		<option value="div">나누기</option>
	</select>
	<input type="text" name="num2" />

	<input type="submit" value=" = " />
</form>

</body>
</html>









