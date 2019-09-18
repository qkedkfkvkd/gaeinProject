package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 학생 상담내용 테이블 VO 객체
public class Counsel {
	private String		counselHistoryNo;			// 상담내역코드(기본키) - 상담예약테이블 참조 외래키 1:1 대응
	private String		counselContent;				// 상담 내용
	private Date		counselRegisteredDate;		// 등록일
	
	public String getCounselHistoryNo() {
		return counselHistoryNo;
	}
	public void setCounselHistoryNo(String counselHistoryNo) {
		System.out.println(counselHistoryNo
				+ " <- counselHistoryNo   setCounselHistoryNo()   Counsel.java");
		this.counselHistoryNo = counselHistoryNo;
	}
	public String getCounselContent() {
		return counselContent;
	}
	public void setCounselContent(String counselContent) {
		System.out.println(counselContent + " <- counselContent   setCounselContent()   Counsel.java");
		this.counselContent = counselContent;
	}
	public Date getCounselRegisteredDate() {
		return counselRegisteredDate;
	}
	public void setCounselRegisteredDate(Date counselRegisteredDate) {
		System.out.println(counselRegisteredDate
				+ " <- counselRegisteredDate   setCounselRegisteredDate()   Counsel.java");
		this.counselRegisteredDate = counselRegisteredDate;
	}
}
