package com.cafe24.smart_academy.academy_manage.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.member.mapper.MemberMapper;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselResult;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselType;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberSearchVO;
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
	
	
	// 관리자 : 결제정보가 없는 학생일 경우
	// 제목 상단에 아이디, 이름, 생년월일을 나타내기 위함
	public Member memberSimpleInfo(String memberId) {
		return memberMapper.memberSimpleInfo(memberId);
	}
	
	
	// 관리자 : 학생이나 강사 목록 페이지로 이동할 때 가져갈 리스트 객체
	// 관리자 : 입력한 이름과 가입기간으로 디비에서 권한이
	//			학생이거나 강사인 사람들만 목록을 가져온다.
	// -> 로그인 테이블 - 회원 신상정보 테이블 아이디로 조인
	public List<Map<String, Object>> memberInfoList(MemberSearchVO memberSearchVO) {
		return memberMapper.memberInfoList(memberSearchVO);
	}
	
	
	// 관리자 : 상담기준코드 리스트 가져오기
	public List<Map<String, Object>> counselStandardList() {
		return memberMapper.counselStandardList();
	}
	
	
	// 관리자 : 선택한 상담구분코드와 상담결과코드로 상담기준리스트 검색결과 가져오기
	public List<Map<String, Object>> counselStandardList(CounselResult counselResult) {
		return memberMapper.counselStandardList(counselResult);
	}
	
	
	// 관리자 : 상담기준코드 리스트에서 상담결과코드를 추가할려고 할 때
	// 참조하는 테이블인 상담구분코드에 레코드가 존재하는지 리스트 사이즈 숫자 리턴
	// --> 0: 존재하지 않음
	public int counselTypeListSize() {
		return memberMapper.counselTypeListSize();
	}
	
	
	// 관리자 : 상담기준코드 리스트에서 상담구분코드 선택시
	//			선택값에 따른 상담결과코드 보이기
	public List<Map<String, Object>> counselResultListBycounselTypeNo(String counselTypeNo) {
		return memberMapper.counselResultListBycounselTypeNo(counselTypeNo);
	}
	
	
	// 관리자 : 상담구분코드 테이블에서 상담구분코드 중복확인
	public String counselTypeByCounselTypeNo(String inputCounselTypeNo) {
		return memberMapper.counselTypeByCounselTypeNo(inputCounselTypeNo);
		// 존재하는 코드인지 아닌지 체크한다.
	}
	
	
	// 관리자 : 상담구분코드 추가처리
	public String addCounselType(CounselType counselType) {
		String existChk = counselTypeByCounselTypeNo(counselType.getCounselTypeNo());
		// 상담구분코드 테이블의 기본키인 상담구분코드로 추가하려는 상담구분코드가
		// 중복되는지 확인한다.
		
		String resultMessage = "usedCounselTypeNo";
		
		if(existChk == null) { // 존재하지않는 상담구분코드(사용 가능한 상담구분코드)
			int result = memberMapper.addCounselType(counselType);
			// 상담구분코드 추가 처리
			
			if(result == 1) { // 상담구분코드 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지가 널값을 준다.
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 상담구분코드 리스트 가져오기
	public List<CounselType> counselTypeList() {
		return memberMapper.counselTypeList();
	}
	
	
	// 관리자 : 상담구분코드 검색결과 리스트 가져오기
	public List<CounselType> counselTypeList(String counselTypeNo) {
		return memberMapper.counselTypeList(counselTypeNo);
	}
	
	
	// 관리자 : 상담구분코드 상세 보기
	public CounselType detailCounselTypeByCounselTypeNo(String counselTypeNo) {
		return memberMapper.detailCounselTypeByCounselTypeNo(counselTypeNo);
	}
	
	
	// 관리자 : 상담구분코드 수정 처리
	public String updateCounselType(CounselType counselType) {
		String resultMessage = "updateCounselTypeFail";
		// 만약 상담구분 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = memberMapper.updateCounselType(counselType);
		// 상담구분 수정 처리
		
		if(result == 1) {  // 상담구분 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 상담구분코드 삭제 처리
	public String deleteCounselType(String counselTypeNo) {
		String existChk = counselTypeByCounselTypeNo(counselTypeNo);
		// 삭제하기 전 해당 상담구분코드로된 상담구분이 존재하는지 확인
		
		String resultMessage = "deleteCounselTypeFail";
		// 상담구분삭제 실패로 초기화
		
		if(existChk != null) { // 해당 상담구분코드 존재(삭제 가능)
			int result = memberMapper.deleteCounselType(counselTypeNo);
			// 해당 상담구분 삭제 처리
			
			if(result == 1) { // 해당 상담구분 삭제 성공
				resultMessage = "deleteCounselTypeSuccess";
				// 상담구분 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
	
	
	
	
	// 관리자 : 상담결과코드 테이블에서 상담결과코드 중복확인
	public String counselResultByCounselResultNo(String inputCounselResultNo) {
		return memberMapper.counselResultByCounselResultNo(inputCounselResultNo);
		// 존재하는 코드인지 아닌지 체크한다.
	}
	
	
	// 관리자 : 상담결과코드 추가처리
	public String addCounselResult(CounselResult counselResult) {
		String existChk = counselResultByCounselResultNo(counselResult.getCounselResultNo());
		// 상담결과코드 테이블의 기본키인 상담결과코드로 추가하려는 상담결과코드가
		// 중복되는지 확인한다.
		
		String resultMessage = "usedCounselResultNo";
		
		if(existChk == null) { // 존재하지않는 상담결과코드(사용 가능한 상담결과코드)
			int result = memberMapper.addCounselResult(counselResult);
			// 상담결과코드 추가 처리
			
			if(result == 1) { // 상담결과코드 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지가 널값을 준다.
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 상담결과코드 리스트 가져오기
	public List<CounselResult> counselResultList() {
		return memberMapper.counselResultList();
	}
	
	
	// 관리자 : 해당 상담결과코드를 가진 상담결과 전체 내용 가져오기
	public CounselResult detailCounselResultByCounselResultNo(String counselResultNo) {
		return memberMapper.detailCounselResultByCounselResultNo(counselResultNo);
	}
	
	
	// 관리자 : 해당 상담결과코드를 참조하는 상담예약목록 가져오기
	public List<Map<String, Object>> counselAppointmentListBycounselResultNo(String counselResultNo) {
		return memberMapper.counselAppointmentListBycounselResultNo(counselResultNo);
	}
	
	
	// 관리자 : 상담결과코드 수정처리
	public String updateCounselResult(CounselResult counselResult) {
		String resultMessage = "updateCounselResultFail";
		// 만약 상담결과 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = memberMapper.updateCounselResult(counselResult);
		// 상담결과 수정 처리
		
		if(result == 1) {  // 상담결과 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 상담결과코드 삭제 처리
	public String deleteCounselResult(String counselResultNo) {
		String existChk = counselResultByCounselResultNo(counselResultNo);
		// 삭제하기 전 해당 상담결과코드로된 상담결과가 존재하는지 확인
		
		String resultMessage = "deleteCounselResultFail";
		// 상담결과삭제 실패로 초기화
		
		if(existChk != null) { // 해당 상담결과코드 존재(삭제 가능)
			int result = memberMapper.deleteCounselResult(counselResultNo);
			// 해당 상담결과 삭제 처리
			
			if(result == 1) { // 해당 상담결과 삭제 성공
				resultMessage = "deleteCounselResultSuccess";
				// 상담결과 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
}
