package com.cafe24.smart_academy.academy_manage.course.manage.vo;

import java.util.Date;

// 교재 테이블 VO 객체
public class TextBook {
	private String	textbookNo;					// 교재코드(기본키)
	private String	courseNo;					// 강좌코드(강좌 테이블 참조 외래키 1:다 대응)
	// 강좌 하나에 교재 한권이라고 할 경우 교재코드가 필요없다.
	// 강좌코드를 외래키 겸 기본키로 하면 될 것이다.
	
	private String	textbookName;				// 교재이름
	private String	textbookPublisher;			// 출판사
	private int		textbookPrice;				// 가격
	
	private String	textbookIsChanged;			// 코드변경유무
	private String	textbookReasonForChange;	// 코드변경사유
	private Date	textbookChangedDate;		// 코드변경일자
	// 만약 강좌코드를 외래키 겸 기본키로 설정한다면
	// 위의 코드변경 관련 컬럼은 필요가 없어질 것이다.
	
	private Date	textbookRegisteredDate;		// 등록일

	public String getTextbookNo() {
		return textbookNo;
	}
	public void setTextbookNo(String textbookNo) {
		System.out.println(textbookNo + " <- textbookNo   setTextbookNo()   TextBook.java");
		this.textbookNo = textbookNo;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		System.out.println(courseNo + " <- courseNo   setCourseNo()   TextBook.java");
		this.courseNo = courseNo;
	}
	public String getTextbookName() {
		return textbookName;
	}
	public void setTextbookName(String textbookName) {
		System.out.println(textbookName + " <- textbookName   setTextbookName()   TextBook.java");
		this.textbookName = textbookName;
	}
	public String getTextbookPublisher() {
		return textbookPublisher;
	}
	public void setTextbookPublisher(String textbookPublisher) {
		System.out.println(textbookPublisher
				+ " <- textbookPublisher   setTextbookPublisher()   TextBook.java");
		this.textbookPublisher = textbookPublisher;
	}
	public int getTextbookPrice() {
		return textbookPrice;
	}
	public void setTextbookPrice(int textbookPrice) {
		System.out.println(textbookPrice + " <- textbookPrice   setTextbookPrice()   TextBook.java");
		this.textbookPrice = textbookPrice;
	}
	public String getTextbookIsChanged() {
		return textbookIsChanged;
	}
	public void setTextbookIsChanged(String textbookIsChanged) {
		System.out.println(textbookIsChanged
				+ " <- textbookIsChanged   setTextbookIsChanged()   TextBook.java");
		this.textbookIsChanged = textbookIsChanged;
	}
	public String getTextbookReasonForChange() {
		return textbookReasonForChange;
	}
	public void setTextbookReasonForChange(String textbookReasonForChange) {
		System.out.println(textbookReasonForChange
				+ " <- textbookReasonForChange   setTextbookReasonForChange()   TextBook.java");
		this.textbookReasonForChange = textbookReasonForChange;
	}
	public Date getTextbookChangedDate() {
		return textbookChangedDate;
	}
	public void setTextbookChangedDate(Date textbookChangedDate) {
		System.out.println(textbookChangedDate
				+ " <- textbookChangedDate   setTextbookChangedDate()   TextBook.java");
		this.textbookChangedDate = textbookChangedDate;
	}
	public Date getTextbookRegisteredDate() {
		return textbookRegisteredDate;
	}
	public void setTextbookRegisteredDate(Date textbookRegisteredDate) {
		System.out.println(textbookRegisteredDate
				+ " <- textbookRegisteredDate   setTextbookRegisteredDate()   TextBook.java");
		this.textbookRegisteredDate = textbookRegisteredDate;
	}
}
