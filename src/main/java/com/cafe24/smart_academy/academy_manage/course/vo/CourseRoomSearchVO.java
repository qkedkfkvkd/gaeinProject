package com.cafe24.smart_academy.academy_manage.course.vo;

// 강좌강의실배정 리스트 검색 객체
public class CourseRoomSearchVO {
	private String	subjectNo;		// 과목테이블의 과목코드(기본키)
	private String	courseNo;		// 강좌테이블의 강좌코드(기본키)
	private String	roomNo;					// 강의실코드(기본키)
	
	
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		System.out.println(subjectNo + " <- subjectNo   setSubjectNo()   CourseSearchVO.java");
		this.subjectNo = subjectNo;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		System.out.println(courseNo + " <- courseNo   setCourseNo()   CourseSearchVO.java");
		this.courseNo = courseNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		System.out.println(roomNo + " <- roomNo   setRoomNo()   CourseSearchVO.java");
		this.roomNo = roomNo;
	}
}
