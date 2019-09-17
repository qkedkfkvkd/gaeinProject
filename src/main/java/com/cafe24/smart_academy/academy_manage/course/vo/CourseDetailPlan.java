package com.cafe24.smart_academy.academy_manage.course.vo;

import java.util.Date;

// 강의 세부계획 테이블 VO 객체
public class CourseDetailPlan {
	private String	coursePlanNo;						// 강의계획서 코드(강의 계획서 테이블 참조 외래키 1:다 대응)
	private String	courseDays;							// 회차 (1회차, 2회차, ...)
	private String	courseContent;						// 강의내용
	private Date	courseDetailPlanRegisteredDate;		// 등록일
	
	
}
