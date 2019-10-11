package com.cafe24.smart_academy.academy_manage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cafe24.smart_academy.academy_manage.course.manage.service.CourseManageService;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.AcademyRoom;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.Subject;
import com.cafe24.smart_academy.academy_manage.course.service.ScheduleService;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.member.service.TeacherInfoService;
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
	TeacherInfoService teacherInfoService;
	// 강사 정보 관리 서비스 객체
	
	
	// 전체 강좌 시간표를 조회한다.
	@GetMapping("/scheduleList")
	public String scheduleList(
			 CourseRoomSearchVO searchVO
			,Model model) {
		
		List<Map<String, Object>> scheduleList = scheduleService.scheduleList(searchVO);
		// 전체 강좌 시간표를 얻어온다.
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		List<Map<String, Object>> teacherList = teacherInfoService.teacherInfoOneOrList();
		// 전체 강사 리스트를 가져온다.
		
		
		model.addAttribute("subjectList", subjectList);
		// 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		model.addAttribute("roomList", roomList);
		// 샐랙트박스에 넣어줄 전체 강의실 리스트
		
		model.addAttribute("teacherList", teacherList);
		// 샐랙트박스에 넣어줄 전체 강사 리스트
		
		model.addAttribute("scheduleList", scheduleList);
		// 화면에 보여줄 시간표 리스트
		
		model.addAttribute("scheduleListSize", scheduleList.size());
		// 리스트의 사이즈를 보고 시간표 존재 여부를 판단한다.
		
		return "/view/lesson/schedule/listSchedule";
	}
	
	
	// 전체 강좌 시간표 검색결과 조회
	@PostMapping("/searchSchedule")
	public String searchSchedule(
			 CourseRoomSearchVO searchVO
			,Model model) {
		
		List<Map<String, Object>> scheduleList = scheduleService.scheduleList(searchVO);
		// 선택한 값과 일치하는 전체 강좌 시간표를 얻어온다.
		// 과목코드, 강좌코드, 강의실코드, 강사명(회원 아이디)
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		List<Map<String, Object>> teacherList = teacherInfoService.teacherInfoOneOrList();
		// 전체 강사 리스트를 가져온다.
		
		
		model.addAttribute("subjectList", subjectList);
		// 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		model.addAttribute("roomList", roomList);
		// 샐랙트박스에 넣어줄 전체 강의실 리스트
		
		model.addAttribute("teacherList", teacherList);
		// 샐랙트박스에 넣어줄 전체 강사 리스트
		
		model.addAttribute("scheduleList", scheduleList);
		// 화면에 보여줄 시간표 리스트
		
		model.addAttribute("scheduleListSize", scheduleList.size());
		// 리스트의 사이즈를 보고 시간표 존재 여부를 판단한다.
		
		return "/view/lesson/schedule/listSchedule";
	}
	
	
	// 관리자 : 강사의 강좌 시간표 승인 요청 목록으로 이동
	@GetMapping("/scheduleApprovalRequestList")
	public String scheduleApprovalRequestList(
			 CourseRoomSearchVO searchVO
			,Model model) {
		
		List<Map<String, Object>> scheduleList = scheduleService.scheduleList(searchVO);
		// 승인여부가 '무'인 강좌 시간표를 얻어온다. (관리자 강좌시간표 승인 안됨)
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		List<Map<String, Object>> teacherList = teacherInfoService.teacherInfoOneOrList();
		// 전체 강사 리스트를 가져온다.
		
		
		model.addAttribute("subjectList", subjectList);
		// 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		model.addAttribute("roomList", roomList);
		// 샐랙트박스에 넣어줄 전체 강의실 리스트
		
		model.addAttribute("teacherList", teacherList);
		// 샐랙트박스에 넣어줄 전체 강사 리스트
		
		model.addAttribute("scheduleList", scheduleList);
		// 화면에 보여줄 시간표 리스트
		
		model.addAttribute("scheduleListSize", scheduleList.size());
		// 리스트의 사이즈를 보고 시간표 존재 여부를 판단한다.
		
		return "/view/lesson/schedule/listScheduleApprovalRequest";
	}
	
	
	// 관리자 : 관리자 : 강사의 강좌 시간표 승인 요청 검색결과 리스트
	@PostMapping("/searchScheduleApprovalRequest")
	public String searchScheduleApprovalRequest(
				 CourseRoomSearchVO searchVO
				,Model model) {
		
		List<Map<String, Object>> scheduleList = scheduleService.scheduleList(searchVO);
		// 승인여부가 '무'인 강좌 시간표를 얻어온다. (관리자 강좌시간표 승인 안됨)
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		List<Map<String, Object>> teacherList = teacherInfoService.teacherInfoOneOrList();
		// 전체 강사 리스트를 가져온다.
		
		
		model.addAttribute("subjectList", subjectList);
		// 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		model.addAttribute("roomList", roomList);
		// 샐랙트박스에 넣어줄 전체 강의실 리스트
		
		model.addAttribute("teacherList", teacherList);
		// 샐랙트박스에 넣어줄 전체 강사 리스트
		
		model.addAttribute("scheduleList", scheduleList);
		// 화면에 보여줄 시간표 리스트
		
		model.addAttribute("scheduleListSize", scheduleList.size());
		// 리스트의 사이즈를 보고 시간표 존재 여부를 판단한다.
		
		return "/view/lesson/schedule/listScheduleApprovalRequest";
	}
	
	
	
	
	
}
