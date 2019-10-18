package com.parking.bookmark.model.dao;

import static com.parking.common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.parking.api.model.vo.Parking;
import com.parking.bookmark.model.vo.Bookmark;

@Repository
public class BookmarkDaoImpl implements BookmarkDao {
	
	
	@Override
	public List<Bookmark> selectBookmarkList(SqlSessionTemplate session, String userCode) {
		
		return session.selectList("bookmark.selectBookmarkList", userCode);
	}
	
	
//  private Properties prop = new Properties();
//
//  public BookmarkDaoImpl() {
//    String path = BookmarkDaoImpl.class.getResource("/sql/bookmark/bookmark-query.properties").getPath();
//    try {
//      prop.load(new FileReader(path));
//    } catch(IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  public Bookmark selectBookmark(Connection conn, String userCode, String parkingCode) {
//    Bookmark b = null;
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//    String sql = prop.getProperty("selectBookmark");
//
//    try {
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setString(1, userCode);
//      pstmt.setString(2, parkingCode);
//      rs = pstmt.executeQuery();
//
//      if(rs.next()) {
//        b = new Bookmark();
//        
//        b.setBookmarkNo(rs.getInt("bookmarkno"));
//        b.setBookmarkUserCode(rs.getString("bookmarkusercode"));
//        b.setBookmarkParkingCode(rs.getString("bookmarkparkingcode"));
//      }
//    } catch(SQLException e) {
//      e.printStackTrace();
//    } finally {
//      close(rs);
//      close(pstmt);
//    }
//
//    return b;
//  }
//
//  public List<Bookmark> selectBookmarkList(Connection conn, String userCode){
//    PreparedStatement pstmt = null;
//    ResultSet rs =  null;
//    String sql = prop.getProperty("selectBookmarkList");
//    List<Bookmark> list = new ArrayList<Bookmark>();
//    Bookmark b = null;
//
//    try {
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setString(1, userCode);
//
//      rs = pstmt.executeQuery();
//      while(rs.next()) {
//        b = new Bookmark();
//
//        b.setBookmarkNo(rs.getInt("bookmarkno"));
//        b.setBookmarkParkingCode(rs.getString("bookmarkparkingcode"));
//        b.setBookmarkUserCode(rs.getString("bookmarkusercode"));
//        
//        list.add(b);
//      }
//    } catch(SQLException e) {
//      e.printStackTrace();
//    } finally {
//      close(rs);
//      close(pstmt);
//    }
//
//    return list;
//  }
//
//  public List<Parking> selectBookmarkParkingList(Connection conn, String userCode){
//    PreparedStatement pstmt = null;
//    ResultSet rs =  null;
//    String sql = prop.getProperty("selectBookmarkParkingList");
//    List<Parking> list = new ArrayList<Parking>();
//    Parking p = null;
//
//    try {
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setString(1, userCode);
//
//      rs = pstmt.executeQuery();
//      while(rs.next()) {
//        p = new Parking();
//
//        p.setParkingCode(rs.getString("psparkingcode"));
//        p.setParkingName(rs.getString("psname"));
//        p.setAddr(rs.getString("psaddr"));
//        p.setLatitude(rs.getDouble("pslatitude"));
//        p.setLongitude(rs.getDouble("pslongitude"));
//
//        list.add(p);
//      }
//    } catch(SQLException e) {
//      e.printStackTrace();
//    } finally {
//      close(rs);
//      close(pstmt);
//    }
//
//    return list;
//  }
//
//  public int insertBookmark(Connection conn, String userCode, String parkingCode) {
//    PreparedStatement pstmt = null;
//    int result = 0;
//    String sql = prop.getProperty("insertBookmark");
//    
//    try {
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setString(1, userCode);
//      pstmt.setString(2, parkingCode);
//
//      result = pstmt.executeUpdate();
//
//    } catch(SQLException e) {
//      e.printStackTrace();
//    } finally {
//      close(pstmt);
//    }
//    
//    return result;
//  }
//
//  public int deleteBookmark(Connection conn, String userCode, String parkingCode) {
//    PreparedStatement pstmt = null;
//    int result = 0;
//    String sql = prop.getProperty("deleteBookmark");
//    
//    try {
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setString(1, userCode);
//      pstmt.setString(2, parkingCode);
//
//      result = pstmt.executeUpdate();
//
//    } catch(SQLException e) {
//      e.printStackTrace();
//    } finally {
//      close(pstmt);
//    }
//    
//    return result;
//  }
}