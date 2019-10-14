package com.parking.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.api.model.vo.Parking;
import com.parking.history.model.service.UserHistoryService;
import com.parking.history.model.vo.UserHistory;

/**
 * Servlet implementation class QnaBoardListServlet
 */
@WebServlet("/board/reviewWrite")
public class ReviewWriteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String userCode = request.getParameter("userCode");
    String parkingCode = request.getParameter("parkingCode");

    Parking parking = new UserHistoryService().selectParking(userCode, parkingCode);
    UserHistory userhistory = new UserHistoryService().selectUserHistory(userCode, parkingCode);

    request.setAttribute("parking", parking);
    request.setAttribute("userhistory", userhistory);

    request.getRequestDispatcher("/views/board/reviewWrite.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
