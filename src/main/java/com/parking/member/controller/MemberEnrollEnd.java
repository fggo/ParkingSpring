package com.parking.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.parking.api.model.service.ParkingApiServiceImpl;
import com.parking.api.model.vo.Coupon;
import com.parking.common.api.CouponCreate;
import com.parking.member.model.service.MemberService;
import com.parking.member.model.vo.Member;

import web.email.MailSend;

/**
 * Servlet implementation class MemberEnrollEnd
 */
@WebServlet(name="MemberEnroll", urlPatterns="/memberEnrollEnd")
public class MemberEnrollEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollEnd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String userCode = this.generateUserCode();
	  String email = request.getParameter("email");
	  String pwEnroll = request.getParameter("pwEnroll");
	  String phone = request.getParameter("phone");
	  String userName = request.getParameter("userName");
	  String userAddr = request.getParameter("roadAddress") + request.getParameter("postcode");
	  String snsAccount = request.getParameter("snsAccount");

    int smsYn = request.getParameter("smsYn") != null? 1:0;
    int emailYn = request.getParameter("emailYn") != null? 1:0;
	  int emailVerified = 0; //DEFAULT
	  Date createdDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	  Date loginDate = null;

	  Member m = new Member(userCode, email, pwEnroll, phone, userName, userAddr,
                          createdDate, loginDate, smsYn, emailYn, emailVerified, snsAccount, null, null);

	  int result = service.insertMember(m);
	  
	  MailSend ms = new MailSend();
	  ms.SendingMail(email);

	
	  String msg = result > 0? "Hello "+userName + ". Please check your email to activate your account!" : "Sign up Failed!";
	  String loc = "/";
	  if(result >0)
    {
        //쿠폰생성 잠시사용
         int resultCoupon = 0;
         CouponCreate cc = new CouponCreate();
         Set<Coupon> set = new HashSet<Coupon>();
         Coupon c= new Coupon();
         Iterator<Coupon> it = set.iterator();
         
         ParkingApiServiceImpl service = new ParkingApiServiceImpl();
         while(it.hasNext())
         {
            Coupon obj = it.next();
            c.setUserCode(userCode);
            c.setDiscountRate(10);
            c.setDuration(1);
            c.setExpiredYn(0);
         }
         //resultCoupon = service.insertCoupon(c);
         
         if(result > 0)
            System.out.println("쿠폰등록완료");
    }
	  
	  request.setAttribute("msg", msg);
	  request.setAttribute("loc", loc);

	  request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

  /**
   * generateUserCode() generates unique and random 6-digit user code
   * @return String randDigit is a generated random 6 digit in String type
   */
	protected String generateUserCode() {
	  int rand = 0;
	  String randDigit ="";
	  Member m = null;
	  Map<String, Object> map = null;

	  do {
      // assign 'user_code' unique random digit string : 000001 ~ 999999
	    rand = ThreadLocalRandom.current().nextInt(1, 999999 + 1);
	    randDigit = String.format("%06d",  rand);

	    m = new Member();
	    m.setUserCode(randDigit);

	    map = service.selectUserCode(m);
	  } while(map != null && map.get("USEREMAIL") !=null);

	  return randDigit;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
