package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 강사 테이블 VO 객체
public class Teacher {
	private String		teacherId;					// 강사아이디(기본키, 로그인테이블 참조 외래키 1:1 대응)
	private String		subjectNo;					// 담당과목코드 (과목 테이블 참조 외래키 1:다 대응)
	private Date		teacher_registered_date;	// 강사 등록일
	
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		System.out.println(teacherId + " <- teacherId   setTeacherId()   Teacher.java");
		this.teacherId = teacherId;
	}
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		System.out.println(subjectNo + " <- subjectNo   setSubjectNo()   Teacher.java");
		this.subjectNo = subjectNo;
	}
	public Date getTeacher_registered_date() {
		return teacher_registered_date;
	}
	public void setTeacher_registered_date(Date teacher_registered_date) {
		System.out.println(teacher_registered_date + " <- teacher_registered_date   setTeacher_registered_date()   Teacher.java");
		this.teacher_registered_date = teacher_registered_date;
	}
}
