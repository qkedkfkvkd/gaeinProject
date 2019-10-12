package com.cafe24.smart_academy.academy_manage.course.vo;

// 강좌강의실배정, 시간표 리스트 검색 객체
public class CourseRoomSearchVO {
	private String	subjectNo;				// 과목테이블의 과목코드(기본키)
	private String	courseNo;				// 강좌테이블의 강좌코드(기본키)
	private String	roomNo;					// 강의실 테이블의 강의실코드(기본키)
	private String	courseAssignmentNo;		// 강좌 강의실 배정 테이블의 강좌배정코드(기본키)
	private String	memberId;				// 로그인, 회원신상정보, 강사, 강사개인정보 테이블들의 회원아이디(기본키)
	private String	courseEnrolleeNo;		// 수강신청 테이블의 수강신청코드(기본키)
	private String	scheduleNo;				// 강좌 시간표 테이블의 시간표코드(기본키)
	
	////// 강의 시간표 검색 변수들
	private String	day;					// 강좌시간표의 요일
	private String	scheduleApprovalStatus;	// 강좌시간표의 관리자 승인여부
	
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		System.out.println(subjectNo + " <- subjectNo   setSubjectNo()   CourseRoomSearchVO.java");
		this.subjectNo = subjectNo;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		System.out.println(courseNo + " <- courseNo   setCourseNo()   CourseRoomSearchVO.java");
		this.courseNo = courseNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		System.out.println(roomNo + " <- roomNo   setRoomNo()   CourseRoomSearchVO.java");
		this.roomNo = roomNo;
	}
	public String getCourseAssignmentNo() {
		return courseAssignmentNo;
	}
	public void setCourseAssignmentNo(String courseAssignmentNo) {
		System.out.println(courseAssignmentNo
				+ " <- courseAssignmentNo   setCourseAssignmentNo()   CourseRoomSearchVO.java");
		this.courseAssignmentNo = courseAssignmentNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   CourseRoomSearchVO.java");
		this.memberId = memberId;
	}
	public String getCourseEnrolleeNo() {
		return courseEnrolleeNo;
	}
	public void setCourseEnrolleeNo(String courseEnrolleeNo) {
		System.out.println(courseEnrolleeNo
				+ " <- courseEnrolleeNo   setCourseEnrolleeNo()   CourseRoomSearchVO.java");
		this.courseEnrolleeNo = courseEnrolleeNo;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		System.out.println(day + " <- day   setDay()   CourseRoomSearchVO.java");
		this.day = day;
	}
	public String getScheduleApprovalStatus() {
		return scheduleApprovalStatus;
	}
	public void setScheduleApprovalStatus(String scheduleApprovalStatus) {
		System.out.println(scheduleApprovalStatus
				+ " <- scheduleApprovalStatus   setScheduleApprovalStatus()   CourseRoomSearchVO.java");
		this.scheduleApprovalStatus = scheduleApprovalStatus;
	}
	public String getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(String scheduleNo) {
		System.out.println(scheduleNo + " <- scheduleNo   setScheduleNo()   CourseRoomSearchVO.java");
		this.scheduleNo = scheduleNo;
	}
}
