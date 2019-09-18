package com.cafe24.smart_academy.academy_manage.course.vo;

import java.util.Date;

// 강의평가결과 테이블 VO 객체
public class CourseEvaluationResults {
	private String	evaluationResultsNo;						// 강의평가 결과코드(기본키)
	private String	evaluationAssignmentNo;
	// 강의별 평가항목 배정코드(강의별 평가항목 배정테이블 참조 외래키 1:다 대응)
	
	private String	evaluationResultsResponseContents;			// 응답내용
	private Date	evaluationResultsResponseDate;				// 응답일자
	private Date	evaluationResultsResponseRegisteredDate;	// 등록일
	
	public String getEvaluationResultsNo() {
		return evaluationResultsNo;
	}
	public void setEvaluationResultsNo(String evaluationResultsNo) {
		System.out.println(evaluationResultsNo
				+ " <- evaluationResultsNo   setEvaluationResultsNo()   CourseEvaluationResults.java");
		this.evaluationResultsNo = evaluationResultsNo;
	}
	public String getEvaluationAssignmentNo() {
		return evaluationAssignmentNo;
	}
	public void setEvaluationAssignmentNo(String evaluationAssignmentNo) {
		System.out.println(evaluationAssignmentNo
				+ " <- evaluationAssignmentNo   setEvaluationAssignmentNo()   CourseEvaluationResults.java");
		this.evaluationAssignmentNo = evaluationAssignmentNo;
	}
	public String getEvaluationResultsResponseContents() {
		return evaluationResultsResponseContents;
	}
	public void setEvaluationResultsResponseContents(String evaluationResultsResponseContents) {
		System.out.println(evaluationResultsResponseContents
				+ " <- evaluationResultsResponseContents   setEvaluationResultsResponseContents()   CourseEvaluationResults.java");
		this.evaluationResultsResponseContents = evaluationResultsResponseContents;
	}
	public Date getEvaluationResultsResponseDate() {
		return evaluationResultsResponseDate;
	}
	public void setEvaluationResultsResponseDate(Date evaluationResultsResponseDate) {
		System.out.println(evaluationResultsResponseDate
				+ " <- evaluationResultsResponseDate   setEvaluationResultsResponseDate()   CourseEvaluationResults.java");
		this.evaluationResultsResponseDate = evaluationResultsResponseDate;
	}
	public Date getEvaluationResultsResponseRegisteredDate() {
		return evaluationResultsResponseRegisteredDate;
	}
	public void setEvaluationResultsResponseRegisteredDate(Date evaluationResultsResponseRegisteredDate) {
		System.out.println(evaluationResultsResponseRegisteredDate
				+ " <- evaluationResultsResponseRegisteredDate   setEvaluationResultsResponseRegisteredDate()   CourseEvaluationResults.java");
		this.evaluationResultsResponseRegisteredDate = evaluationResultsResponseRegisteredDate;
	}
}
