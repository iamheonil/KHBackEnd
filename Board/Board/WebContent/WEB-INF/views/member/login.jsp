<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	//페이지 첫 접속 시 입력창으로 포커스 이동
	$("input").eq(0).focus();
	
	//패스워드 입력 창에서 엔터 입력 시 form submit
	$("input").eq(1).keydown(function(key) {
		if(key.keyCode == 13) {
			$(this).parents("form").submit();
		}
	})

	//로그인 버튼 클릭 시 form submit
	$("#btnLogin").click(function() {
		$(this).parents("form").submit();
	})
	
	//취소 버튼 누르면 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
	})

});
</script>

<style type="text/css">
form {
	width: 400px;
	margin: 0 auto;
}
</style>

<form action="/member/login" method="post" class="form-horizontal">
	<div class="form-group">
		<label for="userid" class="control-label">아이디</label>
		<input type="text" id="userid" name="userid" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="userpw" class="control-label">패스워드</label>
		<input type="text" id="userpw" name="userpw" class="form-control"/>
	</div>

	<div class="text-center">
		<button type="button" id="btnLogin" class="btn btn-primary">로그인</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>
</form>

<c:import url="/WEB-INF/views/layout/footer.jsp" />
