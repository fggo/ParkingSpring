package com.parking.payment.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.api.model.service.ParkingApiServiceImpl;
import com.parking.api.model.vo.Coupon;
import com.parking.common.api.CouponCreate;

/**
 * Servlet implementation class paymentServlet
 */
@WebServlet("/payment/paymentView")
public class paymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parkingnum = request.getParameter("parkingNum");
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
//		int sysMonth = Integer.parseInt(sdf.format(cal.getTime()).substring(5, 7));
//		int sysDay = Integer.parseInt(sdf.format(cal.getTime()).substring(8, 10));
//		int sysTime = Integer.parseInt(sdf.format(cal.getTime()).substring(11, 13));
//		int sysMin = Integer.parseInt(sdf.format(cal.getTime()).substring(14, 16));
		
		System.out.println(parkingnum);
		
		ParkingApiServiceImpl service = new ParkingApiServiceImpl();
				
		//List<Coupon> list = service.selectCouponList();	
		//System.out.println("쿠폰리스트 : "+ list.size());
		
		//request.setAttribute("CouponList", list);
		request.setAttribute("parkingNum", parkingnum);
		request.setAttribute("today", sdf.format(cal.getTime()));
		request.getRequestDispatcher("/views/payment/paymentView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
