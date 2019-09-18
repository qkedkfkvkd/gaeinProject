package com.cafe24.smart_academy.academy_manage.employeeinfo.vo;

import java.util.Date;

// 강사 고등학교 학력정보 테이블 VO 객체
public class TeacherHighschoolEducation {
	private String	teacherId;						// 회원아이디(기본키) 강사테이블 참조 외래키 1:1 대응
	private String	highschoolSchoolname;			// 고등학교명
	private String	highschoolGraduation;			// 졸업일
	private Date	highschoolRegisteredDate;		// 등록일
	private Date	highschoolModificationDate;		// 수정일
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		System.out.println(teacherId + " <- teacherId   setTeacherId()   TeacherHighschoolEducation.java");
		this.teacherId = teacherId;
	}
	public String getHighschoolSchoolname() {
		return highschoolSchoolname;
	}
	public void setHighschoolSchoolname(String highschoolSchoolname) {
		System.out.println(highschoolSchoolname
				+ " <- highschoolSchoolname   setHighschoolSchoolname()   TeacherHighschoolEducation.java");
		this.highschoolSchoolname = highschoolSchoolname;
	}
	public String getHighschoolGraduation() {
		return highschoolGraduation;
	}
	public void setHighschoolGraduation(String highschoolGraduation) {
		System.out.println(highschoolGraduation
				+ " <- highschoolGraduation   setHighschoolGraduation()   TeacherHighschoolEducation.java");
		this.highschoolGraduation = highschoolGraduation;
	}
	public Date getHighschoolRegisteredDate() {
		return highschoolRegisteredDate;
	}
	public void setHighschoolRegisteredDate(Date highschoolRegisteredDate) {
		System.out.println(highschoolRegisteredDate
				+ " <- highschoolRegisteredDate   setHighschoolRegisteredDate()   TeacherHighschoolEducation.java");
		this.highschoolRegisteredDate = highschoolRegisteredDate;
	}
	public Date getHighschoolModificationDate() {
		return highschoolModificationDate;
	}
	public void setHighschoolModificationDate(Date highschoolModificationDate) {
		System.out.println(highschoolModificationDate
				+ " <- highschoolModificationDate   setHighschoolModificationDate()   TeacherHighschoolEducation.java");
		this.highschoolModificationDate = highschoolModificationDate;
	}
}
