package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 학생의 학부모 테이블 VO 객체
public class Parent {
	private String	memberId;					// 학생 아이디(기본키, 로그인테이블 외래키 1:1 대응)
	private String	parentName;					// 학부모명
	private String	parentPhone;				// 학부모 핸드폰번호
	private Date	parent_registered_date;		// 학부모 등록일
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   Parent.java");
		this.memberId = memberId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		System.out.println(parentName + " <- parentName   setParentName()   Parent.java");
		this.parentName = parentName;
	}
	public String getParentPhone() {
		return parentPhone;
	}
	public void setParentPhone(String parentPhone) {
		System.out.println(parentPhone + " <- parentPhone   setParentPhone()   Parent.java");
		this.parentPhone = parentPhone;
	}
	public Date getParent_registered_date() {
		return parent_registered_date;
	}
	public void setParent_registered_date(Date parent_registered_date) {
		System.out.println(parent_registered_date + " <- parent_registered_date   setParent_registered_date()   Parent.java");
		this.parent_registered_date = parent_registered_date;
	}	
}
