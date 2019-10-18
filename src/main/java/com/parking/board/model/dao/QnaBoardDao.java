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

import com.parking.board.model.vo.QnaBoard;

public class QnaBoardDao {
  private Properties prop = new Properties();

  public QnaBoardDao() {
    try {
      String path = QnaBoardDao.class.getResource("/sql/board/qnaboard-query.properties").getPath();
      prop.load(new FileReader(path));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
  
  public List<QnaBoard> selectQnaBoardList(Connection conn, int cPage, int numPerPage){
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    List<QnaBoard> list = new ArrayList<QnaBoard>();
    QnaBoard q = null;
    
    int start = (cPage-1)*numPerPage + 1;
    int end = cPage*numPerPage;

    String sql = prop.getProperty("selectQnaBoardList");
    
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, start);
      pstmt.setInt(2, end);

      rs = pstmt.executeQuery();
      
      while(rs.next()) {
        q = new QnaBoard();

        q.setQnaNo(rs.getInt("qnano"));
        q.setQnaTitle(rs.getString("qnatitle"));
        q.setUserCode(rs.getString("qnausercode"));
        q.setQnaContent(rs.getString("qnacontent"));

        Timestamp timestamp = rs.getTimestamp("qnacreateddate");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        q.setQnaCreatedDate(new java.sql.Date(date.getTime()));

        q.setQnaCreatedDate(rs.getDate("qnacreateddate"));
        q.setQnaReadcount(rs.getInt("qnareadcount"));
        
        list.add(q);
      }
      
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }
    
    return list;
  }

  public QnaBoard selectQnaBoard(Connection conn, int qnaNo) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    QnaBoard q = null;
    String sql = prop.getProperty("selectQnaBoard");
    
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, qnaNo);

      rs = pstmt.executeQuery();
      
      if(rs.next()) {
        q = new QnaBoard();
        
        q.setQnaNo(rs.getInt("qnano"));
        q.setQnaTitle(rs.getString("qnatitle"));
        q.setQnaTitle(rs.getString("qnatitle"));
        q.setQnaContent(rs.getString("qnacontent"));

        Timestamp timestamp = rs.getTimestamp("qnacreateddate");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        q.setQnaCreatedDate(new java.sql.Date(date.getTime()));

        q.setQnaReadcount(rs.getInt("qnareadcount"));
      }

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return q;
    
  }

  public int selectCountQnaBoard(Connection conn) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int count = 0;
    String sql = prop.getProperty("selectCountQnaBoard");
    
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
  
  public int insertQnaBoard(Connection conn, QnaBoard q) {
    PreparedStatement pstmt = null;
    int result = 0;
    String sql = prop.getProperty("insertQnaboard");
    
    try {
      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, q.getQnaTitle());
      pstmt.setString(2, q.getUserCode());
      pstmt.setString(3, q.getQnaContent());
//      qnano NUMBER(5) NOT NULL,
//      qnatitle VARCHAR2(50) NOT NULL,
//      qnausercode CHAR(6) NOT NULL,
//      qnacontent VARCHAR2(300) NOT NULL,
//      qnacreateddate DATE DEFAULT SYSDATE,
//      qnareadcount NUMBER DEFAULT 0

      result = pstmt.executeUpdate();

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(pstmt);
    }
    
    return result;
  }

  public List<QnaBoard> selectPopularBoard(Connection conn)
  {
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  List<QnaBoard> list = new ArrayList<QnaBoard>();
	  QnaBoard qb = null;
	  String sql = prop.getProperty("selectPopularBoard");
	  try
	  {
		  pstmt = conn.prepareStatement(sql);
		  rs = pstmt.executeQuery();
		  while(rs.next())
		  {
			  qb = new QnaBoard();
			  qb.setQnaNo(rs.getInt("QNANO"));
			  qb.setQnaTitle(rs.getString("QNATITLE"));
			  qb.setUserCode(rs.getString("QNAUSERCODE"));
			  qb.setQnaCreatedDate(rs.getDate("QNACREATEDDATE"));
			  qb.setQnaReadcount(rs.getInt("QNAREADCOUNT"));		  
			  list.add(qb);
		  }
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
	  }finally
	  {
		  close(rs);
		  close(pstmt);
	  }
	  
	  return list;
  }
  
}
