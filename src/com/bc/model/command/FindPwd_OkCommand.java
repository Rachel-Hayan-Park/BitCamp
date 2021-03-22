package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.vo.MemberVO;

public class FindPwd_OkCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String path = "";
		
		System.out.println("id: " + id + ", email: " + email);
		
		MemberVO mvo = SelectDAO.findPwd(id, email);
		System.out.println("검색결과: "+ mvo);
	
		request.setAttribute("mvo", mvo);
		if(mvo == null)
		{
			path = "findPwdfail.jsp";
		}else {
			path = "findPwd_Ok.jsp";
		}
		
		return path;
	}
	
}
