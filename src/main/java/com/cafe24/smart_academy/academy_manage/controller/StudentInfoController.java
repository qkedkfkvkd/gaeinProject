package com.cafe24.smart_academy.academy_manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.smart_academy.academy_manage.member.service.MemberService;
import com.cafe24.smart_academy.academy_manage.member.service.StudentInfoService;
import com.cafe24.smart_academy.academy_manage.member.vo.Counsel;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselAppointment;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselResult;
import com.cafe24.smart_academy.academy_manage.member.vo.CounselType;
import com.cafe24.smart_academy.academy_manage.member.vo.GetCounselResultNo;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.Parent;
import com.cafe24.smart_academy.academy_manage.member.vo.PaymentInfo;

@Controller
public class StudentInfoController {
// 학생정보관리 컨트롤러
	
	@Autowired
	private MemberService memberService;
	
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
		System.out.println(inputParentPhone + "<- inputParentPhone   parentPhoneOverlapChk()   StudentInfoController.java");
		
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
		System.out.println(memberId + " <- memberId   addPayment()   StudentInfoController.java");
		
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
		
		model.addAttribute("counselTypeList", memberService.counselTypeList());
		// 검색할때 사용할 상담구분테이블에 있는 모든 객체 가져오기
		
		model.addAttribute("counselResultList", memberService.counselResultList());
		// 검색할때 사용할 상담결과테이블에 있는 모든 객체 가져오기.
		
		List<Map<String, Object>> counselHistoryList = 
				studentInfoService.oneStudentCounselHistoryList(memberId);
		// 화면에 보여줄 해당 학생 상담 내역 리스트
		
		System.out.println(counselHistoryList
					+ " <- counselHistoryList   oneStudentCounselManage()   StudentInfoController.java");
		
		
		System.out.println("상담내역리스트 크기 : " + counselHistoryList.size());
		
		
		if(counselHistoryList.size() == 0) { // 해당 학생의 상담 내역이 존재하지 않는다면
			counselHistoryList = null; // 객체참조변수에 할당된 주소값을 날려버린다.
		}
		
		
		System.out.println(counselHistoryList
				+ " <- counselHistoryList   oneStudentCounselManage()   StudentInfoController.java");
		
		
		model.addAttribute("counselHistoryList", counselHistoryList);
		// 화면에 보여줄 해당 학생 상담내역 리스트
		
		
		List<Map<String, Object>> counselAppointmentList = 
				studentInfoService.oneStudentCounselAppointmentList(memberId);
		// 화면에 보여줄 해당 학생 상담예약현황 리스트
		
		System.out.println(counselAppointmentList
				+ " <- counselAppointmentList   oneStudentCounselManage()   StudentInfoController.java");
		
		
		System.out.println("상담예약현황리스트 크기 : " + counselAppointmentList.size());
		
		
		if(counselAppointmentList.size() == 0) { // 해당 학생의 상담예약현황이 존재하지 않는다면
			counselAppointmentList = null; // 객체참조변수에 할당된 주소값을 날려버린다.
		}
		
		
		System.out.println(counselAppointmentList
				+ " <- counselAppointmentList   oneStudentCounselManage()   StudentInfoController.java");
		
		
		model.addAttribute("counselAppointmentList", counselAppointmentList);
		// 화면에 보여줄 해당 학생 상담예약현황 리스트
		
