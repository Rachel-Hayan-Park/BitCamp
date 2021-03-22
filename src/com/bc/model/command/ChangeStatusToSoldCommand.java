package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.CreateDAO;
import com.bc.model.UpdateDAO;
import com.bc.model.vo.PListVO;
import com.bc.model.vo.ReviewVO;

public class ChangeStatusToSoldCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = "";
		String p_status = "PS02";
		
		String sale_id = request.getParameter("sale_id");
		String purchase_id = request.getParameter("purchase_id");
		String picture01 = request.getParameter("picture01");
		String price = request.getParameter("price");
		String p_title = request.getParameter("p_title");
		String p_regdate = request.getParameter("p_regdate");
		String common_name = request.getParameter("comm_name");
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		System.out.println("구매자아이디 처음값: " + purchase_id);
		PListVO pvo = new PListVO(p_no, sale_id, purchase_id, picture01, price, p_title, p_status, common_name, p_regdate);
		int result = UpdateDAO.changeStatusToSold(pvo);
		System.out.println("구매자아이디 상태값~~: " + pvo.getPurchase_id());
	
		System.out.println("changestatustosold result :" + result);
		
		if(result == 1) {
			String r_status = "등록대기";
			ReviewVO rvo = new ReviewVO();
			rvo.setP_no(p_no);
			rvo.setPurchase_id(purchase_id);
			rvo.setR_status(r_status);
			
			System.out.println("rvo: " + rvo);
			int result1 = CreateDAO.insertReview(rvo);
			System.out.println("result1 결과값: " + result1);
			return "changeStatusToSold.jsp";
		} else {
			return "error.jsp";
		}
	}

}
