package com.parking.member.model.vo;

import java.sql.Date;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("session")
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

}