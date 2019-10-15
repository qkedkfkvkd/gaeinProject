package com.cafe24.smart_academy.academy_manage.courseandscore.vo;

import java.util.Date;

// 시험날짜 테이블 VO 객체
public class ExaminationDay {
	private String	examinationDayNo;				// 시험날짜코드(기본키)
	private String	scheduleNo;						// 시간표코드(강좌시간표 테이블 참조 외래키 1:다 대응)
	private String	examinationDay;					// 시험일
	private String	examinationName;				// 시험명
	private String	examinationDayIsChanged;		// 코드변경유무 -> ENUM('유','무')
	private String	examinationDayReasonForChange;	// 코드변경사유
	private Date	examinationDayChangedDate;		// 코드변경일
	private Date	examinationDayRegisteredDate;	// 등록일
	
	public String getExaminationDayNo() {
		return examinationDayNo;
	}
	public void setExaminationDayNo(String examinationDayNo) {
		System.out.println(examinationDayNo
				+ " <- examinationDayNo   setExaminationDayNo()   ExaminationDay.java");
		this.examinationDayNo = examinationDayNo;
	}
	public String getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(String scheduleNo) {
		System.out.println(scheduleNo + " <- scheduleNo   setScheduleNo()   ExaminationDay.java");
		this.scheduleNo = scheduleNo;
	}
	public String getExaminationDay() {
		return examinationDay;
	}
	public void setExaminationDay(String examinationDay) {
		System.out.println(examinationDay
				+ " <- examinationDay   setExaminationDay()   ExaminationDay.java");
		this.examinationDay = examinationDay;
	}
	public String getExaminationName() {
		return examinationName;
	}
	public void setExaminationName(String examinationName) {
		System.out.println(examinationName
				+ " <- examinationName   setExaminationName()   ExaminationDay.java");
		this.examinationName = examinationName;
	}
	public String getExaminationDayIsChanged() {
		return examinationDayIsChanged;
	}
	public void setExaminationDayIsChanged(String examinationDayIsChanged) {
		System.out.println(examinationDayIsChanged
				+ " <- examinationDayIsChanged   setExaminationDayIsChanged()   ExaminationDay.java");
		this.examinationDayIsChanged = examinationDayIsChanged;
	}
	public String getExaminationDayReasonForChange() {
		return examinationDayReasonForChange;
	}
	public void setExaminationDayReasonForChange(String examinationDayReasonForChange) {
		System.out.println(examinationDayReasonForChange
				+ " <- examinationDayReasonForChange   setExaminationDayReasonForChange()   ExaminationDay.java");
		this.examinationDayReasonForChange = examinationDayReasonForChange;
	}
	public Date getExaminationDayChangedDate() {
		return examinationDayChangedDate;
	}
	public void setExaminationDayChangedDate(Date examinationDayChangedDate) {
		System.out.println(examinationDayChangedDate
				+ " <- examinationDayChangedDate   setExaminationDayChangedDate()   ExaminationDay.java");
		this.examinationDayChangedDate = examinationDayChangedDate;
	}
	public Date getExaminationDayRegisteredDate() {
		return examinationDayRegisteredDate;
	}
	public void setExaminationDayRegisteredDate(Date examinationDayRegisteredDate) {
		System.out.println(examinationDayRegisteredDate
				+ " <- examinationDayRegisteredDate   setExaminationDayRegisteredDate()   ExaminationDay.java");
		this.examinationDayRegisteredDate = examinationDayRegisteredDate;
	}
}
