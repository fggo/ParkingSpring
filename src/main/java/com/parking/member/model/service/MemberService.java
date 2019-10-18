package com.parking.member.model.service;

import java.util.Map;

import com.parking.member.model.vo.Member;

public interface MemberService {
  public Map<String, Object> selectEmail(Member m);
  public Member selectMemberEmail(String email);
  public int insertMember(Member m);
  public Map<String, Object> selectUserCode(Member m);
  public boolean updateLoginDate(Member m);
  public int updateMember(Member m);
  public int deleteMember(Member m);
  public int activateaccount(String decryptedcode);
	public int changepassword(Member m);
}
