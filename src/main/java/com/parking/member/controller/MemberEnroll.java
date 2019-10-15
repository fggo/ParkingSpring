package com.parking.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;


public class MemberEnroll {

	@RequestMapping("/memberEnroll")
	public String memberEnroll(HttpServletRequest request, HttpServletResponse response)
	{
		String email = request.getParameter("userEmail");
		String snsAccount = request.getParameter("snsAccount");
		
		if(email !=null)
		  {
			  request.setAttribute("userEmail", email);
			  request.setAttribute("snsAccount", snsAccount);
			  
			 // return "/member/memberEnroll";
			  //request.getRequestDispatcher("/views/member/memberEnroll.jsp").forward(request, response);
//			  System.out.println("userEmail : "+email);
//			  System.out.println("snsAccount : "+snsAccount);
		  }else
		  {
			  //response.sendRedirect(request.getContextPath() + "/views/member/memberEnroll.jsp");	
			  //return "/member/memberEnroll";
		  }
		return "/member/memberEnroll";
	}
	
}
