package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.vo.MemberVO;
import com.bc.model.vo.PListVO;

public class ReviewWriteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		PListVO pvo = SelectDAO.getReviewInfoProduct(p_no);
		request.setAttribute("pvo", pvo);
		
		return "reviewWrite.jsp";
	}

}
