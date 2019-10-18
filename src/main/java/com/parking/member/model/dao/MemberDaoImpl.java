package com.parking.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.parking.member.model.vo.Member;


@Repository
public class MemberDaoImpl implements MemberDao{

  @Override
  public Map<String, Object> selectEmail(SqlSessionTemplate session, Member m) {
    return session.selectOne("selectEmail", m);
  }

  @Override
  public Member selectMemberEmail(SqlSessionTemplate session, String email) {
    return session.selectOne("selectMemberEmail", email);
  }

  @Override
  public int insertMember(SqlSessionTemplate session, Member m) {
    return session.insert("insertMember", m);
  }

  @Override
  public Map<String, Object> selectUserCode(SqlSessionTemplate session, Member m) {
    return session.selectOne("selectUserCode", m);
  }

  @Override
  public boolean updateLoginDate(SqlSessionTemplate session, Member m) {
    int result = session.update("updateLoginDate", m);
    return result == 1;
  }

  @Override
  public int updateMember(SqlSessionTemplate session, Member m) {
    return session.update("updateMember", m);
  }

  @Override
  public int deleteMember(SqlSessionTemplate session, Member m) {
    return session.delete("deleteMember", m);
  }

  @Override
  public int activateaccount(SqlSessionTemplate session, String decryptedcode) {
    return session.update("activateaccount", decryptedcode);
  }

  @Override
  public int changepassword(SqlSessionTemplate session, Member m) {
    return session.update("changepassword", m);
  }
  
}



