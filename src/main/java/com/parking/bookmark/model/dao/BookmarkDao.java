package com.parking.bookmark.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.parking.api.model.vo.ParkingSeoul;
import com.parking.bookmark.model.vo.Bookmark;
import com.parking.member.model.vo.Member;

public interface BookmarkDao {
  List<Bookmark> selectBookmarkList(SqlSessionTemplate session, Member m);

  List<ParkingSeoul> selectBookmarkParkingList(SqlSessionTemplate session, Member m);
  
  int insertBookmark(SqlSessionTemplate session, Bookmark b);

  int deleteBookmark(SqlSessionTemplate session, Bookmark b);
}
