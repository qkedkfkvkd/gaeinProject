package com.cafe24.smart_academy.academy_manage.controller;

import java.util.ArrayList;
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

import com.cafe24.smart_academy.academy_manage.course.manage.service.CourseManageService;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.AcademyRoom;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.Subject;
import com.cafe24.smart_academy.academy_manage.course.service.CourseService;
import com.cafe24.smart_academy.academy_manage.course.service.ScheduleService;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseSchedule;
import com.cafe24.smart_academy.academy_manage.member.service.MemberService;
import com.cafe24.smart_academy.academy_manage.member.service.TeacherInfoService;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberSearchVO;

@Controller
public class ScheduleController {
// 시간표 관리 컨트롤러
	
	@Autowired
	ScheduleService scheduleService;
	// 시간표 관리 서비스 객체
	
	@Autowired
	CourseManageService courseManageService;
	// 과목과 강의실에 관한 정보를 가져오기위한 서비스 객체
	
	@Autowired
	MemberService memberService;
	// 회원 정보 관리 서비스 객체
	
	@Autowired
	TeacherInfoService teacherInfoService;
	// 강사 정보 관리 서비스 객체
	
	@Autowired
	CourseService courseService;
	// 강좌 및 강좌강의실 배정 정보 관리 서비스 객체
	
	
	// 관리자 승인된 전체 강좌 시간표를 조회한다.
	// 관리자 승인된 전체 강좌 시간표 검색결과 조회
	// 강사 : 강사 담당 강좌 시간표 조회
	@GetMapping("/scheduleList")
	public String scheduleList(
			 CourseRoomSearchVO searchVO
			,Model model) {
		
		Member member = null;
		// 강사의 강사 담당 시간표 접근 시 해당 강사의 간단한 정보를 저장할 객체
		// 아이디, 이름, 생년월일
		
		if(searchVO.getMemberId() != null && !searchVO.getMemberId().equals("")) {
			// 강사의 강사 담당시간표를 접근한 것이라면
			
			member = memberService.memberSimpleInfo(searchVO.getMemberId());
			// 해당 강사의 간단한 정보를 가져온다. (아이디, 이름, 생년월일)
		}
		
		List<Map<String, Object>> scheduleList = scheduleService.scheduleOneOrList(searchVO);
		// 전체 강좌 시간표를 얻어온다.
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		List<Map<String, Object>> teacherList = teacherInfoService.teacherInfoOneOrList();
		// 전체 강사 리스트를 가져온다.
		
		String title = "전체";
		// 시간표 페이지의 제목부분
		
		if(searchVO.getScheduleDay() != null && !searchVO.getScheduleDay().equals("")) {
			// 로그인 전 및 학생로그인 후 특정 요일의 강좌 시간표를 클릭했다면
			title = searchVO.getScheduleDay() + "요일";
			// 몇요일의 날짜를 클릭했는지 제목부분에 표시해준다.
			
			model.addAttribute("scheduleDay", searchVO.getScheduleDay());
			// 어떤 요일의 강좌시간표를 클릭했는지 해당 요일에서 검색할 수 있도록 요일을 모델에 넣어준다.
		} else if (searchVO.getMemberId() != null && !searchVO.getMemberId().equals("")) {
			// 강사가 강사담당 강좌시간표를 접근한 것이라면
			
			title = member.getMemberId() + " : " + member.getMemberName() + " 강사의";
			// 강사의 아이디와 이름을 제목부분에 표시해준다.
		}
		
		
		model.addAttribute("title", title);
		// 시간표 페이지의 제목부분
		
		model.addAttribute("subjectList", subjectList);
		// 검색 폼의 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		model.addAttribute("roomList", roomList);
		// 검색 폼의 샐랙트박스에 넣어줄 전체 강의실 리스트
		
		model.addAttribute("teacherList", teacherList);
		// 검색 폼의 샐랙트박스에 넣어줄 전체 강사 리스트
		
		model.addAttribute("scheduleList", scheduleList);
		// 화면에 보여줄 시간표 리스트
		
		model.addAttribute("scheduleListSize", scheduleList.size());
		// 리스트의 사이즈를 보고 시간표 존재 여부를 판단한다.
		
		return "/view/lesson/schedule/listSchedule";
	}
	
	
	// 관리자 : 강사의 강좌 시간표 승인 요청 목록으로 이동
	// 관리자 : 강사의 강좌 시간표 승인 요청 검색결과 리스트
	// 강사 : 내 강좌시간표 승인요청 목록으로 이동
	@GetMapping("/scheduleApprovalRequestList")
	public String scheduleApprovalRequestList(
			 CourseRoomSearchVO searchVO
			,Model model) {
		
		if(searchVO.getMemberId() != null && !searchVO.getMemberId().equals("")) {
			// 강사로 로그인하여 자신의 강좌시간표 승인요청 리스트로 이동할 경우
			
			System.out.println(searchVO.getMemberId()
					+ " <- searchVO.getMemberId()   scheduleApprovalRequestList()   ScheduleController.java");
			// 관리자의 경우 특정 강사의 아이디가 필요 없으나 강사의 경우 자신의 아이디값을 넘기게 된다.
		}
		
		List<Map<String, Object>> scheduleList = scheduleService.scheduleOneOrList(searchVO);
		// 승인여부가 '무'인 강좌 시간표를 얻어온다. (관리자 강좌시간표 승인 안됨)
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		List<Map<String, Object>> teacherList = teacherInfoService.teacherInfoOneOrList();
		// 전체 강사 리스트를 가져온다.
		
		
		model.addAttribute("subjectList", subjectList);
		// 검색 폼의 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		model.addAttribute("roomList", roomList);
		// 검색 폼의 샐랙트박스에 넣어줄 전체 강의실 리스트
		
		model.addAttribute("teacherList", teacherList);
		// (관리자)검색 폼의 샐랙트박스에 넣어줄 전체 강사 리스트
		
		model.addAttribute("scheduleList", scheduleList);
		// 화면에 보여줄 시간표 리스트
		
		model.addAttribute("scheduleListSize", scheduleList.size());
		// 리스트의 사이즈를 보고 시간표 존재 여부를 판단한다.
		
		return "/view/lesson/schedule/listScheduleApprovalRequest";
	}
	
	
	// 관리자 : 강사가 추가하거나 수정한 강좌시간표 승인 처리
	@PostMapping("/scheduleApproval")
	@ResponseBody
	public Map<Object, Object> scheduleApproval(@RequestBody String scheduleNo) {
		System.out.println(scheduleNo + "<- scheduleNo   scheduleApproval()   ScheduleController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		// 뷰페이지에 보낼 객체
		
		String message = scheduleService.scheduleApproval(scheduleNo);
		// 해당 시간표 관리자 승인 처리 메소드 호출
		
		System.out.println(message + " <- message   scheduleApproval()   ScheduleController.java");
		
		if(message == null) {
			map.put("result", 1);
			// 메세지가 존재하지 않음 : 승인 성공
			
		} else {
			map.put("result", 0);
			//메세지가 존재함 : 승인 실패
		}
		
		return map;
	}
	
	
	// 강사 : 시간표 작성 폼 이동
	@GetMapping("/addSchedule")
	public String addSchedule(MemberSearchVO memberSearchVO, Model model) {
		System.out.println(memberSearchVO.getMemberId() + " <- memberId   addSchedule()   ScheduleController.java");
		// 특정 강사의 정보만을 가져와야 하므로 검색 키워드에 특정 강사의 아이디를 넣어준다.
		
		List<Map<String, Object>> oneMap = 
				teacherInfoService.teacherInfoOneOrList(memberSearchVO);
		// 리턴타입을 맞춰주기 위해 리스트로 받았을 뿐 로그인 테이블의 기본키인 회원아이디로
		// 검색을 실시하므로 하나의 객체만 리턴될 것이다.
		
		System.out.println(oneMap.toString()
				+ " <- oneMap.toString()   teacherCourseEnrolleeStudentList()   ScheduleController.java");
		System.out.println(oneMap.size()
				+ " <- oneMap.size()   teacherCourseEnrolleeStudentList()   ScheduleController.java");
		
		Map<String, Object> teacherInfo = oneMap.get(0);
		// 하나의 객체 밖에 없으므로 맨 앞에있는 객체를 가져다가 맵에 넣어준다.
		
		CourseRoomSearchVO searchVO = new CourseRoomSearchVO();
		searchVO.setMemberId(memberSearchVO.getMemberId());
		// 강좌강의실 배정리스트를 특정 강사의 아이디를 검색 키워드로 넣어서 조회한다.
		
		List<Map<String, Object>> courseAssignList =
				courseService.courseAssignmentOneOrList(searchVO);
		// 특정 강사의 아이디를 참조하는 강좌강의실배정 리스트 가져오기
		
		
		//List<Map<String, Object>> scheduleList = scheduleService.scheduleOneOrList();
		// 관리자의 승인여부 상관없이 전체 강좌 시간표를 얻어온다.
		
		
		model.addAttribute("teacherInfo", teacherInfo);
		// 화면에 보여줄 특정 강사에 관한 정보 넣어주기
		
		model.addAttribute("courseAssignList", courseAssignList);
		// 샐랙트박스에 넣어줄 강사 담당 강좌의 강좌강의실 배정 리스트
		
		
		//model.addAttribute("scheduleList", scheduleList);
		// 화면에 보여줄 전체 강좌 시간표
		
		//model.addAttribute("scheduleListSize", scheduleList.size());
		// 리스트의 사이즈를 보고 강좌시간표의 존재여부 판단
		
		return "/view/lesson/schedule/addSchedule";
	}
	
	
	// 강사 : 요일 선택시 해당 요일을 참조하는 리스트 보여주기
	@PostMapping("/getScheduleByDay")
	@ResponseBody
	public List<Map<String, Object>> getScheduleByDay(@RequestBody String scheduleDay) {
		System.out.println(scheduleDay + "<- scheduleDay   getScheduleByDay()   ScheduleController.java");
		
		List<Map<String, Object>> scheduleList = null;
		// 뷰페이지에 보낼 객체
		
		if(scheduleDay.equals("not")) { // "선택"을 선택했을 경우
			scheduleList = new ArrayList<Map<String,Object>>();
			// 객체만 선언한다. (리스트의 사이즈가 0이다.)
			
		} else {
			// 특정 요일을 선택했을 경우
			CourseRoomSearchVO searchVO = new CourseRoomSearchVO();
			searchVO.setScheduleDay(scheduleDay);
			// 해당 요일의 강좌 시간표를 가져와야하므로 검색 키워드로 요일을 넣어준다.
			
			scheduleList = scheduleService.scheduleOneOrList(searchVO);
			// 해당 요일의 강좌 시간표 가져오기
		}
		
		
		return scheduleList;
	}
	
	
	// 강사 : 시간표코드 중복 확인
	@PostMapping("/scheduleNoOverlapChk")
	@ResponseBody
	public Map<String, Object> scheduleNoOverlapChk(@RequestBody String scheduleNo) {
		System.out.println(scheduleNo
				+ " <- scheduleNo   scheduleNoOverlapChk()   ScheduleController.java");
		
		Map<String, Object> map = new HashMap<String, Object>();
		// 뷰페이지에 보낼 객체
		
		String result = scheduleService.CourseScheduleByscheduleNo(scheduleNo);
		// 강좌시간표 테이블에서 해당 시간표코드가 존재하는지 확인
		
		if(result == null) {	// 결과가 없다 : 해당 시간표코드가 존재하지 않는다.
			map.put("result", 1);
			// 강좌시간표 테이블에 해당 시간표코드값이 존재하지 않음 : 사용가능한 시간표코드
		} else {
			map.put("result", 0);
			// 강좌시간표 테이블에 해당 시간표코드값이 존재함 : 이미 사용 중인 시간표코드
		}
		
		return map;
	}
	
	
	// 강사 : 시간표 입력 처리
	@PostMapping("/addSchedule")
	public String addSchedule(
			 CourseSchedule schedule
			,@RequestParam(value = "memberId") String memberId
			,RedirectAttributes redirectAttributes) {
		
		String message = scheduleService.addSchedule(schedule);
		// 시간표 추가 처리 후 메세지를 반환받는다.
		
		String path = "redirect:/addSchedule";
		// 강좌추가에 실패했을 경우 다시 강좌시간표를 추가하는 폼으로 이동하게 초기화한다.
		
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 시간표추가에 성공했다는 뜻이다.
			
			redirectAttributes.addAttribute("scheduleApprovalStatus", "무");
			// 승인요청 목록으로 이동하므로 승인여부(유,무) 중 "무"를 넣어준다.
			
			path = "redirect:/scheduleApprovalRequestList";
			// 강좌시간표 승인 요청 목록으로 이동한다.
		}
		
		redirectAttributes.addAttribute("memberId", memberId);
		// 시간표 추가에 실패하여 추가폼으로 가든,
		// 시간표 추가에 성공하여 시간표 리스트로 가든
		// 강사 자신의 아이디는 항상 가지고 가야한다.
		
		return path;
	}
	
	
	// 관리자, 강사 : 시간표 상세 보기
	@GetMapping("/updateSchedule")
	public String updateSchedule(
			 CourseRoomSearchVO searchVO
			,Model model) {
		System.out.println(searchVO.getScheduleNo()
				+ " <- searchVO.getScheduleNo()   updateSchedule()   ScheduleController.java");
		
		List<Map<String, Object>> oneMap = scheduleService.scheduleOneOrList(searchVO);
		// 시간표 테이블의 기본키인 시간표 코드로 값을 얻어올 것이기에 하나의 객체만 나온다.
		
		System.out.println(oneMap.toString() + " <- oneMap.toString()   updateSchedule()   ScheduleController.java");
		System.out.println(oneMap.size() + " <- oneMap.size()   updateSchedule()   ScheduleController.java");
		
		Map<String, Object> scheduleInfo = oneMap.get(0);
		// 하나의 객체밖에 없기에 그 객체를 꺼내서 담아준다.
		
		
		searchVO.setScheduleNo(null);
		// 시간표 상세보기 폼 우측에 상세보기의 요일을 참조하는 시간표리스트를 가져와야 하므로
		// 강좌시간표 테이블의 기본키인 시간표코드를 없애줘야한다.
		
		searchVO.setMemberId((String)scheduleInfo.get("memberId"));
		// 강좌강의실 배정리스트를 특정 강사의 아이디를 검색 키워드로 넣어서 조회한다.
		
		List<Map<String, Object>> courseAssignList =
				courseService.courseAssignmentOneOrList(searchVO);
		// 특정 강사의 아이디를 참조하는 강좌강의실배정 리스트 가져오기
		
		
		searchVO.setMemberId(null);
		// 해당 요일을 참조하는 전체 시간표리스트를 가져와야하므로
		// 해당 시간표의 강사아이디 검색 쿼리가 발동하면 안된다.
		
		searchVO.setScheduleDay((String)scheduleInfo.get("scheduleDay"));
		// 해당 요일의 강좌 시간표를 가져와야하므로 검색 키워드로 요일을 넣어준다.
		
		List<Map<String, Object>> scheduleList = scheduleService.scheduleOneOrList(searchVO);
		// 관리자의 승인여부 상관없이 해당 요일의 강좌 시간표 가져오기
		
		
		model.addAttribute("scheduleInfo", scheduleInfo);
		// 화면에 뿌려줄 객체를 담아준다.
		
		model.addAttribute("courseAssignList", courseAssignList);
		// 샐랙트박스에 넣어줄 강사 담당 강좌의 강좌강의실 배정 리스트
		
		model.addAttribute("scheduleList", scheduleList);
		// 화면에 보여줄 전체 강좌 시간표
		
		return "/view/lesson/schedule/detailSchedule";
	}
	
	
	// 관리자, 강사 : 시간표 수정처리
	@PostMapping("/updateSchedule")
	public String updateSchedule(
			 CourseSchedule schedule
			,@RequestParam(value = "memberId", required = false) String memberId
			,RedirectAttributes redirectAttributes) {
		
		String message = scheduleService.updateSchedule(schedule);
		// 시간표 수정처리 후 메세지를 리턴받는다.
		
		String path = "redirect:/scheduleApprovalRequestList";
		// 시간표 수정에 성공했을 경우 강좌시간표 승인 요청 리스트로 이동하게 초기화한다.
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 시간표 수정에 실패했다는 뜻이다.
			
			System.out.println("시간표 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("scheduleNo", schedule.getScheduleNo());
			// 시간표 상세 페이지로 리다이렉트하면서 시간표코드를 넘겨준다.
			
			path = "redirect:/updateSchedule";
			// 시간표 상세 페이지로 이동
		} else {
			// 리턴받은 메세지가 널이라면 시간표 수정에 성공했다는 뜻이다.
			
			redirectAttributes.addAttribute("scheduleApprovalStatus", "무");
			// 시간표 수정에 성공하여 강좌시간표 승인요청 리스트로 넘어갈 경우
			// 관리자의 승인여부를 같이 넣어준다.
		}
		
		if(memberId != null) { // 강사회원이 시간표를 수정한 것이라면
			redirectAttributes.addAttribute("memberId", memberId);
			// 강사의 아이디를 넣어준다.
		}
		
		return path;
	}
	
	
	// 관리자, 강사 : 시간표 삭제 처리
	@GetMapping("/deleteSchedule")
	public String deleteSchedule(
			 @RequestParam(value = "scheduleNo") String scheduleNo
			,@RequestParam(value = "memberId", required = false) String memberId
			,RedirectAttributes redirectAttributes) {
		
		System.out.println(scheduleNo + " <- scheduleNo   deleteSchedule()   ScheduleController.java");
		
		String message = scheduleService.deleteSchedule(scheduleNo);
		// 해당 시간표 삭제 쿼리 실행 후 메세지 반환
		
		System.out.println(message + " <- message   deleteSchedule()   ScheduleController.java");
		
		redirectAttributes.addAttribute("scheduleApprovalStatus", "유");
		// 시간표 리스트로 이동하므로 관리자 승인 키워드를 넣어준다.
		
		if(memberId != null) { // 강사회원이 로그인해서 자신의 강좌시간표를 삭제했다면
			redirectAttributes.addAttribute("memberId", memberId);
			// 해당 강사 담당 강좌를 보여줘야하므로 강사의 아이디를 넣어준다.
		}
		
		return "redirect:/scheduleList";
	}
	
	
	// 관리자, 학생 : 특정 학생의 강좌 시간표 리스트
	// 관리자, 학생 : 특정 학생의 강좌 시간표 검색결과 리스트
	@GetMapping("/oneStudentCourseScheduleList")
	public String oneStudentCourseScheduleList(
			 CourseRoomSearchVO searchVO
			,Model model) {
		
		Member student = memberService.memberSimpleInfo(searchVO.getMemberId());
		// 회원의 간단한 정보를 가져온다. (아이디, 이름, 생년월일)
		
		List<Map<String, Object>> courseScheduleList =
				scheduleService.oneStudentCourseScheduleList(searchVO);
		// 특정 학생의 강좌 시간표를 얻어온다.
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		
		model.addAttribute("selectScheduleDay", searchVO.getScheduleDay());
		// 화면의 제목 부분에 보여줄 특정 요일
		
		model.addAttribute("student", student);
		// 화면의 제목 부분에 보여줄 학생의 간단한 정보
		
		model.addAttribute("courseScheduleList", courseScheduleList);
		// 화면에 보여줄 특정 학생의 강좌 시간표 리스트
		
		model.addAttribute("courseScheduleListSize", courseScheduleList.size());
		// 리스트의 길이를 보고 해당 학생의 강좌 시간표 존재 여부 판단
		
		
		model.addAttribute("subjectList", subjectList);
		// 검색 폼의 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		model.addAttribute("roomList", roomList);
		// 검색 폼의 샐랙트박스에 넣어줄 전체 강의실 리스트
		
		return "/view/lesson/schedule/listOneStudentCourseSchedule";
	}
}
