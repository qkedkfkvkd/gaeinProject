package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 상담결과코드 테이블 VO 객체
public class CounselResult {
	private String		counselResultNo;					// 상담결과코드(기본키)
	private String		counselTypeNo;						// 상담구분코드(상담구분코드 테이블 참조 외래키 1:다 대응)
	private String		counselResultName;					// 상담결과명  ex) 입학상담, 학습상담, 성적상담 등
	private String		counselResultIsChanged;				// 코드 변경 유무
	private String		counselResultReasonForChange;		// 코드 변경 사유
	private Date		counselResultChangeDate;			// 코드 변경일
	private Date		counselResultRegisteredDate;		// 등록일
	
	public String getCounselResultNo() {
		return counselResultNo;
	}
	public void setCounselResultNo(String counselResultNo) {
		System.out.println(counselResultNo + " <- counselResultNo   setCounselResultNo()   CounselResult.java");
		this.counselResultNo = counselResultNo;
	}
	public String getCounselTypeNo() {
		return counselTypeNo;
	}
	public void setCounselTypeNo(String counselTypeNo) {
		System.out.println(counselTypeNo + " <- counselTypeNo   setCounselTypeNo()   CounselResult.java");
		this.counselTypeNo = counselTypeNo;
	}
	public String getCounselResultName() {
		return counselResultName;
	}
	public void setCounselResultName(String counselResultName) {
		System.out.println(counselResultName + " <- counselResultName   setCounselResultName()   CounselResult.java");
		this.counselResultName = counselResultName;
	}
	public String getCounselResultIsChanged() {
		return counselResultIsChanged;
	}
	public void setCounselResultIsChanged(String counselResultIsChanged) {
		System.out.println(counselResultIsChanged + " <- counselResultIsChanged   setCounselResultIsChanged()   CounselResult.java");
		this.counselResultIsChanged = counselResultIsChanged;
	}
	public String getCounselResultReasonForChange() {
		return counselResultReasonForChange;
	}
	public void setCounselResultReasonForChange(String counselResultReasonForChange) {
		System.out.println(counselResultReasonForChange + " <- counselResultReasonForChange   setCounselResultReasonForChange()   CounselResult.java");
		this.counselResultReasonForChange = counselResultReasonForChange;
	}
	public Date getCounselResultChangeDate() {
		return counselResultChangeDate;
	}
	public void setCounselResultChangeDate(Date counselResultChangeDate) {
		System.out.println(counselResultChangeDate + " <- counselResultChangeDate   setCounselResultChangeDate()   CounselResult.java");
		this.counselResultChangeDate = counselResultChangeDate;
	}
	public Date getCounselResultRegisteredDate() {
		return counselResultRegisteredDate;
	}
	public void setCounselResultRegisteredDate(Date counselResultRegisteredDate) {
		System.out.println(counselResultRegisteredDate + " <- counselResultRegisteredDate   setCounselResultRegisteredDate()   CounselResult.java");
		this.counselResultRegisteredDate = counselResultRegisteredDate;
	}
}
