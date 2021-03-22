package com.bc.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.vo.MemberVO;

public class FindId_OkCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String phonnum = request.getParameter("phonnum");
		String path = "";
		
		System.out.println("name: " + name + ", phonnum: " + phonnum);
		
		MemberVO mvo = SelectDAO.findId(name, phonnum);
		System.out.println("검색결과: "+ mvo);
	
		request.setAttribute("mvo", mvo);
		
		if(mvo == null)
		{
			path = "findIdfail.jsp";
		}else {
			path = "findId_Ok.jsp";
		}
		
		return path;
	}
}
