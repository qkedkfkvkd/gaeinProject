package com.cafe24.smart_academy.academy_manage.course.vo;

import java.util.Date;

// 강의 계획서 테이블 VO 객체
public class CoursePlan {
	private String	coursePlanNo;				// 강의계획서코드(기본키)
	private String	courseAssignmentNo;
	// 강좌배정코드(강좌 강의실 배정 테이블 참조 외래키 1:다 대응)
	
	private String	coursePlanTitle;			// 제목
	private String	courseGoal;					// 학습목표
	private Date	coursePlanRegisteredDate;	// 등록일
	
	
}
