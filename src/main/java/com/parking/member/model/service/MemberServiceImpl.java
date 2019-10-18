package com.parking.member.model.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.member.model.dao.MemberDao;
import com.parking.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
  @Autowired
  MemberDao dao;
  
  @Autowired
  SqlSessionTemplate session;
  
  @Override
  public Map<String,Object> selectEmail(Member m) {
    return dao.selectEmail(session, m);
  }

  @Override
  public Member selectMemberEmail(String email) {
    return dao.selectMemberEmail(session, email);
  }
  
  @Override
  public int insertMember(Member m) {
    return dao.insertMember(session, m);
  }
  
  @Override
  public Map<String,Object> selectUserCode(Member m) {
    return dao.selectUserCode(session, m);
  }

  @Override
  public boolean updateLoginDate(Member m) {
    return dao.updateLoginDate(session, m);
  }
  
  @Override
  public int updateMember(Member m) {
    return dao.updateMember(session, m);
  }

  @Override
  public int deleteMember(Member m) {
    return dao.deleteMember(session, m);
  }
  
  @Override
  public int activateaccount(String decryptedcode) {
    return dao.activateaccount(session, decryptedcode);
  }

  @Override
	public int changepassword(Member m) {
		    return dao.changepassword(session, m);
	}

}
