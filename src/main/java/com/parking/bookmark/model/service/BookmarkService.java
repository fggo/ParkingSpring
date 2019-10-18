package com.parking.bookmark.model.service;

import java.util.List;

import com.parking.bookmark.model.vo.Bookmark;

public interface BookmarkService {
	List<Bookmark> selectBookmarkList(String userCode);

}
