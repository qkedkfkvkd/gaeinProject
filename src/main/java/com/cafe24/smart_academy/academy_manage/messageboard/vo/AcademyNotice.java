package com.cafe24.smart_academy.academy_manage.messageboard.vo;

import java.util.Date;

// 학원 공지사항 테이블 VO 객체
public class AcademyNotice {
	private String		academyNoticeNo;				// 게시판코드(기본키)
	private String		memberId;						// 회원아이디(로그인 테이블 참조 외래키 1:다 대응)
	private String		academyNoticeTitle;				// 제목
	private String		academyNoticeContent;			// 내용
	private Date		academyNoticeRegisteredDate;	// 등록일
	private String		academyNoticeModificationDate;	// 최종수정일
	
	public String getAcademyNoticeNo() {
		return academyNoticeNo;
	}
	public void setAcademyNoticeNo(String academyNoticeNo) {
		System.out.println(academyNoticeNo
				+ " <- academyNoticeNo   setAcademyNoticeNo()   AcademyNotice.java");
		this.academyNoticeNo = academyNoticeNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   AcademyNotice.java");
		this.memberId = memberId;
	}
	public String getAcademyNoticeTitle() {
		return academyNoticeTitle;
	}
	public void setAcademyNoticeTitle(String academyNoticeTitle) {
		System.out.println(academyNoticeTitle
				+ " <- academyNoticeTitle   setAcademyNoticeTitle()   AcademyNotice.java");
		this.academyNoticeTitle = academyNoticeTitle;
	}
	public String getAcademyNoticeContent() {
		return academyNoticeContent;
	}
	public void setAcademyNoticeContent(String academyNoticeContent) {
		System.out.println(academyNoticeContent
				+ " <- academyNoticeContent   setAcademyNoticeContent()   AcademyNotice.java");
		this.academyNoticeContent = academyNoticeContent;
	}
	public Date getAcademyNoticeRegisteredDate() {
		return academyNoticeRegisteredDate;
	}
	public void setAcademyNoticeRegisteredDate(Date academyNoticeRegisteredDate) {
		System.out.println(academyNoticeRegisteredDate
				+ " <- academyNoticeRegisteredDate   setAcademyNoticeRegisteredDate()   AcademyNotice.java");
		this.academyNoticeRegisteredDate = academyNoticeRegisteredDate;
	}
	public String getAcademyNoticeModificationDate() {
		return academyNoticeModificationDate;
	}
	public void setAcademyNoticeModificationDate(String academyNoticeModificationDate) {
		System.out.println(academyNoticeModificationDate
				+ " <- academyNoticeModificationDate   setAcademyNoticeModificationDate()   AcademyNotice.java");
		this.academyNoticeModificationDate = academyNoticeModificationDate;
	}
}
