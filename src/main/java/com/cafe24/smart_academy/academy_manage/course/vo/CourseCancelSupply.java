package com.cafe24.smart_academy.academy_manage.course.vo;

import java.util.Date;

// 휴보강관리 테이블
public class CourseCancelSupply {
	private String		cancelSupplyNo;					// 휴보강코드(기본키)
	private String		roomNo;							// 강의실코드(강의실 테이블 참조 외래키 1:다 대응)
	private String		scheduleNo;						// 시간표코드(강의시간표 테이블 참조 외래키 1:다 대응)
	private String		lessonDate;						// 강의 일자
	private String		startTime;						// 시작시간
	private String		endTime;						// 마침시간
	private String		cancelReason;					// 휴강사유
	private String		approvalStatus;					// 승인여부 -> ENUM('승인','미승인')
	private Date		cancelSupplyRegisteredDate;		// 등록일
	
	
}
