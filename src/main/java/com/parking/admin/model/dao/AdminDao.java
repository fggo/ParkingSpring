package com.parking.admin.model.dao;


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

import com.parking.member.model.vo.Member;

public class AdminDao {
  private Properties prop = new Properties();

  public AdminDao() {
    String path = AdminDao.class.getResource("/sql/admin/admin-query.properties").getPath();
    try {
      prop.load(new FileReader(path));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
  
  public int selectCountMember(Connection conn) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = prop.getProperty("selectCountMember");
    int count =0;
    
    try {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if(rs.next()) {
        count = rs.getInt("cnt"); // count = rs.getInt(1);
      }

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return count;
  }
  
  public List<Member> selectListPage(Connection conn, int cPage, int numPerPage){
    PreparedStatement pstmt = null;
    ResultSet rs =  null;
    String sql = prop.getProperty("selectListPage");
    List<Member> list = new ArrayList<Member>();
    Member m = null;

    int start = (cPage-1)*numPerPage +1;
    int end = cPage*numPerPage;
    
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, start);
      pstmt.setInt(2, end);

      rs = pstmt.executeQuery();
      while(rs.next()) {
        m = new Member();

        m.setUserCode(rs.getString("user_code"));
        m.setUserEmail(rs.getString("user_email"));
        m.setUserPw(rs.getString("user_pw"));
        m.setUserPhone(rs.getString("user_phone"));
        m.setUserName(rs.getString("user_name"));
        m.setUserAddr(rs.getString("user_addr"));

        Timestamp timestamp = rs.getTimestamp("user_created_date");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        m.setUserCreatedDate(new java.sql.Date(date.getTime()));

        timestamp = rs.getTimestamp("user_login_date");
        if(timestamp != null) {
          date = new java.util.Date(timestamp.getTime());
          m.setUserLoginDate(new java.sql.Date(date.getTime()));
        }

        m.setUserSmsYn(rs.getInt("user_sms_yn"));
        m.setUserEmailYn(rs.getInt("user_email_yn"));
        m.setUserEmailVerified(rs.getInt("user_email_verified"));
        m.setUserSnsAccount(rs.getString("user_sns_account"));
        m.setUserOriginalFilename(rs.getString("user_original_filename"));
        m.setUserRenamedFilename(rs.getString("user_renamed_filename"));
        
        list.add(m);
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return list;
  }

  /* overload */
  public int selectCountMember(Connection conn, String searchType, String keyword) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
//    String sql = prop.getProperty("selectCountMemberPartial");
    String sql = "select count(*) as cnt from member where upper("+ searchType+ ") like '%" + keyword.toUpperCase() + "%'";
    int count =0;
    
    try {
      pstmt = conn.prepareStatement(sql);
//      pstmt.setString(1, searchType);
//      pstmt.setString(2, "'%" + keyword + "%'");
      rs = pstmt.executeQuery();
      if(rs.next()) {
        count = rs.getInt("cnt"); // count = rs.getInt(1);
      }

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return count;
  }

  /* overload */
  public List<Member> selectListPage(Connection conn, String searchType, String keyword, int cPage, int numPerPage){
    PreparedStatement pstmt = null;
    ResultSet rs =  null;

    int start = (cPage-1)*numPerPage +1;
    int end = cPage*numPerPage;

//    String sql = prop.getProperty("selectListPagePartial");
    String sql="select * from ("
        + "select rownum as rnum, a.* from("
        + "select * from member where upper("
        + searchType+") like '%"+keyword.toUpperCase()+"%' )a) "
        + "where rnum between "+start+" and "+end;

    List<Member> list = new ArrayList<Member>();
    Member m = null;
    
    try {
      pstmt = conn.prepareStatement(sql);
//      pstmt.setString(1, searchType);
//      pstmt.setString(2, "'%" + keyword + "%'");
//      pstmt.setInt(1, start);
//      pstmt.setInt(2, end);

      rs = pstmt.executeQuery();
      
      while(rs.next()) {
        m = new Member();

        m.setUserCode(rs.getString("user_code"));
        m.setUserEmail(rs.getString("user_email"));
        m.setUserPw(rs.getString("user_pw"));
        m.setUserPhone(rs.getString("user_phone"));
        m.setUserName(rs.getString("user_name"));
        m.setUserAddr(rs.getString("user_addr"));

        Timestamp timestamp = rs.getTimestamp("user_created_date");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        m.setUserCreatedDate(new java.sql.Date(date.getTime()));

        timestamp = rs.getTimestamp("user_login_date");
        if(timestamp != null) {
          date = new java.util.Date(timestamp.getTime());
          m.setUserLoginDate(new java.sql.Date(date.getTime()));
        }

        m.setUserSmsYn(rs.getInt("user_sms_yn"));
        m.setUserEmailYn(rs.getInt("user_email_yn"));
        m.setUserEmailVerified(rs.getInt("user_email_verified"));
        m.setUserSnsAccount(rs.getString("user_sns_account"));
        m.setUserOriginalFilename(rs.getString("user_original_filename"));
        m.setUserRenamedFilename(rs.getString("user_renamed_filename"));
        
        list.add(m);
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