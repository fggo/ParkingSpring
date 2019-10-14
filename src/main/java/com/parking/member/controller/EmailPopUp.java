package com.parking.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmailPopUP
 */
@WebServlet("/member/EmailPopUp")
public class EmailPopUp extends HttpServlet {
 private static final long serialVersionUID = 1L;
    
  /**
   * @see HttpServlet#HttpServlet()
   */
  public EmailPopUp() {
    super();
    // TODO Auto-generated constructor stub
  }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  String email = request.getParameter("userEmail");
  String snsAccount = request.getParameter("snsAccount");
  
  request.setAttribute("userEmail", email);
  request.setAttribute("snsAccount", snsAccount);

  request.getRequestDispatcher("/views/member/emailPopUp.jsp")
  .forward(request,response);
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  // TODO Auto-generated method stub
  doGet(request, response);
 }

}
