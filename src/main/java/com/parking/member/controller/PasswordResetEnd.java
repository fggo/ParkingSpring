package com.parking.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.member.model.service.MemberService;

import web.email.AES256D;

/**
 * Servlet implementation class PasswordResetEnd
 */
@WebServlet(name="ResetPassword", urlPatterns="/member/emailpwdresetend")
public class PasswordResetEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordResetEnd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * String email1 = (String)request.getAttribute("email");
		 * System.out.println("request.getattribute : " +email1); String email =
		 * request.getParameter("email"); System.out.println("email::: "+email);
		 */
		
		String encryptedcode = request.getParameter("code");
		String decryptedcode = new AES256D().decrypt(encryptedcode, "hi");
		
		String password = request.getParameter("newPwChk");
		MemberService ms = new MemberService();
		int result = ms.changepassword(decryptedcode,password);
		String msg ="";
		String loc = "";
		
		if(result > 0) {
		      msg ="You have successfully change your password";
		      loc = "/";
		      }
			else {
				 msg = "Failed to change your password";
				 loc = "/";	
			}
			  
			  request.setAttribute("msg", msg);
			  request.setAttribute("loc", loc);
			  request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
