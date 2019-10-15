package com.parking.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.board.model.service.ReviewService;
import com.parking.board.model.vo.Review;
import com.parking.history.model.service.UserHistoryService;
import com.parking.history.model.vo.UserHistory;

/**
 * Servlet implementation class QnaBoardListServlet
 */
@WebServlet("/board/reviewWriteEnd")
public class ReviewWriteEndServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String userCode = request.getParameter("userCode");
    String reviewTitle = request.getParameter("reviewTitle");
    String reviewContent = request.getParameter("reviewContent");
    int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
    String parkingCode = request.getParameter("parkingCode");
    
    UserHistory h = new UserHistoryService().selectUserHistory(userCode, parkingCode);
    int reviewUserHistoryNo = h.getUserHistoryNo();
    Review review = new Review(reviewUserHistoryNo, reviewTitle, reviewContent, reviewRating);

    int result = new ReviewService().insertReview(review);

	  String msg = result > 0? "Thanks for Reviewing Parking Lot[parkingCode="+parkingCode + "]!" : "Review Failed!";
	  String loc = "/board/reviewList";
	  
	  request.setAttribute("msg", msg);
	  request.setAttribute("loc", loc);

	  request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
