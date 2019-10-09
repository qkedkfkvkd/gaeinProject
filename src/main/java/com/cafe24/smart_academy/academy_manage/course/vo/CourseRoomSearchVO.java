package com.cafe24.smart_academy.academy_manage.course.vo;

// 강좌강의실배정, 시간표 리스트 검색 객체
public class CourseRoomSearchVO {
	private String	subjectNo;			// 과목테이블의 과목코드(기본키)
	private String	courseNo;			// 강좌테이블의 강좌코드(기본키)
	private String	roomNo;				// 강의실 테이블의 강의실코드(기본키)
	private String	courseAssignmentNo;	// 강좌 강의실 배정 테이블의 강좌배정코드(기본키)
	private String	memberId;
	// 로그인, 회원신상정보, 강사, 강사개인정보 테이블들의 회원아이디(기본키)
	private String scheduleApprovalStatus;	// 시간표테이블의 관리자 승인여부
	
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		System.out.println(subjectNo + " <- subjectNo   setSubjectNo()   CourseSearchVO.java");
		this.subjectNo = subjectNo;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		System.out.println(courseNo + " <- courseNo   setCourseNo()   CourseSearchVO.java");
		this.courseNo = courseNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		System.out.println(roomNo + " <- roomNo   setRoomNo()   CourseSearchVO.java");
		this.roomNo = roomNo;
	}
	public String getCourseAssignmentNo() {
		return courseAssignmentNo;
	}
	public void setCourseAssignmentNo(String courseAssignmentNo) {
		System.out.println(courseAssignmentNo
				+ " <- courseAssignmentNo   setCourseAssignmentNo()   CourseSearchVO.java");
		this.courseAssignmentNo = courseAssignmentNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   CourseSearchVO.java");
		this.memberId = memberId;
	}
	public String getScheduleApprovalStatus() {
		return scheduleApprovalStatus;
	}
	public void setScheduleApprovalStatus(String scheduleApprovalStatus) {
		System.out.println(scheduleApprovalStatus
				+ " <- scheduleApprovalStatus   setScheduleApprovalStatus()   CourseSearchVO.java");
		this.scheduleApprovalStatus = scheduleApprovalStatus;
	}
	
}
