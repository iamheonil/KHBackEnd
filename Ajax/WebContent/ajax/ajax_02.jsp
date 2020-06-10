<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
//AJAX객체인 XHR 을 생성하는 함수 - 크로스 브라우징 처리
function createXMLHttpRequest() {
	
	// XHR 객체 변수
	var xmlReq = null;
	
	if( window.XMLHttpRequest ) { //XMLHttpRequest 객체가 존재한다면
		//IE 7.0 이상일 때
		//IE 브라우저가 아닐 때 (크롬, 사파리, 오페라 등등)
		
		xmlReq = new XMLHttpRequest();
	} else if ( window.ActiveXObject ) { //ActiveXObject 객체가 존재한다면
		//IE 계열 브라우저
		
		try {
			
			//비교적 최근 버전의 IE
			xmlReq = new ActiveXObject("Msxml2.XMLHTTP");
			
		} catch(e1) {
			
			try {
				//비교적 예전 버전의 IE
				xmlReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch(e2) {
				
				//XHR이 없는 경우엔 수행할 수 없음
				console.log("AJAX 객체 생성 실패")
				
			}
			
		}
		
	}
	
	return xmlReq;
	
}
</script>



<script type="text/javascript">

//AJAX 객체 변수 - XHR
var xmlHttp = null;

function send() {
// 	console.log("send called")
	
	//XHR 객체 생성
	// AJAX 수행 객체 생성하기
	xmlHttp = createXMLHttpRequest();
// 	console.log(xmlHttp);
	
	
	//입력한 데이터 구하기 - 전송 데이터만들기
	var n1 = num1.value;
	var n2 = num2.value;
	var op = oper.value;
	
// 	console.log("n1 : " + n1)
// 	console.log("n2 : " + n2)
// 	console.log("op : " + op)
	
	
	
	// - - - AJAX 요청 전 설정 - - -
	
	//요청 URL
	var query = "num1="+n1+"&num2="+n2+"&oper="+op; //쿼리스트링 작성
	var url = "/ajax/ajax_02_ok.jsp" + "?" + query; //전송 URL 작성
	console.log(url);
	
	//요청 Method
	var method = "GET";
	
	//서버로부터 전달된 응답 데이터 처리 콜백함수
	xmlHttp.onreadystatechange = callback;
	
	// - - - - - - - - - - - - - - -
	
	
	// - - - AJAX 요청 준비 - - -
	//XHR 객체가 서버로 통신할 준비를 함 - 서버로 접속함(통신스트림 개설)
	xmlHttp.open(method, url);
	// - - - - - - - - - - - - - -

	
	// - - - AJAX 요청 보내기 - - -
	//XHR 객체가 서버로 요청메시지를 전송함
	//	-> 서버로 데이터를 전송함
	xmlHttp.send(null);	
	// - - - - - - - - - - - - - - -
	
}

//응답 완료 시 데이터 처리함수
function callback() {
// 	console.log("callback called")

	if(xmlHttp.readyState == 4) { //readyState 4, DONE, 응답 완료
		if( xmlHttp.status == 200 ) { // OK.
			console.log("정상 응답")
			
			//응답결과처리 전용함수
			printData();
			
		} else if( xmlHttp.status == 404 ) { //Not Found.
			console.log("페이지 없음")
			
		} else if( xmlHttp.status == 405 ) { //Method Not Allowed.
			console.log("HTTP Method 처리불가")
			
		} else if( xmlHttp.status == 500 ) { //Internal Server Error.
			console.log("서버 에러")
			
		} else {
			console.log( xmlHttp.statusText )
			
		}
	}
}


//응답 처리 함수
function printData() {
	console.log("printData called")
	
	console.log( xmlHttp.responseText );
	
	//div#resultLayout에 결과 반영하기
	resultLayout.innerHTML = xmlHttp.responseText;
	
}
</script>

</head>
<body>

<h1>계산기 02</h1>
<h3>AJAX 통신 (비동기식)</h3>
<hr>

<input type="text" id="num1" />
<select id="oper">
	<option value="add">더하기</option>
	<option value="sub">빼기</option>
	<option value="mul">곱하기</option>
	<option value="div">나누기</option>
</select>
<input type="text" id="num2" />

<button onclick="send();"> = </button>

<hr>
<!-- AJAX 요청의 결과(응답)이 추가될 DIV -->
<div id="resultLayout"></div>

</body>
</html>














