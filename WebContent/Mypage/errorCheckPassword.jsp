<%@page import="com.bc.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String password = (String)request.getAttribute("pwd");
	String path = "";
	
	System.out.println("errercheck password: " + password);
	MemberVO mvo = (MemberVO)request.getSession().getAttribute("loginUser");
	System.out.println("errercheck mvo: " + mvo);
	
	if(!mvo.getPassword().equals(password)) { %>
	<script>
		alert("비밀번호가 다릅니다. 다시 입력해주세요.");
		location.href="checkPassword.jsp";
	</script>
	<%
	} else {
		////return "../main/bitmarket"; //메인페이지
		response.sendRedirect("../Mypage/mypageController?type=updateProfile");
	}
%>

