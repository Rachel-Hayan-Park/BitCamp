package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.vo.MemberVO;
import com.bc.model.vo.ReviewVO;

public class ReviewCheckCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("loginUser");
		String id = mvo.getId();
		List<ReviewVO> list = SelectDAO.checkReview(id);
		
		request.setAttribute("list", list);
		
		return "reviewCheck.jsp";
	}

}
