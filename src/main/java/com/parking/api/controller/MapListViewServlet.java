package com.parking.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.api.model.service.ParkingApiService;
import com.parking.api.model.vo.Coupon;
import com.parking.bookmark.model.service.BookmarkService;
import com.parking.bookmark.model.vo.Bookmark;
import com.parking.common.api.CouponCreate;

/**
 * Servlet implementation class MapListViewServlet
 */
@WebServlet("/map/mapListView")
public class MapListViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public MapListViewServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //1 주소를 토대로한 글자를 가져오고
    String search = request.getParameter("search");
    String userCode = request.getParameter("userCode");

//    System.out.println("서블릿 :" + search);

    //ParkingApiService parkingService = new ParkingApiService();
    //주소 네임을 가져와서 서비스로 넘겨주고 데이터를 담는다
    //List<Parking> list = parkingService.selectParkingList(search);
    //System.out.println("리스트 사이즈 : " +list.size());
    
 

    
    
//    if(list.get(0) != null)
//    {
//      request.setAttribute("list", list);  
//      request.getRequestDispatcher("/views/map/mapListView.jsp").forward(request, response);      
//    }else
//    {
//      request.setAttribute("msg", "Search Failed!");
//      request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//    }

    //retrieve parking lot Bookmark list of a user w/ 'userCode'
    List<Bookmark> bookmarkList = new BookmarkService().selectBookmarkList(userCode);
//    System.out.println(bookmarkList.size());

    if (bookmarkList == null)
      bookmarkList = new ArrayList<Bookmark>();

    
    if(search != null) {
      request.setAttribute("search", search);  
      request.setAttribute("bookmarkList", bookmarkList);
      request.getRequestDispatcher("/views/map/mapListView.jsp").forward(request, response);
    }
    else {
      request.setAttribute("msg", "Search Failed!");
      request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    }

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
