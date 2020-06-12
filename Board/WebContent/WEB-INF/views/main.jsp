<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<script type="text/javascript">
	function logout() {
		
		location.href = "/member/logout";
		
	}
</script>
 
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>




<h1>메인입니다</h1>
<hr>

안녕하세요! ${memberView.id } 님, <br><br>
패스워드 : ${memberView.pw } <br><br>
닉네임 : ${memberView.nick } <br><br>

<br>
<hr>
<a href="/board/list"><button>게시판 목록 버튼</button></a> &nbsp; <button onclick="logout();">로그아웃</button>

<br>
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>