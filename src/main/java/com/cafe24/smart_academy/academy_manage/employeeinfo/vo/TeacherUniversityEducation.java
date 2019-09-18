package com.cafe24.smart_academy.academy_manage.employeeinfo.vo;

import java.util.Date;

// 강사 대학교 학력정보 테이블 VO 객체
public class TeacherUniversityEducation {
	private String	teacherId;						// 회원아이디(기본키) 강사테이블 참조 외래키 1:1 대응
	private String	universitySchoolname;			// 대학교명
	private String	universityEntrance;				// 입학일
	private String	universityGraduation;			// 졸업일
	private String	universityMajor;				// 전공명
	private String	universityCredit;				// 학점평점
	private String	universityCreditPoint;			// 이수학점
	private Date	universityRegisteredDate;		// 등록일
	private Date	universityModificationDate;		// 최종수정일
	
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		System.out.println(teacherId + " <- teacherId   setTeacherId()   TeacherUniversityEducation.java");
		this.teacherId = teacherId;
	}
	public String getUniversitySchoolname() {
		return universitySchoolname;
	}
	public void setUniversitySchoolname(String universitySchoolname) {
		System.out.println(universitySchoolname
				+ " <- universitySchoolname   setUniversitySchoolname()   TeacherUniversityEducation.java");
		this.universitySchoolname = universitySchoolname;
	}
	public String getUniversityEntrance() {
		return universityEntrance;
	}
	public void setUniversityEntrance(String universityEntrance) {
		System.out.println(universityEntrance
				+ " <- universityEntrance   setUniversityEntrance()   TeacherUniversityEducation.java");
		this.universityEntrance = universityEntrance;
	}
	public String getUniversityGraduation() {
		return universityGraduation;
	}
	public void setUniversityGraduation(String universityGraduation) {
		System.out.println(universityGraduation
				+ " <- universityGraduation   setUniversityGraduation()   TeacherUniversityEducation.java");
		this.universityGraduation = universityGraduation;
	}
	public String getUniversityMajor() {
		return universityMajor;
	}
	public void setUniversityMajor(String universityMajor) {
		System.out.println(universityMajor
				+ " <- universityMajor   setUniversityMajor()   TeacherUniversityEducation.java");
		this.universityMajor = universityMajor;
	}
	public String getUniversityCredit() {
		return universityCredit;
	}
	public void setUniversityCredit(String universityCredit) {
		System.out.println(universityCredit
				+ " <- universityCredit   setUniversityCredit()   TeacherUniversityEducation.java");
		this.universityCredit = universityCredit;
	}
	public String getUniversityCreditPoint() {
		return universityCreditPoint;
	}
	public void setUniversityCreditPoint(String universityCreditPoint) {
		System.out.println(universityCreditPoint
				+ " <- universityCreditPoint   setUniversityCreditPoint()   TeacherUniversityEducation.java");
		this.universityCreditPoint = universityCreditPoint;
	}
	public Date getUniversityRegisteredDate() {
		return universityRegisteredDate;
	}
	public void setUniversityRegisteredDate(Date universityRegisteredDate) {
		System.out.println(universityRegisteredDate
				+ " <- universityRegisteredDate   setUniversityRegisteredDate()   TeacherUniversityEducation.java");
		this.universityRegisteredDate = universityRegisteredDate;
	}
	public Date getUniversityModificationDate() {
		return universityModificationDate;
	}
	public void setUniversityModificationDate(Date universityModificationDate) {
		System.out.println(universityModificationDate
				+ " <- universityModificationDate   setUniversityModificationDate()   TeacherUniversityEducation.java");
		this.universityModificationDate = universityModificationDate;
	}
}
