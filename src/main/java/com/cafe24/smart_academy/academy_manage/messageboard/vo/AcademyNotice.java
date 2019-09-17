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
	
	
}
