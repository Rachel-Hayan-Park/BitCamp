package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.vo.MemberVO;

public class CheckPasswordOkCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pwd1 = request.getParameter("pwd");
		//비밀번호 일치/불일치 확인
		request.setAttribute("pwd", pwd1);
		
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("loginUser");
		
		String pwd2 = mvo.getPassword();
		
		if(pwd1 == pwd2) {
			String path = "";
			path = "updateProfile.jsp";
			return path;
		} else {
			String path = "errorCheckPassword.jsp";
			return path;
		}
		
	}
	
}
