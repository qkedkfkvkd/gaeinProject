package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 학생의 결제정보 테이블 VO객체
public class PaymentInfo {
	private String		memberId;					// 학생 아이디(기본키, 로그인테이블 외래키 1:1 대응)
	private String		paymentDay;					// 납부일자
	private String		paymentWay;					// 결제수단방법  ex)카드, 현금결제, 무통장입금 등
	private String		paymentContent;				// 결제수단내용  ex) 일시불, 부분입금 등
	private int			paymentScheduleMoney;		// 납부예정금액(부분 미납시에만)
	private int			actualityPaymentMoney;		// 실납부금액
	private String		startCourseDay;				// 시작 수업일
	private String		endCourseDay;				// 마지막 수업일
	private String		paymentReference;			// 결제 참고사항
	private Date		paymentRegisteredDate;		// 결제정보 등록일
	
	// 모든 강좌를 동일한 금액으로 통일시키든지,
	// 강좌별 금액 테이블을 하나 더 생성해서 만들든지 해야한다.
	// 테이블 생성시 강좌코드(기본키이자 강좌테이블 강좌코드 참조 외래키 1:1 대응),
	// 금액, 등록일로 속성 3개만 넣는다.
	// 일단은 모든 강좌를 동일한 금액으로 통일시켜서 진행하는 방향으로 결정한다.
	// 한 강좌당 한달에 얼마 내고 다니는 방식으로 한다.
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   PaymentInfo.java");
		this.memberId = memberId;
	}
	public String getPaymentDay() {
		return paymentDay;
	}
	public void setPaymentDay(String paymentDay) {
		System.out.println(paymentDay + " <- paymentDay   setPaymentDay()   PaymentInfo.java");
		this.paymentDay = paymentDay;
	}
	public String getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(String paymentWay) {
		System.out.println(paymentWay + " <- paymentWay   setPaymentWay()   PaymentInfo.java");
		this.paymentWay = paymentWay;
	}
	public String getPaymentContent() {
		return paymentContent;
	}
	public void setPaymentContent(String paymentContent) {
		System.out.println(paymentContent
				+ " <- paymentContent   setPaymentContent()   PaymentInfo.java");
		this.paymentContent = paymentContent;
	}
	public int getPaymentScheduleMoney() {
		return paymentScheduleMoney;
	}
	public void setPaymentScheduleMoney(int paymentScheduleMoney) {
		System.out.println(paymentScheduleMoney
				+ " <- paymentScheduleMoney   setPaymentScheduleMoney()   PaymentInfo.java");
		this.paymentScheduleMoney = paymentScheduleMoney;
	}
	public int getActualityPaymentMoney() {
		return actualityPaymentMoney;
	}
	public void setActualityPaymentMoney(int actualityPaymentMoney) {
		System.out.println(actualityPaymentMoney
				+ " <- actualityPaymentMoney   setActualityPaymentMoney()   PaymentInfo.java");
		this.actualityPaymentMoney = actualityPaymentMoney;
	}
	public String getStartCourseDay() {
		return startCourseDay;
	}
	public void setStartCourseDay(String startCourseDay) {
		System.out.println(startCourseDay
				+ " <- startCourseDay   setStartCourseDay()   PaymentInfo.java");
		this.startCourseDay = startCourseDay;
	}
	public String getEndCourseDay() {
		return endCourseDay;
	}
	public void setEndCourseDay(String endCourseDay) {
		System.out.println(endCourseDay + " <- endCourseDay   setEndCourseDay()   PaymentInfo.java");
		this.endCourseDay = endCourseDay;
	}
	public String getPaymentReference() {
		return paymentReference;
	}
	public void setPaymentReference(String paymentReference) {
		System.out.println(paymentReference
				+ " <- paymentReference   setPaymentReference()   PaymentInfo.java");
		this.paymentReference = paymentReference;
	}
	public Date getPaymentRegisteredDate() {
		return paymentRegisteredDate;
	}
	public void setPaymentRegisteredDate(Date paymentRegisteredDate) {
		System.out.println(paymentRegisteredDate
				+ " <- paymentRegisteredDate   setPaymentRegisteredDate()   PaymentInfo.java");
		this.paymentRegisteredDate = paymentRegisteredDate;
	}
}
