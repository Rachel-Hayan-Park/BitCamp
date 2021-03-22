package com.bc.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.UpdateDAO;
import com.bc.model.vo.MemberVO;
import com.bc.model.vo.PListVO;

public class BuyingOkCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("buyingOK커맨드 시작");
		
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("loginUser");
		PListVO pvo = (PListVO)request.getSession().getAttribute("pvo");
		
		System.out.println("buyingok 커맨드 p_no: " + pvo.getP_no());
		int p_no = pvo.getP_no();
		
		String purchase_id = mvo.getId();
		//System.out.println("purchase_id: " + purchase_id);
		
		String p_status = "PS03";
		
		String sale_id = pvo.getSale_id();
		//System.out.println("sale_id: " + sale_id);
		
		PListVO vo = new PListVO(p_no, purchase_id, p_status); 
		if(sale_id.equals(purchase_id)) {
			return "errorBuying.jsp";
		}
		
		int result = UpdateDAO.updateBuying(vo);
		if(result == 1) {
			return "buyingOK.jsp";
		} else {
			return "error.jsp";
		}
		

	}

}
