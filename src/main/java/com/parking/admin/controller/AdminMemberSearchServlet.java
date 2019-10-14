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
 * Servlet implementation class AdminMemberSearchServlet
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberSearchServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String searchType=request.getParameter("searchType");
    String searchKeyword=request.getParameter("searchKeyword");
    int cPage;
    try {
      cPage=Integer.parseInt(request.getParameter("cPage"));
    }catch(NumberFormatException e) {
      cPage=1;
    }
    
    int totalMember =new AdminService().selectCountMember(searchType,searchKeyword);
    int numPerPage=10;
    List<Member> list=new AdminService().selectListPage(searchType,searchKeyword,cPage,numPerPage);
    
    //전체page계산
    int totalPage=(int)Math.ceil((double)totalMember/numPerPage);
    int pageBarSize=5;//페이지바크기(나오는 숫자갯수)
    int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
    int pageEnd=pageNo+pageBarSize-1;

    //pageBar
    String pageBar="";//전송할 코드(pageBar구성) 누적할 문자열
    if(pageNo==1) {
      pageBar += "<li class='page-item disabled'>"
                  + "<span class='page-link'>Previous</span></li>";
//      pageBar+="<span>[이전]</span>";
    }else {
      pageBar += "<li class='page-item'>"
                  +"<a class='page-link' href='javascript:;' " 
                  + " onclick=\"ajaxReqSearchList("+(pageNo-1) + ",'"
                                                +searchType+"','"
                                                +searchKeyword+ "');\"> "
                  + "Previous</a>"
               + "</li>";
//      pageBar+="<a href='"+request.getContextPath()
//      +"/admin/memberFinder?cPage="+(pageNo-1)
//      +"&searchType="+searchType+"&searchKeyword="+searchKeyword
//      +"'>[이전]</a>";
    }
    while(!(pageNo>pageEnd||pageNo>totalPage)) {
      if(cPage==pageNo) {
        pageBar += "<li class='page-item active'> <span class='page-link'>"+ pageNo + "</span> </li>";
//        pageBar+="<span class='cPage'>"+pageNo+"</span>";
      }else {
      pageBar += "<li class='page-item'>"
                  +"<a class='page-link' href='javascript:;' " 
                  + " onclick=\"ajaxReqSearchList("+pageNo + ",'"
                                                +searchType+"','"
                                                +searchKeyword+ "');\"> " + pageNo
                  + "</a>"
               + "</li>";
//        pageBar+="<a href='"+request.getContextPath()
//        +"/admin/memberFinder?cPage="+(pageNo)
//        +"&searchType="+searchType+"&searchKeyword="+searchKeyword
//        +"'>"+pageNo+"</a>";
      }
      pageNo++;
    }
    if(pageNo>totalPage) {
      pageBar += "<li class='page-item disabled'>"
                  + "<span class='page-link'>Next</span></li>";
//      pageBar+="<span>[다음]</span>";
    }else {
      pageBar += "<li class='page-item'>"
                  +"<a class='page-link' href='javascript:;' " 
                  + " onclick=\"ajaxReqSearchList("+pageNo + ",'"
                                                +searchType+"','"
                                                +searchKeyword+ "');\"> "
                  + "Next</a>"
               + "</li>";
//      pageBar+="<a href='"+request.getContextPath()
//      +"/admin/memberFinder?cPage="+(pageNo)
//      +"&searchType="+searchType+"&searchKeyword="+searchKeyword
//      +"'>[다음]</a>";
    }
    request.setAttribute("pageBar", pageBar);
    request.setAttribute("searchType", searchType);
    request.setAttribute("searchKeyword",searchKeyword);
    request.setAttribute("cPage",cPage);
    request.setAttribute("numPerPage",numPerPage);
    request.setAttribute("members",list);
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