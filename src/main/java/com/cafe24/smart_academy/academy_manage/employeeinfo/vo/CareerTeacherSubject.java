package com.cafe24.smart_academy.academy_manage.employeeinfo.vo;

import java.util.Date;

// 경력강사 담당과목 테이블 VO 객체
public class CareerTeacherSubject {
	private String	careerTeacherSubjectNo;					// 담당과목코드(기본키)
	private String	careerTeacherSubjectName;				// 과목명
	private Date	careerTeacherSubjectRegisteredDate;		// 등록일
	
	public String getCareerTeacherSubjectNo() {
		return careerTeacherSubjectNo;
	}
	public void setCareerTeacherSubjectNo(String careerTeacherSubjectNo) {
		System.out.println(careerTeacherSubjectNo
				+ " <- careerTeacherSubjectNo   setCareerTeacherSubjectNo()   CareerTeacherSubject.java");
		this.careerTeacherSubjectNo = careerTeacherSubjectNo;
	}
	public String getCareerTeacherSubjectName() {
		return careerTeacherSubjectName;
	}
	public void setCareerTeacherSubjectName(String careerTeacherSubjectName) {
		System.out.println(careerTeacherSubjectName
				+ " <- careerTeacherSubjectName   setCareerTeacherSubjectName()   CareerTeacherSubject.java");
		this.careerTeacherSubjectName = careerTeacherSubjectName;
	}
	public Date getCareerTeacherSubjectRegisteredDate() {
		return careerTeacherSubjectRegisteredDate;
	}
	public void setCareerTeacherSubjectRegisteredDate(Date careerTeacherSubjectRegisteredDate) {
		System.out.println(careerTeacherSubjectRegisteredDate
				+ " <- careerTeacherSubjectRegisteredDate   setCareerTeacherSubjectRegisteredDate()   CareerTeacherSubject.java");
		this.careerTeacherSubjectRegisteredDate = careerTeacherSubjectRegisteredDate;
	}
}
