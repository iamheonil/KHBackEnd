<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<script type="text/javascript">
function send() {
// 	console.log("send called")

	// - - - 전달파라미터 구성 - - -
	var n = username.value;
	var c = content.value;
	
	var params = "name="+n+"&content="+c;
	console.log(params);
	// - - - - - - - - - - - - - - -
	
	
	// - - - URL 구성 - - -
	var url = "/ajax/ajax_04_ok.jsp";
	// - - - - - - - - - - -
	
	
	// - - - AJAX 요청 전송 - - -
	sendRequest("POST", url, params, callback);
	// - - - - - - - - - - - - - -
	
}

//AJAX 요청 처리 콜백함수
function callback() {
	if( httpRequest.readyState == 4 ) { //XHR DONE.
		if( httpRequest.status == 200 ) { //HTTP 200, OK.
			console.log("정상적인 응답")
			
			//정상응답 처리 함수
			appendResult();
			
		} else {
			console.log("AJAX 요청/응답 에러")
		}
	}
}

//결과 <div>를 추가해주는 함수
function appendResult() {
	console.log("appendResult called")
	
	//방법1.
	//	직접 <div>태그를 생성하고 응답값을 추가하고, div#result 에 추가한다
// 	var newDiv = document.createElement("div"); //새로운 <div>생성
// 	newDiv.innerHTML = httpRequest.responseText; //응답 데이터 추가
// 	result.appendChild(newDiv); //div#result의 자식요소로 <div> 추가
	
	
	//방법2.
	//	방법1. 을 좀 더 축약(간단히)해서 작성한 방법
// 	result.innerHTML += "<div>" + httpRequest.responseText + "</div>";
	
	
	//방법3.
	//	응답 메시지에 <div>를 포함시키고 처리하는 방법
	result.innerHTML += httpRequest.responseText;
	
}
</script>

</head>
<body>

<h1>AJAX 04</h1>
<hr>

<!-- 이름, <input>태그, id="username" -->
<!-- 내용, <input>태그, id="content" -->

<!-- 이름, 내용 두가지 데이터를 ajax_04_ok.jsp 로 전송 -->
<!-- 전송한 데이터를 이용하여 응답데이터로 작성한다 -->

<!-- 응답 탇은 데이터를 이용하여 <div>를 생성하고 그 안에 데이터를 기록 -->
<!-- 형식

	<div>
		name: Alice
		content: 안녕하세요!
		
		Alice님, 안녕하세요!
	</div>

 -->
<!-- 응답받은 <div>데이터는 <button> 하단에 계속해서 추가되도록 만든다 -->

<!-- ----------------------------------------------------------------- -->

<div>
	<label>이름 <input type="text" id="username" /></label><br>
	<label>내용<br><textarea id="content"></textarea></label><br><br>
	
	<button onclick="send();">전송</button>
</div>

<div id="result"></div>

</body>
</html>











