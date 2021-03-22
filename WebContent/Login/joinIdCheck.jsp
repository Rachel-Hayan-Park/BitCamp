<%@page import="com.bc.model.vo.MemberVO"%>
<%@page import="com.bc.model.SelectDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복체크</title>
</head>
<body>
	<h2>아이디 중복체크</h2>

<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	System.out.println("아이디 중복체크 id: " + id);
	
	int result = SelectDAO.joinIdCheck(id);
	System.out.println("아이디 중복체크 result: " + result);
	
	if(result == 1) { 
		System.out.println("중복아이디");
		out.print("중복된 아이디입니다.");
	} else { 
		System.out.println("사용가능한아이디");
		out.print("사용 가능한 아이디입니다.");
%>
	<input type="button" value="아이디 사용하기" onclick="result();">
<% 	} %>
<!-- 팝업창구현 -->
<fieldset>
	<form action="joinIdCheck.jsp" method="post" name="wfr">
		ID 
		<input type="text" id="txtId" name="id" value="<%=id %>">
		<input type="submit" value="중복확인"> 
	</form>
</fieldset>

<script type="text/javascript">
	function result() {
		//ID정보를 회원가입창에 전달
		opener.document.getElementById("txtId").value = document.wfr.id.value;
		
		//회원가입창 제어(readonly)
		opener.document.getElementById("txtId").readOnly=true;
		
		//창닫기
		window.close();
	}
	
</script>
</body>
</html>