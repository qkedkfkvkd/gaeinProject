package com.cafe24.smart_academy.academy_manage.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.member.vo.CounselResult;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselType;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.Parent;
import com.cafe24.smart_academy.academy_manage.member.vo.PaymentInfo;

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
	// 관리자가 학생 혹은 강사를 등록할 때 회원신상정보 테이블에서 유니크값인 이메일이 중복되는지 확인
	
	public List<Map<String, Object>> listCounselStandard();
	// 관리자 : 상담기준코드 리스트 가져오기
	
	public int counselTypeListSize();
	// 관리자 : 상담구분테이블에 레코드가 존재하는지 확인
	
	public String counselTypeByCounselTypeNo(String inputCounselTypeNo);
	// 관리자 : 상담구분테이블의 기본키인 상담구분코드 중복 확인
	
	public int addCounselType(CounselType counselType);
	// 관리자 : 상담구분테이블 추가처리
	
	public String counselResultByCounselResultNo(String inputCounselResultNo);
	// 관리자 : 상담결과코드 테이블에서 상담결과코드 중복확인
	
	public int addCounselResult(CounselResult counselResult);
	// 관리자 : 상담결과테이블 추가처리
	
	public List<CounselType> counselTypeList();
	// 관리자 : 상담구분코드 리스트 가져오기
	
	public List<CounselResult> counselResultList();
	// 관리자 : 상담결과코드 리스트 가져오기
	
}
