package com.cafe24.smart_academy.academy_manage.course.manage.vo;

import java.util.Date;

// 강의실 테이블 VO 객체
public class AcademyRoom {
	private String	roomNo;					// 강의실코드(기본키)
	private int		roomNumber;				// 호실 번호
	private int		roomFloor;				// 층
	private String	roomUsage;				// 실용도 (국어 수업용 등)
	private String	roomIsChanged;			// 코드변경유무
	private String	roomReasonForChange;	// 코드변경사유
	private Date	roomChangedDate;		// 코드변경일자
	private Date	roomRegisteredDate;		// 등록일
	
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		System.out.println(roomNo + " <- roomNo   setRoomNo()   AcademyRoom.java");
		this.roomNo = roomNo;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		System.out.println(roomNumber + " <- roomNumber   setRoomNumber()   AcademyRoom.java");
		this.roomNumber = roomNumber;
	}
	public int getRoomFloor() {
		return roomFloor;
	}
	public void setRoomFloor(int roomFloor) {
		System.out.println(roomFloor + " <- roomFloor   setRoomFloor()   AcademyRoom.java");
		this.roomFloor = roomFloor;
	}
	public String getRoomUsage() {
		return roomUsage;
	}
	public void setRoomUsage(String roomUsage) {
		System.out.println(roomUsage + " <- roomUsage   setRoomUsage()   AcademyRoom.java");
		this.roomUsage = roomUsage;
	}
	public String getRoomIsChanged() {
		return roomIsChanged;
	}
	public void setRoomIsChanged(String roomIsChanged) {
		System.out.println(roomIsChanged + " <- roomIsChanged   setRoomIsChanged()   AcademyRoom.java");
		this.roomIsChanged = roomIsChanged;
	}
	public String getRoomReasonForChange() {
		return roomReasonForChange;
	}
	public void setRoomReasonForChange(String roomReasonForChange) {
		System.out.println(roomReasonForChange
				+ " <- roomReasonForChange   setRoomReasonForChange()   AcademyRoom.java");
		this.roomReasonForChange = roomReasonForChange;
	}
	public Date getRoomChangedDate() {
		return roomChangedDate;
	}
	public void setRoomChangedDate(Date roomChangedDate) {
		System.out.println(roomChangedDate
				+ " <- roomChangedDate   setRoomChangedDate()   AcademyRoom.java");
		this.roomChangedDate = roomChangedDate;
	}
	public Date getRoomRegisteredDate() {
		return roomRegisteredDate;
	}
	public void setRoomRegisteredDate(Date roomRegisteredDate) {
		System.out.println(roomRegisteredDate
				+ " <- roomRegisteredDate   setRoomRegisteredDate()   AcademyRoom.java");
		this.roomRegisteredDate = roomRegisteredDate;
	}
}
