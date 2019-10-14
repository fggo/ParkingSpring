package com.parking.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.board.model.service.QnaBoardService;
import com.parking.board.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaBoardListServlet
 */
@WebServlet("/board/qnaBoardList")
public class QnaBoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int cPage;

    try {
      cPage = Integer.parseInt(request.getParameter("cPage"));
    } catch(NumberFormatException e) {
      cPage = 1;
    }

    int totalQnaBoard = new QnaBoardService().selectCountQnaBoard(); //number of total qna items
    int numPerPage = 5;
    int pageBarSize = 5;
    int totalPage = (int)Math.ceil((double)totalQnaBoard/numPerPage);
    int pageNo = ((cPage - 1)/pageBarSize) * pageBarSize + 1;
    int pageEnd = pageNo + pageBarSize -1;

    //list
    List<QnaBoard> list = new QnaBoardService().selectQnaBoardList(cPage, numPerPage);

    //pageBar
    String pageBar = "";
    if(pageNo == 1) {
      pageBar += "<li class='page-item disabled'>"
                  + "<span class='page-link btn btn-sm'>Previous</span></li>";
    }
    else {
      pageBar += "<li class='page-item'>"
                  +"<a class='page-link btn btn-sm' href='javascript:;' onclick='ajaxRequestPage(" +(pageNo-1) + ");'>Previous</a>"
               + "</li>";
    }
    while(!(pageNo > pageEnd || pageNo > totalPage)) {
      if(pageNo == cPage) {
        pageBar += "<li class='page-item active'> <span class='page-link btn btn-sm'>"+ pageNo + "</span> </li>";
      }
      else {
        pageBar += "<li class='page-item'>"
                    + "<a href='javascript:;' onclick='ajaxRequestPage(" +pageNo +");'"
                    + " class='page-link btn btn-sm'>"+ pageNo + "</a>"
                 + "</li>";
      }

      pageNo++;
    }

    if(pageNo > totalPage) {
      pageBar += "<li class='page-item disabled'>"
                  + "<span class='page-link btn btn-sm'>Next</span></li>";
    }
    else {
      pageBar += "<li class='page-item'>"
                  + "<a href='javascript:;' onclick='ajaxRequestPage("+ pageNo + ");'"
                  + " class='page-link btn btn-sm'>Next</a>"
               + "</li>";
    }
    
    request.setAttribute("pageBar", pageBar);
    request.setAttribute("cPage", cPage);
    request.setAttribute("qnalist", list);
    
    request.getRequestDispatcher("/views/board/qnaBoardList.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
