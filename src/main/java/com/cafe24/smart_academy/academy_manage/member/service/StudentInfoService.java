package com.cafe24.smart_academy.academy_manage.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.member.mapper.StudentInfoMapper;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.Parent;
import com.cafe24.smart_academy.academy_manage.member.vo.PaymentInfo;

@Service
@Transactional
public class StudentInfoService {

	@Autowired
	StudentInfoMapper studentInfoMapper;
	
	// 관리자가 학생 등록시 학부모 테이블에서 유니크값인 폰번호 중복 체크
	public String parentByPhone(String inputParentPhone) {
		return studentInfoMapper.parentByPhone(inputParentPhone);
	}
	
	// 관리자가 회원 등록할 때 회원 등록 처리
	public String addStudent(MemberLogin loginInfo, Member memberInfo, Parent parent) {
		String memberIdchk = studentInfoMapper.memberLoginInfoById(loginInfo.getMemberId());
		// 중복되는 아이디를 입력하여 가입 시도를 하였는가?
		
		String memberEmailChk = studentInfoMapper.memberByEmail(memberInfo.getMemberEmail());
		// 중복되는 이메일을 입력하여 가입 시도를 하였는가?
		
		String studentParentPhoneChk = studentInfoMapper.parentByPhone(parent.getParentPhone());
		// 중복되는 학생의 학부모 휴대폰 번호를 입력하여 가입 시도를 하였는가?
		
		
		String result = null;
		// 널값이 계속 유지되서 리턴되면 입력 성공했다는 뜻
		
		
		if(memberIdchk != null) { // 이미 존재하는 아이디로 회원가입하려고 했을 경우
			result = "idUsed";		// 아이디 사용 중 메세지
			
		} else if(memberEmailChk != null) { // 유니크값인 이메일을 중복 입력하여 가입시도
			result = "emailUsed";	// 이메일 사용 중 메세지
			
		} else if( studentParentPhoneChk != null) { // 유니크값인 학부모 휴대폰 번호를 중복 입력하여 가입시도
			result = "parentPhoneUsed";
			
		} else {
			int check = studentInfoMapper.addMemberLogin(loginInfo);
			// 로그인 테이블 먼저 등록처리한다. (회원 신상정보 테이블의 기본키인 회원 아이디가 로그인 테이블의 회원 아이디를 참조)
			// 로그인 테이블 : 회원 신상정보 테이블 -> 1:1 관계
			
			int memCheck = 0;
			// 회원 신상정보 입력 실패로 초기화
			
			int studentParent = 0;
			// 학부모 입력 실패로 초기화
			
			
			if(check == 1) {  // 로그인 테이블 등록 성공
				memberInfo.setMemberId(loginInfo.getMemberId());
				// 로그인 테이블 객체에서 아이디 값을 꺼내와서 회원 신상정보 테이블의 아이디값에 셋팅
				
				memCheck = studentInfoMapper.addStudent(memberInfo);
				// 회원 신상정보 테이블 등록 처리
				
				parent.setMemberId(loginInfo.getMemberId());
				// 로그인 테이블 객체에서 아이디 값을 꺼내와서 학부모 테이블의 학생 아이디값에 셋팅
				
				studentParent = studentInfoMapper.addParent(parent);
				// 학부모 테이블 등록 처리
			}
			
			if(studentParent == 0 || check == 0 || memCheck == 0) {
				// 학부모 테이블 혹은 로그인 테이블 혹은 회원신상정보 테이블 둘 중 하나라도 입력 실패시
				
				
				// 디비가서 있을지도 모르는 해당 레코드 삭제 코드 추가해야함.
				
				
				result = "insertFail";
				// 입력 실패 메세지
			}
		}
		
		return result;
	}
	
	
	// 관리자 학생 목록 페이지 목록 가져오기
	public List<Map<String, Object>> listStudentInfo() {
		return studentInfoMapper.listStudentInfo();
	}
	
	
	// 관리자 특정 학생 결제정보 가져오기
	public PaymentInfo viewPaymentInfo(String memberId) {
		PaymentInfo paymentInfo = studentInfoMapper.paymentInfoById(memberId);
		// 학생의 아이디를 가지고 결제정보 테이블에서 객체를 얻어온다.
		return paymentInfo;
	}
	
	
	// 관리자 특정 학생 결제정보 입력 처리
	public String addPaymentInfo(PaymentInfo paymentInfo) {
		int result = studentInfoMapper.addPaymentInfo(paymentInfo);
		// 특정학생 결제정보 입력 처리
		
		String message = null;
		// 널값이 그대로 리턴되면 입력 성공
		
		if(result == 0) {  // 입력이 안됬다면
			message = "paymentInfoInsertFail";
			// 입력실패 메세지
		}
		
		return message;
	}
	
	
	// 관리자 상담관리 페이지에 보여줄 특정 학생의 이름과 생년월일 가져오기
	public Member studentInfoIdNameBirthById(String memberId) {
		return studentInfoMapper.studentInfoIdNameBirthById(memberId);
	}
	
	
	// 관리자가 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 해당 학생 상담내역 리스트
	public List<Map<String, Object>> oneStudentCounselHistoryList(String memberId) {
		System.out.println(memberId + " <- memberId   oneStudentCounselHistoryList()   MemberService.java");
		return studentInfoMapper.oneStudentCounselHistoryList(memberId);
	}
	
	
	// 관리자가 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 해당 학생 상담예약현황 리스트
	public List<Map<String, Object>> oneStudentCounselAppointmentList(String memberId) {
		return studentInfoMapper.oneStudentCounselAppointmentList(memberId);
	}
}
