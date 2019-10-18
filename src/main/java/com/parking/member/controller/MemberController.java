package com.parking.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.parking.api.model.service.ParkingApiService;
import com.parking.api.model.service.ParkingApiServiceImpl;
import com.parking.api.model.vo.Coupon;
import com.parking.common.api.CouponCreate;
import com.parking.member.model.service.MemberService;
import com.parking.member.model.vo.Member;

import web.email.AES256D;
import web.email.MailSend;
import web.email.PwdMailSend;

@Controller
@SessionAttributes(value= {"loginMember",}) //manage Member object as a session attribute
public class MemberController {
  
  @Autowired
  private MemberService service; // member service

  @Autowired
  private BCryptPasswordEncoder pwEncoder; // to encode userpw
  

  //active account : update userverified =1
  @RequestMapping("/emailverification")
  public ModelAndView accountActivation(HttpServletRequest request) {

    String encryptedcode = request.getParameter("code");
    String decryptedcode = new AES256D().decrypt(encryptedcode, "hi");

    int result = service.activateaccount(decryptedcode);
    String msg ="";
    String loc = "/";
    
    if(result > 0) 
        msg ="You have successfully activated the account!";
    else
       msg = "Activation failed!";
    ModelAndView mv = new ModelAndView();
    mv.addObject("msg", msg);
    mv.addObject("loc", loc);
    mv.setViewName("common/msg");
    
    return mv;
  }

  @RequestMapping("checkEmailDuplicate")
  public ModelAndView checkEmailDuplicate(HttpServletRequest request){

    ModelAndView mv = new ModelAndView();
    String emailToChk = request.getParameter("useremail") ==null? 
                          request.getParameter("emailHidden"): request.getParameter("useremail");
    Member m = new Member();

    if(emailToChk != null && emailToChk.length() > 0) {
      m.setUserEmail(emailToChk);
      
      Map<String, Object> result = service.selectEmail(m);
      
      mv.addObject("email", emailToChk);
      mv.addObject("isUseable", result !=null);
      mv.setViewName("member/checkEmailDuplicate");
    }
    return mv;
  }

  @RequestMapping("/member/EmailPopUp")
  public ModelAndView emailPopUp(HttpServletRequest request, HttpServletResponse response) {
    String email = request.getParameter("userEmail");
    String snsAccount = request.getParameter("snsAccount");
    
    
    ModelAndView mv = new ModelAndView();

    mv.addObject("userEmail", email);
    mv.addObject("snsAccount", snsAccount);
    mv.setViewName("member/emailPopUp");
    
    return mv;
  }

  //
  @RequestMapping("/member/JsonMemberEmailcheck")
  public ModelAndView jsonMemberEmailCheck(Member m, HttpServletResponse response) throws IOException {
    Map<String,Object> map = service.selectEmail(m);

    List<Member> list = new ArrayList<Member>();

    if(map != null && map.size() > 0)
      list.add(m);
    else
      list.add(new Member());

    response.setContentType("application/json;charset=UTF-8");

    ModelAndView mv = new ModelAndView();
    mv.addObject(new Gson().toJson(list));

    return mv;
  }
  
  @RequestMapping("/member/loginView")
  public String loginView() {
    return "member/loginView";
  }

  @RequestMapping("/member/loginEnd.do")
  public ModelAndView loginEnd(Member m, HttpServletResponse response,
                               @RequestParam(value = "saveEmail") String saveEmail) {

    ModelAndView mv = new ModelAndView();

    Member loginMember = service.selectMemberEmail(m.getUserEmail());

    String msg = "";
    String loc="/";

    if(loginMember != null && pwEncoder.matches(m.getUserPw(), loginMember.getUserPw())) {
      msg ="login success";
      service.updateLoginDate(loginMember);

      loginMember.setUserLoginDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));

//    model.addAttribute("loginMember", loginMember); //model은 request같아서 세션 유지 X
//    @SessionAttributes등록하면 loginMember를 session에 올림

      int duration = (saveEmail != null? 7*12*60*60 : 0); //seconds

