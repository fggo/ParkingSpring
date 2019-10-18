package com.parking.member.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.parking.member.model.service.MemberService;
import com.parking.member.model.vo.Member;

/**
 * Servlet implementation class MemberEmailCheck
 */
@WebServlet("/member/MemberEmailcheck")
public class MemberEmailCheck extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Autowired
  private MemberService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEmailCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("userEmail");

    Member m = service.selectMemberEmail(email);
    m.setUserLoginDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
    service.updateLoginDate(m);

    HttpSession session = request.getSession();
    String view="";
      session.setAttribute("loginMember", m);
      view = "/"; //return to index.jsp
      request.getRequestDispatcher(view).forward(request,response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
