package com.cafe24.smart_academy.academy_manage.course.attendance.vo;

import java.util.Date;

// 출석부관리 테이블 VO 객체
public class AcademyAttendance {
	private String	attendanceNo;				// 출석부코드(기본키)
	private String	scheduleNo;					// 시간표코드(강의 시간표 테이블 참조 외래키 1:다 대응)
	private String	courseEnrolleeNo;			// 수강신청코드(수강신청 테이블 참조 외래키 1:다 대응)
	private String	attendState;				// 출결상태
	private Date	attendanceRegisteredDate;	// 등록일
	
	
}
