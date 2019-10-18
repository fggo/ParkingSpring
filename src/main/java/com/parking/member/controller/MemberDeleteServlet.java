package com.parking.member.controller;

import java.io.File;
import java.io.IOException;

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
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Autowired
  private MemberService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();

    Member m = (Member)session.getAttribute("loginMember");
    
    int result = service.deleteMember(m);

    String view = "/views/common/msg.jsp";
    String msg = "";
    String loc = "";

    if(result > 0) {
      msg = "Successfully Deleted Account!";
      loc = "/logout";

	    //delete profile picture
	    String saveDir = getServletContext().getRealPath(File.separator + "upload" + File.separator + "member");
	    File remove = new File(saveDir + File.separator + m.getUserRenamedFilename());
	    remove.delete();
    }
    else {
      msg = "Failed to Delete Account...";
      loc = "/member/memberUpdate";
    }

    request.setAttribute("msg", msg);
    request.setAttribute("loc", loc);
    request.getRequestDispatcher(view).forward(request, response);

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
