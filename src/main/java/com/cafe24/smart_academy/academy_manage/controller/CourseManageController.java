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

import com.cafe24.smart_academy.academy_manage.course.manage.service.CourseManageService;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.AcademyRoom;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.GradingCriteria;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.Subject;

@Controller
public class CourseManageController {
// 과목 및 강좌 등록, 성적기준 관리 등
	
	@Autowired
	private CourseManageService courseManageService;
	
	
	// 관리자 : 과목 리스트로 이동
	@GetMapping("/listSubject")
	public String listSubject(Model model) {
		
		List<Subject> subjectList = courseManageService.listSubject();
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("subjectListSize", subjectList.size());
		return "/view/lesson/courseCode/listSubjectCode";
	}
	
	
	// 관리자 : 과목을 추가하는 폼으로 이동
	@GetMapping("/addSubject")
	public String addSubject() {
		return "/view/lesson/courseCode/addSubjectCode";
	}
	
	
	// 과목 코드 중복확인
	@PostMapping("/subjectNoOverlapCheck")
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
	@PostMapping("/addSubject")
	public String addSubject(Model model, Subject subject) {
		String message = courseManageService.addSubject(subject);
		
		String path = "/view/lesson/courseCode/addSubjectCode";
		// 과목추가에 실패했을 경우 다시 과목을 추가하는 폼으로 이동하게 초기화한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 과목추가에 성공했다는 뜻이다.
			path = "redirect:/listSubject";
			// 과목 리스트로 이동한다.
		}
		
		return path;
	}
	
	
	
	// 관리자 : 강의실 리스트로 이동
	@GetMapping("/listAcademyRoom")
	public String listAcademyRoom(Model model) {
		
		List<AcademyRoom> roomList = courseManageService.listAcademyRoom();
		// 전체 강의실 리스트를 가지고 온다.
		
		List<Integer> roomFloorTempList = new ArrayList<Integer>();
		// 강의실 층수를 저장할 리스트 (중복포함)
		
		// 강의실 층수 중복 제거를 위한 반복문
		for(int i=0; i<roomList.size(); i++) {
			AcademyRoom room = roomList.get(i);
			// 강의실 객체 한개씩 가져온다.
			
			int roomFloor = room.getRoomFloor();
			// 가져온 강의실 객체에서 층수만을 꺼내서 변수에 저장한다.
			
			roomFloorTempList.add(roomFloor);
			// 각각의 강의실 객체에서 가져온 층수를 리스트에 저장한다.
			// 여기까지는 층수가 중복되어 저장되게 될 것이다.
		}
		
		Set<Integer> tempSet = new TreeSet<Integer>(roomFloorTempList);
		// 층수의 중복을 제거하여 저장할 셋 객체
		
		
		List<Integer> roomFloorList = new ArrayList<Integer>(tempSet);
		// 강의실 층수를 저장할 리스트 (중복제거)
		
		
		model.addAttribute("roomList", roomList);
		model.addAttribute("roomListSize", roomList.size());
		model.addAttribute("roomFloorList", roomFloorList);
		return "/view/lesson/courseCode/listAcademyRoomCode";
	}
	
	
	// 관리자 : 강의실을 추가하는 폼으로 이동
	@GetMapping("/addAcademyRoom")
	public String addAcademyRoom() {
		return "/view/lesson/courseCode/addAcademyRoomCode";
	}
	
	
	// 강의실 코드 중복확인
	@PostMapping("/academyRoomNoOverlapCheck")
	@ResponseBody
	public Map<Object, Object> academyRoomNoOverlapCheck(@RequestBody String roomNo) {
		System.out.println(roomNo + " <- roomNo   academyRoomOverlapCheck()   CourseManageController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String result = courseManageService.academyRoomByRoomNo(roomNo);
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
	
	
	// 관리자 : 강의실 추가
	@PostMapping("/addAcademyRoom")
	public String addAcademyRoomCode(Model model, AcademyRoom room) {
		String message = courseManageService.addAcademyRoom(room);
		
		String path = "/view/lesson/courseCode/addAcademyRoomCode";
		// 강의실 추가에 실패했을 경우 다시 강의실 추가 폼으로 이동한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 강의실 추가에 성공했다는 뜻이다.
			
			path = "redirect:/listAcademyRoom";
			// 강의실 리스트로 리다이렉트한다.
		}
		
		return path;
	}
	
	
	
	// 관리자 : 성적기준 리스트로 이동
	@GetMapping("/listGradingCriteria")
	public String listGradingCriteria(Model model) {
		
		List<GradingCriteria> gradingCriteriaList =
				courseManageService.listGradingCriteria();
		
		model.addAttribute("gradingCriteriaList", gradingCriteriaList);
		model.addAttribute("gradingCriteriaListSize", gradingCriteriaList.size());
		return "view/lesson/courseCode/listGradingCriteriaCode";
	}
	
	
	// 관리자 : 성적기준을 추가하는 폼으로 이동
	@GetMapping("/addGradingCriteria")
	public String addGradingCriteria() {
		return "/view/lesson/courseCode/addGradingCriteriaCode";
	}
	
	
	// 관리자 : 성적기준 테이블의 기본키인 등급 중복확인
	@PostMapping("/gradingCriteriaRatingOverlapCheck")
	@ResponseBody
	public Map<Object, Object> gradingCriteriaRatingOverlapCheck(@RequestBody String inputGradingCriteriaRating) {
		System.out.println(inputGradingCriteriaRating
				+ " <- inputGradingCriteriaRating   gradingCriteriaRatingOverlapCheck()   CourseManageController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String result = courseManageService.gradingCriteriaRatingOverlapCheck(inputGradingCriteriaRating);
		// 성적기준 테이블에서 해당 등급이 존재하는지 확인
		
		if(result == null) {	// 결과가 없다 : 해당 등급이 존재하지 않는다.
			map.put("result", 1);
			// 성적기준 테이블에 해당 등급이 존재하지 않음 : 사용가능한 등급
		} else {
			map.put("result", 0);
			// 성적기준 테이블에 해당 등급이 존재함 : 이미 등록된 등급
		}
		
		return map;
	}
	
	
	// 관리자 : 성적기준 테이블에 성적기준 추가
	@PostMapping("/addGradingCriteria")
	public String addGradingCriteria(Model model, GradingCriteria criteria) {
		String message = courseManageService.addGradingCriteria(criteria);
		
		String path = "/view/lesson/courseCode/addGradingCriteriaCode";
		// 성적기준 추가에 실패했을 경우 다시 성적기준 추가 폼으로 이동한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 성적기준 추가에 성공했다는 뜻이다.
			
			path = "redirect:/listGradingCriteria";
			// 성적기준 리스트로 리다이렉트한다.
		}
		
		return path;
	}
}
