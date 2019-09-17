package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 상담구분코드 테이블 VO 객체
public class CounselType {
	private String		counselTypeNo;					// 상담구분코드(기본키)
	private String		counselTypeName;				// 상담구분명  ex) 전화상담, 방문상담
	private String		counselTypeIsChanged;			// 코드 변경 유무
	private String		counselTypeReasonForChange;		// 변경 사유
	private Date		counselTypeChangedDate;			// 변경 일자
	private Date		counselTypeRegisteredDate;		// 등록일
	
	public String getCounselTypeNo() {
		return counselTypeNo;
	}
	public void setCounselTypeNo(String counselTypeNo) {
		System.out.println(counselTypeNo + " <- counselTypeNo   setCounselTypeNo()   CounselType.java");
		this.counselTypeNo = counselTypeNo;
	}
	public String getCounselTypeName() {
		return counselTypeName;
	}
	public void setCounselTypeName(String counselTypeName) {
		System.out.println(counselTypeName + " <- counselTypeName   setCounselTypeName()   CounselType.java");
		this.counselTypeName = counselTypeName;
	}
	public String getCounselTypeIsChanged() {
		return counselTypeIsChanged;
	}
	public void setCounselTypeIsChanged(String counselTypeIsChanged) {
		System.out.println(counselTypeIsChanged + " <- counselTypeIsChanged   setCounselTypeIsChanged()   CounselType.java");
		this.counselTypeIsChanged = counselTypeIsChanged;
	}
	public String getCounselTypeReasonForChange() {
		return counselTypeReasonForChange;
	}
	public void setCounselTypeReasonForChange(String counselTypeReasonForChange) {
		System.out.println(counselTypeReasonForChange + " <- counselTypeReasonForChange   setCounselTypeReasonForChange()   CounselType.java");
		this.counselTypeReasonForChange = counselTypeReasonForChange;
	}
	public Date getCounselTypeChangedDate() {
		return counselTypeChangedDate;
	}
	public void setCounselTypeChangedDate(Date counselTypeChangedDate) {
		System.out.println(counselTypeChangedDate + " <- counselTypeChangedDate   setCounselTypeChangedDate()   CounselType.java");
		this.counselTypeChangedDate = counselTypeChangedDate;
	}
	public Date getCounselTypeRegisteredDate() {
		return counselTypeRegisteredDate;
	}
	public void setCounselTypeRegisteredDate(Date counselTypeRegisteredDate) {
		System.out.println(counselTypeRegisteredDate + " <- counselTypeRegisteredDate   setCounselTypeRegisteredDate()   CounselType.java");
		this.counselTypeRegisteredDate = counselTypeRegisteredDate;
	}
}
