package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.vo.MemberVO;

public class LogoutCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("loginUser");
		
		mvo = null;
		request.getSession().setAttribute("loginUser", null);
		
		return "../main/bitmarket";
		
	}
	
}
