package com.cafe24.smart_academy.academy_manage.course.manage.vo;

import java.util.Date;

// 성적평가 기준 테이블 VO 객체
public class GradingCriteria {
	private String	gradingCriteriaRating;				// 등급(기본키)
	private int		gradingCriteriaStartOfSection;		// 시작점수
	private int		gradingCriteriaEndOfSection;		// 끝점수
	private double	gradingCriteria;					// 평점
	private Date	gradingCriteriaRegisteredDate;		// 등록일
	private Date	gradingCriteriaModificationDate;	// 최종 수정일
	
	public String getGradingCriteriaRating() {
		return gradingCriteriaRating;
	}
	public void setGradingCriteriaRating(String gradingCriteriaRating) {
		System.out.println(gradingCriteriaRating
				+ " <- gradingCriteriaRating   setGradingCriteriaRating()   GradingCriteria.java");
		this.gradingCriteriaRating = gradingCriteriaRating;
	}
	public int getGradingCriteriaStartOfSection() {
		return gradingCriteriaStartOfSection;
	}
	public void setGradingCriteriaStartOfSection(int gradingCriteriaStartOfSection) {
		System.out.println(gradingCriteriaStartOfSection
				+ " <- gradingCriteriaStartOfSection   setGradingCriteriaStartOfSection()   GradingCriteria.java");
		this.gradingCriteriaStartOfSection = gradingCriteriaStartOfSection;
	}
	public int getGradingCriteriaEndOfSection() {
		return gradingCriteriaEndOfSection;
	}
	public void setGradingCriteriaEndOfSection(int gradingCriteriaEndOfSection) {
		System.out.println(gradingCriteriaEndOfSection
				+ " <- gradingCriteriaEndOfSection   setGradingCriteriaEndOfSection()   GradingCriteria.java");
		this.gradingCriteriaEndOfSection = gradingCriteriaEndOfSection;
	}
	public double getGradingCriteria() {
		return gradingCriteria;
	}
	public void setGradingCriteria(double gradingCriteria) {
		System.out.println(gradingCriteria
				+ " <- gradingCriteria   setGradingCriteria()   GradingCriteria.java");
		this.gradingCriteria = gradingCriteria;
	}
	public Date getGradingCriteriaRegisteredDate() {
		return gradingCriteriaRegisteredDate;
	}
	public void setGradingCriteriaRegisteredDate(Date gradingCriteriaRegisteredDate) {
		System.out.println(gradingCriteriaRegisteredDate
				+ " <- gradingCriteriaRegisteredDate   setGradingCriteriaRegisteredDate()   GradingCriteria.java");
		this.gradingCriteriaRegisteredDate = gradingCriteriaRegisteredDate;
	}
	public Date getGradingCriteriaModificationDate() {
		return gradingCriteriaModificationDate;
	}
	public void setGradingCriteriaModificationDate(Date gradingCriteriaModificationDate) {
		System.out.println(gradingCriteriaModificationDate
				+ " <- gradingCriteriaModificationDate   setGradingCriteriaModificationDate()   GradingCriteria.java");
		this.gradingCriteriaModificationDate = gradingCriteriaModificationDate;
	}
}
