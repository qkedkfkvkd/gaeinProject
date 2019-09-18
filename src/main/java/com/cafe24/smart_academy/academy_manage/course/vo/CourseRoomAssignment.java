package com.cafe24.smart_academy.academy_manage.course.vo;

import java.util.Date;

// 강좌 강의실 배정 테이블 VO 객체
// -- 강사가 시간표 작성하여 관리자에게 승인요청한다.
public class CourseRoomAssignment {
	private String	courseAssignmentNo;					// 강좌배정코드(기본키)
	private String	roomNo;								// 강의실코드(강의실 테이블 참조 외래키 1:다 대응)
	private String	courseNo;							// 강좌코드(강좌 테이블 참조 외래키 1:다 대응)
	private String	courseAssignmentIsChanged;			// 코드변경유무 -> ENUM('유','무')
	private String	courseAssignmentReasonForChange;	// 코드변경사유
	private Date	courseAssignmentChangedDate;		// 코드변경일자
	private Date	courseAssignmentRegisteredDate;		// 등록일
	
	public String getCourseAssignmentNo() {
		return courseAssignmentNo;
	}
	public void setCourseAssignmentNo(String courseAssignmentNo) {
		System.out.println(courseAssignmentNo
				+ " <- courseAssignmentNo   setCourseAssignmentNo()   CourseRoomAssignment.java");
		this.courseAssignmentNo = courseAssignmentNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		System.out.println(roomNo + " <- roomNo   setRoomNo()   CourseRoomAssignment.java");
		this.roomNo = roomNo;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		System.out.println(courseNo + " <- courseNo   setCourseNo()   CourseRoomAssignment.java");
		this.courseNo = courseNo;
	}
	public String getCourseAssignmentIsChanged() {
		return courseAssignmentIsChanged;
	}
	public void setCourseAssignmentIsChanged(String courseAssignmentIsChanged) {
		System.out.println(courseAssignmentIsChanged
				+ " <- courseAssignmentIsChanged   setCourseAssignmentIsChanged()   CourseRoomAssignment.java");
		this.courseAssignmentIsChanged = courseAssignmentIsChanged;
	}
	public String getCourseAssignmentReasonForChange() {
		return courseAssignmentReasonForChange;
	}
	public void setCourseAssignmentReasonForChange(String courseAssignmentReasonForChange) {
		System.out.println(courseAssignmentReasonForChange
				+ " <- courseAssignmentReasonForChange   setCourseAssignmentReasonForChange()   CourseRoomAssignment.java");
		this.courseAssignmentReasonForChange = courseAssignmentReasonForChange;
	}
	public Date getCourseAssignmentChangedDate() {
		return courseAssignmentChangedDate;
	}
	public void setCourseAssignmentChangedDate(Date courseAssignmentChangedDate) {
		System.out.println(courseAssignmentChangedDate
				+ " <- courseAssignmentChangedDate   setCourseAssignmentChangedDate()   CourseRoomAssignment.java");
		this.courseAssignmentChangedDate = courseAssignmentChangedDate;
	}
	public Date getCourseAssignmentRegisteredDate() {
		return courseAssignmentRegisteredDate;
	}
	public void setCourseAssignmentRegisteredDate(Date courseAssignmentRegisteredDate) {
		System.out.println(courseAssignmentRegisteredDate
				+ " <- courseAssignmentRegisteredDate   setCourseAssignmentRegisteredDate()   CourseRoomAssignment.java");
		this.courseAssignmentRegisteredDate = courseAssignmentRegisteredDate;
	}
}
