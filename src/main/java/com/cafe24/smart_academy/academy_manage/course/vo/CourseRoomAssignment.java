package com.cafe24.smart_academy.academy_manage.course.vo;

import java.util.Date;

// 강좌 강의실 배정 테이블 VO 객체
// -- 강사가 시간표 작성하여 관리자에게 승인요청한다.
public class CourseRoomAssignment {
	private String	courseAssignmentNo;
	private String	roomNo;
	private String	courseNo;
	private String	courseAssignmentIsChanged;
	private String	courseAssignmentReasonForChange;
	private String	courseAssignmentChangedDate;
	private Date	courseAssignmentRegisteredDate;
}
