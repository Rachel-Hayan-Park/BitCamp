package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.vo.MemberVO;
import com.bc.model.vo.PListVO;

public class SellingListCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = "";
		String p_status = "PS01";
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("loginUser");
		
		String sale_id = mvo.getId();
		
		List<PListVO> plist = SelectDAO.sellingList(sale_id, p_status);
		
		request.setAttribute("plist", plist);
		path = "sellingList.jsp";
		
		return path;
	}
	
}
