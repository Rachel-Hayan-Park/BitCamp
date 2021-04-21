package com.bc.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.CreateDAO;
import com.bc.model.vo.MemberVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class JoinCheckCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getServletContext().getRealPath("uplo");
		
		MultipartRequest mr = new MultipartRequest(
				request, path, 10 * 1024 * 1024, 
				"UTF-8", new DefaultFileRenamePolicy()
				);
		
		MemberVO mvo = new MemberVO();
		
		mvo.setId(mr.getParameter("id"));
		mvo.setPassword(mr.getParameter("password"));
		mvo.setName(mr.getParameter("name"));
		mvo.setYear(mr.getParameter("year"));
		mvo.setMonth(mr.getParameter("month"));
		mvo.setDay(mr.getParameter("day"));
		mvo.setPhonnum(mr.getParameter("phonnum"));
		mvo.setLocation(mr.getParameter("location"));
		mvo.setEmail(mr.getParameter("email"));
		mvo.setJoindate(mr.getParameter("joindate"));
		mvo.setProfile_img(mr.getFilesystemName("profile_img"));
		
		System.out.println(mvo.toString());
		//DB에 회원가입 정보 넣기
		CreateDAO.insert(mvo);
	
		return "login.jsp";
	}
	
}
