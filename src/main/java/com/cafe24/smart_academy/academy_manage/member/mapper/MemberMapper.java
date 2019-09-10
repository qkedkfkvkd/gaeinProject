package com.cafe24.smart_academy.academy_manage.member.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;

@Mapper
public interface MemberMapper {
	public String memberLoginInfoById(String memberId);
	// 로그인 테이블에서 아이디 존재 여부 확인
	
	public MemberLogin memberLogin(MemberLogin login);
	// 로그인 체크 - 비밀번호까지 일치하는지 확인
	// 회원 가입시 아이디 및 비밀번호값이 들어가는 테이블
	
	public Map<String, Object> memberLoginInfo(String memberId);
	// 로그인 됬을 시 기본키인 아이디값으로 정보 가져오기,
	// 회원가입 시 로그인 테이블 먼저 insert 해준 후 신상정보 입력하기
	
	public String memberByEmail(String memberEmail);
	// 관리자가 회원등록할 시 회원신상정보 테이블에서 유니크값인 이메일이 중복되는지 확인
	
	public int addMemberLogin(MemberLogin loginInfo);
	// 관리자가 회원 등록할 때 회원 등록 처리 (로그인 테이블 먼저 등록 처리)
	
	public int addMember(Member memberInfo);
	// 관리자가 회원 등록할 때 회원 등록 처리 (로그인 테이블 먼저 등록 후 회원 신상정보 등록)
}
