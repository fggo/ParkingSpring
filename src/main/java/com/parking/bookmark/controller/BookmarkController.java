package com.parking.bookmark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.api.model.vo.ParkingSeoul;
import com.parking.bookmark.model.service.BookmarkService;
import com.parking.bookmark.model.vo.Bookmark;
import com.parking.member.model.vo.Member;

@Controller
public class BookmarkController {

  @Autowired
  private BookmarkService service;

  @RequestMapping("/bookmark/bookmarkList")
  public String bookmarkList(Member m) throws JsonProcessingException{
    // String userCode = request.getParameter("userCode");
    
    List<ParkingSeoul> list = service.selectBookmarkParkingList(m);
    
//    new Gson().toJson(list, response.getWriter());
    //jackson은 gson과 비슷한 역할, better functionality
    //mapper.readValue(json값, vo클래스);
    ObjectMapper mapper = new ObjectMapper();

    return mapper.writeValueAsString(list);
  }
  
  @RequestMapping("/bookmark/bookmarkView")
  public String bookmarkView(){
    return "bookmark/bookmarkView";
  }

  @RequestMapping("/bookmark/toggleBookmark")
  public String toggleBookmark(Member m, @RequestParam(value = "toggleOption") String toggleOption,
                                         @RequestParam(value = "parkingCode") String parkingCode)
                                             throws JsonProcessingException {
//    String toggleOption = request.getParameter("toggleOption");
//    String userCode = request.getParameter("userCode");
//    String parkingCode = request.getParameter("parkingCode");
    
    int result = 0;
    Bookmark b = new Bookmark();
    b.setBookmarkUserCode(m.getUserCode());
    b.setBookmarkParkingCode(parkingCode);

    if(toggleOption.equals("insert")) {
      result = service.insertBookmark(b);
    }
    else if(toggleOption.equals("delete")) {
      result = service.deleteBookmark(b);
    }
    
    List<Bookmark> bookmarkList = service.selectBookmarkList(m);

    //jackson은 gson과 비슷한 역할, better functionality
    //mapper.readValue(json값, vo클래스);
    ObjectMapper mapper = new ObjectMapper();

    return mapper.writeValueAsString(bookmarkList);
  }

}
