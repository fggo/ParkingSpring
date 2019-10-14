package com.parking.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.api.model.vo.Parking;
import com.parking.board.model.service.ReviewService;
import com.parking.board.model.vo.Review;
import com.parking.history.model.service.UserHistoryService;
import com.parking.history.model.vo.UserHistory;

/**
 * Servlet implementation class QnaBoardListServlet
 */
@WebServlet("/board/reviewContentView")
public class ReviewContentViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewContentViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
    
    Review r = new ReviewService().selectReview(reviewNo);
    UserHistory h = new UserHistoryService().selectUserHistoryNo(r.getReviewUserHistoryNo());
    Parking p = new UserHistoryService().selectParking(h.getUserHistoryUserCode(), h.getUserHistoryParkingCode());

    request.setAttribute("review", r);
    request.setAttribute("userhistory", h);
    request.setAttribute("parking", p);

    request.getRequestDispatcher("/views/board/reviewContentView.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
