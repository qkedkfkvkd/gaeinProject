package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

public class CounselAppointment {
	private String		counselHistoryNo;					// 상담내역코드(기본키)
	private String		counselResultNo;					// 상담결과코드(상담결과코드 테이블 참조 외래키 1:다 대응)
	private String		memberId;							// 학생 아이디(로그인테이블 참조 외래키 1:다 대응)
	private String		counselAppointmentDate;				// 상담예약일
	private Date		counselAppointmentRegisteredDate;	// 상담예약 등록일
	
	public String getCounselHistoryNo() {
		return counselHistoryNo;
	}
	public void setCounselHistoryNo(String counselHistoryNo) {
		System.out.println(counselHistoryNo
				+ " <- counselHistoryNo   setCounselHistoryNo()   Counsel.java");
		this.counselHistoryNo = counselHistoryNo;
	}
	public String getCounselResultNo() {
		return counselResultNo;
	}
	public void setCounselResultNo(String counselResultNo) {
		System.out.println(counselResultNo
				+ " <- counselResultNo   setCounselResultNo()   Counsel.java");
		this.counselResultNo = counselResultNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   Counsel.java");
		this.memberId = memberId;
	}
	public String getCounselAppointmentDate() {
		return counselAppointmentDate;
	}
	public void setCounselAppointmentDate(String counselAppointmentDate) {
		System.out.println(counselAppointmentDate
				+ " <- counselAppointmentDate   setCounselAppointmentDate()   Counsel.java");
		this.counselAppointmentDate = counselAppointmentDate;
	}
	public Date getCounselAppointmentRegisteredDate() {
		return counselAppointmentRegisteredDate;
	}
	public void setCounselAppointmentRegisteredDate(Date counselAppointmentRegisteredDate) {
		System.out.println(counselAppointmentRegisteredDate +
				" <- counselAppointmentRegisteredDate   setCounselAppointmentRegisteredDate()   Counsel.java");
		this.counselAppointmentRegisteredDate = counselAppointmentRegisteredDate;
	}
}
