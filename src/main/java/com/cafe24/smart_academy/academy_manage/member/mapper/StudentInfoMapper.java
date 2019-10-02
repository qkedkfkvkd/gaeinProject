package com.cafe24.smart_academy.academy_manage.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.smart_academy.academy_manage.member.vo.Counsel;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselAppointment;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselResult;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselType;
import com.cafe24.smart_academy.academy_manage.member.vo.GetCounselResultNo;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.Parent;
import com.cafe24.smart_academy.academy_manage.member.vo.PaymentInfo;

@Mapper
public interface StudentInfoMapper {
	public String memberLoginInfoById(String memberId);
	// 로그인 테이블에서 아이디 존재 여부 확인
	
	public String memberByEmail(String memberEmail);
	// 관리자가 학생 등록할 때 회원신상정보 테이블에서 유니크값인 이메일이 중복되는지 확인
	
	public String parentByPhone(String inputParentPhone);
	// 관리자가 학생 등록시 학부모 테이블에서 유니크값인 폰번호 중복 체크
	
	public int addMemberLogin(MemberLogin loginInfo);
	// 관리자가 학생 등록할 때 회원 등록 처리 (로그인 테이블 먼저 등록 처리)
	
	public int addStudent(Member memberInfo);
	// 관리자가 학생 등록할 때 회원 등록 처리 (로그인 테이블 먼저 등록 후 회원 신상정보 등록)
	
	public int addParent(Parent parent);
	// 관리자가 학생 등록할 때 학부모 등록 처리
	
	
	
	public List<Map<String, Object>> studentInfoList();
	// 관리자가 학생 목록 페이지로 이동할 때 가져올 목록
	
	public List<Map<String, Object>> studentInfoList(Member member);
	// 관리자 : 입력한 학생명과 가입기간으로 디비에서 권한이
	//			학생인 사람들만 목록을 가져온다.
	// -> 로그인 테이블 - 회원 신상정보 테이블 아이디로 조인
	
	public Map<String, Object> detailStudentInfoByMemberId(String memberId);
	// 관리자 : 특정 학생 상세 페이지 이동
	
	public int updateStudentInfo(Member member);
	// 관리자 : 특정 학생정보 수정 처리
	
	public int updateParent(Parent parent);
	// 관리자 : 학부모정보 수정 처리
	
	
	
	public Map<String, Object> paymentInfoById(String memberId);
	// 관리자 전용 특정 학생의 결제정보 가져오기
	
	public Member memberSimpleInfo(String memberId);
	// 관리자 : 결제정보가 없는 학생일 경우
	// 제목 상단에 아이디, 이름, 생년월일을 나타내기 위함
	
	public int addPaymentInfo(PaymentInfo paymentInfo);
	// 관리자 전용 특정 학생 결제정보 입력 처리
	
	public int updatePaymentInfo(PaymentInfo paymentInfo);
	// 관리자 : 특정학생 결제정보 수정 처리
	
	public List<Map<String, Object>> notPaymentStateList();
	// 관리자 : 미납현황 리스트(결제 테이블에서 납부예정금액이 0보다 큰 리스트)
	
	
	
	public Member studentInfoIdNameBirthById(String memberId);
	// 관리자 상담관리 페이지에 보여줄 특정 학생의 이름과 생년월일 가져오기
	
	public List<Map<String, Object>> oneStudentCounselHistory(CounselAppointment counselAppointment);
	// 관리자가 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 해당 학생 상담내역 리스트
	
	public List<Map<String, Object>> counselAppointmentOneOrList(CounselAppointment counselAppointment);
	// 관리자 : 학생목록에서 특정 학생의 상담 관리 클릭했을 시 보여줄 상담예약현황 리스트
	// 관리자 : 학생예약신청 상세보기
	// 관리자 : 예약 현황 리스트 가져오기
	
	public List<Map<String, Object>> counselKindList(String counselResultName);
	// 관리자 : 학생 상담관련 폼 이동 시 상담구분코드와 상담결과코드에서
	// 특정 상담결과명으로된 모든 리스트 가지고 오기
	
	public List<CounselType> counselTypeListByCounselResultName(String counselResultName);
	// 관리자 : 해당 상담결과코드로 상담구분코드 리스트 가져오기
	
	public List<CounselResult> counselResultNameList();
	// 관리자 : 상담결과코드 이름만 리스트로 가져오기
	
	public String getCounselResultNo(GetCounselResultNo getCounselResultNo);
	// 관리자 : 상담구분코드와 상담결과명으로 상담결과코드를 얻어온다.
	
	public int addCounselAppointment(CounselAppointment appointment);
	// 관리자 : 상담예약테이블 추가 처리
	
	public String counselAppointmentByCounselHistoryNo(String counselHistoryNo);
	// 학생 : 상담예약신청 시 상담내역코드 중복 확인
	
	
	
	public List<Map<String, Object>> counselReservationStateList();
	// 관리자 : 예약 현황 리스트 가져오기
	
	public List<Map<String, Object>> counselReservationStateList(CounselResult counselResult);
	// 관리자 : 상담예약현황 리스트에서 선택한 상담코드로 검색
	
	public int permissionCounselAppointment(String counselHistoryNo);
	// 관리자 : 학생예약신청 예약처리
	
	public int updateCounselAppointment(CounselAppointment counselAppointment);
	// 관리자, 학생 : 상담예약신청 수정처리
	
	public int deleteCounselAppointment(String counselHistoryNo);
	// 관리자, 학생 : 학생상담예약신청 삭제 처리
	
	public int permissionCounsel(CounselAppointment counselAppointment);
	// 관리자 : 상담예약테이블 상담처리
	
	
	
	public String counselByCounselHistoryNo(String counselHistoryNo);
	// 관리자 : 상담테이블에 해당 상담내역코드로된 레코드가 존재하는지 확인
	
	public int addCounsel(Counsel counsel);
	// 관리자 : 상담테이블에 상담내용 추가 처리
	
	public int updateCounsel(Counsel counsel);
	// 관리자 : 상담내용 테이블 수정 처리
	
	public int deleteCounsel(String counselHistoryNo);
	// 관리자 : 해당 상담내용 삭제 처리
}
