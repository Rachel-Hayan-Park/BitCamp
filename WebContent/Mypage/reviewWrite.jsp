<%@page import="com.bc.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기작성하기</title>
</head>
<body>
	<form action="mypageController?type=reviewWriteOk" method="post">
		<h2>>${pvo.sale_id }님에게 보내는 후기</h2>
		<img src="${pageContext.request.contextPath}/uplo/${pvo.picture01}" alt="${pvo.p_title }" style="width : 400px;">
	    <p>${pvo.p_title }</p>
	   	<p>${pvo.price }</p>
		<textarea rows="10" cols="50" name="r_content"></textarea>
		<input type="hidden" value="${pvo.p_no }" name="p_no">
		<input type="submit" value="보내기">
	</form>
</body>
</html>