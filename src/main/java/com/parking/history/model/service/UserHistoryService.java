package com.parking.history.model.service;

import static com.parking.common.template.JDBCTemplate.close;
import static com.parking.common.template.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.parking.api.model.vo.Parking;
import com.parking.history.model.dao.UserHistoryDao;
import com.parking.history.model.vo.UserHistory;

public class UserHistoryService {
  private UserHistoryDao dao = new UserHistoryDao();

  
  public UserHistory selectUserHistoryNo(int no) {
    Connection conn = getConnection();
    
    UserHistory h = dao.selectUserHistoryNo(conn, no);

    close(conn);
    
    return h;
  }

  public UserHistory selectUserHistory(String userCode, String parkingCode){
    Connection conn = getConnection();
    
    UserHistory h = dao.selectUserHistory(conn, userCode, parkingCode);

    close(conn);
    
    return h;
  }

  public Parking selectParking(String userCode, String parkingCode){
    Connection conn = getConnection();
    
    Parking p = dao.selectParking(conn, userCode, parkingCode);

    close(conn);
    
    return p;
  }

  public List<UserHistory> selectUserHistoryList(String userCode){
    Connection conn = getConnection();
    
    List<UserHistory> list = dao.selectUserHistoryList(conn, userCode);

    close(conn);
    
    return list;
  }

  public List<Parking> selectUserHistoryParkingList(String userCode){
    Connection conn = getConnection();
    
    List<Parking> list = dao.selectUserHistoryParkingList(conn, userCode);

    close(conn);
    
    return list;
  }
}
