package com.bc.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.DeleteDAO;
import com.bc.model.SelectDAO;
import com.bc.model.vo.MemberVO;

public class ExitMemberOkCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//비밀번호 일치/불일치 확인
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("loginUser");
		
		String id = mvo.getId();
		
		int result = DeleteDAO.deleteMember(id);
		System.out.println("삭제 결과: " + result);
		
		mvo = null;
		request.getSession().setAttribute("loginUser", null);
		
		if(result == -1) {
			String path = "";
			path = "exitMemberOK.jsp";
			return path;
		} else {
			String path = "error.jsp";
			return path;
		}
		
	}
	
}
