package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 회원 신상정보 테이블
public class Member {
	private String member_id;				// 아이디(기본키) - 로그인테이블 외래키 1:1 대응
	private String member_name;				// 이름
	private String member_birth;			// 생년월일
	private String member_email;			// 이메일(유니크) - 중복 불가
	private String member_tel;				// 집전화 (널 가능)
	private String member_phone;			// 휴대폰(유니크) - 중복 불가
	private String member_address;			// 주소
	private Date member_registered_date;	// 등록일(가입일)
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		System.out.println(member_id + " <- member_id   setMember_id()   Member.java");
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		System.out.println(member_name + " <- member_name   setMember_name()   Member.java");
		this.member_name = member_name;
	}
	public String getMember_birth() {
		return member_birth;
	}
	public void setMember_birth(String member_birth) {
		System.out.println(member_birth + " <- member_birth   setMember_birth()   Member.java");
		this.member_birth = member_birth;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		System.out.println(member_email + " <- member_email   setMember_email()   Member.java");
		this.member_email = member_email;
	}
	public String getMember_tel() {
		return member_tel;
	}
	public void setMember_tel(String member_tel) {
		System.out.println(member_tel + " <- member_tel   setMember_tel()   Member.java");
		this.member_tel = member_tel;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		System.out.println(member_phone + " <- member_phone   setMember_phone()   Member.java");
		this.member_phone = member_phone;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		System.out.println(member_address + " <- member_address   setMember_address()   Member.java");
		this.member_address = member_address;
	}
	public Date getMember_registered_date() {
		return member_registered_date;
	}
	public void setMember_registered_date(Date member_registered_date) {
		System.out.println(member_registered_date + " <- member_registered_date   setMember_registered_date()   Member.java");
		this.member_registered_date = member_registered_date;
	}
}
