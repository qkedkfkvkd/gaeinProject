package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 상담예약 테이블 VO 객체
public class CounselAppointment {
	private String		counselHistoryNo;					// 상담내역코드(기본키)
	private String		counselResultNo;					// 상담결과코드(상담결과코드 테이블 참조 외래키 1:다 대응)
	private String		memberId;							// 학생 아이디(로그인테이블 참조 외래키 1:다 대응)
	private String		counselAppointmentDate;				// 상담예약일
	private String		counselWhether;						// 상담여부 -> ENUM('유','무')
	private String		counselAppointmentWhether;			// 예약여부 -> ENUM('유','무')
	private Date		counselAppointmentRegisteredDate;	// 상담예약 등록일
	
	
	////////////////////////////////////////////////////
	// 검색 키워드 변수 선언
	private String		counselTypeNo;		// 상담구분테이블의 상담구분코드(기본키)
	
	
	public String getCounselHistoryNo() {
		return counselHistoryNo;
	}
	public void setCounselHistoryNo(String counselHistoryNo) {
		System.out.println(counselHistoryNo
				+ " <- counselHistoryNo   setCounselHistoryNo()   CounselAppointment.java");
		this.counselHistoryNo = counselHistoryNo;
	}
	public String getCounselResultNo() {
		return counselResultNo;
	}
	public void setCounselResultNo(String counselResultNo) {
		System.out.println(counselResultNo
				+ " <- counselResultNo   setCounselResultNo()   CounselAppointment.java");
		this.counselResultNo = counselResultNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   CounselAppointment.java");
		this.memberId = memberId;
	}
	public String getCounselAppointmentDate() {
		return counselAppointmentDate;
	}
	public void setCounselAppointmentDate(String counselAppointmentDate) {
		System.out.println(counselAppointmentDate
				+ " <- counselAppointmentDate   setCounselAppointmentDate()   CounselAppointment.java");
		this.counselAppointmentDate = counselAppointmentDate;
	}
	public String getCounselWhether() {
		return counselWhether;
	}
	public void setCounselWhether(String counselWhether) {
		System.out.println(counselWhether + " <- counselWhether   setCounselWhether()   CounselAppointment.java");
		this.counselWhether = counselWhether;
	}
	public String getCounselAppointmentWhether() {
		return counselAppointmentWhether;
	}
	public void setCounselAppointmentWhether(String counselAppointmentWhether) {
		System.out.println(counselAppointmentWhether
				+ " <- counselAppointmentWhether   setCounselAppointmentWhether()   CounselAppointment.java");
		this.counselAppointmentWhether = counselAppointmentWhether;
	}
	public Date getCounselAppointmentRegisteredDate() {
		return counselAppointmentRegisteredDate;
	}
	public void setCounselAppointmentRegisteredDate(Date counselAppointmentRegisteredDate) {
		System.out.println(counselAppointmentRegisteredDate +
				" <- counselAppointmentRegisteredDate   setCounselAppointmentRegisteredDate()   CounselAppointment.java");
		this.counselAppointmentRegisteredDate = counselAppointmentRegisteredDate;
	}
	public String getCounselTypeNo() {
		return counselTypeNo;
	}
	public void setCounselTypeNo(String counselTypeNo) {
		System.out.println(counselTypeNo + " <- counselTypeNo   setCounselTypeNo()   CounselAppointment.java");
		this.counselTypeNo = counselTypeNo;
	}
	
}
