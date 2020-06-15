<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 스마트 라이브러리 적용 -->
<script type="text/javascript"
	src="/resources/se2/js/service/HuskyEZCreator.js" charset="UTF-8"></script>

<style type="text/css">
#container {
	width: 95%;
}
</style>

<div class="container" style="width: 95%;">

	<h1>글쓰기 페이지</h1>
	<hr>

	<form action="/board/write" method="post" enctype="multipart/form-data">
		<table style="width: 95%;">
			<tr>
				<td style="width: 10%;">제목</td>
				<td><input type="text" name="title" id="title"></td>
			</tr>

			<tr>
				<td style="width: 10%;">아이디</td>
				<td><input type="text" name="writer" id="writer" readonly
					value="${userid }"></td>
			</tr>

			<tr>
				<td style="width: 10%;">닉네임</td>
				<td><input type="text" name="nick" id="nick" readonly
					value="${usernick }"></td>
			</tr>

			<tr>
				<td style="width: 10%;">첨부파일 #1</td>
				<td><input type="file" name="file" id="file"></td>
			</tr>

			<tr>
				<td style="width: 10%;">내용</td>
				<td><textarea rows="15" cols="135" id="content" name="content"></textarea></td>
			</tr>

		</table>

		<br> <br>
		<button id="btnWrite">작성하기</button>
		<input type="button" id="Cancel" name="Cancel" value="취소">
		&nbsp;
	</form>
	<br>

</div>

<!-- 스마트 에디터 적용 -->

<!-- <form> 태그의 submit 을 수행하면 SmartEditor 에 작성한 내용을 Textarea 에 반영한다 -->

<script type="text/javascript">
	function submitContents(elClickedObj) {

		// 에디터의 내용을 #Content 에 반영한다.
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

		try {
			elClickedObj.form.submit();
		} catch (e) {
		}
	}
</script>



<script type="text/javascript">
	// 작성버튼 동작
	$(document).ready(function() {

		$("#btnWrite").click(function() {
			submitContents($("#btnWrite"));
			$("form").submit();
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		//취소 버튼 누르면 뒤로가기
		$("#Cancel").click(function() {
			history.go(-1);
		});
	})
</script>



<!-- <textarea> 에 스마트 에디터의 스킨을 입히는 코드 -->
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({

		oAppRef : oEditors,
		elPlaceHolder : "content", // 에디터가 적용될 <textArea>의 아이디
		sSkinURI : "/resources/se2/SmartEditor2Skin.html", // Editor Skin
		fCreator : "createSEditor2"

	});
</script>

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>