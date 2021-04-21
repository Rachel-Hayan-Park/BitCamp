package com.bc.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.UpdateDAO;
import com.bc.model.vo.PListVO;

public class ChangeStatusToSellingCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		String p_status = "PS01";
		
		String sale_id = request.getParameter("sale_id");
		String purchase_id = request.getParameter("purchase_id");
		String picture01 = request.getParameter("picture01");
		String price = request.getParameter("price");
		String p_title = request.getParameter("p_title");
		String p_regdate = request.getParameter("p_regdate");
		String common_name = request.getParameter("comm_name");
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		System.out.println("상태값변경 p_title: " + p_title);
		PListVO pvo = new PListVO(p_no, sale_id, purchase_id, picture01, price, p_title, p_status, common_name, p_regdate);
		int result = UpdateDAO.changeStatusToSelling(pvo);
		
		if(result == 1) {
			return "changeStatusToSelling.jsp";
		} else {
			
			return "error.jsp";
		}
	}

}
