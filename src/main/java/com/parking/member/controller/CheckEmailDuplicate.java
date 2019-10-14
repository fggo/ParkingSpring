package com.parking.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.member.model.service.MemberService;

/**
 * Servlet implementation class CheckEmailDuplicate
 */
@WebServlet("/checkEmailDuplicate")
public class CheckEmailDuplicate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckEmailDuplicate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String emailToChk = request.getParameter("email_") ==null? 
                          request.getParameter("emailHidden"): request.getParameter("email_");
	  boolean isUseable = new MemberService().selectCheckEmail(emailToChk);

	  request.setAttribute("email", emailToChk);
	  request.setAttribute("isUseable", isUseable);
	  request.getRequestDispatcher("/views/member/checkEmailDuplicate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
