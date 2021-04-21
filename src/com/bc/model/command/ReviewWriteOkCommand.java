package com.bc.model.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.UpdateDAO;
import com.bc.model.vo.MemberVO;
import com.bc.model.vo.ReviewVO;

public class ReviewWriteOkCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		String r_content = request.getParameter("r_content");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_no", p_no);
		map.put("r_content", r_content);
		
		int result = UpdateDAO.updateReview(map);
		
		if(result < 1) {
			System.out.println("인서트 실패");
		}else {
			System.out.println("인서트 성공");
		}
		
		return "/Mypage/mypageController?type=buyingList";
	}

}
