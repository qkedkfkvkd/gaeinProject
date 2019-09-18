package com.cafe24.smart_academy.academy_manage.course.vo;

import java.util.Date;

// 강의 시간표 테이블 VO 객체
public class CourseSchedule {
	private String	scheduleNo;					// 시간표코드(기본키)
	private String	courseAssignmentNo;			// 강좌배정코드(강좌 강의실 배정 테이블 참조 외래키 1:다 대응)
	private String	day;						// 요일
	private String	startTime;					// 시작시간
	private String	endTime;					// 마침시간
	private String	scheduleApprovalStatus;		// 승인여부
	private Date	scheduleRegisteredDate;		// 등록일
	private Date	scheduleModificationDate;	// 최종수정일자
	
	public String getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(String scheduleNo) {
		System.out.println(scheduleNo + " <- scheduleNo   setScheduleNo()   CourseSchedule.java");
		this.scheduleNo = scheduleNo;
	}
	public String getCourseAssignmentNo() {
		return courseAssignmentNo;
	}
	public void setCourseAssignmentNo(String courseAssignmentNo) {
		System.out.println(courseAssignmentNo
				+ " <- courseAssignmentNo   setCourseAssignmentNo()   CourseSchedule.java");
		this.courseAssignmentNo = courseAssignmentNo;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		System.out.println(day + " <- day   setDay()   CourseSchedule.java");
		this.day = day;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		System.out.println(startTime + " <- startTime   setStartTime()   CourseSchedule.java");
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		System.out.println(endTime + " <- endTime   setEndTime()   CourseSchedule.java");
		this.endTime = endTime;
	}
	public String getScheduleApprovalStatus() {
		return scheduleApprovalStatus;
	}
	public void setScheduleApprovalStatus(String scheduleApprovalStatus) {
		System.out.println(scheduleApprovalStatus
				+ " <- scheduleApprovalStatus   setScheduleApprovalStatus()   CourseSchedule.java");
		this.scheduleApprovalStatus = scheduleApprovalStatus;
	}
	public Date getScheduleRegisteredDate() {
		return scheduleRegisteredDate;
	}
	public void setScheduleRegisteredDate(Date scheduleRegisteredDate) {
		System.out.println(scheduleRegisteredDate
				+ " <- scheduleRegisteredDate   setScheduleRegisteredDate()   CourseSchedule.java");
		this.scheduleRegisteredDate = scheduleRegisteredDate;
	}
	public Date getScheduleModificationDate() {
		return scheduleModificationDate;
	}
	public void setScheduleModificationDate(Date scheduleModificationDate) {
		System.out.println(scheduleModificationDate
				+ " <- scheduleModificationDate   setScheduleModificationDate()   CourseSchedule.java");
		this.scheduleModificationDate = scheduleModificationDate;
	}
}