      Cookie c = new Cookie("saveEmail", m.getUserEmail());
      c.setMaxAge(duration);
      mv.addObject("saveEmail", c);
      response.addCookie(c);
    }
    else { //Not Logged in
      msg = "Email or password does not match!";
    }
    mv.addObject("msg", msg);
    mv.addObject("loc", loc);
    mv.setViewName("common/msg");

    return mv;
  }
  
  @RequestMapping("/logout")
  public String logout(Member m, Model model, SessionStatus status) {
    if(!status.isComplete()) //check if session is closed
      status.setComplete(); //httpsessison.invalidate()와 같은기능

    return "redirect:/";
  }

  @RequestMapping("/member/emailpwdresetstart")
  public ModelAndView pwdResetStart(@RequestParam(value = "email") String email) {
    ModelAndView mv = new ModelAndView();

    PwdMailSend pms = new PwdMailSend();
    pms.PwdMailSend(email);

    String msg ="Please check your email to change your password!";
    String loc = "/";

    mv.addObject("email", email);
    mv.addObject("msg", msg);
    mv.addObject("loc", loc);
    mv.setViewName("common/msg");

    return mv;
  }

  @RequestMapping("/member/emailpwdresetend")
  public ModelAndView passwordResetEnd(@RequestParam(value = "code") String encryptedcode,
                                       @RequestParam(value = "newPwChk") String password) {

    ModelAndView mv = new ModelAndView();

    String decryptedEmail = new AES256D().decrypt(encryptedcode, "hi");
    Member m = new Member();
    m.setUserEmail(decryptedEmail);
    m.setUserPw(password);
    
    int result = service.changepassword(m);

    String msg ="";
    String loc = "/";
    
    if(result > 0)
      msg ="You have successfully change your password";
    else
      msg = "Failed to change your password";

    mv.addObject("msg", msg);
    mv.addObject("loc", loc);
    mv.setViewName("common/msg");

    return mv;
  }
  
  @RequestMapping("/member/memberEnroll")
  public ModelAndView memberEnroll(@RequestParam(value = "userEmail", required=false) String userEmail,
                                   @RequestParam(value = "snsAccount", required=false) String snsAccount) {

    ModelAndView mv = new ModelAndView();

    if(userEmail !=null) {
      mv.addObject("userEmail", userEmail);
      mv.addObject("snsAccount", snsAccount);
      mv.setViewName("member/memberEnroll");
        
    }
    else {
      mv.setViewName("redirect:/member/memberEnroll");
    }
    return mv;
  }
  
	public String generateUserCode() {
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

  @RequestMapping("/member/memberEnrollEnd.do")
  public ModelAndView memberEnrollEnd(Member m, 
                                      @RequestParam(value = "roadAddress") String roadAddress,
                                      @RequestParam(value = "postcode") String postcode,
                                      @RequestParam(value = "smsYn") String smsChk,
                                      @RequestParam(value = "emailYn") String emailChk) {

	  m.setUserCode(this.generateUserCode());
	  m.setUserAddr(roadAddress + postcode);
	  m.setUserPw(pwEncoder.encode(m.getUserPw()));
	  m.setUserSmsYn(smsChk !=null? 1:0);
	  m.setUserEmailYn(emailChk != null? 1:0);
	  m.setUserEmailVerified(0);
	  m.setUserCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

	  int result = service.insertMember(m);
	  
	  MailSend ms = new MailSend();
	  ms.SendingMail(m.getUserEmail());

	
	  String msg = result > 0? "Hello "+m.getUserName() + ". Please check your email to activate your account!" : "Sign up Failed!";
	  String loc = "/";
//	  if(result >0)
//    {
//        //쿠폰생성 잠시사용
//         int resultCoupon = 0;
//         CouponCreate cc = new CouponCreate();
//         Set<Coupon> set = new HashSet<Coupon>();
//         Coupon c= new Coupon();
//         Iterator<Coupon> it = set.iterator();
//         
//         ParkingApiService service = new ParkingApiServiceImpl();
//         while(it.hasNext())
//         {
//            Coupon obj = it.next();
//            c.setUserCode(m.getUserCode());
//            c.setDiscountRate(10);
//            c.setDuration(1);
//            c.setExpiredYn(0);
//         }
//         resultCoupon = service.insertCoupon(c);
//         
//         if(result > 0)
//            System.out.println("쿠폰등록완료");
//    }
//	  
	  ModelAndView mv = new ModelAndView();
	  mv.addObject("msg", msg);
	  mv.addObject("loc", loc);
	  mv.setViewName("common/msg");

	  return mv;
  }
  @RequestMapping("privacyPolicy")
  public String privacyPolicy() {
    return "member/privacyPolicy";
  }

  @RequestMapping("termsofuse")
  public String temrsofuse() {
    return "member/termsofuse";
  }

  @RequestMapping("member/memberDelete")
  public ModelAndView memberDelete(Member m, Model model, HttpServletRequest request, SessionStatus status) {
    ModelAndView mv = new ModelAndView();
    
    int result = service.deleteMember(m);

    String msg = "";
    String loc = "";

    if(result > 0) {
      msg = "Successfully Deleted Account!";
      loc = "/logout";

	    //delete profile picture
	    String saveDir = request.getServletContext().getRealPath(File.separator + "upload" + File.separator + "member");
	    File remove = new File(saveDir + File.separator + m.getUserRenamedFilename());
	    remove.delete();
    }
    else {
      msg = "Failed to Delete Account...";
      loc = "/member/memberUpdate";
    }


    if(!status.isComplete()) //check if session is closed
      status.setComplete(); //httpsessison.invalidate()와 같은기능

    mv.addObject("msg", msg);
    mv.addObject("loc", loc);

    mv.setViewName("/views/common/msg.jsp");

    return mv;
  }
}