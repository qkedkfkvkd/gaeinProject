package com.cafe24.smart_academy.academy_manage.course.vo;

import java.util.Date;

// 강의평가결과 테이블 VO 객체
public class CourseEvaluationResults {
	private String	evaluationResultsNo;						// 강의평가 결과코드(기본키)
	private String	evaluationAssignmentNo;
	// 강의별 평가항목 배정코드(강의별 평가항목 배정테이블 참조 외래키 1:다 대응)
	
	private String	evaluationResultsResponseContents;			// 응답내용
	private String	evaluationResultsResponseDate;				// 응답일자
	private Date	evaluationResultsResponseRegisteredDate;	// 등록일
	
	
}
