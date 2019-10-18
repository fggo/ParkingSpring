package com.parking.bookmark.controller;

import java.io.IOException;
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
 * Servlet implementation class BookmarkInsertServlet
 */
@WebServlet("/bookmark/toggleBookmark")
public class BookmarkToggleServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkToggleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String toggleOption = request.getParameter("toggleOption");
    String userCode = request.getParameter("userCode");
    String parkingCode = request.getParameter("parkingCode");
    
    int result = 0;

//    if(toggleOption.equals("insert")) {
//      result = new BookmarkServiceImpl().insertBookmark(userCode, parkingCode);
//    }
//    else if(toggleOption.equals("delete")) {
//      result = new BookmarkServiceImpl().deleteBookmark(userCode, parkingCode);
//    }
    
    List<Bookmark> bookmarkList = new BookmarkServiceImpl().selectBookmarkList(userCode);


    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    new Gson().toJson(bookmarkList, response.getWriter());
  }
  

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
