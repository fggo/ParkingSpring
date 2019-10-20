package com.parking.bookmark.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.parking.api.model.vo.ParkingSeoul;
import com.parking.bookmark.model.vo.Bookmark;
import com.parking.member.model.vo.Member;

@Repository
public class BookmarkDaoImpl implements BookmarkDao {

  @Override
  public List<Bookmark> selectBookmarkList(SqlSessionTemplate session, Member m) {
    return session.selectList("bookmark.selectBookmarkList", m);
  }

  @Override
  public List<ParkingSeoul> selectBookmarkParkingList(SqlSessionTemplate session, Member m) {
    return session.selectList("bookmark.selectBookmarkParkingList", m);
  }

  @Override
  public int insertBookmark(SqlSessionTemplate session, Bookmark b) {
    return session.insert("bookmark.insertBookmark", b);
  }

  @Override
  public int deleteBookmark(SqlSessionTemplate session, Bookmark b) {
    return session.insert("bookmark.deleteBookmark", b);
  }
  
}