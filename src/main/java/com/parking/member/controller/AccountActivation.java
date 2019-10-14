package com.parking.member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.member.model.service.MemberService;

import web.email.AES256D;
import web.email.AES256E;


/**
 * Servlet implementation class AccountActivation
 */
@WebServlet("/emailverification")
public class AccountActivation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountActivation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encryptedcode = request.getParameter("code");
//		System.out.println(encryptedcode);
		String decryptedcode = new AES256D().decrypt(encryptedcode, "hi");
//		System.out.println("decrytped value : " +decryptedcode);
			MemberService ms = new MemberService();
			int result = ms.activateaccount(decryptedcode);
//			System.out.println("final result : " +result);
			String msg ="";
			String loc = "";
			
			if(result > 0) {
		      msg ="You have successfully activated the account!";
		      loc = "/";
		      }
			else {
				 msg = "Activation failed!";
				 loc = "/";	
			}
			  //String msg = result > 0? "You have successfully activated the account!" : "Activation failed!";
			 // String loc = "/";
			  
			  request.setAttribute("msg", msg);
			  request.setAttribute("loc", loc);
			  request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}

	//}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
