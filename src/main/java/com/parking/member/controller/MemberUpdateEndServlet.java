package com.parking.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;

import com.oreilly.servlet.MultipartRequest;
import com.parking.common.file.rename.MyFileRenamePolicy;
import com.parking.member.model.service.MemberService;
import com.parking.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateEndServlet
 */
@WebServlet("/member/memberUpdateEnd")
public class MemberUpdateEndServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Autowired
  private MemberService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  if(!ServletFileUpload.isMultipartContent(request)) {
	    request.setAttribute("msg", "enctype ERROR");
	    request.setAttribute("loc", "/");
	    request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	  }
	  
	  String saveDir = getServletContext().getRealPath(File.separator + "upload" + File.separator + "member");
	  File dir = new File(saveDir);
	  if(!dir.exists()) {
	    dir.mkdirs(); //mkdirs 서브 dir 경로까지 전부
	  }
	  
	  int maxSize = 1024*1024*1024; // 1GB
	  
	  //MultipartRequest객체 생성
	  MultipartRequest mr = new MultipartRequest(
	      request,
	      saveDir,
	      maxSize,
	      "UTF-8",
	      new MyFileRenamePolicy()); //new DefaultRenamePolicy() 대신 커스텀 rename policy

    String userPhone = mr.getParameter("phone");
    String userName = mr.getParameter("name");
    String userAddr = mr.getParameter("addr");
    String userSmsYn = mr.getParameter("smsYn"); //null or "on"(checked)
    String userEmailYn = mr.getParameter("emailYn"); //null or "on"(checked)
    String old_ori = mr.getParameter("old_up_file_ori");
    String old_re = mr.getParameter("old_up_file_re");
    String new_ori = mr.getOriginalFileName("new_up_file");
    String new_re = mr.getFilesystemName("new_up_file");

    HttpSession session = request.getSession();

    Member m = (Member)session.getAttribute("loginMember");
    m.setUserPhone(userPhone);
    m.setUserName(userName);
    m.setUserAddr(userAddr);
    m.setUserSmsYn(userSmsYn != null? 1:0);
    m.setUserEmailYn(userEmailYn != null? 1:0);
    m.setUserOriginalFilename(new_ori==null? old_ori:new_ori);
    m.setUserRenamedFilename(new_re==null? old_re:new_re);

    int result = service.updateMember(m);

    String msg = "";
    String loc = "";

    if(result > 0) {
	    //update 성공하여 이전 파일 삭제 (update할 새로운 파일을 지정한 경우에만 삭제)
      msg ="Member update Successful!";
      loc = "/member/memberView";

      if(new_ori != null && new_re != null) {
        File remove = new File(saveDir + File.separator + old_re);
        remove.delete();
      }

      session.setAttribute("loginMember", m);
    }
    else {
	    //update 실패했으니 MultipartRequest로 생성된 파일을 지워줌
      msg ="Member update Failed.";
      loc = "/member/memberUpdate";

      File remove = new File(saveDir + File.separator + new_re);
      remove.delete();
      
      m = (Member)session.getAttribute("loginMember"); //revert changes
    }

    request.setAttribute("msg", msg);
    request.setAttribute("loc", loc);
    request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
