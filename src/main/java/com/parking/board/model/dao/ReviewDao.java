package com.parking.board.model.dao;

import static com.parking.common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.parking.board.model.vo.Review;

public class ReviewDao {
  private Properties prop = new Properties();

  public ReviewDao() {
    try {
      String path = ReviewDao.class.getResource("/sql/board/review-query.properties").getPath();
      prop.load(new FileReader(path));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
  
  public List<Review> selectReviewList(Connection conn, int cPage, int numPerPage){
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    List<Review> list = new ArrayList<Review>();
    Review r = null;
    
    int start = (cPage-1)*numPerPage + 1;
    int end = cPage*numPerPage;

    String sql = prop.getProperty("selectReviewList");
    
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, start);
      pstmt.setInt(2, end);

      rs = pstmt.executeQuery();
      
      while(rs.next()) {
        r = new Review();

        r.setReviewNo(rs.getInt("reviewno"));
        r.setReviewUserHistoryNo(rs.getInt("reviewuserhistoryno"));
        r.setReviewTitle(rs.getString("reviewtitle"));
        r.setReviewContent(rs.getString("reviewcontent"));

        Timestamp timestamp = rs.getTimestamp("reviewcreateddate");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        r.setReviewCreatedDate(new java.sql.Date(date.getTime()));

        r.setReviewRating(rs.getInt("reviewrating"));
        r.setReviewReadcount(rs.getInt("reviewreadcount"));
        
        list.add(r);
      }
      
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }
    
    return list;
  }

  public Review selectReview(Connection conn, int reviewNo){
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    Review r = null;

    String sql = prop.getProperty("selectReview");
    
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, reviewNo);

      rs = pstmt.executeQuery();
      
      if(rs.next()) {
        r = new Review();

        r.setReviewNo(rs.getInt("reviewno"));
        r.setReviewUserHistoryNo(rs.getInt("reviewuserhistoryno"));
        r.setReviewTitle(rs.getString("reviewtitle"));
        r.setReviewContent(rs.getString("reviewcontent"));

        Timestamp timestamp = rs.getTimestamp("reviewcreateddate");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        r.setReviewCreatedDate(new java.sql.Date(date.getTime()));

        r.setReviewRating(rs.getInt("reviewrating"));
        r.setReviewReadcount(rs.getInt("reviewreadcount"));
      }
      
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }
    
    return r;
  }

  public int selectCountReview(Connection conn) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int count = 0;
    String sql = prop.getProperty("selectCountReview");
    
    try {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      
      if(rs.next())
        count = rs.getInt("cnt");

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return count;
  }

  public int insertReview(Connection conn, Review r) {
    PreparedStatement pstmt = null;
    int result = 0;
    String sql = prop.getProperty("insertReview");
    
    try {
      pstmt = conn.prepareStatement(sql);

      pstmt.setInt(1, r.getReviewUserHistoryNo()); //usercode (assigned by sequence)
      pstmt.setString(2, r.getReviewTitle());
      pstmt.setString(3, r.getReviewContent());
      pstmt.setInt(4, r.getReviewRating());
//      insert into review values(DEFAULT,?,?,?,DEFAULT,?,DEFAULT)

      result = pstmt.executeUpdate();

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(pstmt);
    }
    
    return result;
  }
}
