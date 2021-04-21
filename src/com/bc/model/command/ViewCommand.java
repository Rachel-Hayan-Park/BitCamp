package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.vo.MemberVO;
import com.bc.model.vo.PListVO;

public class ViewCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("view command p_no : " + (String)request.getParameter("p_no"));
		String pno = (String)request.getParameter("p_no");
		int p_no = Integer.parseInt(pno);
		//System.out.println(p_no);
		
		PListVO pvo = SelectDAO.selectPrise(p_no);
		System.out.println("view command pvo: " + pvo);
		//request.setAttribute("pvo", pvo);
		request.getSession().setAttribute("pvo", pvo);
		
		return "prise.jsp";
	}
	
}
