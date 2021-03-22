<%@page import="com.bc.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	MemberVO mvo = (MemberVO)request.getAttribute("mvo");
	System.out.println(mvo);
	String name = request.getParameter("name");
	String phonnum = request.getParameter("phonnum");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과</title>
<script type="text/javascript">
	function login(frm) {
		frm.action="login.jsp";
		frm.submit();
	}
	function findpwd(frm) {
		frm.action="loginController?type=findPwd"
		frm.submit();
	}
</script>
</head>
<body>
	<h2>아이디찾기</h2>
	<form method="post">
	<p> ${mvo.name}님의 아이디는 </p><br/>
	<p> ${mvo.id} 입니다. </p><br/>	
	<input type="button" value="로그인" onclick="login(this.form)">
	<input type="button" value="비밀번호찾기" onclick="findpwd(this.form)">		
	</form>
</body>
</html>