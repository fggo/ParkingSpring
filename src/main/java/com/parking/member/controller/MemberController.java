package com.parking.member.controller;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.parking.member.model.service.MemberService;
import com.parking.member.model.vo.Member;

import web.email.AES256D;
import web.email.PwdMailSend;

@Controller
@SessionAttributes(value= {"loginMember",})
public class MemberController {
  
  @Autowired
  private MemberService service;

  @Autowired
  private BCryptPasswordEncoder pwEncoder;
  

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

//  @RequestMapping("/member/JsonMemberEmailcheck")
//  public ModelAndView jsonMemberEmailCheck(){}
  
  @RequestMapping("MemberLogin")
  public ModelAndView login(Member m, HttpServletResponse response) {
    String saveEmail="";
    

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
}