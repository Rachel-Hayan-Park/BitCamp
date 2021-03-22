package com.bc.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.DeleteDAO;

public class DeletePlistCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String p_no = request.getParameter("p_no");
		
		System.out.println("deletelist p_no: " + p_no);
		int result = DeleteDAO.deletePlist(p_no);
		System.out.println("delete result: " + result);
		if(result == 1) {
			return "deletePlist.jsp";
		} else {
			return "error.jsp";
		}
		
	}

}