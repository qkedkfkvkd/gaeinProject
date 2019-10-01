package com.cafe24.smart_academy.academy_manage.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.member.mapper.StudentInfoMapper;
import com.cafe24.smart_academy.academy_manage.member.vo.Counsel;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselAppointment;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselResult;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselType;
import com.cafe24.smart_academy.academy_manage.member.vo.GetCounselResultNo;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.Parent;
import com.cafe24.smart_academy.academy_manage.member.vo.PaymentInfo;

@Service
@Transactional
public class StudentInfoService {
	
	@Autowired
	StudentInfoMapper studentInfoMapper;
	// 학생 정보 관리 담당 매퍼
	
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
	
	
	
	// 관리자 : 디비에서 권한이 학생인 사람들만 목록을 가져오기
	// -> 로그인 테이블 - 회원 신상정보 테이블 아이디로 조인
	public List<Map<String, Object>> studentInfoList() {
		return studentInfoMapper.studentInfoList();
	}
	
	
	// 관리자 : 입력한 학생명과 가입기간으로 디비에서 권한이
	//			학생인 사람들만 목록을 가져온다.
	// -> 로그인 테이블 - 회원 신상정보 테이블 아이디로 조인
	public List<Map<String, Object>> studentInfoList(Member member) {
		return studentInfoMapper.studentInfoList(member);
	}
	
	
	// 관리자 : 특정 학생 상세 페이지 이동
	public Map<String, Object> detailStudentInfoByMemberId(String memberId) {
		return studentInfoMapper.detailStudentInfoByMemberId(memberId);
	}
	
	
	// 관리자 : 특정 학생정보 수정 처리
	public String updateStudentInfo(Member member, Parent parent) {
		String resultMessage = "updateStudentInfoFail";
		// 만약 학생정보 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int studentResult = studentInfoMapper.updateStudentInfo(member);
		// 학생정보 수정 처리
		
		int parentResult = studentInfoMapper.updateParent(parent);
		// 학부모 정보 수정 처리
		
		if(studentResult == 1 & parentResult == 1) {
			// 학생과 학부모 정보 수정에 성공했다면
			
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 회원 삭제 처리
/*	public String deleteStudentInfo(String memberId) {
		String existChk = memberMapper.memberLoginInfoById(memberId);
		// 삭제하기 전 로그인 테이블에서 해당 아이디로된 회원이 존재하는지 확인
		
		String resultMessage = "deleteStudentInfoFail";
		// 회원 삭제 실패로 초기화
		
		if(existChk != null) { // 해당 회원아이디 존재(삭제 가능)
			int result = studentInfoMapper.deleteStudentInfo(memberId);
			// 해당 회원 삭제 처리
			
			if(result == 1) { // 해당 회원 삭제 성공
				resultMessage = "deleteStudentInfoSuccess";
				// 회원 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}*/
	
	
	
	
	// 관리자 특정 학생 결제정보 가져오기
	public Map<String, Object> detailPaymentInfoByMemberId(String memberId) {
		Map<String, Object> paymentInfo = studentInfoMapper.paymentInfoById(memberId);
		// 학생의 아이디를 가지고 결제정보 테이블에서 객체를 얻어온다.
		return paymentInfo;
	}
	
	
	// 관리자 : 결제정보가 없는 학생일 경우
	// 제목 상단에 아이디, 이름, 생년월일을 나타내기 위함
	public Member memberSimpleInfo(String memberId) {
		return studentInfoMapper.memberSimpleInfo(memberId);
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
	
	
	// 관리자 : 특정학생 결제정보 수정 처리
	public String updatePaymentInfo(PaymentInfo paymentInfo) {
		String resultMessage = "updatePaymentInfoFail";
		// 만약 결제정보 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = studentInfoMapper.updatePaymentInfo(paymentInfo);
		// 결제정보 수정 처리
		
		if(result == 1) {// 결제 정보 수정에 성공했다면
			
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 미납현황 리스트(결제 테이블에서 납부예정금액이 0보다 큰 리스트)
	public List<Map<String, Object>> notPaymentStateList() {
		return studentInfoMapper.notPaymentStateList();
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
	
	
	// 관리자 : 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 상담예약현황 리스트
	// 관리자 : 학생 예약신청 상세보기
	public List<Map<String, Object>> oneStudentCounselAppointment(CounselAppointment counselAppointment) {
		return studentInfoMapper.oneStudentCounselAppointment(counselAppointment);
	}
	
	
	// 관리자 : 학생 상담관련 폼 이동 시 상담구분코드와 상담결과코드에서
	// 특정 상담결과명으로된 모든 리스트 가지고 오기
	public List<Map<String, Object>> counselKindList(String counselResultName) {
		return studentInfoMapper.counselKindList(counselResultName);
	}
	
	
	// 관리자 : 해당 상담결과코드로 상담구분코드 리스트 가져오기
	public List<CounselType> counselTypeListByCounselResultName(String counselResultName) {
		return studentInfoMapper.counselTypeListByCounselResultName(counselResultName);
	}
	
	
	// 관리자 : 상담결과코드에서 이름만 리스트로 가져오기
	public List<CounselResult> counselResultNameList(){
		return studentInfoMapper.counselResultNameList();
	}
	
	
	// 관리자 : 상담구분코드와 상담결과명으로 상담결과코드를 얻어온다.
	public String getCounselResultNo(GetCounselResultNo getCounselResultNo) {
		return studentInfoMapper.getCounselResultNo(getCounselResultNo);
	}
	

	// 관리자 : 상담예약테이블에서 상담내역코드 중복 확인
	public String counselAppointmentBycounselHistoryNo(String inputCounselHistoryNo) {
		return studentInfoMapper.counselAppointmentBycounselHistoryNo(inputCounselHistoryNo);
	}
	
	
	// 관리자 : 상담예약테이블에 추가처리
	public String addCounselAppointment(CounselAppointment appointment) {
		String existChk = counselAppointmentBycounselHistoryNo(
									appointment.getCounselHistoryNo());
		// 상담예약 테이블의 기본키인 상담내역코드로 추가하려는 상담내역코드가
		// 중복되는지 확인한다.
		
		String resultMessage = "usedCounselHistoryNo";
		
		if(existChk == null) { // 존재하지않는 상담내역코드(사용 가능한 상담내역코드)
			int result = studentInfoMapper.addCounselAppointment(appointment);
			// 상담예약 테이블 추가 처리
			
			if(result == 1) { // 상담예약 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지에 널값을 준다.
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 상담테이블에 상담 내용 추가 처리
	public int addCounsel(Counsel counsel) {
		return studentInfoMapper.addCounsel(counsel);
		// 상담 테이블 추가 처리
	}
	
	
	// 관리자 : 예약 현황 리스트 가져오기
	public List<Map<String, Object>> counselReservationStateList() {
		return studentInfoMapper.counselReservationStateList();
	}
	
	
	// 관리자 : 상담예약현황 리스트에서 선택한 상담코드로 검색
	public List<Map<String, Object>> counselReservationStateList(
						CounselResult counselResult) {
		return studentInfoMapper.counselReservationStateList(counselResult);
	}
}
