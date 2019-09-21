package com.cafe24.smart_academy.academy_manage.course.vo;

import java.util.Date;

// 강좌테이블 VO 객체
public class Course {
	private String	courseNo;				// 강좌코드(기본키)
	private String	subjectNo;				// 과목코드(과목테이블 참조 외래키 1:다 대응)
	private String	courseName;				// 강좌명
	private String	courseIsChanged;		// 코드변경유무 -> ENUM('유','무')
	private String	courseReasonForChange;	// 코드변경사유
	private Date	courseChangedDate;		// 코드변경일자
	private Date	courseRegisteredDate;	// 등록일
	
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		System.out.println(courseNo + " <- courseNo   setCourseNo()   Course.java");
		this.courseNo = courseNo;
	}
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		System.out.println(subjectNo + " <- subjectNo   setSubjectNo()   Course.java");
		this.subjectNo = subjectNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		System.out.println(courseName + " <- courseName   setCourseName()   Course.java");
		this.courseName = courseName;
	}
	public String getCourseIsChanged() {
		return courseIsChanged;
	}
	public void setCourseIsChanged(String courseIsChanged) {
		System.out.println(courseIsChanged
				+ " <- courseIsChanged   setCourseIsChanged()   Course.java");
		this.courseIsChanged = courseIsChanged;
	}
	public String getCourseReasonForChange() {
		return courseReasonForChange;
	}
	public void setCourseReasonForChange(String courseReasonForChange) {
		System.out.println(courseReasonForChange
				+ " <- courseReasonForChange   setCourseReasonForChange()   Course.java");
		this.courseReasonForChange = courseReasonForChange;
	}
	public Date getCourseChangedDate() {
		return courseChangedDate;
	}
	public void setCourseChangedDate(Date courseChangedDate) {
		System.out.println(courseChangedDate
				+ " <- courseChangedDate   setCourseChangedDate()   Course.java");
		this.courseChangedDate = courseChangedDate;
	}
	public Date getCourseRegisteredDate() {
		return courseRegisteredDate;
	}
	public void setCourseRegisteredDate(Date courseRegisteredDate) {
		System.out.println(courseRegisteredDate
				+ " <- courseRegisteredDate   setCourseRegisteredDate()   Course.java");
		this.courseRegisteredDate = courseRegisteredDate;
	}
}
