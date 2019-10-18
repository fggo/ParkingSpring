package com.parking.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.parking.member.model.vo.Member;


public interface MemberDao{
  
  Map<String, Object> selectEmail(SqlSessionTemplate session, Member m);

  Member selectMemberEmail(SqlSessionTemplate session, String email);

  int insertMember(SqlSessionTemplate session, Member m);
  
  Map<String, Object> selectUserCode(SqlSessionTemplate session, Member m);
  
  boolean updateLoginDate(SqlSessionTemplate session, Member m);

  int updateMember(SqlSessionTemplate session, Member m);

  int deleteMember(SqlSessionTemplate session, Member m);

  int activateaccount(SqlSessionTemplate session, String decryptedcode);

  int changepassword(SqlSessionTemplate session, Member m);
}



