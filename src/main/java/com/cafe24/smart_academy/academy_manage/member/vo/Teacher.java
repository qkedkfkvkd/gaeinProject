package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 강사 테이블 VO 객체
public class Teacher {
	private String		memberId;					// 강사아이디(기본키, 로그인테이블 참조 외래키 1:1 대응)
	private String		courseNo;					// 담당강좌코드 (과목 테이블 참조 외래키 1:다 대응)
	private String		teacherIsChanged;			// 코드변경유무 -> ENUM('유','무')
	private String		teacherReasonForChange;		// 코드변경사유
	private Date		teacherChangedDate;			// 코드변경일
	private Date		teacherRegisteredDate;		// 강사 등록일
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   Teacher.java");
		this.memberId = memberId;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		System.out.println(courseNo + " <- courseNo   setCourseNo()   Teacher.java");
		this.courseNo = courseNo;
	}
	public String getTeacherIsChanged() {
		return teacherIsChanged;
	}
	public void setTeacherIsChanged(String teacherIsChanged) {
		System.out.println(teacherIsChanged
				+ " <- teacherIsChanged   setTeacherIsChanged()   Teacher.java");
		this.teacherIsChanged = teacherIsChanged;
	}
	public String getTeacherReasonForChange() {
		return teacherReasonForChange;
	}
	public void setTeacherReasonForChange(String teacherReasonForChange) {
		System.out.println(teacherReasonForChange
				+ " <- teacherReasonForChange   setTeacherReasonForChange()   Teacher.java");
		this.teacherReasonForChange = teacherReasonForChange;
	}
	public Date getTeacherChangedDate() {
		return teacherChangedDate;
	}
	public void setTeacherChangedDate(Date teacherChangedDate) {
		System.out.println(teacherChangedDate
				+ " <- teacherChangedDate   setTeacherChangedDate()   Teacher.java");
		this.teacherChangedDate = teacherChangedDate;
	}
	public Date getTeacherRegisteredDate() {
		return teacherRegisteredDate;
	}
	public void setTeacherRegisteredDate(Date teacherRegisteredDate) {
		System.out.println(teacherRegisteredDate
				+ " <- teacherRegisteredDate   setTeacherRegisteredDate()   Teacher.java");
		this.teacherRegisteredDate = teacherRegisteredDate;
	}
}
