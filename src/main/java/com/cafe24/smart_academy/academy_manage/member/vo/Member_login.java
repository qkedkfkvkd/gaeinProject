package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 회원 로그인 테이블 VO 객체
public class Member_login {
	private String member_id;				// 아이디(기본키)
	private String member_pw;				// 비밀번호
	private String member_level;			// 권한
	private Date member_registered_date;	// 등록일(가입일)
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		System.out.println(member_id + " <- member_id   setMember_id()   Member_login.java");
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		System.out.println(member_pw + " <- member_pw   setMember_pw()   Member_login.java");
		this.member_pw = member_pw;
	}
	public String getMember_level() {
		return member_level;
	}
	public void setMember_level(String member_level) {
		System.out.println(member_level + " <- member_level   setMember_level()   Member_login.java");
		this.member_level = member_level;
	}
	public Date getMember_registered_date() {
		return member_registered_date;
	}
	public void setMember_registered_date(Date member_registered_date) {
		System.out.println(member_registered_date + " <- member_registered_date   setMember_registered_date()   Member_login.java");
		this.member_registered_date = member_registered_date;
	}
}
