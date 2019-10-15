package com.parking.member.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.Map;

import com.parking.member.model.dao.MemberDaoImpl;
import com.parking.member.model.vo.Member;

public class MemberService {
  MemberDaoImpl dao = new MemberDaoImpl();
  

  public Member selectEmail(String email) {
    Connection conn = getConnection();
    Member m = dao.selectEmail(conn, email);

    close(conn);

    return m;
  }
  
  public boolean selectCheckEmail(String emailToChk) {
    Connection conn = getConnection();
    boolean result = dao.selectCheckEmail(conn, emailToChk);
    
    close(conn);

    return result;
  }
  
  public int insertMember(Member m) {
    Connection conn = getConnection();

    int result = dao.insertMember(conn, m);
    
    if (result > 0)
      commit(conn);
    else
      rollback(conn);
    
    close(conn);
    
    return result;
  }
  
  public Member selectUserCode(String userCode) {
    Connection conn = getConnection();
    Member m = dao.selectUserCode(conn, userCode);

    close(conn);

    return m;
  }

  public boolean updateLoginDate(String email) {
    Connection conn = getConnection();

    boolean logged = dao.updateLoginDate(conn, email);
    
    if (logged)
      commit(conn);
    else
      rollback(conn);
    
    close(conn);
    
    return logged;
    
  }
  
//  public int updateBoard(Connection conn, Board b, Map<String, String> newAttr) {
  public int updateMember(Member m, Map<String, String> newAttr) {
    Connection conn = getConnection();

    int result = dao.updateMember(conn, m, newAttr);
    
    if(result > 0)
      commit(conn);
    else
      rollback(conn);
    
    close(conn);
    
    return result;
  }

  public int deleteMember(String userCode) {
    Connection conn = getConnection();

    int result = dao.deleteMember(conn, userCode);
    
    if(result > 0)
      commit(conn);
    else
      rollback(conn);
    
    close(conn);
    
    return result;
  }
  
  public int activateaccount(String decryptedcode) {
	    Connection conn = getConnection();

	    int result = dao.activateaccount(conn, decryptedcode);
//	    System.out.println("from dao result : " +result);
	    if (result > 0)
	      commit(conn);
	    else
	      rollback(conn);
	    
	    close(conn);
	    
	    return result;
	  }

	public String getUserEmail(String email) {
	    Connection conn = getConnection();
	   String result = dao.getUserEmail(conn, email);

	    close(conn);

	    return result;
	}

	public int changepassword(String email, String password) {
//			System.out.println(email);
//			System.out.println(password);
		   Connection conn = getConnection();

		    int result = dao.changepassword(conn, email, password);
//		    System.out.println("from dao result : " +result);
		    if (result > 0)
		      commit(conn);
		    else
		      rollback(conn);
		    
		    close(conn);
		    
		    return result;
		
	}

	public boolean fbselectEmail(String useremail) {
		Connection conn = getConnection();
		boolean result = dao.fbselectEmail(conn, useremail);
		if (result)
		      commit(conn);
		    else
		      rollback(conn);
		    
		    close(conn);
		    
		    return result;
	}
}
