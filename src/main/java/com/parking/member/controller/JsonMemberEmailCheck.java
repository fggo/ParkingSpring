package com.parking.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.parking.member.model.service.MemberService;
import com.parking.member.model.vo.Member;

/**
 * Servlet implementation class JsonMemberEmailCheck
 */
@WebServlet("/member/JsonMemberEmailcheck")
public class JsonMemberEmailCheck extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired
  private MemberService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonMemberEmailCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("userEmail");
    
    Member m = new Member();
    m.setUserEmail(email);

    Map<String,Object> map = service.selectEmail(m);

    List<Member> list = new ArrayList<Member>();
    if(map != null && map.size() > 0) {
      list.add(m);
      JSONArray jlist = new JSONArray();
      
      response.setContentType("application/json;charset=UTF-8");
      new Gson().toJson(list,response.getWriter());    
    }
    else {
      list.add(new Member());
      JSONArray jlist = new JSONArray();
      
      response.setContentType("application/json;charset=UTF-8");
      new Gson().toJson(list,response.getWriter());
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
