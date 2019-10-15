package com.parking.member.model.vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Member {
  private String userCode;
  private String userEmail;
  private String userPw;
  private String userPhone;
  private String userName;
  private String userAddr;
  private Date userCreatedDate;
  private Date userLoginDate;
  private int userSmsYn;
  private int userEmailYn;
  private int userEmailVerified;
  private String userSnsAccount;
  private String userOriginalFilename;
  private String userRenamedFilename;

  public Member() {
    // TODO Auto-generated constructor stub
  }
  
  public Member(String userCode, String userEmail, String userPw, String userPhone, String userName, String userAddr,
      Date userCreatedDate, Date userLoginDate, int userSmsYn, int userEmailYn, int userEmailVerified, String userSnsAccount,
      String userOriginalFilename, String userRenamedFilename) {
    super();
    this.userCode = userCode;
    this.userEmail = userEmail;
    this.userPw = userPw;
    this.userPhone = userPhone;
    this.userName = userName;
    this.userAddr = userAddr;
    this.userCreatedDate = userCreatedDate;
    this.userLoginDate = userLoginDate;
    this.userSmsYn = userSmsYn;
    this.userEmailYn = userEmailYn;
    this.userEmailVerified = userEmailVerified;
    this.userSnsAccount = userSnsAccount;
    this.userOriginalFilename = userOriginalFilename;
    this.userRenamedFilename = userRenamedFilename;
  }
  
  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String usercreateddate = sdf.format(userCreatedDate);
    String userlogindate = userLoginDate !=null? sdf.format(userLoginDate): null;

    return "Member [userCode=" + userCode + ", userEmail=" + userEmail + ", userPw=" + userPw + ", userPhone=" + userPhone + ", userName="
        + userName + ", userAddr=" + userAddr + ", userCreatedDate=" + usercreateddate + ", userLoginDate=" + userlogindate + ", userSmsYn="
        + userSmsYn + ", userEmailYn=" + userEmailYn + ", userEmailVerified=" + userEmailVerified + ", userSnsAccount="
        + userSnsAccount + ", userOriginalFilename=" + userOriginalFilename + ",userRenamedFilename=" + userRenamedFilename  + "]";
  }
  
  

  public String getUserCode() { return userCode; } 
  public void setUserCode(String userCode) { this.userCode = userCode; } 
  public String getUserEmail() { return userEmail; } 
  public void setUserEmail(String userEmail) { this.userEmail = userEmail; } 
  public String getUserPw() { return userPw; } 
  public void setUserPw(String userPw) { this.userPw = userPw; } 
  public String getUserPhone() { return userPhone; } 
  public void setUserPhone(String userPhone) { this.userPhone = userPhone; } 
  public String getUserName() { return userName; } 
  public void setUserName(String userName) { this.userName = userName; } 
  public String getUserAddr() { return userAddr; } 
  public void setUserAddr(String userAddr) { this.userAddr = userAddr; } 
  public Date getUserCreatedDate() { return userCreatedDate; } 
  public void setUserCreatedDate(Date userCreatedDate) { this.userCreatedDate = userCreatedDate; } 
  public Date getUserLoginDate() { return userLoginDate; } 
  public void setUserLoginDate(Date userLoginDate) { this.userLoginDate = userLoginDate; } 
  public int getUserSmsYn() { return userSmsYn; } 
  public void setUserSmsYn(int userSmsYn) { this.userSmsYn = userSmsYn; } 
  public int getUserEmailYn() { return userEmailYn; } 
  public void setUserEmailYn(int userEmailYn) { this.userEmailYn = userEmailYn; }
  public int getUserEmailVerified() { return userEmailVerified; } 
  public void setUserEmailVerified(int userEmailVerified) { this.userEmailVerified = userEmailVerified; }
  public String getUserSnsAccount() { return userSnsAccount; } 
  public void setUserSnsAccount(String userSnsAccount) { this.userSnsAccount = userSnsAccount; }
  public String getUserOriginalFilename() { return userOriginalFilename; } 
  public void setUserOriginalFilename(String userOriginalFilename) { this.userOriginalFilename = userOriginalFilename; } 
  public String getUserRenamedFilename() { return userRenamedFilename; } 
  public void setUserRenamedFilename(String userRenamedFilename) { this.userRenamedFilename = userRenamedFilename; } 

}