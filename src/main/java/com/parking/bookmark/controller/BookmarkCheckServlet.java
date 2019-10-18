package com.parking.bookmark.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.parking.bookmark.model.service.BookmarkServiceImpl;
import com.parking.bookmark.model.vo.Bookmark;

/**
 * Servlet implementation class BookmarkCheckServlet
 */
@WebServlet("/bookmark/checkBookmarkSaved")
public class BookmarkCheckServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    String userCode = request.getParameter("userCode");
//    String parkingCode = request.getParameter("parkingCode");
//    Bookmark b = new BookmarkServiceImpl().selectBookmark(userCode, parkingCode);
//    int result = b!=null? 1 : 0;
//    
//    response.setContentType("application/json;charset=UTF-8");
//    List<Bookmark> list = new ArrayList<Bookmark>();
//    if (result == 1) {
//      list.add(b);
//    }
//
//    new Gson().toJson(list,response.getWriter());
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
