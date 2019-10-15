package com.parking.history.model.dao;


import static common.template.JDBCTemplate.close;

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

import com.parking.api.model.vo.Parking;
import com.parking.history.model.vo.UserHistory;

public class UserHistoryDao {
  private Properties prop = new Properties();

  public UserHistoryDao() {
    String path = UserHistoryDao.class.getResource("/sql/history/userhistory-query.properties").getPath();
    try {
      prop.load(new FileReader(path));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
  
  public UserHistory selectUserHistoryNo(Connection conn, int no) {
    PreparedStatement pstmt = null;
    ResultSet rs =  null;
    String sql = prop.getProperty("selectUserHistoryNo");
    UserHistory h = null;

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, no);

      rs = pstmt.executeQuery();
      if(rs.next()) {
        h = new UserHistory();

        h.setUserHistoryNo(rs.getInt("userhistoryno"));
        h.setUserHistoryUserCode(rs.getString("userhistoryusercode"));
        h.setUserHistoryParkingCode(rs.getString("userhistoryparkingcode"));

        Timestamp timestamp = rs.getTimestamp("userhistoryparkingdate");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        h.setUserHistoryParkingDate(new java.sql.Date(date.getTime()));

        h.setUserHistoryPayment(rs.getInt("userhistorypayment"));
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return h;
  }

  public UserHistory selectUserHistory(Connection conn, String userCode, String parkingCode){
    PreparedStatement pstmt = null;
    ResultSet rs =  null;
    String sql = prop.getProperty("selectUserHistory");
    UserHistory h = null;

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);
      pstmt.setString(2, parkingCode);

      rs = pstmt.executeQuery();
      if(rs.next()) {
        h = new UserHistory();

        h.setUserHistoryNo(rs.getInt("userhistoryno"));
        h.setUserHistoryUserCode(rs.getString("userhistoryusercode"));
        h.setUserHistoryParkingCode(rs.getString("userhistoryparkingcode"));

        Timestamp timestamp = rs.getTimestamp("userhistoryparkingdate");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        h.setUserHistoryParkingDate(new java.sql.Date(date.getTime()));

        h.setUserHistoryPayment(rs.getInt("userhistorypayment"));
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return h;
  }

  public Parking selectParking(Connection conn, String userCode, String parkingCode){
    PreparedStatement pstmt = null;
    ResultSet rs =  null;
    String sql = prop.getProperty("selectParking");
    Parking p = null;

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);
      pstmt.setString(2, parkingCode);

      rs = pstmt.executeQuery();
      while(rs.next()) {
        p = new Parking();

        p.setParkingCode(rs.getString("psparkingcode"));
        p.setParkingName(rs.getString("psname"));
        p.setAddr(rs.getString("psaddr"));
        p.setLatitude(rs.getDouble("pslatitude"));
        p.setLongitude(rs.getDouble("pslongitude"));
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return p;
  }
  
  public List<UserHistory> selectUserHistoryList(Connection conn, String userCode){
    PreparedStatement pstmt = null;
    ResultSet rs =  null;
    String sql = prop.getProperty("selectUserHistoryList");
    List<UserHistory> list = new ArrayList<UserHistory>();
    UserHistory h = null;

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);

      rs = pstmt.executeQuery();
      while(rs.next()) {
        h = new UserHistory();

        h.setUserHistoryNo(rs.getInt("userhistoryno"));
        h.setUserHistoryUserCode(rs.getString("userhistoryusercode"));
        h.setUserHistoryParkingCode(rs.getString("userhistoryparkingcode"));

        Timestamp timestamp = rs.getTimestamp("userhistoryparkingdate");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        h.setUserHistoryParkingDate(new java.sql.Date(date.getTime()));
        
        h.setUserHistoryPayment(rs.getInt("userhistorypayment"));

        list.add(h);
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return list;
  }

  public List<Parking> selectUserHistoryParkingList(Connection conn, String userCode){
    PreparedStatement pstmt = null;
    ResultSet rs =  null;
    String sql = prop.getProperty("selectUserHistoryParkingList");
    List<Parking> list = new ArrayList<Parking>();
    Parking p = null;

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);

      rs = pstmt.executeQuery();
      while(rs.next()) {
        p = new Parking();

        p.setParkingCode(rs.getString("psparkingcode"));
        p.setParkingName(rs.getString("psname"));
        p.setAddr(rs.getString("psaddr"));
        p.setLatitude(rs.getDouble("pslatitude"));
        p.setLongitude(rs.getDouble("pslongitude"));

        list.add(p);
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return list;
  }
}