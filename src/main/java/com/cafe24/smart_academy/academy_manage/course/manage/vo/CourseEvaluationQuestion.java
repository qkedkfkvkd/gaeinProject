package com.cafe24.smart_academy.academy_manage.course.manage.vo;

import java.util.Date;

// 강의평가항목코드 테이블 VO 객체
public class CourseEvaluationQuestion {
	private String	evaluationQuestionNo;				// 강의평가항목코드(기본키)
	private String	evaluationQuestionTarget;
	// 평가대상 -> ENUM('수업진행','강의방법','교육환경','수업참여')
	
	private String	evaluationQuestionName;				// 강의평가항목명
	private String	evaluationQuestionIsChanged;		// 코드변경유무 -> ENUM('유','무')
	private String	evaluationQuestionReasonForChange;	// 코드변경사유
	private Date	evaluationQuestionChangedDate;		// 코드변경일자
	private Date	evaluationQuestionRegisteredDate;	// 등록일
	
	public String getEvaluationQuestionNo() {
		return evaluationQuestionNo;
	}
	public void setEvaluationQuestionNo(String evaluationQuestionNo) {
		System.out.println(evaluationQuestionNo
				+ " <- evaluationQuestionNo   setEvaluationQuestionNo()   CourseEvaluationQuestion.java");
		this.evaluationQuestionNo = evaluationQuestionNo;
	}
	public String getEvaluationQuestionTarget() {
		return evaluationQuestionTarget;
	}
	public void setEvaluationQuestionTarget(String evaluationQuestionTarget) {
		System.out.println(evaluationQuestionTarget
				+ " <- evaluationQuestionTarget   setEvaluationQuestionTarget()   CourseEvaluationQuestion.java");
		this.evaluationQuestionTarget = evaluationQuestionTarget;
	}
	public String getEvaluationQuestionName() {
		return evaluationQuestionName;
	}
	public void setEvaluationQuestionName(String evaluationQuestionName) {
		System.out.println(evaluationQuestionName
				+ " <- evaluationQuestionName   setEvaluationQuestionName()   CourseEvaluationQuestion.java");
		this.evaluationQuestionName = evaluationQuestionName;
	}
	public String getEvaluationQuestionIsChanged() {
		return evaluationQuestionIsChanged;
	}
	public void setEvaluationQuestionIsChanged(String evaluationQuestionIsChanged) {
		System.out.println(evaluationQuestionIsChanged
				+ " <- evaluationQuestionIsChanged   setEvaluationQuestionIsChanged()   CourseEvaluationQuestion.java");
		this.evaluationQuestionIsChanged = evaluationQuestionIsChanged;
	}
	public String getEvaluationQuestionReasonForChange() {
		return evaluationQuestionReasonForChange;
	}
	public void setEvaluationQuestionReasonForChange(String evaluationQuestionReasonForChange) {
		System.out.println(evaluationQuestionReasonForChange
				+ " <- evaluationQuestionReasonForChange   setEvaluationQuestionReasonForChange()   CourseEvaluationQuestion.java");
		this.evaluationQuestionReasonForChange = evaluationQuestionReasonForChange;
	}
	public Date getEvaluationQuestionChangedDate() {
		return evaluationQuestionChangedDate;
	}
	public void setEvaluationQuestionChangedDate(Date evaluationQuestionChangedDate) {
		System.out.println(evaluationQuestionChangedDate
				+ " <- evaluationQuestionChangedDate   setEvaluationQuestionChangedDate()   CourseEvaluationQuestion.java");
		this.evaluationQuestionChangedDate = evaluationQuestionChangedDate;
	}
	public Date getEvaluationQuestionRegisteredDate() {
		return evaluationQuestionRegisteredDate;
	}
	public void setEvaluationQuestionRegisteredDate(Date evaluationQuestionRegisteredDate) {
		System.out.println(evaluationQuestionRegisteredDate
				+ " <- evaluationQuestionRegisteredDate   setEvaluationQuestionRegisteredDate()   CourseEvaluationQuestion.java");
		this.evaluationQuestionRegisteredDate = evaluationQuestionRegisteredDate;
	}
}
