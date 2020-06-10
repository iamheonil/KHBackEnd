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
		sendRequest("POST", "/ajax/test", "", callback);
		
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
	
	console.log("콜백에 프린트 함수 잘 되는 중");
	
	// AJAX 응답 데이터를
	var respText = httpRequest.responseText;
	
	console.log("------ RESP TEXT -------")
	console.log(respText);
	
	// 응답받은 JSON 데이터 언마샬링 (JSON Object)로 변환
	var jsonObject = JSON.parse(respText);
	console.log("----- json Object -----");
	console.log(jsonObject);
	
	// console.log(jsonObject.id);
	// console.log(jsonObject.pw);
	
	for(var i = 0; i < jsonObject.length; i++) {
		result.innerHTML += "<div>" + jsonObject[i].id + " : " + jsonObject[i].pw + "</div>";
	}
	
}

</script>
</head>
<body>

<h1>아작스 테스트</h1>

<hr>

<button id="btnAction">AJAX 요청</button>

<div id="result"></div>

</body>
</html>