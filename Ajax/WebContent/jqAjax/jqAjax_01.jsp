<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$("#btnCalc").click(function() {

			console.log("btn Click!")

			// 전달 파라미터 얻어오기
			var n1 = $("#num1").val();
			var n2 = $("#num2").val();
			var op = $("#oper").val();

			// console.log(n1 + ", " + n2 + ", " + op);

			// - - - - - 요청 URL 변수 지정
			var url = "/jqAjax/jqAjax_01_ok.jsp";

			// - - - - - 요청 파라미터
			// 1. JSON Object (PlainObject)
			var data = {
				"num1" : n1,
				"num2" : n2,
				"oper" : op

			};
			// 2. String
			var query = "num1=" + n1 + "&num2=" + n2 + "&oper=" + op;

			// - - - - - Ajax 요청하기
			// $("#resultLayout").load(url, data); // POST 요청
			// $(#resultLayout).load(url, query); // GET 요청
			
			$("#resultLayout").load(url, data, function () {
				console.log(".load() 함수 호출 완료");
				
				$("#num1").val("");
				$("#num2").val("");
				$("#oper").val("add");
				
				$("#num1").focus();
			}); // POST 요청
			
		});
	});
</script>

</head>
<body>

	<h1>jQuery Ajax 계산기 01</h1>
	<hr>

	<input type="text" id="num1">
	<select id="oper">
		<option value="add">더하기</option>
		<option value="sub">빼기</option>
		<option value="mul">곱하기</option>
		<option value="div">나누기</option>
	</select>

	<input type="text" id="num2">

	<button id="btnCalc">=</button>

	<hr>
	<div id="resultLayout"></div>

</body>
</html>