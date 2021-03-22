<%@page import="com.bc.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO mvo = (MemberVO)request.getAttribute("mvo");
	String id = request.getParameter("id");
	String email = request.getParameter("email");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 결과</title>
<script>
	function login(frm) {
		frm.action="login.jsp";
		frm.submit();
	}
</script>
</head>
<body>
	<h2>비밀번호찾기</h2>
	<form method="post">
	<p> ${mvo.name}님의 비밀번호는 </p><br/>
	<p> ${mvo.password} 입니다. </p><br/>	
	<input type="button" value="로그인" onclick="login(this.form)">
	</form>
</body>
</html>