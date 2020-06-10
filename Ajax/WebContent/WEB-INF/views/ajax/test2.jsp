<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<script type="text/javascript">

window.onload = function() {
	
	// Button ID => btnAction
	btnAction.onclick = function() {
		
		// console.log("btnAction Click!")
		
		// AJAX 요청 보내기
		sendRequest("POST", "/ajax/test2", "", callback);
		
	}
	
}

// 위의 요청의 콜백 함수 
function callback() {
	
	if(httpRequest.readyState == 4) {
		if(httpRequest.status == 200) {

			// console.log("정상적인 Ajax 요청, 응답 완료");
			
			print();
			
			
		} else {
			console.log("AJAX 요청, 응답 실패");
		}
	}
	
}

function print() {
	
	console.log("Print Call")
	
	console.log("response Text");
	console.log(httpRequest.responseText);
	
	result.innerHTML = httpRequest.responseText;
}

</script>
</head>
<body>

<h1>아작스 테스트 02(HTML)</h1>

<hr>

<button id="btnAction">AJAX 요청</button>

<div id="result"></div>

</body>
</html>