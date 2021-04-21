package com.bc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.command.Command;
import com.bc.model.command.FindIdCommand;
import com.bc.model.command.FindId_OkCommand;
import com.bc.model.command.FindPwdCommand;
import com.bc.model.command.FindPwd_OkCommand;
import com.bc.model.command.JoinCheckCommand;
import com.bc.model.command.JoinCommand;
import com.bc.model.command.LoginMainCommand;
import com.bc.model.command.LogoutCommand;

@WebServlet("/Login/loginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">>loginController 실행");
		String type = request.getParameter("type");
		System.out.println("type: " + type);
		
		Command command = null;
		
		if("join".equals(type)) {
			command = new JoinCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		
		} else if("joinCheck".equals(type)) {
			command = new JoinCheckCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("findId".equals(type)) {
			command = new FindIdCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);	
		} else if("findId_Ok".equals(type)) {
			command = new FindId_OkCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("findPwd".equals(type)) {
			command = new FindPwdCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("findPwd_Ok".equals(type)) {
			command = new FindPwd_OkCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} /*
			 * else if("login".equals(type)) { command = new LoginCommand(); String path =
			 * command.execute(request, response); response.sendRedirect(path); //redirect
			 * 처리 }
			 */else if("loginMain".equals(type)) {
			command = new LoginMainCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}else if("logout".equals(type)) {
			command = new LogoutCommand();
			String path = command.execute(request, response);
			response.sendRedirect(path);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">> loginController doPost() 실행");
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
