package com.cafe24.smart_academy.academy_manage.member.vo;

import java.util.Date;

// 학생의 결제정보 테이블 VO객체
public class PaymentInfo {
	private String		memberId;					// 학생 아이디(기본키, 로그인테이블 외래키 1:1 대응)
	private Date		paymentDay;					// 납부일자
	private String		paymentWay;					// 결제수단방법  ex)카드, 현금결제, 무통장입금 등
	private String		paymentContent;				// 결제수단내용  ex) 일시불, 부분입금 등
	private int			paymentScheduleMoney;		// 납부예정금액(부분 미납시에만)
	private int			actualityPaymentMoney;		// 실납부금액
	private Date		startCourseDay;				// 시작 수업일
	private Date		endCourseDay;				// 마지막 수업일
	private String		paymentReference;			// 결제 참고사항
	private Date		payment_registered_date;	// 결제정보 등록일
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		System.out.println(memberId + " <- memberId   setMemberId()   PaymentInfo.java");
		this.memberId = memberId;
	}
	public Date getPaymentDay() {
		return paymentDay;
	}
	public void setPaymentDay(Date paymentDay) {
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
		System.out.println(paymentContent + " <- paymentContent   setPaymentContent()   PaymentInfo.java");
		this.paymentContent = paymentContent;
	}
	public int getPaymentScheduleMoney() {
		return paymentScheduleMoney;
	}
	public void setPaymentScheduleMoney(int paymentScheduleMoney) {
		System.out.println(paymentScheduleMoney + " <- paymentScheduleMoney   setPaymentScheduleMoney()   PaymentInfo.java");
		this.paymentScheduleMoney = paymentScheduleMoney;
	}
	public int getActualityPaymentMoney() {
		return actualityPaymentMoney;
	}
	public void setActualityPaymentMoney(int actualityPaymentMoney) {
		System.out.println(actualityPaymentMoney + " <- actualityPaymentMoney   setActualityPaymentMoney()   PaymentInfo.java");
		this.actualityPaymentMoney = actualityPaymentMoney;
	}
	public Date getStartCourseDay() {
		return startCourseDay;
	}
	public void setStartCourseDay(Date startCourseDay) {
		System.out.println(startCourseDay + " <- startCourseDay   setStartCourseDay()   PaymentInfo.java");
		this.startCourseDay = startCourseDay;
	}
	public Date getEndCourseDay() {
		return endCourseDay;
	}
	public void setEndCourseDay(Date endCourseDay) {
		System.out.println(endCourseDay + " <- endCourseDay   setEndCourseDay()   PaymentInfo.java");
		this.endCourseDay = endCourseDay;
	}
	public String getPaymentReference() {
		return paymentReference;
	}
	public void setPaymentReference(String paymentReference) {
		System.out.println(paymentReference + " <- paymentReference   setPaymentReference()   PaymentInfo.java");
		this.paymentReference = paymentReference;
	}
	public Date getPayment_registered_date() {
		return payment_registered_date;
	}
	public void setPayment_registered_date(Date payment_registered_date) {
		System.out.println(payment_registered_date + " <- payment_registered_date   setPayment_registered_date()   PaymentInfo.java");
		this.payment_registered_date = payment_registered_date;
	}
}
