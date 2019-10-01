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
	public String studentInfoList(Model model) {
		List<Map<String, Object>> studentList = studentInfoService.studentInfoList();
		// 디비에서 권한이 학생인 사람들만 목록을 가져온다. (로그인 테이블 - 회원 신상정보 테이블 아이디로 조인)
		
		model.addAttribute("studentList", studentList);
		
		return "/view/academyRegister/studentInfo/listStudentInfo";
	}
	
	
	// 관리자 : 학생을 이름 혹은 가입기간으로 검색
	@PostMapping("/searchStudentInfo")
	public String searchStudentInfo(Member member, Model model) {
		List<Map<String, Object>> studentList =
				studentInfoService.studentInfoList(member);
		// 입력한 학생명과 가입기간으로 디비에서 권한이 학생인 사람들만 목록을 가져온다.
		// -> 로그인 테이블 - 회원 신상정보 테이블 아이디로 조인
		
		model.addAttribute("studentList", studentList);
		
		return "/view/academyRegister/studentInfo/listStudentInfo";
	}
	
	
	// 관리자 전용 학생관리 페이지 이동
//	@GetMapping("/studentManage")
//	public String studentManage() {
//		return "/view/academyRegister/memberInfo/student/studentManage";
//	}
	
	
	// 관리자 : 특정 학생 상세 페이지 이동
	@GetMapping("/updateStudentInfo")
	public String updateStudentInfo(@RequestParam(value = "memberId")String memberId
			, Model model) {
		
		Map<String, Object> studentInfo =
				studentInfoService.detailStudentInfoByMemberId(memberId);
		// 특정 학생의 아이디를 이용하여 아이디, 권한, 개인 신상정보, 학부모 정보를 가져온다.
		// 두개 이상의 테이블을 조인하므로 맵으로 가져온다.
		
		model.addAttribute("studentInfo", studentInfo);
		// 가져온 학생의 신상정보를 화면에 뿌려줄 객체에 넣는다.
		
		return "/view/academyRegister/studentInfo/detailStudentInfo";
	}
	
	
	// 관리자 : 특정 학생정보 수정 처리
	@PostMapping("/updateStudentInfo")
	public String updateStudentInfo(Member member, Parent parent, Model model
			,RedirectAttributes redirectAttributes) {
		String message = studentInfoService.updateStudentInfo(member, parent);
		// 학생과 학부모 정보 수정 처리 후 메세지 반환
		
		String path = "redirect:/studentList";
		// 학생정보 수정에 성공했을 경우 학생목록 리스트로 이동하게 초기화한다.
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 학생정보 수정에 실패했다는 뜻이다.
			
			System.out.println("학생정보 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("memberId", member.getMemberId());
			// 학생정보 상세 페이지로 리다이렉트하면서 회원 아이디를 넘겨준다.
			
			path = "redirect:/updateStudentInfo";
			// 학생정보 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자 : 회원 삭제 처리
/*	@GetMapping("/deleteStudentInfo")
	public String deleteStudentInfo(@RequestParam(value = "memberId")String memberId) {
		System.out.println(memberId + " <- memberId   deleteStudentInfo()   StudentInfoController.java");
		String message = studentInfoService.deleteStudentInfo(memberId);
		// 해당 회원 삭제 쿼리 실행 후 메세지 반환
		
		System.out.println(message + " <- message   deleteStudentInfo()   StudentInfoController.java");
		
		return "redirect:/listSubject";
	}*/
	
	
	
	
	// 관리자 전용 특정학생 결제정보 폼으로 이동
	@GetMapping("/updatePaymentInfo")
	public String updatePaymentInfo(@RequestParam(value = "memberId") String memberId, Model model) {
		System.out.println(memberId + " <- memberId   addPayment()   StudentInfoController.java");
		
		Map<String, Object> paymentInfo =
				studentInfoService.detailPaymentInfoByMemberId(memberId);
		// 학생의 아이디를 가지고 결제정보, 신상정보 테이블에서 객체를 얻어온다.
		
		String path = "/view/academyRegister/studentInfo/addPaymentInfo";
		// 결제정보가 없을 경우 결제정보를 추가할 수 있도록 경로를 설정해준다.
		
		if(paymentInfo != null) { // 결제정보가 있다면
			path = "/view/academyRegister/studentInfo/detailPaymentInfo";
			// 결제정보 테이블에 있는 내용을 볼 수 있는 페이지로 이동
			
			model.addAttribute("paymentInfo", paymentInfo);
			// 결제정보 객체를 모델에 넣어준다.
		} else { // 결제정보가 없다면
			Member studentSimpleInfo = studentInfoService.memberSimpleInfo(memberId);
			// 결제 추가창 제목에 뿌려줄 학생의 아이디, 이름, 생년월일을 가져온다.
			
			model.addAttribute("student", studentSimpleInfo);
		}
		
		model.addAttribute("memberId", memberId);
		// 결제 추가 페이지로 가든, 상세 페이지로 가든 회원 아이디는 항상
		// 가지고 가야 하므로 조건문 밖에 놓았다.
		
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
	
	
	// 관리자 : 특정학생 결제 정보 수정 처리
	@PostMapping("/updatePaymentInfo")
	public String updatePaymentInfo(PaymentInfo paymentInfo, Model model
			,RedirectAttributes redirectAttributes) {
		String message = studentInfoService.updatePaymentInfo(paymentInfo);
		// 결제정보 수정 처리 후 메세지 반환
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 결제정보 수정에 실패했다는 뜻이다.
			
			System.out.println("결제정보 수정 실패!!!!!!!!!!!!");
		}
		
		redirectAttributes.addAttribute("memberId", paymentInfo.getMemberId());
		// 결제정보 상세 페이지로 리다이렉트하면서 회원 아이디를 넘겨준다.
		
		return "redirect:/updatePaymentInfo";
		// 결제정보 수정처리 후 결제정보 상세 페이지로 이동하게 초기화한다.
	}
	
	
	// 관리자 : 미납현황 리스트(결제 테이블에서 납부예정금액이 0보다 큰 리스트)
	@GetMapping("/notPaymentStateList")
	public String notPaymentStateList(Model model) {
		
		List<Map<String, Object>> notPaymentStateList =
				studentInfoService.notPaymentStateList();
		// 납부예정금액이 0보다 큰 값을 가진 모든 리스트를 가져온다.
		
		model.addAttribute("notPaymentStateList", notPaymentStateList);
		// 화면에 뿌려주기위해 모델 객체에 넣는다.
		
		model.addAttribute("notPaymentStateListSize", notPaymentStateList.size());
		// 내용의 존재여부를 판단하기 위한 리스트의 사이즈
		
		return "/view/academyRegister/studentInfo/listNotPaymentState";
	}
	
	
	
	
	// 관리자가 학생 목록에서 특정 학생의 상담 관리 페이지로 이동
	@GetMapping("/counselManage")
	public String oneStudentCounselManage(CounselAppointment counselAppointment, Model model) {
		
		List<Map<String, Object>> counselHistoryList = 
				studentInfoService.oneStudentCounselHistoryList(counselAppointment.getMemberId());
		// 화면에 보여줄 해당 학생 상담 내역 리스트
		
//		if(counselHistoryList.size() == 0) { // 해당 학생의 상담 내역이 존재하지 않는다면
//			counselHistoryList = null; // 객체참조변수에 할당된 주소값을 날려버린다.
//		}
		
		System.out.println(counselHistoryList + " <- counselHistoryList   oneStudentCounselManage()   StudentInfoController.java");
	
		System.out.println("상담내역리스트 크기 : " + counselHistoryList.size());
		
		
		List<Map<String, Object>> counselAppointmentList = 
				studentInfoService.oneStudentCounselAppointment(counselAppointment);
		// 화면에 보여줄 해당 학생 상담예약현황 리스트
		
		
//		if(counselAppointmentList.size() == 0) { // 해당 학생의 상담예약현황이 존재하지 않는다면
//			counselAppointmentList = null; // 객체참조변수에 할당된 주소값을 날려버린다.
//		}
		
		System.out.println(counselAppointmentList + " <- counselAppointmentList   oneStudentCounselManage()   StudentInfoController.java");
		
		System.out.println("상담예약현황리스트 크기 : " + counselAppointmentList.size());
		
		
		model.addAttribute("studentInfo", studentInfoService.studentInfoIdNameBirthById(counselAppointment.getMemberId()));
		// 서비스에서 아이디로 해당 학생의 아이디, 이름과 생년월일만 가져와서 바로 모델에 넣어준다.
		
		model.addAttribute("counselTypeList", memberService.counselTypeList());
		// 검색할때 사용할 상담구분테이블에 있는 모든 객체 가져오기
		
		//model.addAttribute("counselResultList", memberService.counselResultList());
		// 검색할때 사용할 상담결과테이블에 있는 모든 객체 가져오기.
		
		model.addAttribute("counselHistoryList", counselHistoryList);
		// 화면에 보여줄 해당 학생 상담내역 리스트
		
		model.addAttribute("counselHistoryListSize", counselHistoryList.size());
		// 상담 내역을 뿌려줄 것인지, 상담내역이 없다는 메세지를 뿌려줄 것인지 판단용
		
		model.addAttribute("counselAppointmentList", counselAppointmentList);
		// 화면에 보여줄 해당 학생 상담예약현황 리스트
		
		model.addAttribute("counselAppointmentListSize", counselAppointmentList.size());
		// 상담예약현황을 뿌려줄 것인지, 상담예약현황이 없다는 메세지를 뿌려줄 것인지 판단용
		
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
	
	
	
	// 관리자 : 상담예약현황 리스트 이동
	@GetMapping("/currentReservationStateList")
	public String currentReservationStateList(Model model) {
		
		List<Map<String, Object>> counselReservationStateList =
				studentInfoService.counselReservationStateList();
		// 상담구분 테이블에서 상담구분코드(기본키)와 상담구분명(전화상담, 방문상담 등),
		// 상담결과 테이블에서 상담결과코드(기본키)와 상담결과명(입학상담, 성적상담 등),
		// 상담예약 테이블에서 상담내역코드(기본키)와 상담예약일(상담일),
		// 회원 로그인 테이블에서 회원 아이디,
		// 회원신상정보 테이블에서 회원명과 생년월일을 가져온다.
		// 여러 개의 테이블을 조인해서 데이터를 가져오므로 맵 형태의 리스트로 가져온다.
		
		
		List<CounselType> counselTypeList = memberService.counselTypeList();
		// 상담구분코드 테이블에서 전체 상담구분코드 리스트 가져오기
		// (기본키 - 상담구분코드)와 상담구분명만 가져온다.
		
		
		model.addAttribute("counselReservationStateList", counselReservationStateList);
		model.addAttribute("counselReservationStateListSize",
							counselReservationStateList.size());
		// 상담예약현황 리스트의 사이즈를 전달하여 사이즈가 0일 경우와 0이 아닐 경우를 판단한다.
		// 사이즈가 0이면 리스트 대신 '예약된 상담예약현황이 없습니다.' 메세지를 보여준다.
		
		model.addAttribute("counselTypeList", counselTypeList);
		
		return "/view/academyRegister/studentInfo/listCurrentReservationState";
	}
	
	
	// 관리자 : 상담예약현황 리스트에서 선택한 상담코드로 검색
	@PostMapping("/searchCounselReservationStateList")
	public String searchCounselReservationStateList(
					CounselResult counselResult ,Model model) {
		
		List<Map<String, Object>> counselReservationStateList = 
				studentInfoService.counselReservationStateList(counselResult);
		// 선택한 상담구분코드 혹은 상담구분코드와 상담결과코드로
		// 상담구분 테이블에서 상담구분코드(기본키)와 상담구분명(전화상담, 방문상담 등),
		// 상담결과 테이블에서 상담결과코드(기본키)와 상담결과명(입학상담, 성적상담 등),
		// 상담예약 테이블에서 상담내역코드(기본키)와 상담예약일(상담일),
		// 회원 로그인 테이블에서 회원 아이디,
		// 회원신상정보 테이블에서 회원명과 생년월일을 가져온다.
		// 여러 개의 테이블을 조인해서 데이터를 가져오므로 맵 형태의 리스트로 가져온다.
		
		
		List<CounselType> counselTypeList = memberService.counselTypeList();
		// 상담구분코드 테이블에서 전체 상담구분코드 리스트 가져오기
		// (기본키 - 상담구분코드)와 상담구분명만 가져온다.
		
		
		model.addAttribute("counselReservationStateList", counselReservationStateList);
		model.addAttribute("counselReservationStateListSize",
							counselReservationStateList.size());
		// 상담예약현황 리스트의 사이즈를 전달하여 사이즈가 0일 경우와 0이 아닐 경우를 판단한다.
		// 사이즈가 0이면 리스트 대신 '예약된 상담예약현황이 없습니다.' 메세지를 보여준다.
		
		model.addAttribute("counselTypeList", counselTypeList);
		
		return "/view/academyRegister/studentInfo/listCurrentReservationState";
	}
	
	
	// 관리자 : 학생 예약신청 상세보기
	@GetMapping("/updateCounselAppointment")
	public String updateCounselAppointment(
			CounselAppointment counselAppointment, Model model) {
		System.out.println(counselAppointment.getCounselHistoryNo() + " <- counselHistoryNo   updateCounselAppointment()   StudentInfoController.java");
		System.out.println(counselAppointment.getMemberId() + " <- memberId   updateCounselAppointment()   StudentInfoController.java");
		
		List<Map<String, Object>> oneMap =
				studentInfoService.oneStudentCounselAppointment(counselAppointment);
		// 상담예약 테이블의 기본키인 상담내역코드로 값을 구해올 것이기에 한개만 나올 것이다.
		
		Map<String, Object> counselAppointmentInfo = oneMap.get(0);
		
		
		
		return "/view/academyRegister/studentInfo/detailCounselAppointment";
	}
}
