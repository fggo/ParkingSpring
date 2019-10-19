package com.parking.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.parking.member.model.vo.Member;


@Repository
public class MemberDaoImpl implements MemberDao{

  @Override
  public Map<String, Object> selectEmail(SqlSessionTemplate session, Member m) {
    return session.selectOne("member.selectEmail", m);
  }

  @Override
  public Member selectMemberEmail(SqlSessionTemplate session, String email) {
    return session.selectOne("member.selectMemberEmail", email);
  }

  @Override
  public int insertMember(SqlSessionTemplate session, Member m) {
    return session.insert("member.insertMember", m);
  }

  @Override
  public Map<String, Object> selectUserCode(SqlSessionTemplate session, Member m) {
    return session.selectOne("member.selectUserCode", m);
  }

  @Override
  public boolean updateLoginDate(SqlSessionTemplate session, Member m) {
    int result = session.update("member.updateLoginDate", m);
    return result == 1;
  }

  @Override
  public int updateMember(SqlSessionTemplate session, Member m) {
    return session.update("member.updateMember", m);
  }

  @Override
  public int deleteMember(SqlSessionTemplate session, Member m) {
    return session.delete("member.deleteMember", m);
  }

  @Override
  public int activateaccount(SqlSessionTemplate session, Member m) {
    return session.update("member.activateMember", m);
  }

  @Override
  public int changepassword(SqlSessionTemplate session, Member m) {
    return session.update("member.changepassword", m);
  }
  
}



