package com.bc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.SelectDAO;
import com.bc.model.command.BuyingOkCommand;
import com.bc.model.command.Command;
import com.bc.model.command.PLCommand;
import com.bc.model.command.PListCommand;
import com.bc.model.command.ViewCommand;
import com.bc.model.vo.PListVO;


@WebServlet("/PList/PListController")
public class PListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		Command command = null;
		
		if("enroll".equals(type)) {
			command = new PLCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		
		} else if("goenroll".equals(type)) {
			command = new PListCommand();
			String path = command.execute(request, response);
			response.sendRedirect(path);
		}else if("viPrise".equals(type)) {
			command = new ViewCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("buyingOk".equals(type)) {
			command = new BuyingOkCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
