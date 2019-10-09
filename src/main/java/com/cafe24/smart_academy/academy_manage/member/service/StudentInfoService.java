package com.cafe24.smart_academy.academy_manage.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.member.mapper.MemberMapper;
import com.cafe24.smart_academy.academy_manage.member.mapper.StudentInfoMapper;
import com.cafe24.smart_academy.academy_manage.member.vo.Counsel;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselAppointment;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselResult;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselType;
import com.cafe24.smart_academy.academy_manage.member.vo.GetCounselResultNo;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberSearchVO;
import com.cafe24.smart_academy.academy_manage.member.vo.Parent;
import com.cafe24.smart_academy.academy_manage.member.vo.PaymentInfo;

@Service
@Transactional
public class StudentInfoService {
	
	@Autowired
	MemberMapper memberMapper;
	// 아이디, 이메일 중복 확인 및 로그인 테이블, 회원신상정보 테이블 관리 담당 매퍼
	
	@Autowired
	StudentInfoMapper studentInfoMapper;
	// 학생 정보 관리 담당 매퍼
	
	
	
	// 관리자가 학생 등록시 학부모 테이블에서 유니크값인 폰번호 중복 체크
	public String parentByPhone(String inputParentPhone) {
		return studentInfoMapper.parentByPhone(inputParentPhone);
	}
	
	
	// 관리자가 회원 등록할 때 회원 등록 처리
	public String addStudent(MemberLogin loginInfo, Member memberInfo, Parent parent) {
		String memberIdchk = memberMapper.memberLoginInfoById(loginInfo.getMemberId());
		// 중복되는 아이디를 입력하여 가입 시도를 하였는가?
		
		String memberEmailChk = memberMapper.memberByEmail(memberInfo.getMemberEmail());
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
			int loginJoincheck = memberMapper.addMemberLogin(loginInfo);
			// 로그인 테이블 먼저 등록처리한다. (회원 신상정보 테이블의 기본키인 회원 아이디가 로그인 테이블의 회원 아이디를 참조)
			// 로그인 테이블 : 회원 신상정보 테이블 -> 1:1 관계
			
			int memCheck = 0;
			// 회원 신상정보 입력 실패로 초기화
			
			int studentParent = 0;
			// 학부모 입력 실패로 초기화
			
			
			if(loginJoincheck == 1) {  // 로그인 테이블 등록 성공
				memberInfo.setMemberId(loginInfo.getMemberId());
				// 로그인 테이블 객체에서 아이디 값을 꺼내와서 회원 신상정보 테이블의 아이디값에 셋팅
				
				memCheck = memberMapper.addMember(memberInfo);
				// 회원 신상정보 테이블 등록 처리
				
				parent.setMemberId(loginInfo.getMemberId());
				// 로그인 테이블 객체에서 아이디 값을 꺼내와서 학부모 테이블의 학생 아이디값에 셋팅
				
				studentParent = studentInfoMapper.addParent(parent);
				// 학부모 테이블 등록 처리
			}
			
			if(studentParent == 0 || loginJoincheck == 0 || memCheck == 0) {
				// 학부모 테이블 혹은 로그인 테이블 혹은 회원신상정보 테이블 둘 중 하나라도 입력 실패시
				
				String loginInfoChk = memberMapper.memberLoginInfoById(loginInfo.getMemberId());
				// 학부모 테이블과 회원테이블의 기본키가되는 회원 아이디는
				// 모두 로그인 테이블의 회원아이디(기본키)와 1:1대응 관계이다.
				// 즉, 로그인 테이블에 해당 아이디가 없는데
				// 회원신상정보 테이블이나 학부모 테이블에 값이 있다는 것은 말이 안된다.
				// --> 참조관계이므로
				
				result = "insertDeleteFail";
				// 입력 삭제 둘 다 실패 메세지
				
				if(loginInfoChk != null) {
					int deleteChk = memberMapper.deleteMemberLogin(loginInfo.getMemberId());
					// 디비가서 있을지도 모르는 해당 레코드 삭제 처리
					
					if(deleteChk == 1) {
						result = "insertFailDeleteSuccess";
						// 입력은 실패했으나 삭제에는 성공했다는 메세지
					}
				}
			}
		}
		
