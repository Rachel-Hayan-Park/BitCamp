<%@page import="com.bc.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<script type="text/javascript">
	function checkPassword(frm) {
		frm.action="mypageController?type=checkPasswordOk"
		frm.submit();		
	}
</script>
</head>
<body>
	<h2>비밀번호 확인</h2>
	<p>정보확인을 위해 비밀번호를 입력해주세요.</p>
	<form method="post">
		<table border>
			<tbody>
				<tr>
					<td>*아이디</td>
					<td><%=mvo.getId() %></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pwd"></td>
				</tr>
			</tbody>
		</table>
		<input type="button" value="확인" onclick="checkPassword(this.form)">
	</form>
</body>
</html>