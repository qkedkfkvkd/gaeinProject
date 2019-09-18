package com.cafe24.smart_academy.academy_manage.employeeinfo.vo;

import java.util.Date;

// 강사 경력정보 테이블 VO 객체
public class TeacherCareerInfo {
	private String	teacherId;						// 회원아이디(강사테이블 참조 외래키 1:1 대응)
	private String	careerTeacherSubjectNo;			// 담당과목코드(경력강사 담당과목 테이블 참조 외래키 1:다 대응)
	private String	careerPlaceOfEmployment;		// 근무처명
	private String	careerPlaceOfEmploymentAddr;	// 근무처주소
	private String	careerPlaceOfEmploymentCall;	// 근무처전화번호
	private String	careerDateOfEmployment;			// 입사일자
	private String	careerJoiningACompanyCategory;	// 입사구분
	private String	careerRetirementDate;			// 퇴직일자
	private String	careerRetirementReason;			// 퇴직사유
	private String	careerPosition;					// 직책
	private Date	careerRegisteredDate;			// 등록일
	private Date	careerModificationDate;			// 최종수정일
	
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		System.out.println(teacherId + " <- teacherId   setTeacherId()   TeacherCareerInfo.java");
		this.teacherId = teacherId;
	}
	public String getCareerTeacherSubjectNo() {
		return careerTeacherSubjectNo;
	}
	public void setCareerTeacherSubjectNo(String careerTeacherSubjectNo) {
		System.out.println(careerTeacherSubjectNo
				+ " <- careerTeacherSubjectNo   setCareerTeacherSubjectNo()   TeacherCareerInfo.java");
		this.careerTeacherSubjectNo = careerTeacherSubjectNo;
	}
	public String getCareerPlaceOfEmployment() {
		return careerPlaceOfEmployment;
	}
	public void setCareerPlaceOfEmployment(String careerPlaceOfEmployment) {
		System.out.println(careerPlaceOfEmployment
				+ " <- careerPlaceOfEmployment   setCareerPlaceOfEmployment()   TeacherCareerInfo.java");
		this.careerPlaceOfEmployment = careerPlaceOfEmployment;
	}
	public String getCareerPlaceOfEmploymentAddr() {
		return careerPlaceOfEmploymentAddr;
	}
	public void setCareerPlaceOfEmploymentAddr(String careerPlaceOfEmploymentAddr) {
		System.out.println(careerPlaceOfEmploymentAddr
				+ " <- careerPlaceOfEmploymentAddr   setCareerPlaceOfEmploymentAddr()   TeacherCareerInfo.java");
		this.careerPlaceOfEmploymentAddr = careerPlaceOfEmploymentAddr;
	}
	public String getCareerPlaceOfEmploymentCall() {
		return careerPlaceOfEmploymentCall;
	}
	public void setCareerPlaceOfEmploymentCall(String careerPlaceOfEmploymentCall) {
		System.out.println(careerPlaceOfEmploymentCall
				+ " <- careerPlaceOfEmploymentCall   setCareerPlaceOfEmploymentCall()   TeacherCareerInfo.java");
		this.careerPlaceOfEmploymentCall = careerPlaceOfEmploymentCall;
	}
	public String getCareerDateOfEmployment() {
		return careerDateOfEmployment;
	}
	public void setCareerDateOfEmployment(String careerDateOfEmployment) {
		System.out.println(careerDateOfEmployment
				+ " <- careerDateOfEmployment   setCareerDateOfEmployment()   TeacherCareerInfo.java");
		this.careerDateOfEmployment = careerDateOfEmployment;
	}
	public String getCareerJoiningACompanyCategory() {
		return careerJoiningACompanyCategory;
	}
	public void setCareerJoiningACompanyCategory(String careerJoiningACompanyCategory) {
		System.out.println(careerJoiningACompanyCategory
				+ " <- careerJoiningACompanyCategory   setCareerJoiningACompanyCategory()   TeacherCareerInfo.java");
		this.careerJoiningACompanyCategory = careerJoiningACompanyCategory;
	}
	public String getCareerRetirementDate() {
		return careerRetirementDate;
	}
	public void setCareerRetirementDate(String careerRetirementDate) {
		System.out.println(careerRetirementDate
				+ " <- careerRetirementDate   setCareerRetirementDate()   TeacherCareerInfo.java");
		this.careerRetirementDate = careerRetirementDate;
	}
	public String getCareerRetirementReason() {
		return careerRetirementReason;
	}
	public void setCareerRetirementReason(String careerRetirementReason) {
		System.out.println(careerRetirementReason
				+ " <- careerRetirementReason   setCareerRetirementReason()   TeacherCareerInfo.java");
		this.careerRetirementReason = careerRetirementReason;
	}
	public String getCareerPosition() {
		return careerPosition;
	}
	public void setCareerPosition(String careerPosition) {
		System.out.println(careerPosition
				+ " <- careerPosition   setCareerPosition()   TeacherCareerInfo.java");
		this.careerPosition = careerPosition;
	}
	public Date getCareerRegisteredDate() {
		return careerRegisteredDate;
	}
	public void setCareerRegisteredDate(Date careerRegisteredDate) {
		System.out.println(careerRegisteredDate
				+ " <- careerRegisteredDate   setCareerRegisteredDate()   TeacherCareerInfo.java");
		this.careerRegisteredDate = careerRegisteredDate;
	}
	public Date getCareerModificationDate() {
		return careerModificationDate;
	}
	public void setCareerModificationDate(Date careerModificationDate) {
		System.out.println(careerModificationDate
				+ " <- careerModificationDate   setCareerModificationDate()   TeacherCareerInfo.java");
		this.careerModificationDate = careerModificationDate;
	}
}
