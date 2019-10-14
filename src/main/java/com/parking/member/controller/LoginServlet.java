package com.parking.member.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.parking.member.model.service.MemberService;
import com.parking.member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="MemberLogin", urlPatterns="/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String email = request.getParameter("email");
	  String pwLoginOriginal = request.getParameter("pwLogin");

	  Member m = new MemberService().selectEmail(email);
	  m.setUserLoginDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));


	  boolean validatePw = false;

	  try {
	    validatePw = validatePassword(pwLoginOriginal, m.getUserPw());
	  } catch(InvalidKeySpecException e) {
	    e.printStackTrace();
	  } catch(NoSuchAlgorithmException e) {
	    e.printStackTrace();
	  }

	  request.setAttribute("loginMember", m);

	  //for debugging
//	  System.out.println(email);
//	  System.out.println(pwLoginOriginal);
//	  System.out.println(m);

    //update login date
    boolean logged = new MemberService().updateLoginDate(email);

	  String view = "";

	  if(m!= null && validatePw && logged) { //Logged in
	    /* request.getSession(boolean); boolean parameter
	     *   true(default) : load or create session object
	     *   false : load session (allow null)
	     */
	    HttpSession session = request.getSession();

	    session.setAttribute("loginMember", m);
	    

	    /* Cookie
	     * remember login email for a week if "checked"
	     */
	    String saveEmail = request.getParameter("saveEmail"); //null or "on"(checked)

	    int duration = (saveEmail != null? 7*12*60*60 : 0); //seconds

	    Cookie c = new Cookie("saveEmail", email);
      c.setMaxAge(duration);
      response.addCookie(c);
      
	    view = "/"; //return to index.jsp

	    response.sendRedirect(request.getContextPath() + view);
	  }
	  else { //Not Logged in
	    String msg = "Email or password does not match!";
	    String loc = "/"; //return to index.jsp after showing message in msg.jsp

	    request.setAttribute("msg", msg);
	    request.setAttribute("loc", loc);

	    view = "/views/common/msg.jsp"; //dispatched to msg.jsp

	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);
	  }

	}

	private boolean validatePassword(String originalPassword, String storedPassword) 
      throws NoSuchAlgorithmException, InvalidKeySpecException {
      String[] parts = storedPassword.split(":");
      int iterations = Integer.parseInt(parts[0]);
      byte[] salt = fromHex(parts[1]);
      byte[] hash = fromHex(parts[2]);
       
      PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      byte[] testHash = skf.generateSecret(spec).getEncoded();
       
      int diff = hash.length ^ testHash.length;
      for(int i = 0; i < hash.length && i < testHash.length; i++)
      {
          diff |= hash[i] ^ testHash[i];
      }
      return diff == 0;
  }

  private byte[] fromHex(String hex) throws NoSuchAlgorithmException {
      byte[] bytes = new byte[hex.length() / 2];
      for(int i = 0; i<bytes.length ;i++)
      {
          bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
      }
      return bytes;
  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
