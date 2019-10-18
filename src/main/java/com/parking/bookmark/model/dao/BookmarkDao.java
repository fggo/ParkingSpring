package com.parking.bookmark.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.parking.bookmark.model.vo.Bookmark;

public interface BookmarkDao {
	List<Bookmark> selectBookmarkList(SqlSessionTemplate session, String userCode);

}
