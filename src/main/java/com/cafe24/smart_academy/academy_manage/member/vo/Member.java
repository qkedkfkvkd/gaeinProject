package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 회원 신상정보 테이블
public class Member {
	private String	memberId;					// 아이디(기본키) - 로그인테이블 외래키 1:1 대응
	private String	memberName;					// 이름
	private String	memberBirth;				// 생년월일
	private String	memberEmail;				// 이메일(유니크) - 중복 불가
	private String	memberTel;					// 집전화 (널 가능)
	private String	memberPhone;				// 휴대폰(유니크) - 중복 불가
	private String	memberAddress;				// 주소
	private Date	memberRegisteredDate;		// 등록일(가입일)
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   Member.java");
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		System.out.println(memberName + " <- memberName   setMemberName()   Member.java");
		this.memberName = memberName;
	}
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		System.out.println(memberBirth + " <- memberBirth   setMemberBirth()   Member.java");
		this.memberBirth = memberBirth;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		System.out.println(memberEmail + " <- memberEmail   setMemberEmail()   Member.java");
		this.memberEmail = memberEmail;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		System.out.println(memberTel + " <- memberTel   setMemberTel()   Member.java");
		this.memberTel = memberTel;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		System.out.println(memberPhone + " <- memberPhone   setMemberPhone()   Member.java");
		this.memberPhone = memberPhone;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		System.out.println(memberAddress + " <- memberAddress   setMemberAddress()   Member.java");
		this.memberAddress = memberAddress;
	}
	public Date getMemberRegisteredDate() {
		return memberRegisteredDate;
	}
	public void setMemberRegisteredDate(Date memberRegisteredDate) {
		System.out.println(memberRegisteredDate + " <- memberRegisteredDate   setMemberRegisteredDate()   Member.java");
		this.memberRegisteredDate = memberRegisteredDate;
	}
}
