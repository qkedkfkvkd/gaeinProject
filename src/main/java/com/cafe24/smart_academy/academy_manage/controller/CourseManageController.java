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

import com.cafe24.smart_academy.academy_manage.course.manage.service.CourseManageService;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.AcademyRoom;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.GradingCriteria;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.Subject;

@Controller
public class CourseManageController {
// 과목 및 강좌 등록, 성적 관리 등
	
	@Autowired
	private CourseManageService courseManageService;
	
	
	
	// 관리자가 학생목록에서 해당 학생의 전체 성적 결과를 보고자 할때 사용한다.
//	@GetMapping("/oneStudentTotalGradeResult")
//	public String oneStudentTotalGradeResult(@RequestParam(value = "memberId")String memberId) {
//		
//		
//		return "";
//	}
	
	
	// 관리자 : 과목 리스트로 이동
	@GetMapping("/listSubjectCode")
	public String listSubjectCode(Model model) {
		
		List<Subject> subjectList = courseManageService.listSubjectCode();
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("subjectListSize", subjectList.size());
		return "/view/lesson/courseCode/listSubjectCode";
	}
	
	
	// 관리자 : 과목을 추가하는 폼으로 이동
	@GetMapping("/addSubjectCode")
	public String addSubjectCode() {
		return "/view/lesson/courseCode/addSubjectCode";
	}
	
	
	// 과목 코드 중복확인
	@GetMapping("/subjectNoOverlapCheck")
	@ResponseBody
	public Map<Object, Object> subjectOverlapCheck(@RequestBody String subjectNo) {
		System.out.println(subjectNo + " <- subjectNo   subjectOverlapCheck()   CourseManageController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String result = courseManageService.subjectBySubjectNo(subjectNo);
		// 과목 테이블에서 해당 과목코드가 존재하는지 확인
		
		if(result == null) {	// 결과가 없다 : 해당 과목코드가 존재하지 않는다.
			map.put("result", 1);
			// 과목 테이블에 해당 과목코드값이 존재하지 않음 : 사용가능한 과목코드
		} else {
			map.put("result", 0);
			// 과목 테이블에 해당 과목코드값이 존재함 : 이미 사용 중인 과목코드
		}
		
		return map;
	}
	
	
	// 관리자 : 과목 추가 처리
//	@PostMapping("/addSubjectCode")
//	public String addSubjectCode(Model model, Subject subject) {
//		String message = courseManageService.addSubjectCode(subject);
//		
//		
//		return "";
//	}
	
	
	
	// 관리자 : 강의실 리스트로 이동
	@GetMapping("/listAcademyRoom")
	public String listAcademyRoom(Model model) {
		
		List<AcademyRoom> roomList = courseManageService.listAcademyRoom();
		
		model.addAttribute("roomList", roomList);
		model.addAttribute("roomListSize", roomList.size());
		return "/view/lesson/courseCode/listAcademyRoomCode";
	}
	
	
	// 관리자 : 강의실을 추가하는 폼으로 이동
	@GetMapping("/addAcademyRoomCode")
	public String addAcademyRoomCode() {
		return "/view/lesson/courseCode/addAcademyRoomCode";
	}
	
	
	// 강의실 코드 중복확인
	@GetMapping("/academyRoomNoOverlapCheck")
	@ResponseBody
	public Map<Object, Object> academyRoomNoOverlapCheck(@RequestBody String roomNo) {
		System.out.println(roomNo + " <- roomNo   academyRoomOverlapCheck()   CourseManageController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String result = courseManageService.academyRoomNoByacademyRoomNo(roomNo);
		// 강의실 테이블에서 해당 강의실 코드가 존재하는지 확인
		
		if(result == null) {	// 결과가 없다 : 해당 강의실코드가 존재하지 않는다.
			map.put("result", 1);
			// 강의실 테이블에 해당 강의실코드값이 존재하지 않음 : 사용가능한 강의실코드
		} else {
			map.put("result", 0);
			// 강의실 테이블에 해당 강의실코드값이 존재함 : 이미 사용 중인 강의실코드
		}
		
		return map;
	}
	
	
	
	// 관리자 : 성적기준 리스트로 이동
	@GetMapping("/listGradingCriteriaCode")
	public String listGradingCriteriaCode(Model model) {
		
		List<GradingCriteria> gradingCriteriaList =
				courseManageService.listGradingCriteria();
		
		model.addAttribute("gradingCriteriaList", gradingCriteriaList);
		model.addAttribute("gradingCriteriaListSize", gradingCriteriaList.size());
		return "/view/lesson/courseCode/listGradingCriteriaCode";
	}
	
	
	// 관리자 : 성적기준을 추가하는 폼으로 이동
	@GetMapping("/addGradingCriteriaCode")
	public String addGradingCriteriaCode() {
		return "/view/lesson/courseCode/addGradingCriteriaCode";
	}
	
	
	// 성적기준 테이블의 기본키인 등급 중복확인
	@GetMapping("/gradingCriteriaRatingOverlapCheck")
	@ResponseBody
	public Map<Object, Object> gradingCriteriaRatingOverlapCheck(@RequestBody String inputGradingCriteriaRating) {
		System.out.println(inputGradingCriteriaRating
				+ " <- inputGradingCriteriaRating   gradingCriteriaRatingOverlapCheck()   CourseManageController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String result = courseManageService.gradingCriteriaRatingOverlapCheck(inputGradingCriteriaRating);
		// 강의실 테이블에서 해당 강의실 코드가 존재하는지 확인
		
		if(result == null) {	// 결과가 없다 : 해당 강의실코드가 존재하지 않는다.
			map.put("result", 1);
			// 강의실 테이블에 해당 강의실코드값이 존재하지 않음 : 사용가능한 강의실코드
		} else {
			map.put("result", 0);
			// 강의실 테이블에 해당 강의실코드값이 존재함 : 이미 사용 중인 강의실코드
		}
		
		return map;
	}
	
	
	
	
}
