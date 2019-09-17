package com.cafe24.smart_academy.academy_manage.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.member.mapper.MemberMapper;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.Parent;
import com.cafe24.smart_academy.academy_manage.member.vo.PaymentInfo;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	// 로그인 체크 메소드
	public Map<String, Object> memberLogin(MemberLogin login) {
		String memberId = memberMapper.memberLoginInfoById(login.getMemberId());
		// 아이디 존재여부 확인
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(memberId != null) { // 아이디 일치
			MemberLogin loginInfo = memberMapper.memberLogin(login);
			// 비밀번호까지 일치하는지 확인
			
			if(loginInfo != null) { // 비밀번호까지 완전히 일치
				map = memberMapper.memberLoginInfo(login.getMemberId());
				// 로그인 테이블에서 아이디와 권한값을,
				// 회원테이블에서 이름값을 가져온다.(아이디로 조인)
				
			} else { // 비밀번호 불일치
				map.put("result", "비밀번호가 일치하지 않습니다.");
			}
		} else { // 아이디 불일치
			map.put("result", "존재하지 않는 아이디입니다.");
		}
		
		return map;
	}
	
	
	// 관리자가 학생 혹은 강사를 등록할 시 로그인 테이블에서 아이디 중복 체크
	public String memberLoginById(String memberId) {
		return memberMapper.memberLoginInfoById(memberId);
		// 존재하는 아이디인지 아닌지 체크한다.
	}
	
	
	// 관리자가 학생 혹은 강사를 등록할 시 회원신상정보 테이블에서 유니크값인 이메일 중복 체크
	public String memberByEmail(String memberEmail) {
		return memberMapper.memberByEmail(memberEmail);
		// 존재하는 이메일인지 아닌지 체크한다.
	}
	
	
	// 관리자가 학생 등록시 학부모 테이블에서 유니크값인 폰번호 중복 체크
	public String parentByPhone(String inputParentPhone) {
		return memberMapper.parentByPhone(inputParentPhone);
	}
	
	
	// 관리자가 회원 등록할 때 회원 등록 처리
	public String addMember(MemberLogin loginInfo, Member memberInfo, Parent parent) {
		String memberIdchk = memberMapper.memberLoginInfoById(loginInfo.getMemberId());
		// 중복되는 아이디를 입력하여 가입 시도를 하였는가?
		
		String memberEmailChk = memberMapper.memberByEmail(memberInfo.getMemberEmail());
		// 중복되는 이메일을 입력하여 가입 시도를 하였는가?
		
		String studentParentPhoneChk = null;
		
		if(loginInfo.getMemberLevel().equals("학생")) { // 학생으로 가입시도했을 경우
			studentParentPhoneChk = memberMapper.parentByPhone(parent.getParentPhone());
			// 중복되는 학생의 학부모 휴대폰 번호를 입력하여 가입 시도를 하였는가?
		}
		
		
		String result = null;
		// 널값이 계속 유지되서 리턴되면 입력 성공했다는 뜻
		
		
		if(memberIdchk != null) { // 이미 존재하는 아이디로 회원가입하려고 했을 경우
			result = "idUsed";		// 아이디 사용 중 메세지
			
		} else if(memberEmailChk != null) { // 유니크값인 이메일을 중복 입력하여 가입시도
			result = "emailUsed";	// 이메일 사용 중 메세지
			
		} else if(loginInfo.getMemberLevel().equals("학생") && studentParentPhoneChk != null) {
			// 학생으로 등록하려고 했으며 유니크값인 학부모 휴대폰 번호를 중복 입력하여 가입시도
			result = "parentPhoneUsed";
			
		} else {
			int check = memberMapper.addMemberLogin(loginInfo);
			// 로그인 테이블 먼저 등록처리한다. (회원 신상정보 테이블의 기본키인 회원 아이디가 로그인 테이블의 회원 아이디를 참조)
			// 로그인 테이블 : 회원 신상정보 테이블 -> 1:1 관계
			
			int memCheck = 0;
			// 회원 신상정보 입력 실패로 초기화
			
			int studentParent = 1;
			// 학부모 입력 성공으로 초기화 (강사로 등록하는 경우를 대비하여)
			
			if(loginInfo.getMemberLevel().equals("학생")) {
				studentParent = 0; // 학생으로 등록하는 경우라면 입력 실패로 값 조정한다.
			}
			
			if(check == 1) {  // 로그인 테이블 등록 성공
				memberInfo.setMemberId(loginInfo.getMemberId());
				// 로그인 테이블 객체에서 아이디 값을 꺼내와서 회원 신상정보 테이블의 아이디값에 셋팅
				
				memCheck = memberMapper.addMember(memberInfo);
				// 회원 신상정보 테이블 등록 처리
				
				if(loginInfo.getMemberLevel().equals("학생")) {  // 학생 등록시
					parent.setMemberId(loginInfo.getMemberId());
					// 로그인 테이블 객체에서 아이디 값을 꺼내와서 학부모 테이블의 학생 아이디값에 셋팅
					
					studentParent = memberMapper.addParent(parent);
					// 학부모 테이블 등록 처리
				}
			}
			
			if(studentParent == 0 || check == 0 || memCheck == 0) {
				// [(학생 가입시) 학부모 테이블] 혹은 로그인 테이블 혹은 회원신상정보 테이블 둘 중 하나라도 입력 실패시
				
				
				// 디비가서 있을지도 모르는 해당 레코드 삭제 코드 추가해야함.
				
				
				result = "insertFail";
				// 입력 실패 메세지
			}
		}
		
		return result;
	}
	
	
	// 관리자 학생 목록 페이지 목록 가져오기
	public List<Map<String, Object>> listStudentInfo() {
		return memberMapper.listStudentInfo();
	}
	
	
	// 관리자 특정 학생 결제정보 가져오기
	public PaymentInfo viewPaymentInfo(String memberId) {
		PaymentInfo paymentInfo = memberMapper.paymentInfoById(memberId);
		// 학생의 아이디를 가지고 결제정보 테이블에서 객체를 얻어온다.
		return paymentInfo;
	}
	
	
	// 관리자 특정 학생 결제정보 입력 처리
	public String addPaymentInfo(PaymentInfo paymentInfo) {
		int result = memberMapper.addPaymentInfo(paymentInfo);
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
		return memberMapper.studentInfoIdNameBirthById(memberId);
	}
	
	
	// 관리자가 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 해당 학생 상담내역 리스트
	public List<Map<String, Object>> oneStudentCounselHistoryList(String memberId) {
		System.out.println(memberId + " <- memberId   oneStudentCounselHistoryList()   MemberService.java");
		return memberMapper.oneStudentCounselHistoryList(memberId);
	}
	
	
	// 관리자가 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 해당 학생 상담예약현황 리스트
	public List<Map<String, Object>> oneStudentCounselAppointmentList(String memberId) {
		return memberMapper.oneStudentCounselAppointmentList(memberId);
	}
}
