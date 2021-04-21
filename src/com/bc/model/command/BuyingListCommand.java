package com.bc.model.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.vo.MemberVO;
import com.bc.model.vo.PListVO;
import com.bc.model.vo.ReviewVO;

public class BuyingListCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberVO vo = (MemberVO) request.getSession().getAttribute("loginUser");
		List<PListVO> list = SelectDAO.getbuyinglist(vo.getId());
		System.out.println("커먼드 내 상품리스트 : " + list);
		request.setAttribute("list", list);
		
		List<ReviewVO> rlist = SelectDAO.getReviewIsContain(vo.getId());
		System.out.println("커먼드 내 리뷰리스트 : " + rlist);
		request.setAttribute("rlist", rlist);
		
		return "buyingList.jsp";
	}
	
}
