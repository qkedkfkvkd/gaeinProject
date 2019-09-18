package com.cafe24.smart_academy.academy_manage.employeeinfo.vo;

import java.util.Date;

// 강사 프로필 테이블 VO 객체
public class TeacherProfile {
	private String	teacherId;				// 회원아이디(기본키) 강사 테이블 참조 외래키 1:1 대응
	private String	teacherProfileTitle;	// 프로필 제목
	private String	teacherProfileContent;	// 약력 내용
	private Date	profileRegisteredDate;	// 등록일
	
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		System.out.println(teacherId + " <- teacherId   setTeacherId()   TeacherProfile.java");
		this.teacherId = teacherId;
	}
	public String getTeacherProfileTitle() {
		return teacherProfileTitle;
	}
	public void setTeacherProfileTitle(String teacherProfileTitle) {
		System.out.println(teacherProfileTitle
				+ " <- teacherProfileTitle   setTeacherProfileTitle()   TeacherProfile.java");
		this.teacherProfileTitle = teacherProfileTitle;
	}
	public String getTeacherProfileContent() {
		return teacherProfileContent;
	}
	public void setTeacherProfileContent(String teacherProfileContent) {
		System.out.println(teacherProfileContent
				+ " <- teacherProfileContent   setTeacherProfileContent()   TeacherProfile.java");
		this.teacherProfileContent = teacherProfileContent;
	}
	public Date getProfileRegisteredDate() {
		return profileRegisteredDate;
	}
	public void setProfileRegisteredDate(Date profileRegisteredDate) {
		System.out.println(profileRegisteredDate
				+ " <- profileRegisteredDate   setProfileRegisteredDate()   TeacherProfile.java");
		this.profileRegisteredDate = profileRegisteredDate;
	}
}
