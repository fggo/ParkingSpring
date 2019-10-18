package com.parking.bookmark.model.service;

import static com.parking.common.template.JDBCTemplate.close;
import static com.parking.common.template.JDBCTemplate.commit;
import static com.parking.common.template.JDBCTemplate.getConnection;
import static com.parking.common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.api.model.vo.Parking;
import com.parking.bookmark.model.dao.BookmarkDao;
import com.parking.bookmark.model.dao.BookmarkDaoImpl;
import com.parking.bookmark.model.vo.Bookmark;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	
  @Autowired
  BookmarkDao dao;
  
  @Autowired
  SqlSessionTemplate session;
  
  
  @Override
	public List<Bookmark> selectBookmarkList(String userCode) {
		
		return dao.selectBookmarkList(session, userCode);
	}
  
  
//  public Bookmark selectBookmark(String userCode, String parkingCode) {
//    Connection conn = getConnection();
//
//    Bookmark b = dao.selectBookmark(conn, userCode, parkingCode);
//
//    close(conn);
//
//    return b;
//  }
//  
//  public List<Bookmark> selectBookmarkList(String userCode){
//    Connection conn = getConnection();
//    
//    List<Bookmark> list = dao.selectBookmarkList(conn, userCode);
//    
//    close(conn);
//    
//    return list;
//  }
//  
//  public List<Parking> selectBookmarkParkingList(String userCode){
//    Connection conn = getConnection();
//    
//    List<Parking> list = dao.selectBookmarkParkingList(conn, userCode);
//    
//    close(conn);
//    
//    return list;
//  }
//
//  public int insertBookmark(String userCode, String parkingCode) {
//    Connection conn = getConnection();
//    
//    int result = dao.insertBookmark(conn, userCode, parkingCode);
//    if(result > 0)
//      commit(conn);
//    else
//      rollback(conn);
//    
//    close(conn);
//    
//    return result;
//  }
//  
//  public int deleteBookmark(String userCode, String parkingCode) {
//    Connection conn = getConnection();
//    
//    int result = dao.deleteBookmark(conn, userCode, parkingCode);
//    if(result > 0)
//      commit(conn);
//    else
//      rollback(conn);
//    
//    close(conn);
//    
//    return result;
//  }
}
