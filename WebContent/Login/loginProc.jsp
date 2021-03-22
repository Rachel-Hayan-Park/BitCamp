<%@page import="com.bc.model.SelectDAO"%>
<%@page import="com.bc.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	//System.out.println("id: " + id + ", password: " + password);
	
	MemberVO vo = SelectDAO.login(id, password);
	//System.out.println("LoginCommand vo : " + vo);
	
	request.getSession().setAttribute("loginUser", vo);
	
	if(vo == null) { %>
	<script>
		alert("아이디나 비밀번호가 다릅니다. 다시 입력해주세요.");
		location.href="login.jsp";
	</script>
<%
	} else {
		response.sendRedirect("../main/bitmarket");
	}
%>

