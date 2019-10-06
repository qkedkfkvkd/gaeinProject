package com.cafe24.smart_academy.academy_manage.member.vo;

// 회원 검색 변수 저장용 VO 객체
public class MemberSearchVO {
	private String	memberLevel;				// 권한 검색
	private String	memberName;					// 이름 검색
	private String startJoinDate;				// 가입기간 검색 (시작 날짜)
	private String endJoinDate;					// 가입기간 검색 (마지막 날짜)
	
	
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		System.out.println(memberLevel + " <- memberLevel   setMemberLevel()   MemberSearchVO.java");
		this.memberLevel = memberLevel;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		System.out.println(memberName + " <- memberName   setMemberName()   MemberSearchVO.java");
		this.memberName = memberName;
	}
	public String getStartJoinDate() {
		return startJoinDate;
	}
	public void setStartJoinDate(String startJoinDate) {
		System.out.println(startJoinDate + " <- startJoinDate   setStartJoinDate()   MemberSearchVO.java");
		this.startJoinDate = startJoinDate;
	}
	public String getEndJoinDate() {
		return endJoinDate;
	}
	public void setEndJoinDate(String endJoinDate) {
		System.out.println(endJoinDate + " <- endJoinDate   setEndJoinDate()   MemberSearchVO.java");
		this.endJoinDate = endJoinDate;
	}
}
