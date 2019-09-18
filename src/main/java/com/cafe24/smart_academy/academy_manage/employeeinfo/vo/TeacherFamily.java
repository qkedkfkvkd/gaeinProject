package com.cafe24.smart_academy.academy_manage.employeeinfo.vo;

import java.util.Date;

// 강사 가족사항 테이블 VO 객체
public class TeacherFamily {
	private String	teacherId;					// 회원아이디(기본키) 강사테이블 참조 외래키 1:1 대응
	private String	familyName;					// 가족 이름
	private String	familyRelation;				// 가족관계(부, 모, 형제, 자매)
	private String	familyPhoneNumber;			// 가족 휴대폰번호
	private String	familyBirthday;				// 가족 생년월일
	private String	familyAcademicBackground;	// 가족 학력
	private String	familyJob;					// 가족 직업
	private Date	familyRegisteredDate;		// 등록일
	private String	familyModificationDate;		// 최종수정일
	
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		System.out.println(teacherId + " <- teacherId   setTeacherId()   TeacherFamily.java");
		this.teacherId = teacherId;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		System.out.println(familyName + " <- familyName   setFamilyName()   TeacherFamily.java");
		this.familyName = familyName;
	}
	public String getFamilyRelation() {
		return familyRelation;
	}
	public void setFamilyRelation(String familyRelation) {
		System.out.println(familyRelation + " <- familyRelation   setFamilyRelation()   TeacherFamily.java");
		this.familyRelation = familyRelation;
	}
	public String getFamilyPhoneNumber() {
		return familyPhoneNumber;
	}
	public void setFamilyPhoneNumber(String familyPhoneNumber) {
		System.out.println(familyPhoneNumber
				+ " <- familyPhoneNumber   setFamilyPhoneNumber()   TeacherFamily.java");
		this.familyPhoneNumber = familyPhoneNumber;
	}
	public String getFamilyBirthday() {
		return familyBirthday;
	}
	public void setFamilyBirthday(String familyBirthday) {
		System.out.println(familyBirthday + " <- familyBirthday   setFamilyBirthday()   TeacherFamily.java");
		this.familyBirthday = familyBirthday;
	}
	public String getFamilyAcademicBackground() {
		return familyAcademicBackground;
	}
	public void setFamilyAcademicBackground(String familyAcademicBackground) {
		System.out.println(familyAcademicBackground
				+ " <- familyAcademicBackground   setFamilyAcademicBackground()   TeacherFamily.java");
		this.familyAcademicBackground = familyAcademicBackground;
	}
	public String getFamilyJob() {
		return familyJob;
	}
	public void setFamilyJob(String familyJob) {
		System.out.println(familyJob + " <- familyJob   setFamilyJob()   TeacherFamily.java");
		this.familyJob = familyJob;
	}
	public Date getFamilyRegisteredDate() {
		return familyRegisteredDate;
	}
	public void setFamilyRegisteredDate(Date familyRegisteredDate) {
		System.out.println(familyRegisteredDate
				+ " <- familyRegisteredDate   setFamilyRegisteredDate()   TeacherFamily.java");
		this.familyRegisteredDate = familyRegisteredDate;
	}
	public String getFamilyModificationDate() {
		return familyModificationDate;
	}
	public void setFamilyModificationDate(String familyModificationDate) {
		System.out.println(familyModificationDate
				+ " <- familyModificationDate   setFamilyModificationDate()   TeacherFamily.java");
		this.familyModificationDate = familyModificationDate;
	}
}
