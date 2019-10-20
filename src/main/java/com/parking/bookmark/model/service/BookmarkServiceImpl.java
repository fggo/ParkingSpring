package com.parking.bookmark.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.api.model.vo.ParkingSeoul;
import com.parking.bookmark.model.dao.BookmarkDao;
import com.parking.bookmark.model.vo.Bookmark;
import com.parking.member.model.vo.Member;

@Service
public class BookmarkServiceImpl implements BookmarkService {
  
  @Autowired
  BookmarkDao dao;
  
  @Autowired
  SqlSessionTemplate session;
  
  @Override
  public List<Bookmark> selectBookmarkList(Member m) {
    return dao.selectBookmarkList(session, m);
  }
  
  @Override
  public List<ParkingSeoul> selectBookmarkParkingList(Member m) {
    return dao.selectBookmarkParkingList(session, m);
  }
  
  @Override
  public int insertBookmark(Bookmark b) {
    return dao.insertBookmark(session, b);
  }
  
  @Override
  public int deleteBookmark(Bookmark b) {
    return dao.deleteBookmark(session, b);
  }
  
}
