package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 학생 상담내용 테이블 VO 객체
public class Counsel {
	private String		counselHistoryNo;			// 상담내역코드(기본키)
	private String		memberId;					// 학생 아이디(로그인테이블 참조 외래키 1:다 대응)
	private String		counselResultNo;			// 상담결과코드(상담결과코드 테이블 참조 외래키 1:다 대응)
	private String		counselContent;				// 상담 내용
	private Date		counselDate;				// 상담일자
	private Date		counsel_registered_date;	// 등록일
	
	public String getCounselHistoryNo() {
		return counselHistoryNo;
	}
	public void setCounselHistoryNo(String counselHistoryNo) {
		System.out.println(counselHistoryNo + " <- counselHistoryNo   setCounselHistoryNo()   Counsel.java");
		this.counselHistoryNo = counselHistoryNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   Counsel.java");
		this.memberId = memberId;
	}
	public String getCounselResultNo() {
		return counselResultNo;
	}
	public void setCounselResultNo(String counselResultNo) {
		System.out.println(counselResultNo + " <- counselResultNo   setCounselResultNo()   Counsel.java");
		this.counselResultNo = counselResultNo;
	}
	public String getCounselContent() {
		return counselContent;
	}
	public void setCounselContent(String counselContent) {
		System.out.println(counselContent + " <- counselContent   setCounselContent()   Counsel.java");
		this.counselContent = counselContent;
	}
	public Date getCounselDate() {
		return counselDate;
	}
	public void setCounselDate(Date counselDate) {
		System.out.println(counselDate + " <- counselDate   setCounselDate()   Counsel.java");
		this.counselDate = counselDate;
	}
	public Date getCounsel_registered_date() {
		return counsel_registered_date;
	}
	public void setCounsel_registered_date(Date counsel_registered_date) {
		System.out.println(counsel_registered_date + " <- counsel_registered_date   setCounsel_registered_date()   Counsel.java");
		this.counsel_registered_date = counsel_registered_date;
	}
}
