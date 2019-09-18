package com.cafe24.smart_academy.academy_manage.course.attendance.vo;

import java.util.Date;

// 출석부관리 테이블 VO 객체
public class AcademyAttendance {
	private String	attendanceNo;				// 출석부코드(기본키)
	private String	scheduleNo;					// 시간표코드(강의 시간표 테이블 참조 외래키 1:다 대응)
	private String	courseEnrolleeNo;			// 수강신청코드(수강신청 테이블 참조 외래키 1:다 대응)
	private String	attendState;				// 출결상태
	private Date	attendanceRegisteredDate;	// 등록일
	
	public String getAttendanceNo() {
		return attendanceNo;
	}
	public void setAttendanceNo(String attendanceNo) {
		System.out.println(attendanceNo + " <- attendanceNo   setAttendanceNo()   AcademyAttendance.java");
		this.attendanceNo = attendanceNo;
	}
	public String getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(String scheduleNo) {
		System.out.println(scheduleNo + " <- scheduleNo   setScheduleNo()   AcademyAttendance.java");
		this.scheduleNo = scheduleNo;
	}
	public String getCourseEnrolleeNo() {
		return courseEnrolleeNo;
	}
	public void setCourseEnrolleeNo(String courseEnrolleeNo) {
		System.out.println(courseEnrolleeNo
				+ " <- courseEnrolleeNo   setCourseEnrolleeNo()   AcademyAttendance.java");
		this.courseEnrolleeNo = courseEnrolleeNo;
	}
	public String getAttendState() {
		return attendState;
	}
	public void setAttendState(String attendState) {
		System.out.println(attendState + " <- attendState   setAttendState()   AcademyAttendance.java");
		this.attendState = attendState;
	}
	public Date getAttendanceRegisteredDate() {
		return attendanceRegisteredDate;
	}
	public void setAttendanceRegisteredDate(Date attendanceRegisteredDate) {
		System.out.println(attendanceRegisteredDate
				+ " <- attendanceRegisteredDate   setAttendanceRegisteredDate()   AcademyAttendance.java");
		this.attendanceRegisteredDate = attendanceRegisteredDate;
	}
}
