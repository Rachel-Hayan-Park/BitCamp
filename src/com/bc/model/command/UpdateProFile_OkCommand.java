package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.UpdateDAO;
import com.bc.model.vo.MemberVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateProFile_OkCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//System.out.println("updateProfile command 실행");
		request.setCharacterEncoding("UTF-8");
		
		String uploadPath = request.getServletContext().getRealPath("uplo");
		
		MultipartRequest mr = new MultipartRequest(
				request, uploadPath, 10 * 1024 * 1024, 
				"UTF-8", new DefaultFileRenamePolicy()
				);
				
		
		String location = "";
		String phonnum = "";
		String email = "";
		String profile_img = "";
		String password = "";
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("loginUser");
		String id = mvo.getId();
		
		
		MemberVO vo = new MemberVO(password, phonnum, location, email, profile_img, id);
		vo.setPassword(mr.getParameter("password"));
		vo.setPhonnum(mr.getParameter("phonnum"));
		vo.setLocation(mr.getParameter("location"));
		vo.setEmail(mr.getParameter("email"));
		vo.setProfile_img(mr.getFilesystemName("profile_img1"));
		vo.setId(id);
		String path = "";
		int result = UpdateDAO.updateProfile(vo);
		System.out.println("updateprofile result: " + result);
		
		//System.out.println("프로필이미지: " + vo.getProfile_img());
		
		request.getSession().setAttribute("vo", vo);
		System.out.println("vo: " + vo);
		
		
		if(result == 1) {
			path = "../Mypage/updateProfileOk.jsp";//redirect
		} else if(result == 0) { //값이없을때
			path = "../Mypage/updateProfileError";
		}
		return path;
	}
}
