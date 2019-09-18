package com.cafe24.smart_academy.academy_manage.course.manage.vo;

import java.util.Date;

// 강의별 평가항목 배정 테이블 VO 객체
public class CourseEvaluationAssignment {
	private String	evaluationAssignmentNo;					// 강의별 평가항목 배정코드(기본키)
	private String	courseAssignmentNo;						// 강좌배정코드(강좌 강의실 배정 테이블 참조 외래키 1:다 대응)
	private String	evaluationQuestionNo;					// 강의평가항목코드(강의평가항목코드 테이블 참조 외래키 1:다 대응)
	private Date	evaluationAssignmentRegisteredDate;		// 등록일
	public String getEvaluationAssignmentNo() {
		return evaluationAssignmentNo;
	}
	public void setEvaluationAssignmentNo(String evaluationAssignmentNo) {
		System.out.println(evaluationAssignmentNo
				+ " <- evaluationAssignmentNo   setEvaluationAssignmentNo()   CourseEvaluationAssignment.java");
		this.evaluationAssignmentNo = evaluationAssignmentNo;
	}
	public String getCourseAssignmentNo() {
		return courseAssignmentNo;
	}
	public void setCourseAssignmentNo(String courseAssignmentNo) {
		System.out.println(courseAssignmentNo
				+ " <- courseAssignmentNo   setCourseAssignmentNo()   CourseEvaluationAssignment.java");
		this.courseAssignmentNo = courseAssignmentNo;
	}
	public String getEvaluationQuestionNo() {
		return evaluationQuestionNo;
	}
	public void setEvaluationQuestionNo(String evaluationQuestionNo) {
		System.out.println(evaluationQuestionNo
				+ " <- evaluationQuestionNo   setEvaluationQuestionNo()   CourseEvaluationAssignment.java");
		this.evaluationQuestionNo = evaluationQuestionNo;
	}
	public Date getEvaluationAssignmentRegisteredDate() {
		return evaluationAssignmentRegisteredDate;
	}
	public void setEvaluationAssignmentRegisteredDate(Date evaluationAssignmentRegisteredDate) {
		System.out.println(evaluationAssignmentRegisteredDate
				+ " <- evaluationAssignmentRegisteredDate   setEvaluationAssignmentRegisteredDate()   CourseEvaluationAssignment.java");
		this.evaluationAssignmentRegisteredDate = evaluationAssignmentRegisteredDate;
	}
}
