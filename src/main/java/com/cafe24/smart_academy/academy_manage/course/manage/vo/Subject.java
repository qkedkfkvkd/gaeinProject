package com.cafe24.smart_academy.academy_manage.course.manage.vo;

import java.util.Date;

// 과목 테이블 VO 객체
public class Subject {
	private String	subjectNo;					// 과목코드(기본키)
	private String	subjectName;				// 과목명
	private String	subjectIsChanged;			// 코드변경유무  -> ENUM('유','무')
	private String	subjectReasonForChange;		// 코드변경사유
	private Date	subjectChangedDate;			// 코드변경일자
	private Date	subjectRegisteredDate;		// 등록일
	
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		System.out.println(subjectNo + " <- subjectNo   setSubjectNo()   Subject.java");
		this.subjectNo = subjectNo;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		System.out.println(subjectName + " <- subjectName   setSubjectName()   Subject.java");
		this.subjectName = subjectName;
	}
	public String getSubjectIsChanged() {
		return subjectIsChanged;
	}
	public void setSubjectIsChanged(String subjectIsChanged) {
		System.out.println(subjectIsChanged
				+ " <- subjectIsChanged   setSubjectIsChanged()   Subject.java");
		this.subjectIsChanged = subjectIsChanged;
	}
	public String getSubjectReasonForChange() {
		return subjectReasonForChange;
	}
	public void setSubjectReasonForChange(String subjectReasonForChange) {
		System.out.println(subjectReasonForChange
				+ " <- subjectReasonForChange   setSubjectReasonForChange()   Subject.java");
		this.subjectReasonForChange = subjectReasonForChange;
	}
	public Date getSubjectChangedDate() {
		return subjectChangedDate;
	}
	public void setSubjectChangedDate(Date subjectChangedDate) {
		System.out.println(subjectChangedDate
				+ " <- subjectChangedDate   setSubjectChangedDate()   Subject.java");
		this.subjectChangedDate = subjectChangedDate;
	}
	public Date getSubjectRegisteredDate() {
		return subjectRegisteredDate;
	}
	public void setSubjectRegisteredDate(Date subjectRegisteredDate) {
		System.out.println(subjectRegisteredDate
				+ " <- subjectRegisteredDate   setSubjectRegisteredDate()   Subject.java");
		this.subjectRegisteredDate = subjectRegisteredDate;
	}
}
