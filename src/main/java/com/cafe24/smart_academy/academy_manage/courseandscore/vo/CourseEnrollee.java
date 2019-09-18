package com.cafe24.smart_academy.academy_manage.courseandscore.vo;

import java.util.Date;

// 수강신청 테이블 VO 객체
public class CourseEnrollee {
	private String	courseEnrolleeNo;				// 수강신청코드(기본키)
	private String	memberId;						// 회원아이디(로그인 테이블 참조 외래키 1:다 대응)
	private String	courseAssignmentNo;				// 강좌배정코드(강좌 강의실 배정 테이블 참조 외래키 1:다 대응)
	private Date	courseEnrolleeRegisteredDate;	// 등록일
	
	public String getCourseEnrolleeNo() {
		return courseEnrolleeNo;
	}
	public void setCourseEnrolleeNo(String courseEnrolleeNo) {
		System.out.println(courseEnrolleeNo
				+ " <- courseEnrolleeNo   setCourseEnrolleeNo()   CourseEnrollee.java");
		this.courseEnrolleeNo = courseEnrolleeNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   CourseEnrollee.java");
		this.memberId = memberId;
	}
	public String getCourseAssignmentNo() {
		return courseAssignmentNo;
	}
	public void setCourseAssignmentNo(String courseAssignmentNo) {
		System.out.println(courseAssignmentNo
				+ " <- courseAssignmentNo   setCourseAssignmentNo()   CourseEnrollee.java");
		this.courseAssignmentNo = courseAssignmentNo;
	}
	public Date getCourseEnrolleeRegisteredDate() {
		return courseEnrolleeRegisteredDate;
	}
	public void setCourseEnrolleeRegisteredDate(Date courseEnrolleeRegisteredDate) {
		System.out.println(courseEnrolleeRegisteredDate
				+ " <- courseEnrolleeRegisteredDate   setCourseEnrolleeRegisteredDate()   CourseEnrollee.java");
		this.courseEnrolleeRegisteredDate = courseEnrolleeRegisteredDate;
	}
}
