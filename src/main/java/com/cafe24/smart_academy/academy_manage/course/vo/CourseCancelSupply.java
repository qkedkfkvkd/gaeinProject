package com.cafe24.smart_academy.academy_manage.course.vo;

import java.util.Date;

// 휴보강관리 테이블
public class CourseCancelSupply {
	private String		cancelSupplyNo;					// 휴보강코드(기본키)
	private String		roomNo;							// 강의실코드(강의실 테이블 참조 외래키 1:다 대응)
	private String		scheduleNo;						// 시간표코드(강의시간표 테이블 참조 외래키 1:다 대응)
	private String		lessonDate;						// 강의일자
	private String		startTime;						// 시작시간
	private String		endTime;						// 마침시간
	private String		cancelReason;					// 휴강사유
	private String		approvalStatus;					// 승인여부 -> ENUM('승인','미승인')
	private Date		cancelSupplyRegisteredDate;		// 등록일
	
	public String getCancelSupplyNo() {
		return cancelSupplyNo;
	}
	public void setCancelSupplyNo(String cancelSupplyNo) {
		System.out.println(cancelSupplyNo
				+ " <- cancelSupplyNo   setCancelSupplyNo()   CourseCancelSupply.java");
		this.cancelSupplyNo = cancelSupplyNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		System.out.println(roomNo + " <- roomNo   setRoomNo()   CourseCancelSupply.java");
		this.roomNo = roomNo;
	}
	public String getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(String scheduleNo) {
		System.out.println(scheduleNo + " <- scheduleNo   setScheduleNo()   CourseCancelSupply.java");
		this.scheduleNo = scheduleNo;
	}
	public String getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(String lessonDate) {
		System.out.println(lessonDate + " <- lessonDate   setLessonDate()   CourseCancelSupply.java");
		this.lessonDate = lessonDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		System.out.println(startTime + " <- startTime   setStartTime()   CourseCancelSupply.java");
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		System.out.println(endTime + " <- endTime   setEndTime()   CourseCancelSupply.java");
		this.endTime = endTime;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		System.out.println(cancelReason + " <- cancelReason   setCancelReason()   CourseCancelSupply.java");
		this.cancelReason = cancelReason;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		System.out.println(approvalStatus + " <- approvalStatus   setApprovalStatus()   CourseCancelSupply.java");
		this.approvalStatus = approvalStatus;
	}
	public Date getCancelSupplyRegisteredDate() {
		return cancelSupplyRegisteredDate;
	}
	public void setCancelSupplyRegisteredDate(Date cancelSupplyRegisteredDate) {
		System.out.println(cancelSupplyRegisteredDate
				+ " <- cancelSupplyRegisteredDate   setCancelSupplyRegisteredDate()   CourseCancelSupply.java");
		this.cancelSupplyRegisteredDate = cancelSupplyRegisteredDate;
	}
}
