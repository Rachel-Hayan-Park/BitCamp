package com.bc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.model.command.BuyingListCommand;
import com.bc.model.command.ChangeStatusToSellingCommand;
import com.bc.model.command.ChangeStatusToSoldCommand;
import com.bc.model.command.CheckPasswordCommand;
import com.bc.model.command.CheckPasswordOkCommand;
import com.bc.model.command.Command;
import com.bc.model.command.DeletePlistCommand;
import com.bc.model.command.ExitMemberCommand;
import com.bc.model.command.ExitMemberOkCommand;
import com.bc.model.command.InProgressListCommand;
import com.bc.model.command.MyPageMainCommand;
import com.bc.model.command.ReviewCheckCommand;
import com.bc.model.command.SellingListCommand;
import com.bc.model.command.SoldListCommand;
import com.bc.model.command.UpdatePriseCommand;
import com.bc.model.command.UpdatePriseGOCommand;
import com.bc.model.command.UpdateProFile_OkCommand;
import com.bc.model.command.UpdateProfileCommand;
import com.bc.model.command.ReviewWriteCommand;
import com.bc.model.command.ReviewWriteOkCommand;

@WebServlet("/Mypage/mypageController")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">>MyPageController 실행");
		String type = request.getParameter("type");
		System.out.println("type: " + type);
		
		Command command = null;
		
		if("myPageMain".equals(type)) {
			command = new MyPageMainCommand();
			String path = command.execute(request, response);
			response.sendRedirect(path);
		}
		else if("updateProfile".equals(type)) {
			command = new UpdateProfileCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("checkPassword".equals(type)) {
			command = new CheckPasswordCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("checkPasswordOk".equals(type)) {
			command = new CheckPasswordOkCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("updateProfile_Ok".equals(type)) {
			command = new UpdateProFile_OkCommand();
			String path = command.execute(request, response);
			response.sendRedirect(path);
		} else if("sellingList".equals(type)) {
			command = new SellingListCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}else if("buyingList".equals(type)) {
			command = new BuyingListCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}else if("reviewWrite".equals(type)) {
			command = new ReviewWriteCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}else if("reviewWriteOk".equals(type)) {
			command = new ReviewWriteOkCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}else if("inProgressList".equals(type)) {
			command = new InProgressListCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("sellingList".equals(type)) {
			command = new SellingListCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("soldList".equals(type)) {
			command = new SoldListCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("changeStatusToSold".equals(type)) {
			command = new ChangeStatusToSoldCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("deletePlist".equals(type)) { 
			command = new DeletePlistCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("changeStatusToSelling".equals(type)) {
			command = new ChangeStatusToSellingCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("exitMember".equals(type)) {
			command = new ExitMemberCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("exitMemberOK".equals(type)) {
			command = new ExitMemberOkCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}else if("updatePrise".equals(type)) {
			command = new UpdatePriseCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}else if("updatePriseGO".equals(type)) {
			command = new UpdatePriseGOCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}else if("reviewCheck".equals(type)) {
			command = new ReviewCheckCommand();
			String path = command.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">> MyPageController doPost() 실행");
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
