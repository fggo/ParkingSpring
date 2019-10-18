package com.parking.admin.model.service;

import static com.parking.common.template.JDBCTemplate.close;
import static com.parking.common.template.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.parking.admin.model.dao.AdminDao;
import com.parking.member.model.vo.Member;

public class AdminService {
  private AdminDao dao = new AdminDao();

  public int selectCountMember() {
    Connection conn = getConnection();
    
    int count = dao.selectCountMember(conn);
    
    close(conn);
    
    return count;
  }
  
  public List<Member> selectListPage(int cPage, int numPerPage){
    Connection conn = getConnection();
    
    List<Member> list = dao.selectListPage(conn, cPage, numPerPage);

    close(conn);
    
    return list;
  }

  /* overload */
  public int selectCountMember(String searchType, String keyword) {
    Connection conn = getConnection();
    
    int count = dao.selectCountMember(conn, searchType, keyword);
    
    close(conn);
    
    return count;
  }
  
  /* overload */
  public List<Member> selectListPage(String searchType, String keyword, int cPage, int numPerPage){
    Connection conn = getConnection();
    
    List<Member> list = dao.selectListPage(conn, searchType, keyword, cPage, numPerPage);

    close(conn);
    
    return list;
  }
}
