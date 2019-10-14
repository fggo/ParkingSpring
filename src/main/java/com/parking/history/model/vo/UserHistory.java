package com.parking.history.model.vo;

import java.sql.Date;

public class UserHistory {
  
  private int userHistoryNo;
  private String userHistoryUserCode;
  private String userHistoryParkingCode;
  private Date userHistoryParkingDate;
  private int userHistoryPayment;
  
  public UserHistory() {
    // TODO Auto-generated constructor stub
  }
  
  public UserHistory(int userHistoryNo, String userHistoryUserCode, String userHistoryParkingCode, Date userHistoryParkingDate, int userHistoryPayment) {
    super();
    this.userHistoryNo = userHistoryNo;
    this.userHistoryUserCode = userHistoryUserCode;
    this.userHistoryParkingCode = userHistoryParkingCode;
    this.userHistoryParkingDate = userHistoryParkingDate;
    this.userHistoryPayment = userHistoryPayment;
  }

  @Override
  public String toString() {
    return "UserHistory [userHistoryNo=" + userHistoryNo + ", userHistoryUserCode=" + userHistoryUserCode
        + ", userHistoryParkingCode=" + userHistoryParkingCode + ", userHistoryParkingDate=" + userHistoryParkingDate
        + ", userHistoryPayment=" + userHistoryPayment + "]";
  }

  public int getUserHistoryNo() { return userHistoryNo; }
  public void setUserHistoryNo(int userHistoryNo) { this.userHistoryNo = userHistoryNo; }
  public String getUserHistoryUserCode() { return userHistoryUserCode; }
  public void setUserHistoryUserCode(String userHistoryUserCode) { this.userHistoryUserCode= userHistoryUserCode; }
  public String getUserHistoryParkingCode() { return userHistoryParkingCode; }
  public void setUserHistoryParkingCode(String userHistoryParkingCode) { this.userHistoryParkingCode = userHistoryParkingCode; }
  public Date getUserHistoryParkingDate() { return userHistoryParkingDate; }
  public void setUserHistoryParkingDate(Date userHistoryParkingDate) { this.userHistoryParkingDate = userHistoryParkingDate; }
  public int getUserHistoryPayment() { return userHistoryPayment; } 
  public void setUserHistoryPayment(int userHistoryPayment) { this.userHistoryPayment = userHistoryPayment; }
  
}
