package com.cafe24.smart_academy.academy_manage.member.vo;

// 디비에서 상담결과코드를 얻기위해 값을 저장하는 임시 객체
public class GetCounselResultNo {
	private String		counselTypeNo;
	// 상담구분코드 테이블의 기본키인 상담구분코드
	
	private String		counselResultName;
	// 상담결과코드 테이블의 상담결과명  ex) 입학상담, 학습상담, 성적상담 등
	
	public String getCounselTypeNo() {
		return counselTypeNo;
	}
	public void setCounselTypeNo(String counselTypeNo) {
		System.out.println(counselTypeNo + " <- counselTypeNo   setCounselTypeNo()   GetCounselResultNo.java");
		this.counselTypeNo = counselTypeNo;
	}
	public String getCounselResultName() {
		return counselResultName;
	}
	public void setCounselResultName(String counselResultName) {
		System.out.println(counselResultName
				+ " <- counselResultName   setCounselResultName()   CounselResult.java");
		this.counselResultName = counselResultName;
	}
}
