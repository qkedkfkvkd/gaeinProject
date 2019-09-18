package com.cafe24.smart_academy.academy_manage.courseandscore.vo;

// 성적입력 테이블 VO 객체
public class ScoreInput {
	private String	scoreInputNo;				// 성적입력코드(기본키)
	private String	examinationDayNo;			// 시험날짜코드(시험날짜 테이블 참조 외래키 1:다 대응)
	private String	courseEnrolleeNo;			// 수강신청코드(수강신청 테이블 참조 외래키 1:다 대응)
	private String	gradingCriteriaRating;		// 등급(성적평가기준 테이블 참조 외래키 1:다 대응)
	private String	scoreInputTestScore;		// 평가점수
	private String	scoreInputRegisteredDate;	// 등록일
	
	public String getScoreInputNo() {
		return scoreInputNo;
	}
	public void setScoreInputNo(String scoreInputNo) {
		System.out.println(scoreInputNo + " <- scoreInputNo   setScoreInputNo()   ScoreInput.java");
		this.scoreInputNo = scoreInputNo;
	}
	public String getExaminationDayNo() {
		return examinationDayNo;
	}
	public void setExaminationDayNo(String examinationDayNo) {
		System.out.println(examinationDayNo
				+ " <- examinationDayNo   setExaminationDayNo()   ScoreInput.java");
		this.examinationDayNo = examinationDayNo;
	}
	public String getCourseEnrolleeNo() {
		return courseEnrolleeNo;
	}
	public void setCourseEnrolleeNo(String courseEnrolleeNo) {
		System.out.println(courseEnrolleeNo
				+ " <- courseEnrolleeNo   setCourseEnrolleeNo()   ScoreInput.java");
		this.courseEnrolleeNo = courseEnrolleeNo;
	}
	public String getGradingCriteriaRating() {
		return gradingCriteriaRating;
	}
	public void setGradingCriteriaRating(String gradingCriteriaRating) {
		System.out.println(gradingCriteriaRating
				+ " <- gradingCriteriaRating   setGradingCriteriaRating()   ScoreInput.java");
		this.gradingCriteriaRating = gradingCriteriaRating;
	}
	public String getScoreInputTestScore() {
		return scoreInputTestScore;
	}
	public void setScoreInputTestScore(String scoreInputTestScore) {
		System.out.println(scoreInputTestScore
				+ " <- scoreInputTestScore   setScoreInputTestScore()   ScoreInput.java");
		this.scoreInputTestScore = scoreInputTestScore;
	}
	public String getScoreInputRegisteredDate() {
		return scoreInputRegisteredDate;
	}
	public void setScoreInputRegisteredDate(String scoreInputRegisteredDate) {
		System.out.println(scoreInputRegisteredDate
				+ " <- scoreInputRegisteredDate   setScoreInputRegisteredDate()   ScoreInput.java");
		this.scoreInputRegisteredDate = scoreInputRegisteredDate;
	}
}
