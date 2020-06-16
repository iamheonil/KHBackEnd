<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	//페이지 첫 접속 시 입력창으로 포커스 이동
	$("input").eq(0).focus();
	
	//닉네임 입력 창에서 엔터 입력 시 form submit
	$("input").eq(2).keydown(function(key) {
		if(key.keyCode == 13) {
			$(this).parents("form").submit();
		}
	})

	//로그인 버튼 클릭 시 form submit
	$("#btnJoin").click(function() {
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

<h3>게시판 - 회원가입</h3>
<hr>

<form action="/member/join" method="post" class="form-horizontal">
	<div class="form-group">
		<label for="userid" class="control-label">아이디</label>
		<input type="text" id="userid" name="userid" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="userpw" class="control-label">패스워드</label>
		<input type="password" id="userpw" name="userpw" class="form-control"/>
	</div>
	<div class="form-group">
		<label for="usernick" class="control-label">닉네임</label>
		<input type="text" id="usernick" name="usernick" class="form-control"/>
	</div>

	<div class="text-center">
		<button type="button" id="btnJoin" class="btn btn-primary">가입</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>
</form>

<c:import url="/WEB-INF/views/layout/footer.jsp" />
