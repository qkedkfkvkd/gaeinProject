package com.cafe24.smart_academy.academy_manage.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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
	
	public String parentByPhone(String inputParentPhone);
	// 관리자가 학생 등록시 학부모 테이블에서 유니크값인 폰번호 중복 체크
	
	public int addMemberLogin(MemberLogin loginInfo);
	// 관리자가 학생 혹은 강사 등록할 때 회원 등록 처리 (로그인 테이블 먼저 등록 처리)
	
	public int addMember(Member memberInfo);
	// 관리자가 학생 혹은 강사 등록할 때 회원 등록 처리 (로그인 테이블 먼저 등록 후 회원 신상정보 등록)
	
	public int addParent(Parent parent);
	// 관리자가 학생 등록할 때 학부모 등록 처리
	
	public List<Map<String, Object>> listStudentInfo();
	// 관리자가 학생 목록 페이지로 이동할 때 가져올 목록
	
	public PaymentInfo paymentInfoById(String memberId);
	// 관리자 전용 특정 학생의 결제정보 가져오기
	
	public int addPaymentInfo(PaymentInfo paymentInfo);
	// 관리자 전용 특정 학생 결제정보 입력 처리
	
	public Member studentInfoIdNameBirthById(String memberId);
	// 관리자 상담관리 페이지에 보여줄 특정 학생의 이름과 생년월일 가져오기
	
	public List<Map<String, Object>> oneStudentCounselHistoryList(String memberId);
	// 관리자가 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 해당 학생 상담내역 리스트

	public List<Map<String, Object>> oneStudentCounselAppointmentList(String memberId);
	// 관리자가 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 해당 학생 상담예약현황 리스트
	
}
