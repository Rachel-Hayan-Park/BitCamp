<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래후기</title>

</head>
<body>
	<h2>거래후기</h2>
	<div>
		<c:if test="${empty list }">
			<h2>아직 거래후기가 없습니다. 환경보호를 위해 내게 필요없는 물건을 팔아보세요!</h2>
		</c:if>
		<c:if test="${not empty list }">
		<c:forEach var="vo" items="${list }">
			<fieldset>
				<p><img src="../img/reviewId2.png" alter="리뷰아이디이미지" width=50px height=50px>${vo.r_content }</p>
				<p> ${vo.r_regdate }</p>
			</fieldset>
		</c:forEach>
		<input type="submit" value="뒤로가기" onclick="history.go(-1)">
		</c:if>
	</div>
</body>
</html>