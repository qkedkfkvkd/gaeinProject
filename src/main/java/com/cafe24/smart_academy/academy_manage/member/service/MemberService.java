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
	
	
	// 관리자 : 상담기준코드 리스트 가져오기
	public List<Map<String, Object>> listCounselStandard() {
		return memberMapper.listCounselStandard();
	}
}
