package com.parking.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class DistanceLatLonServlet
 */
@WebServlet("/ajax/centerLatLon.do")
public class DistanceLatLonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DistanceLatLonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double lat1 = Double.parseDouble(request.getParameter("lat1"));
		double lon1 = Double.parseDouble(request.getParameter("lon1"));
		double lat2 = Double.parseDouble(request.getParameter("lat2"));
		double lon2 = Double.parseDouble(request.getParameter("lon2"));
		
		double dLon = Math.toRadians(lon2-lon1);
		
		//convert to radians
	    lat1 = Math.toRadians(lat1);
	    lat2 = Math.toRadians(lat2);
	    lon1 = Math.toRadians(lon1);

	    double Bx = Math.cos(lat2) * Math.cos(dLon);
	    double By = Math.cos(lat2) * Math.sin(dLon);
	    double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
	    double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

	    //print out in degrees
//	    System.out.println(Math.toDegrees(lat3) + " " + Math.toDegrees(lon3));
	    List list = new ArrayList();
	    list.add(Math.toDegrees(lat3));
	    list.add(Math.toDegrees(lon3));
	    response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