		return "/view/academyRegister/studentInfo/counselManage";
	}
	
	
	// 관리자 : 학생 상담내역 작성
	@GetMapping("/addCounselHistory")
	public String addCounselHistory(Model model
			,@RequestParam(value = "memberId")String memberId
	/* ,@RequestParam(value = "counselResultName")String counselResultName */) {
		
		model.addAttribute("memberId", memberId);
		// 모델 객체에 회원 아이디 넣어준다.
		
		//List<Map<String, Object>> counselKindList =
		//		studentInfoService.listCounselKind(counselResultName);
		// 상담결과명이 입학상담인 레코드의 상담구분코드와 상담구분명,
		// 상담결과코드에서 상담결과코드와 상담결과명을 두 테이블을 서로 조인해서 가져온다.
		
		//model.addAttribute("counselKindList", counselKindList);
		
		List<CounselResult> counselResultList =
				studentInfoService.counselResultNameList();
		// 상담결과코드 이름만 리스트로 가져오기
		
		model.addAttribute("counselResultList", counselResultList);
		
		return "/view/academyRegister/studentInfo/addCounselHistory";
	}
	
	
	// 관리자 : 선택한 상담결과코드로 상담구분코드 가져오기
	@PostMapping("/counselTypeSelect")
	@ResponseBody
	public List<CounselType> counselTypeSelect(@RequestBody String counselResultName) {
		System.out.println(counselResultName + "<- counselResultName   counselTypeSelect()   StudentInfoController.java");
		
		//Map<Object, Object> map = new HashMap<Object, Object>();
		
		List<CounselType> counselTypeList =
				studentInfoService.counselTypeListByCounselResultName(counselResultName);
		// 해당 상담결과코드로 상담구분코드 리스트 가져오기
		
		return counselTypeList;
	}
	
	
	// 관리자 : 상담내역코드 중복확인
	@PostMapping("/counselHistoryNoOverlapChk")
	@ResponseBody
	public Map<Object, Object> counselHistoryNoOverlapChk(@RequestBody String inputCounselHistoryNo) {
		System.out.println(inputCounselHistoryNo
				+ "<- inputCounselHistoryNo   counselHistoryNoOverlapChk()   StudentInfoController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String counselHistoryNoResult =
				studentInfoService.counselAppointmentBycounselHistoryNo(inputCounselHistoryNo);
		// 서비스의 상담내역코드 중복확인 메소드 호출
		
		if(counselHistoryNoResult == null) { // 조회한 값이 없다면
			map.put("result", 1);
			// 상담예약 테이블에 해당 상담내역코드 값이 존재하지 않음 : 사용 가능한 코드
			
			//result = "{\"result\":\"1\"}"; // {"result":"1"} -> 사용 가능한 코드
		} else {
			map.put("result", 0);
			// 상담예약 테이블에 해당 상담내역코드 값이 존재함 : 사용 중인 코드
			
			//result = "{\"result\":\"0\"}"; // {"result":"0"} -> 사용 중인 코드
		}
		
		return map;
	}
	
	
	// 관리자 : 학생 상담내역 추가 처리
	@PostMapping("/addCounselHistory")
	public String addCounselHistory(
			 CounselAppointment appointment
			,Counsel counsel
			,GetCounselResultNo getCounselResultNo
			,Model model
			,RedirectAttributes redirectAttributes) {
		// 상담예약객체(CounselAppointment)에는
		// 상담내역코드, 회원아이디, 상담일자(상담예약일), 상담여부, 예약여부(임시)가 있다.
		// 필수로 들어가야하는 상담결과코드가 없다.
		// 상담결과코드는 상담구분코드와 상담결과명으로 구해야한다.
		
		String counselResultNo = studentInfoService.getCounselResultNo(getCounselResultNo);
		// 원래는 이 코드도 학생이 상담예약 신청할 때 있어야하는 코드이다.
		// 나중에 복붙하면 되지 않을까.
		
		appointment.setCounselResultNo(counselResultNo);
		// 상담예약테이블에 필수로 들어가야하는 상담결과코드를 얻어온 코드로 셋팅한다.
		// 원래는 학생이 상담예약신청을 하면 상담예약테이블에 객체가 추가되어야한다.
		// 쿼리 실행을 위해 임시로 써놓았다.
		// 학생까지 코드가 완료되면 수정할 것이다.
		
		String appointmentMessage = studentInfoService.addCounselAppointment(appointment);
		// 상담예약 테이블 추가 처리
		
		int counselResult= studentInfoService.addCounsel(counsel);
		// 상담 테이블 추가 처리
		
		String path = "redirect:/counselManage";
		// 입력 성공시 해당 학생 상담 관리 페이지로 이동한다.
		
		if(appointmentMessage == null & counselResult == 1) { // 입력 성공
			redirectAttributes.addAttribute("memberId", appointment.getMemberId());
			// 학생 상담관리 페이지로 리다이렉트하면서 회원 아이디를 같이 넘겨준다.
			
		} else { // 메세지가 널이 아니다. 메세지에 값이 존재한다면 입력 실패이다.
			path = "/view/academyRegister/studentInfo/addCounselHistory";
			// 상담내역을 다시 입력해야한다.
			
			model.addAttribute("insertFail", "입력실패! 다시 입력해주세요.");
		}
		
		return path;
	}
}
