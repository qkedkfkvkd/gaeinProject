package com.cafe24.smart_academy.academy_manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.smart_academy.academy_manage.member.service.StudentInfoService;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.Parent;
import com.cafe24.smart_academy.academy_manage.member.vo.PaymentInfo;

@Controller
public class StudentInfoController {
// 학생정보관리 컨트롤러
	
	@Autowired
	private StudentInfoService studentInfoService;
	
	
	// 관리자의 학생 등록폼 이동하기
	@GetMapping("/addStudent")
	public String addMember() {
		//System.out.println("멤버컨트롤러 학생등록폼 이동메소드 들어왔다");
		return "/view/academyRegister/studentInfo/addStudentInfo";
	}
	
	
	// 관리자의 회원 등록폼 이동하기
//	@GetMapping("/addMember")
//	public String addMember() {
//		return "/view/academyRegister/memberInfo/memberInfo";
//	}
	
	
	// 관리자 전용 학생 등록 폼에서 학부모 휴대폰 번호 중복확인 버튼을 눌렀을 때
	@PostMapping("/parentPhoneOverlapChk")
	@ResponseBody
	public Map<Object, Object> parentPhoneOverlapChk(@RequestBody String inputParentPhone) {
		System.out.println(inputParentPhone + "<- inputParentPhone   parentPhoneOverlapChk()   MemberController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String parentPhoneResult = studentInfoService.parentByPhone(inputParentPhone);
		// 서비스의 학부모 휴대폰 중복확인 메소드 호출
		
		if(parentPhoneResult == null) {
			map.put("result", 1);
			// 학부모 테이블에 해당 휴대폰 값이 존재하지 않음 : 사용 가능한 휴대폰번호
			
		} else {
			map.put("result", 0);
			// 학부모 테이블에 해당 휴대폰 값이 존재함 : 사용 중인 휴대폰 번호
		}
		
		return map;
	}
	
	
	// 관리자 전용 학생 추가 처리 메소드
	@PostMapping("/addStudent")
	public String addStudent(Model model, MemberLogin loginInfo, Member memberInfo, Parent parent) {
		String message = studentInfoService.addStudent(loginInfo, memberInfo, parent);
		
		String path = "/view/academyRegister/studentInfo/addStudentInfo";
		// 입력 실패했을 경우 이동될 페이지로 미리 초기화시킨다.
		
		
		if(message == null) { // 널값이면 입력 성공했다는 뜻이다.
			path = "redirect:/studentList";
			// 입력 성공 시 학생 목록 페이지로 이동한다.
			
		} else if(message.equals("idUsed")) { // 아이디 중복 메세지가 넘어올 경우
			model.addAttribute("idOverlap", "이미 사용 중인 아이디입니다.");
			
		} else if(message.equals("emailUsed")) {  // 이메일 중복 메세지가 넘어올 경우
			model.addAttribute("emailOverlap", "이미 사용 중인 이메일입니다.");
			
		} else { // 학부모 중복 메세지가 넘어올 경우
			model.addAttribute("parentPhoneOverlap", "이미 사용 중인 휴대폰번호입니다.");
		}
		
		return path;
	}
	
	
	// 관리자 전용 학생 목록 페이지 이동
	@GetMapping("/studentList")
	public String listStudentInfo(Model model) {
		List<Map<String, Object>> studentList = studentInfoService.listStudentInfo();
		// 디비에서 권한이 학생인 사람들만 목록을 가져온다. (로그인 테이블 - 회원 신상정보 테이블 아이디로 조인)
		
		model.addAttribute("studentList", studentList);
		
		return "/view/academyRegister/studentInfo/listStudentInfo";
	}
	
	
	// 관리자 전용 학생관리 페이지 이동
//	@GetMapping("/studentManage")
//	public String studentManage() {
//		return "/view/academyRegister/memberInfo/student/studentManage";
//	}
	
	
	// 관리자 전용 특정학생 결제정보 폼으로 이동
	@GetMapping("/viewPayment")
	public String viewPaymentInfo(@RequestParam(value = "memberId") String memberId, Model model) {
		System.out.println(memberId + " <- memberId   addPayment()   MemberController.java");
		
		PaymentInfo paymentInfo = studentInfoService.viewPaymentInfo(memberId);
		// 학생의 아이디를 가지고 결제정보 테이블에서 객체를 얻어온다.
		
		String path = "/view/academyRegister/studentInfo/addPaymentInfo";
		// 결제정보가 없을 경우 결제정보를 추가할 수 있도록 경로를 설정해준다.
		
		if(paymentInfo != null) { // 결제정보가 있다면
			path = "/view/academyRegister/studentInfo/detailPaymentInfo";
			// 결제정보 테이블에 있는 내용을 볼 수 있는 페이지로 이동
			
			model.addAttribute("paymentInfo", paymentInfo);
			// 결제정보 객체를 모델에 넣어준다.
		}
		
		model.addAttribute("memberId", memberId);
		
		return path;
	}
	
	
	// 관리자 전용 특정학생 결제정보 입력처리
	@PostMapping("/addPaymentInfo")
	public String addPaymentInfo(Model model, PaymentInfo paymentInfo) {
		String message = studentInfoService.addPaymentInfo(paymentInfo);
		
		String path = "/view/academyRegister/studentInfo/detailPaymentInfo";
		// 입력 성공시 결제정보 상세 페이지로 이동한다.
		
		if(message != null) { // 메세지가 널이 아니다. 메세지에 값이 존재한다면 입력 실패이다.
			path = "/view/academyRegister/studentInfo/addPaymentInfo";
			// 결제정보를 다시 입력해야한다.
			
			model.addAttribute("insertFail", "입력실패! 다시 입력해주세요.");
		}
		
		return path;
	}
	
	
	// 관리자 전용 예약 현황 리스트 이동
	@GetMapping("/listCurrentReservationState")
	public String listCurrentReservationState() {
		return "/view/academyRegister/studentInfo/listCurrentReservationState";
	}
	
	
	// 관리자가 학생 목록에서 특정 학생의 상담 관리 페이지로 이동
	@GetMapping("/counselManage")
	public String oneStudentCounselManage(@RequestParam(value = "memberId") String memberId, Model model) {
		model.addAttribute("studentInfo", studentInfoService.studentInfoIdNameBirthById(memberId));
		// 서비스에서 아이디로 해당 학생의 아이디, 이름과 생년월일만 가져와서 바로 모델에 넣어준다.
		
		//model.addAttribute("counselTypeList", memberService.counselTypeList());
		// 검색할때 사용할 상담구분테이블에 있는 모든 객체 가져오기
		
		//model.addAttribute("counselResultList", memberService.counselResultList());
		// 검색할때 사용할 상담결과테이블에 있는 모든 객체 가져오기.
		
		List<Map<String, Object>> counselHistoryList = 
				studentInfoService.oneStudentCounselHistoryList(memberId);
		// 화면에 보여줄 해당 학생 상담 내역 리스트
		
		System.out.println(counselHistoryList
					+ " <- counselHistoryList   oneStudentCounselManage()   MemberController.java");
		
		
		System.out.println("상담내역리스트 크기 : " + counselHistoryList.size());
		
		
		if(counselHistoryList.size() == 0) { // 해당 학생의 상담 내역이 존재하지 않는다면
			counselHistoryList = null; // 객체참조변수에 할당된 주소값을 날려버린다.
		}
		
		
		System.out.println(counselHistoryList
				+ " <- counselHistoryList   oneStudentCounselManage()   MemberController.java");
		
		
		model.addAttribute("counselHistoryList", counselHistoryList);
		// 화면에 보여줄 해당 학생 상담내역 리스트
		
		
		List<Map<String, Object>> counselAppointmentList = 
				studentInfoService.oneStudentCounselAppointmentList(memberId);
		// 화면에 보여줄 해당 학생 상담예약현황 리스트
		
		System.out.println(counselAppointmentList
				+ " <- counselAppointmentList   oneStudentCounselManage()   MemberController.java");
		
		
		System.out.println("상담예약현황리스트 크기 : " + counselAppointmentList.size());
		
		
		if(counselAppointmentList.size() == 0) { // 해당 학생의 상담예약현황이 존재하지 않는다면
			counselAppointmentList = null; // 객체참조변수에 할당된 주소값을 날려버린다.
		}
		
		
		System.out.println(counselAppointmentList
				+ " <- counselAppointmentList   oneStudentCounselManage()   MemberController.java");
		
		
		model.addAttribute("counselAppointmentList", counselAppointmentList);
		// 화면에 보여줄 해당 학생 상담예약현황 리스트
		
		return "/view/academyRegister/studentInfo/counselManage";
	}
}
