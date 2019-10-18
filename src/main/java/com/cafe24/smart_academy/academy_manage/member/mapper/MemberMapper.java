package com.cafe24.smart_academy.academy_manage.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.member.vo.CounselResult;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselType;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberSearchVO;
import com.cafe24.smart_academy.academy_manage.member.vo.Parent;
import com.cafe24.smart_academy.academy_manage.member.vo.PaymentInfo;

@Mapper
public interface MemberMapper {
	public String memberLoginInfoById(String memberId);
	// 로그인 테이블에서 아이디 존재 여부 확인
	
	public int updateLoginPassword(MemberLogin login);
	// 로그인 정보 수정 처리 (패스워드 수정처리)
	
	public String memberByEmail(String memberEmail);
	// 관리자가 학생 혹은 강사를 등록할 때 회원신상정보 테이블에서 유니크값인 이메일이 중복되는지 확인
	
	public int addMemberLogin(MemberLogin loginInfo);
	// 관리자 : 학생이나 강사를 등록할 때 회원 등록 처리
	//			로그인 테이블 먼저 등록 처리
	
	public int addMember(Member memberInfo);
	// 관리자 : 학생이나 강사를 등록할 때 회원 등록 처리
	//			로그인 테이블 먼저 등록 후 회원 신상정보 등록
	
	public int updateMemberInfo(Member member);
	// 관리자 : 관리자, 특정 학생, 강사 상세정보 수정 처리
	
	public int deleteMemberLogin(String memberId);
	// 관리자 : 학생이나 강사 삭제시 해당 로그인 레코드 삭제
	//			학생이나 강사를 등록하다가 중간에 실패시 있을지도 모르는 로그인 레코드 삭제
	// 관리자 : 해당 회원 로그인 정보 삭제 처리
	// 학생을 삭제할 경우 : CASCADE에 의해 결제정보, 회원신상정보, 학부모정보,
	//						수강신청정보, 상담정보가 지워진다.
	// 타 관리자나 강사를 삭제할 경우 : CASCADE에 의해 회원신상정보, 강사정보가 지워진다.
	
	public MemberLogin memberLogin(MemberLogin login);
	// 로그인 체크 - 비밀번호까지 일치하는지 확인
	// 회원 가입시 아이디 및 비밀번호값이 들어가는 테이블
	
	public Map<String, Object> memberLoginInfo(String memberId);
	// 로그인 됬을 시 기본키인 아이디값으로 정보 가져오기,
	// 회원가입 시 로그인 테이블 먼저 insert 해준 후 신상정보 입력하기
	
	public Member memberSimpleInfo(String memberId);
	// 관리자 : 결제정보가 없는 학생일 경우
	// 제목 상단에 아이디, 이름, 생년월일을 나타내기 위함
	
	public Map<String, Object> detailAdminInfoByMemberId(String memberId);
	// 관리자 : 관리자 자신의 상세 정보 가져오기
	
	public List<Map<String, Object>> memberInfoList(MemberSearchVO memberSearchVO);
	// 관리자 : 학생이나 강사 목록 페이지로 이동할 때 가져갈 리스트 객체
	// 관리자 : 입력한 이름과 가입기간으로 디비에서 권한이
	//			학생이나 강사인 사람들만 목록을 가져온다.
	// -> 로그인 테이블 - 회원 신상정보 테이블 아이디로 조인
	
	
	
	public List<Map<String, Object>> counselStandardList();
	// 관리자 : 상담기준코드 리스트 가져오기
	
	public List<Map<String, Object>> counselStandardList(CounselResult counselResult);
	// 관리자 : 선택한 상담구분코드와 상담결과코드로 상담기준리스트 검색결과 가져오기
	
	public List<Map<String, Object>> counselResultListBycounselTypeNo(String counselTypeNo);
	// 관리자 : 상담기준코드 리스트에서 상담구분코드 선택시
	//			선택값에 따른 상담결과코드 보이기
	
	
	
	public List<CounselType> counselTypeList();
	// 관리자 : 상담구분코드 리스트 가져오기
	
	public List<CounselType> counselTypeList(String counselTypeNo);
	// 관리자 : 상담구분코드 검색결과 리스트 가져오기
	
	public int counselTypeListSize();
	// 관리자 : 상담구분테이블에 레코드가 존재하는지 확인
	
	public String counselTypeByCounselTypeNo(String inputCounselTypeNo);
	// 관리자 : 상담구분테이블의 기본키인 상담구분코드 중복 확인
	
	public int addCounselType(CounselType counselType);
	// 관리자 : 상담구분테이블 추가처리
	
	public CounselType detailCounselTypeByCounselTypeNo(String counselTypeNo);
	// 관리자 : 상담구분테이블 상세보기
	
	public int updateCounselType(CounselType counselType);
	// 관리자 : 상담구분 수정 처리
	
	public int deleteCounselType(String counselTypeNo);
	// 관리자 : 상담구분 삭제 처리
	
	
	
	public String counselResultByCounselResultNo(String inputCounselResultNo);
	// 관리자 : 상담결과코드 테이블에서 상담결과코드 중복확인
	
	public int addCounselResult(CounselResult counselResult);
	// 관리자 : 상담결과테이블 추가처리
	
	public List<CounselResult> counselResultList();
	// 관리자 : 상담결과코드 리스트 가져오기
	
	public CounselResult detailCounselResultByCounselResultNo(String counselResultNo);
	// 관리자 : 해당 상담결과코드를 가진 상담결과 전체 내용 가져오기
	
	public List<Map<String, Object>> counselAppointmentListBycounselResultNo(String counselResultNo);
	// 관리자 : 해당 상담결과코드를 참조하는 상담예약목록 가져오기
	
	public int updateCounselResult(CounselResult counselResult);
	// 관리자 : 상담결과코드 수정 처리
	
	public int deleteCounselResult(String counselResultNo);
	// 관리자 : 상담결과코드 삭제 처리
}
