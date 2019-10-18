package com.parking.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.parking.api.model.service.ParkingApiServiceImpl;
import com.parking.api.model.vo.Parking;

/**
 * Servlet implementation class ParkingAutoCommitSerlvlet
 */
@WebServlet("/ajax/parkingAutoCommit")
public class ParkingAutoCommitSerlvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParkingAutoCommitSerlvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String addrName = request.getParameter("addr");
		System.out.println(addrName);
		ParkingApiServiceImpl service = new ParkingApiServiceImpl();
		//List<Parking> list = service.selectAutoCommit(addrName);
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //new Gson().toJson(list, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
