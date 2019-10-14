package com.parking.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.admin.model.service.AdminService;
import com.parking.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Member loginMember = (Member)request.getSession().getAttribute("loginMember");
    if(loginMember == null || !loginMember.getUserEmail().equals("admin@com")) {
      request.setAttribute("msg", "Access denied. Not Enough user privilege.");
      request.setAttribute("loc", "/");
      request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
      return;
    }
    
    int cPage;
    try {
      cPage = Integer.parseInt(request.getParameter("cPage"));
    } catch(NumberFormatException e) {
      cPage = 1;
    }

    int totalMember = new AdminService().selectCountMember();
    int numPerPage = 10;
    List<Member> list = new AdminService().selectListPage(cPage, numPerPage);
    
    int totalPage = (int)Math.ceil((double)totalMember/numPerPage);
    int pageBarSize = 5;
    int pageNo = ((cPage-1)/pageBarSize)*pageBarSize +1;
    int pageEnd = pageNo + pageBarSize -1;

    //pageBar
    String pageBar = "";
    if(pageNo == 1) {
      pageBar += "<li class='page-item disabled'>"
                  + "<span class='page-link'>Previous</span></li>";
    }
    else {
      pageBar += "<li class='page-item'>"
                  +"<a class='page-link' href='javascript:;' onclick='ajaxReqList(" +(pageNo-1) + ");'>Previous</a>"
               + "</li>";
    }
    while(!(pageNo > pageEnd || pageNo > totalPage)) {
      if(pageNo == cPage) {
        pageBar += "<li class='page-item active'> <span class='page-link'>"+ pageNo + "</span> </li>";
      }
      else {
        pageBar += "<li class='page-item'>"
                    + "<a class='page-link' href='javascript:;' onclick='ajaxReqList(" +pageNo +");'>"
                    + pageNo + "</a>"
                 + "</li>";
      }

      pageNo++;
    }

    if(pageNo > totalPage) {
      pageBar += "<li class='page-item disabled'>"
                  + "<span class='page-link'>Next</span></li>";
    }
    else {
      pageBar += "<li class='page-item'>"
                  + "<a class='page-link' href='javascript:;' onclick='ajaxReqList("+ pageNo + ");'>"
               + "Next</a>"
               + "</li>";
    }

    request.setAttribute("pageBar", pageBar);
    request.setAttribute("numPerPage", numPerPage);
    request.setAttribute("cPage", cPage);
    request.setAttribute("members", list);

    request.getRequestDispatcher("/views/admin/memberList.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}