package com.parking.member.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.parking.member.model.service.MemberService;
import com.parking.member.model.vo.Member;

/**
 * Servlet implementation class FacebookServlet
 */
@WebServlet("/FacebookServlet")
public class FacebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacebookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  useremail = request.getParameter("useremail");
		System.out.println("username 1 :" +useremail);
		MemberService service = new MemberService();
		boolean result = service.fbselectEmail(useremail);
		System.out.println("result : " +result);
		String msg ="";
		String loc = "";
		if(result==false) {
			System.out.println("2 :" +result);
			msg ="Please sign up first!";
			loc = "/";
			String snsaccount = "F";	      
			 //request.setAttribute("msg", msg); 
			 request.setAttribute("useremail", useremail); 
			 request.setAttribute("snsaccount", snsaccount);
			 //request.setAttribute("loc", loc); 
		  request.getRequestDispatcher("/views/member/facebookEnroll.jsp").forward(request, response);
			
			
		}
		else {
		String email = useremail;
		Member m = new MemberService().selectEmail(email);
		m.setUserLoginDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		request.setAttribute("loginMember", m);


	    //update login date
	    boolean logged = new MemberService().updateLoginDate(email);

		  String view = "";

		  if(m!= null && logged) { //Logged in
		  
		    HttpSession session = request.getSession();

		    session.setAttribute("loginMember", m);
	
			msg ="Welcome!";
			loc = "/";
				      
		request.setAttribute("msg", msg);
		String saveEmail = useremail; //null or "on"(checked)

	    int duration = (saveEmail != null? 7*12*60*60 : 0); //seconds

	    Cookie c = new Cookie("saveEmail", useremail);
	    c.setMaxAge(duration);
	    response.addCookie(c);
	    response.sendRedirect(request.getContextPath() + loc);
		}
		}
		/*
		 * else { System.out.println("2 :" +result); msg ="Please sign up first!"; loc =
		 * "/";
		 * 
		 * request.setAttribute("msg", msg);
		 * 
		 * request.setAttribute("useremail", useremail);
		 * request.getRequestDispatcher("/views/member/EmailPopUp.jsp").forward(request,
		 * response); //response.sendRedirect(request.getContextPath() +
		 * "/views/member/memberEnroll.jsp"); //var
		 * url="<%=request.getContextPath()%>/member/EmailPopUp?userEmail="+snsEmail +
		 * "&snsAccount=" + snsAccount; }
		 */
		  }			
					
					  
					  
		 
		 
		 
		
		
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
