package com.parking.bookmark.model.service;

import java.util.List;

import com.parking.api.model.vo.ParkingSeoul;
import com.parking.bookmark.model.vo.Bookmark;
import com.parking.member.model.vo.Member;

public interface BookmarkService {
  List<Bookmark> selectBookmarkList(Member m);

  List<ParkingSeoul> selectBookmarkParkingList(Member m);
  
  int insertBookmark(Bookmark b);
  
  int deleteBookmark(Bookmark b);
}