		return result;
	}
	
	
	
	
	// 관리자 : 특정 학생 상세 페이지 이동
	public Map<String, Object> detailStudentInfoByMemberId(String memberId) {
		return studentInfoMapper.detailStudentInfoByMemberId(memberId);
	}
	
	
	// 관리자 : 특정 학생정보 수정 처리
	public String updateStudentInfo(Member member, Parent parent) {
		String resultMessage = "updateStudentInfoFail";
		// 만약 학생정보 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int studentResult = memberMapper.updateMemberInfo(member);
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
	
	
	// 관리자 : 수납현황 리스트
	// (결제 테이블에서 납부예정금액이 0이고 실납부금액이 0보다 큰 리스트)
	public List<Map<String, Object>> paymentStateList() {
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		memberSearchVO.setKeyWord("payment");
		// 수납 현황을 리스트로 얻기 위해 검색 키워드를 넣어준다.
		
		return studentInfoMapper.paymentStateList(memberSearchVO);
	}
	
	
	// 관리자 : 미납현황 리스트(결제 테이블에서 납부예정금액이 0보다 큰 리스트)
	public List<Map<String, Object>> notPaymentStateList() {
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		memberSearchVO.setKeyWord("notPayment");
		// 미납 현황을 리스트로 얻기 위해 검색 키워드를 넣어준다.
		
		return studentInfoMapper.paymentStateList(memberSearchVO);
	}
	
	
	// 관리자 : 수납현황 혹은 미납현황 검색결과 리스트
	// 입력한 회원명 혹은 가입기간과 일치하는 모든 결제정보 리스트를 가져온다.
	public List<Map<String, Object>> paymentStateList(MemberSearchVO memberSearchVO) {
		return studentInfoMapper.paymentStateList(memberSearchVO);
	}
	
	
	
	
	
	// 관리자 상담관리 페이지에 보여줄 특정 학생의 이름과 생년월일 가져오기
	public Member studentInfoIdNameBirthById(String memberId) {
		return studentInfoMapper.studentInfoIdNameBirthById(memberId);
	}
	
	
	// 관리자가 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 해당 학생 상담내역 리스트
	// 관리자 : 상담내용 수정 화면 이동
	// TODO 수정해야됨
	public List<Map<String, Object>> oneStudentCounselHistoryOneOrList(CounselAppointment counselAppointment) {
		System.out.println(counselAppointment + " <- counselAppointment   oneStudentCounselHistoryOneOrList()   MemberService.java");
		return studentInfoMapper.oneStudentCounselHistoryOneOrList(counselAppointment);
	}
	
	
	// 관리자 : 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 상담예약현황 리스트
	// 관리자 : 학생 예약신청 상세보기
	public List<Map<String, Object>> counselAppointmentOneOrList(CounselAppointment counselAppointment) {
		return studentInfoMapper.counselAppointmentOneOrList(counselAppointment);
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
	
	
	// 관리자 : 상담예약테이블에 추가처리
	public String addCounselAppointment(CounselAppointment appointment) {
		String existChk = counselAppointmentByCounselHistoryNo(
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
	
	
	// 학생 : 상담예약신청 시 상담내역코드 중복확인
	public String counselAppointmentByCounselHistoryNo(String counselHistoryNo) {
		return studentInfoMapper.counselAppointmentByCounselHistoryNo(counselHistoryNo);
	}
	
	
	
	
	
	// 관리자 : 신입생상담 리스트 가져오기
	// 학원에 입학하면서 처음 받는 상담이 입학상담이므로
	// 상담예약 테이블에 해당 회원의 레코드가 존재하지 않으면
	// 전부 신입생으로 간주한다.
	public List<Member> admissionCounselList() {
		List<String> memberIdList = studentInfoMapper.admissionCounselMemberIdList();
		// 신입생 학생 아이디를 가져온다.
		
		List<Member> admissionCounselList = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		// 검색할 키워드를 넣어줄 맵 객체를 선언한다.
		
		map.put("memberIdList", memberIdList);
		// 신입생의 회원 아이디 리스트 검색 키워드를 넣어준다.
		
		admissionCounselList = studentInfoMapper.admissionCounselList(map);
		// 가져온 학생 아이디리스트를 넣어서 해당 학생의 회원신상정보 리스트를 얻어온다.
		
		return admissionCounselList;
	}
	
	
	// 관리자 : 신입생상담 검색결과 리스트 가져오기
	// 학원에 입학하면서 처음 받는 상담이 입학상담이므로
	// 상담예약 테이블에 해당 회원의 레코드가 존재하지 않으면
	// 전부 신입생으로 간주한다.
	public List<Member> admissionCounselList(MemberSearchVO memberSearchVO) {
		List<String> memberIdList = studentInfoMapper.admissionCounselMemberIdList();
		// 신입생 학생 아이디를 가져온다.
		
		List<Member> admissionCounselList = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		// 검색할 키워드를 넣어줄 맵 객체를 선언한다.
		
		map.put("memberIdList", memberIdList);
		// 신입생의 회원 아이디는 항상 필요하므로 조건문 밖에 두었다.
		
		map.put("memberName", memberSearchVO.getMemberName());
		// 검색할 키워드 회원 이름을 넣어준다.
		
		map.put("startJoinDate", memberSearchVO.getStartJoinDate());
		// 검색할 키워드 가입 시작기간을 넣어준다.
		
		map.put("endJoinDate", memberSearchVO.getEndJoinDate());
		// 검색할 키워드 가입 마지막기간을 넣어준다.
		
		admissionCounselList = studentInfoMapper.admissionCounselList(map);
		// 가져온 학생 아이디리스트를 넣어서 해당 학생의 회원신상정보 리스트를 얻어온다.
		
		return admissionCounselList;
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
	
	
	// 관리자 : 학생 예약신청 처리 메소드
	public String permissionCounselAppointment(String counselHistoryNo) {
		String resultMessage = "CounselAppointmentFail";
		// 업데이트 실패로 초기화
		
		int result = studentInfoMapper.permissionCounselAppointment(counselHistoryNo);
		// 예약처리하기
		
		if(result == 1) { // 업데이트 성공(예약 성공)
			resultMessage = null;
		}
		
		return resultMessage;
	}
	
	
	// 관리자, 학생 : 상담예약신청 수정처리
	public String updateCounselAppointment(CounselAppointment counselAppointment) {
		String resultMessage = "updateCounselAppointmentFail";
		// 만약 상담예약 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = studentInfoMapper.updateCounselAppointment(counselAppointment);
		// 상담예약 수정 처리
		
		if(result == 1) {  // 상담예약 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자, 학생 : 해당 상담예약 삭제 처리
	public String deleteCounselAppointment(String counselHistoryNo) {
		String existChk = counselAppointmentByCounselHistoryNo(counselHistoryNo);
		// 삭제하기 전 해당 상담내역코드로된 상담예약현황이 존재하는지 확인
		
		String resultMessage = "deleteCounselAppointmentFail";
		// 상담예약 삭제 실패로 초기화
		
		if(existChk != null) { // 해당 상담내역코드 존재(삭제 가능)
			int result = studentInfoMapper.deleteCounselAppointment(counselHistoryNo);
			// 해당 상담예약 삭제 처리
			
			if(result == 1) { // 해당 상담예약 삭제 성공
				resultMessage = "deleteCounselAppointmentSuccess";
				// 상담예약 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 상담예약 테이블에서 상담여부 '유' 처리
	public String permissionCounsel(CounselAppointment counselAppointment) {
		String resultMessage = "permissionCounselFail";
		// 상담여부 '유'처리 실패로 초기화
		
		int result = studentInfoMapper.permissionCounsel(counselAppointment);
		// 상담처리하기
		
		if(result == 1) { // 업데이트 성공(상담처리 성공)
			resultMessage = null;
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 상담테이블에 상담 내용 추가 처리
	public String addCounsel(Counsel counsel) {
		String existChk =
				studentInfoMapper.counselByCounselHistoryNo(counsel.getCounselHistoryNo());
		// 상담테이블에 추가하기 전에 해당 상담내역코드로된 레코드가 존재하는지 확인
		
		String resultMessage = "counselInsertFail";
		// 상담내역 입력 실패로 초기화
		
		if(existChk == null) { // 해당 상담내역코드로된 레코드가 존재하지 않음(추가 가능)
			int result = studentInfoMapper.addCounsel(counsel);
			
			if(result == 1) {  // 상담 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지에 널값을 준다
			}
		}
		
		return resultMessage;
		// 상담 테이블 추가 처리
	}
	
	
	// 관리자 : 상담내용 테이블 수정 처리
	public String updateCounsel(Counsel counsel) {
		String resultMessage = "updateCounselFail";
		// 만약 상담내용 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = studentInfoMapper.updateCounsel(counsel);
		// 상담내용 수정 처리
		
		if(result == 1) {  // 상담내용 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 해당 상담내용 삭제 처리
	public String deleteCounsel(String counselHistoryNo) {
		String existChk = studentInfoMapper.counselByCounselHistoryNo(counselHistoryNo);
		// 삭제하기 전 해당 상담내역코드로된 상담 내용이 존재하는지 확인
		
		String resultMessage = "deleteCounselFail";
		// 상담내용 삭제 실패로 초기화
		
		if(existChk != null) { // 해당 과목코드 존재(삭제 가능)
			int result = studentInfoMapper.deleteCounsel(counselHistoryNo);
			// 해당 과목 삭제 처리
			
			if(result == 1) { // 해당 과목 삭제 성공
				resultMessage = "deleteCounselSuccess";
				// 과목 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
}
