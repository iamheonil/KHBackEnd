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

			$.ajax({
				
				type: "get",
				url: "/jqAjax/jqAjax_ok.jsp",
				data: {
					num1: $("#num1").val() ,
					num2: $("#num2").val() ,
					oper: $("#oper").val() ,
				} ,
				dataType: "html" ,
				success: function(res) {
					console.log("AJAX 성공")
					
					$("#resultLayout").html(res);
				},
				error: function() {
					console.log("AJAX 실패")
				} 
				
			}); // $.ajax
		}); // #btnCalc click
	}); // document ready
</script>
</head>
<body>

	<h1>jQuery Ajax 계산기 03</h1>
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