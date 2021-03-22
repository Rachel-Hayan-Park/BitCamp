<%@page import="com.bc.model.vo.MemberVO"%>
<%@page import="com.bc.model.SelectDAO"%>
<%@page import="com.bc.model.CreateDAO"%>
<%@page import="com.bc.model.vo.BoardVO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 전달받은 파라미터 값을 DB에 입력하고 list.jsp 이동 --%>
<%
	request.setCharacterEncoding("UTF-8");

	//파일저장위치
	String path = request.getServletContext().getRealPath("boardImg");
	//"C:\\git_gitTIL\\60_web\\give_Today_I_Learn\\60_web\\BITCAMP\\WebContent\\boardImg";
	
	MultipartRequest mr = new MultipartRequest(
			request, path, (10 * 1024 * 1024),
			"UTF-8", new DefaultFileRenamePolicy());
	//전달받은 데이터 VO에 저장후 DB에 입력 처리(DB연동 작업)
	BoardVO bvo = new BoardVO();
	bvo.setB_title(mr.getParameter("b_title"));
	bvo.setWriter_id(mr.getParameter("writer_id"));
	bvo.setB_contents(mr.getParameter("b_contents"));
	bvo.setB_password(mr.getParameter("b_password"));
	bvo.setImg_name(mr.getFilesystemName("img_name"));
	System.out.println("bvo: " + bvo);
	//System.out.println("img_name: " + bvo.getImg_name());
	//System.out.println("B_password: " + bvo.getB_password());
	//DB에 입력(저장) 처리

	
	int result = CreateDAO.insert(bvo);
	
	//화면전환(목록페이지로 이동)
	response.sendRedirect("../board/list.jsp");

	
%>
