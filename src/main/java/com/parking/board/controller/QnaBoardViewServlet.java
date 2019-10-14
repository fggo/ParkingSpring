package com.parking.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.board.model.service.QnaBoardService;
import com.parking.board.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaBoardContentViewServlet
 */
@WebServlet("/board/qnaBoardView")
public class QnaBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  int qnaNo = Integer.parseInt(request.getParameter("no"));
	  
	  QnaBoard q = new QnaBoardService().selectQnaBoard(qnaNo);
	  System.out.println(q);
	  
	  request.setAttribute("qnaboard", q);
	  
	  request.getRequestDispatcher("/views/board/qnaBoardView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
