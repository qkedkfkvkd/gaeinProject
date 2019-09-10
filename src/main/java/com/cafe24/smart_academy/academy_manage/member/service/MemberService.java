package com.cafe24.smart_academy.academy_manage.member.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.member.mapper.MemberMapper;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;

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
	
	
	// 관리자가 회원 등록할 시 로그인 테이블에서 아이디 중복 체크
	public String memberLoginById(String memberId) {
		return memberMapper.memberLoginInfoById(memberId);
		// 존재하는 아이디인지 아닌지 체크한다.
	}
	
	
	// 관리자가 회원 등록할 시 회원신상정보 테이블에서 이메일 중복 체크
	public String memberByEmail(String memberEmail) {
		return memberMapper.memberByEmail(memberEmail);
		// 존재하는 이메일인지 아닌지 체크한다.
	}
	
	
	// 관리자가 회원 등록할 때 회원 등록 처리
	public String addMember(MemberLogin loginInfo, Member memberInfo) {
		String memberIdchk = memberMapper.memberLoginInfoById(loginInfo.getMemberId());
		// 중복되는 아이디를 입력하여 가입 시도를 하였는가?
		
		String memberEmailChk = memberMapper.memberByEmail(memberInfo.getMemberEmail());
		// 중복되는 이메일을 입력하여 가입 시도를 하였는가?
		
		String result = "";
		
		if(memberIdchk != null) { // 이미 존재하는 아이디로 회원가입하려고 했을 경우
			result = "idUsed";		// 아이디 사용 중 메세지
		} else if(memberEmailChk != null) { // 유니크값인 이메일이 중복되는 걸 입력하여 가입시도
			result = "emailUsed";	// 이메일 사용 중 메세지
		}  else {
			int check = memberMapper.addMemberLogin(loginInfo);
			// 로그인 테이블 먼저 등록처리한다. (회원 신상정보 테이블의 기본키인 회원 아이디가 로그인 테이블의 회원 아이디를 참조)
			// 로그인 테이블 : 회원 신상정보 테이블 -> 1:1 관계
			
			int memCheck = 0;
			// 회원 신상정보 입력 실패로 초기화
			
			if(check == 1) {
				memberInfo.setMemberId(loginInfo.getMemberId());
				// 로그인 테이블 객체에서 아이디 값을 꺼내와서 회원 신상정보 테이블의 아이디값에 셋팅
				
				memCheck = memberMapper.addMember(memberInfo);
				// 회원 신상정보 테이블 등록처리
			}
			
			result = null;
			// 널값이면 입력 성공했다는 뜻
			
			if(check == 0 || memCheck == 0) { // 로그인 테이블 혹은 회원신상정보 테이블 둘 중 하나라도 입력 실패시
				result = "insertFail";
				// 입력 실패 메세지
			}
		}
		
		return result;
	}
}
