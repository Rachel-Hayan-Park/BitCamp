package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.CreateDAO;
import com.bc.model.SelectDAO;
import com.bc.model.UpdateDAO;
import com.bc.model.vo.MemberVO;
import com.bc.model.vo.PListVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdatePriseGOCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getServletContext().getRealPath("uplo");
		
		String pno = (String)request.getParameter("p_no");
		int p_no = Integer.parseInt(pno);
		
		System.out.println("pno : " + p_no);
		
		MultipartRequest mr = new MultipartRequest(
				request, path, 10 * 1024 * 1024, 
				"UTF-8", new DefaultFileRenamePolicy()
		);
		
		PListVO pvo = new PListVO();
		
		MemberVO vo = (MemberVO) request.getSession().getAttribute("loginUser");
		System.out.println("커먼드 vo 출력 : " + vo.toString());
		
		pvo.setP_no(p_no);
		pvo.setSale_id(vo.getId());
		pvo.setPicture01(mr.getFilesystemName("f_name"));
		pvo.setPrice(mr.getParameter("price"));
		pvo.setP_title(mr.getParameter("title"));
		pvo.setP_contents(mr.getParameter("contents"));
		pvo.setP_category(mr.getParameter("cate"));
		pvo.setLocation(mr.getParameter("location"));
		
		
		System.out.println(pvo.toString());
		int result = UpdateDAO.updatePrise(pvo);
		System.out.println("업데이트 고 커먼드에서 결과수 : " + result);
	
		return "../main/bitmarket";
	}
	
}
