package com.parking.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.parking.admin.model.service.AdminService;
import com.parking.member.model.vo.Member;

@Controller
@SessionAttributes(value= {"loginMember",}) //manage Member object as a session attribute
public class AdminController {
  
  @RequestMapping("/admin/memberList")
  public ModelAndView adminMemberList(HttpSession session,
                                    @RequestParam(value="cPage", required=true, defaultValue= "1") int cPage ) {

    ModelAndView mv = new ModelAndView();

    Member loginMember = (Member)session.getAttribute("loginMember");

    if(loginMember == null || !loginMember.getUserEmail().equals("admin@com")) {
      mv.addObject("msg", "Access denied. Not Enough user privilege.");
      mv.addObject("loc", "/");
      mv.setViewName("common/msg.jsp");
      return mv;
    }
    
    int totalMember = new AdminService().selectCountMember();
    int numPerPage = 10;
    List<Member> list = new AdminService().selectListPage(cPage, numPerPage);
    
    int totalPage = (int)Math.ceil((double)totalMember/numPerPage);
    int pageBarSize = 5;
    int pageNo = ((cPage-1)/pageBarSize)*pageBarSize +1;
    int pageEnd = pageNo + pageBarSize -1;

    //pageBar
    String pageBar = "";
    if(pageNo == 1) {
      pageBar += "<li class='page-item disabled'>"
                  + "<span class='page-link'>Previous</span></li>";
    }
    else {
      pageBar += "<li class='page-item'>"
                  +"<a class='page-link' href='javascript:;' onclick='ajaxReqList(" +(pageNo-1) + ");'>Previous</a>"
               + "</li>";
    }
    while(!(pageNo > pageEnd || pageNo > totalPage)) {
      if(pageNo == cPage) {
        pageBar += "<li class='page-item active'> <span class='page-link'>"+ pageNo + "</span> </li>";
      }
      else {
        pageBar += "<li class='page-item'>"
                    + "<a class='page-link' href='javascript:;' onclick='ajaxReqList(" +pageNo +");'>"
                    + pageNo + "</a>"
                 + "</li>";
      }

      pageNo++;
    }

    if(pageNo > totalPage) {
      pageBar += "<li class='page-item disabled'>"
                  + "<span class='page-link'>Next</span></li>";
    }
    else {
      pageBar += "<li class='page-item'>"
                  + "<a class='page-link' href='javascript:;' onclick='ajaxReqList("+ pageNo + ");'>"
               + "Next</a>"
               + "</li>";
    }

    mv.addObject("pageBar", pageBar);
    mv.addObject("numPerPage", numPerPage);
    mv.addObject("cPage", cPage);
    mv.addObject("members", list);

    mv.setViewName("admin/memberList");

    return mv;
  }

}
