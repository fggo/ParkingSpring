package com.parking.payment.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parking.api.model.service.ParkingApiServiceImpl;

/**
 * Servlet implementation class PaymentEndServlet
 */
@WebServlet("/paymentEnd")
public class PaymentEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pCode = request.getParameter("pCode");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String amount = request.getParameter("amount");
		String addTime = request.getParameter("addTime");
		String userCode = request.getParameter("userCode");
		String discountCoupon = request.getParameter("discountCoupon");
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
		String beginT = String.valueOf(sdf.format(cal.getTime()));
		cal.add(cal.HOUR_OF_DAY, Integer.parseInt(addTime));
		String endT = String.valueOf(sdf.format(cal.getTime()));
		
		
		ParkingApiServiceImpl service = new ParkingApiServiceImpl();
		int result = 0;
		//result = service.insertParkingSlot(pCode,userCode,beginT,endT);
		System.out.println("dis"+discountCoupon);
		if(result > 0)
		{
			System.out.println("parkingSlot insert!");
			int discountAmount=0;
			if(discountCoupon.equals(""))
			{
				discountAmount = Integer.parseInt(amount);
			}else
			{
				discountAmount = Integer.parseInt(amount)-(1-(int)(Integer.parseInt(discountCoupon.substring(0,2))*0.01));
			}
//			discountAmount = Integer.parseInt(amount)+(int)( Integer.parseInt(discountCoupon.substring(0,2))*0.1*Integer.parseInt(amount));
			//result = service.insertParkingUserHistory(pCode,userCode, String.valueOf(discountAmount));
			if(result > 0)
			{
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}else
		{
			 request.setAttribute("msg", "Slot&history Failed");
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
