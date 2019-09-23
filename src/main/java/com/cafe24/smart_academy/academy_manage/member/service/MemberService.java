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
	
	
	// 관리자 : 상담기준코드 리스트 가져오기
	public List<Map<String, Object>> listCounselStandard() {
		return memberMapper.listCounselStandard();
	}
	
	
	// 관리자 : 상담기준코드 리스트에서 상담결과코드를 추가할려고 할 때
	// 참조하는 테이블인 상담구분코드에 레코드가 존재하는지 리스트 사이즈 숫자 리턴
	// --> 0: 존재하지 않음
	public int counselTypeListSize() {
		return memberMapper.counselTypeListSize();
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
	
	
}
