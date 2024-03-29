package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 회원 로그인 테이블 VO 객체
public class MemberLogin {
	private String	memberId;					// 아이디(기본키)
	private String	memberPw;					// 비밀번호
	private String	memberLevel;				// 권한
	private Date	memberRegisteredDate;		// 등록일(가입일)
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   Member_login.java");
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		System.out.println(memberPw + " <- memberPw   setMemberPw()   Member_login.java");
		this.memberPw = memberPw;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		System.out.println(memberLevel + " <- memberLevel   setMemberLevel()   Member_login.java");
		this.memberLevel = memberLevel;
	}
	public Date getMemberRegisteredDate() {
		return memberRegisteredDate;
	}
	public void setMemberRegisteredDate(Date memberRegisteredDate) {
		System.out.println(memberRegisteredDate
				+ " <- memberRegisteredDate   setMemberRegisteredDate()   Member_login.java");
		this.memberRegisteredDate = memberRegisteredDate;
	}
}
