package com.cafe24.smart_academy.academy_manage.course.attendance.vo;

import java.util.Date;

// 결석사유관리 테이블 VO 객체
public class AcademyAbsentReason {
	private String		attendanceNo;					// 출석부코드(기본키) - 출석부관리 테이블 참조 외래키 1:1 대응
	private String		absentReason;					// 결석사유
	private Date		absentReasonRegisteredDate;		// 등록일
	
	public String getAttendanceNo() {
		return attendanceNo;
	}
	public void setAttendanceNo(String attendanceNo) {
		System.out.println(attendanceNo + " <- attendanceNo   setAttendanceNo()   AcademyAbsentReason.java");
		this.attendanceNo = attendanceNo;
	}
	public String getAbsentReason() {
		return absentReason;
	}
	public void setAbsentReason(String absentReason) {
		System.out.println(absentReason + " <- absentReason   setAbsentReason()   AcademyAbsentReason.java");
		this.absentReason = absentReason;
	}
	public Date getAbsentReasonRegisteredDate() {
		return absentReasonRegisteredDate;
	}
	public void setAbsentReasonRegisteredDate(Date absentReasonRegisteredDate) {
		System.out.println(absentReasonRegisteredDate
				+ " <- absentReasonRegisteredDate   setAbsentReasonRegisteredDate()   AcademyAbsentReason.java");
		this.absentReasonRegisteredDate = absentReasonRegisteredDate;
	}
}
