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

import com.cafe24.smart_academy.academy_manage.course.manage.service.CourseManageService;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.AcademyRoom;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.GradingCriteria;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.Subject;
import com.cafe24.smart_academy.academy_manage.course.service.CourseService;
import com.cafe24.smart_academy.academy_manage.course.vo.Course;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.courseandscore.service.CourseAndScoreService;

@Controller
public class CourseManageController {
// 과목 및 강좌 등록, 성적기준 관리 등
	
	@Autowired
	private CourseManageService courseManageService;
	// 강의실, 성적기준, 과목, 교재 관리 서비스 객체
	
	@Autowired
	private CourseService courseService;
	// 강좌, 휴보강, 강의계획서, 강의평가, 강좌강의실배정, 강좌시간표 관리 서비스 객체
	
	@Autowired
	private CourseAndScoreService courseAndScoreService;
	// 수강신청 및 성적 결과 관리 서비스 객체
	
	
	// 관리자 : 과목 리스트로 이동
	@GetMapping("/subjectList")
	public String subjectList(Model model) {
		
		List<Subject> subjectList = courseManageService.subjectList();
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("subjectListSize", subjectList.size());
		return "/view/lesson/courseCode/listSubject";
	}
	
	
	// 관리자 : 과목을 추가하는 폼으로 이동
	@GetMapping("/addSubject")
	public String addSubject() {
		return "/view/lesson/courseCode/addSubject";
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
		
		String path = "/view/lesson/courseCode/addSubject";
		// 과목추가에 실패했을 경우 다시 과목을 추가하는 폼으로 이동하게 초기화한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 과목추가에 성공했다는 뜻이다.
			path = "redirect:/subjectList";
			// 과목 리스트로 이동한다.
		}
		
		return path;
	}
	
	
	// 관리자 : 과목 수정 화면
	@GetMapping("/updateSubject")
	public String updateSubject(CourseRoomSearchVO searchVO, Model model) {
		
		Subject subject = courseManageService.detailSubjectBySubjectNo(searchVO.getSubjectNo());
		// 과목 수정을 위해 해당 과목코드의 모든 데이터 가져오기
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subjectNo", searchVO.getSubjectNo());
		// 해당 과목 코드를 참조하는 모든 강좌 리스트를 가져오기 위해 검색 키워드를 넣어준다.
		
		List<Map<String, Object>> courseList = courseService.courseOneOrList(map);
		// 해당 과목코드를 참조하는 모든 강좌 리스트를 가져온다.
		
		//List<Map<String, Object>> courseAssignmentList =
		//		courseService.courseAssignmentOneOrList(searchVO);
		// 강좌 테이블에서 강좌코드와 강좌명,
		// 과목 테이블에서 과목코드와 과목명,
		// 강의실 테이블에서 강의실코드와 호실번호, 층수,
		// 강사 테이블에서 강사 아이디(회원 아이디)
		// 회원 신상정보 테이블에서 강사명(회원명)을 가지고 온다.
		// 여러 개의 테이블을 조인하므로 맵 형태의 리스트로 가져온다.
		
			
		//model.addAttribute("courseAssignmentList", courseAssignmentList);
		//model.addAttribute("courseAssignmentListSize", courseAssignmentList.size());
		// 강좌강의실 배정리스트의 사이즈를 보고 강좌를 뿌려줄 것인지,
		// '등록된 강좌배정목록이 없습니다.'메세지를 뿌려줄 것인지 판단한다.
		
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseListSize", courseList.size());
		// 강좌 리스트의 사이즈를 보고 강좌를 뿌려줄 것인지,
		// '등록된 강좌목록이 없습니다.'메세지를 뿌려줄 것인지 판단한다.
		
		model.addAttribute("subject", subject);
		
		return "/view/lesson/courseCode/detailSubject";
	}
	
	
	// 관리자 : 과목 수정 처리
	@PostMapping("/updateSubject")
	public String updateSubject(Subject subject, Model model,
			RedirectAttributes redirectAttributes) {
		String message = courseManageService.updateSubject(subject);
		
		String path = "redirect:/subjectList";
		// 과목 수정에 성공했을 경우 과목리스트로 이동하게 초기화한다.
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 과목 수정에 실패했다는 뜻이다.
			
			System.out.println("과목 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("subjectNo", subject.getSubjectNo());
			// 과목 상세 페이지로 리다이렉트하면서 과목코드를 넘겨준다.
			
			path = "redirect:/updateSubject";
			// 과목 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자 : 과목 삭제 처리
	@GetMapping("/deleteSubject")
	public String deleteSubject(@RequestParam(value = "subjectNo")String subjectNo) {
		System.out.println(subjectNo + " <- subjectNo   deleteSubject()   CourseManageController.java");
		String message = courseManageService.deleteSubject(subjectNo);
		// 해당 과목 삭제 쿼리 실행 후 메세지 반환
		
		System.out.println(message + " <- message   deleteSubject()   CourseManageController.java");
		
		return "redirect:/subjectList";
	}
	
	
	
	// 관리자 : 강의실 리스트로 이동
	@GetMapping("/academyRoomList")
	public String academyRoomList(Model model) {
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
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
		return "/view/lesson/courseCode/listAcademyRoom";
	}
	
	
	// 관리자 : 강의실 리스트에서 층수 선택시 자동으로
	//			해당 층수에 맞는 실용도 나오게 하기
	@PostMapping("/roomUsageSelect")
	@ResponseBody
	public List<AcademyRoom> roomUsageSelect(@RequestBody String roomFloor) {
		System.out.println(roomFloor + "<- roomFloor   roomUsageSelect()   CourseManageController.java");
		
		//Map<Object, Object> map = new HashMap<Object, Object>();
		
		List<AcademyRoom> roomUsageList =
				courseManageService.roomUsageListByRoomFloor(roomFloor);
		// 해당 강의실 층수로 강의실 실용도 리스트 가져오기
		
		return roomUsageList;
	}
	
	
	// 관리자 : 강의실 목록에서 강의실 검색
	@PostMapping("/searchAcademyRoom")
	public String searchAcademyRoom(AcademyRoom room, Model model) {
		System.out.println("강의실 검색 메소드 진입");
		
		List<AcademyRoom> roomList =
				courseManageService.academyRoomList(room);
		// 선택한 강의실 층수와 실용도로 강의실 리스트를 검색한다.
		
		List<AcademyRoom> allRoomFloorList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가지고 온다. (여기서는 층수만 사용한다.)
		
		List<Integer> roomFloorTempList = new ArrayList<Integer>();
		// 강의실 층수를 저장할 리스트 (중복포함)
		
		// 강의실 층수 중복 제거를 위한 반복문
		for(int i=0; i<allRoomFloorList.size(); i++) {
			AcademyRoom imsyroom = allRoomFloorList.get(i);
			// 강의실 객체 한개씩 가져온다.
			
			int roomFloor = imsyroom.getRoomFloor();
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
		
		return "/view/lesson/courseCode/listAcademyRoom";
	}
	
	
	// 관리자 : 강의실을 추가하는 폼으로 이동
	@GetMapping("/addAcademyRoom")
	public String addAcademyRoom() {
		return "/view/lesson/courseCode/addAcademyRoom";
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
		
		String path = "/view/lesson/courseCode/addAcademyRoom";
		// 강의실 추가에 실패했을 경우 다시 강의실 추가 폼으로 이동한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 강의실 추가에 성공했다는 뜻이다.
			
			path = "redirect:/academyRoomList";
			// 강의실 리스트로 리다이렉트한다.
		}
		
		return path;
	}
	
	
	// 관리자 : 강의실 상세 보기
	@GetMapping("/updateAcademyRoom")
	public String updateAcademyRoom(CourseRoomSearchVO searchVO,
					Model model) {
		
		AcademyRoom room = courseManageService.detailRoomByRoomNo(searchVO.getRoomNo());
		// 강의실 수정을 위해 해당 강의실코드의 모든 데이터 가져오기
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roomNo", searchVO.getRoomNo());
		// 해당 강의실을 참조하는 강좌강의실 배정 목록을 가져오기위해 검색키워드를 넣어준다.
		
		List<Map<String, Object>> courseAssignmentList =
				courseService.courseNotAssignTeacherOneOrList(map);
		// 해당 강의실코드를 참조하는 모든 강좌강의실 배정목록을 가져온다.
		
		//List<Map<String, Object>> courseAssignmentList =
		//		courseService.courseAssignmentOneOrList(searchVO);
		// 강좌 테이블에서 강좌코드와 강좌명,
		// 과목 테이블에서 과목코드와 과목명,
		// 강의실 테이블에서 강의실코드와 호실번호, 층수,
		// 강사 테이블에서 강사 아이디(회원 아이디)
		// 회원 신상정보 테이블에서 강사명(회원명)을 가지고 온다.
		// 여러 개의 테이블을 조인하므로 맵 형태의 리스트로 가져온다.
		
			
		model.addAttribute("courseAssignmentList", courseAssignmentList);
		model.addAttribute("courseAssignmentListSize", courseAssignmentList.size());
		// 강좌강의실 배정리스트의 사이즈를 보고 강좌를 뿌려줄 것인지,
		// '등록된 강좌배정목록이 없습니다.'메세지를 뿌려줄 것인지 판단한다.
		
		model.addAttribute("room", room);
		
		return "/view/lesson/courseCode/detailAcademyRoom";
	}
	
	
	// 관리자 : 강의실 수정 처리
	@PostMapping("/updateAcademyRoom")
	public String updateAcademyRoom(AcademyRoom room, Model model,
			RedirectAttributes redirectAttributes) {
		
		String message = courseManageService.updateAcademyRoom(room);
		
		String path = "redirect:/academyRoomList";
		// 강의실 수정에 성공했을 경우 강의실 리스트로 이동하게 초기화한다.
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 강의실 수정에 실패했다는 뜻이다.
			
			System.out.println("강의실 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("roomNo", room.getRoomNo());
			// 강의실 상세 페이지로 리다이렉트하면서 강의실코드를 넘겨준다.
			
			path = "redirect:/updateAcademyRoom";
			// 과목 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자 : 강의실 삭제 처리
	@GetMapping("/deleteAcademyRoom")
	public String deleteAcademyRoom(@RequestParam(value = "roomNo") String roomNo) {
		System.out.println(roomNo + " <- roomNo   deleteAcademyRoom()   CourseManageController.java");
		String message = courseManageService.deleteAcademyRoom(roomNo);
		// 해당 강의실 삭제 처리 후 메세지 반환
		
		System.out.println(message + " <- message   deleteAcademyRoom()   CourseManageController.java");
		
		return "redirect:/academyRoomList";
	}
	
	
	
	// 관리자 : 성적기준 리스트로 이동
	@GetMapping("/gradingCriteriaList")
	public String gradingCriteriaList(Model model) {
		
		List<GradingCriteria> gradingCriteriaList =
				courseManageService.gradingCriteriaList();
		
		model.addAttribute("gradingCriteriaList", gradingCriteriaList);
		model.addAttribute("gradingCriteriaListSize", gradingCriteriaList.size());
		return "view/lesson/courseCode/listGradingCriteria";
	}
	
	
	// 관리자 : 성적기준을 선택하여 검색하기
	@PostMapping("/searchGradingCriteria")
	public String searchGradingCriteria(
			 @RequestParam(value = "gradingCriteriaRating") String gradingCriteriaRating
			,Model model) {
		
		List<GradingCriteria> gradingCriteriaList =
				courseManageService.gradingCriteriaList(gradingCriteriaRating);
		// 선택한 등급(성적기준테이블 기본키)로 검색한 결과를 가지고 온다.
		
		model.addAttribute("gradingCriteriaList", gradingCriteriaList);
		// 결과는 하나의 객체만 나오지만 listGradingCriteriaCode.html에서는 무조건
		// 결과를 리스트로 뿌려주므로 어찌됬든 리스트로 받아서 뿌려준다.
		
		return "view/lesson/courseCode/listGradingCriteria";
	}
	
	
	// 관리자 : 성적기준을 추가하는 폼으로 이동
	@GetMapping("/addGradingCriteria")
	public String addGradingCriteria() {
		return "/view/lesson/courseCode/addGradingCriteria";
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
	
	
	// 관리자 : 성적기준 테이블에 성적기준 추가 처리
	@PostMapping("/addGradingCriteria")
	public String addGradingCriteria(Model model, GradingCriteria criteria) {
		String message = courseManageService.addGradingCriteria(criteria);
		
		String path = "/view/lesson/courseCode/addGradingCriteria";
		// 성적기준 추가에 실패했을 경우 다시 성적기준 추가 폼으로 이동한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 성적기준 추가에 성공했다는 뜻이다.
			
			path = "redirect:/gradingCriteriaList";
			// 성적기준 리스트로 리다이렉트한다.
		}
		
		return path;
	}
	
	
	// 관리자 : 성적기준 테이블 상세 보기
	@GetMapping("/updateGradingCriteria")
	public String updateGradingCriteria(
			 /*@RequestParam(value = "gradingCriteriaRating") String gradingCriteriaRating*/
			 CourseRoomSearchVO searchVO
			,Model model) {
		System.out.println(searchVO.getGradingCriteriaRating()
				+ " <- gradingCriteriaRating   updateGradingCriteria()   CourseManageController.java");
		
		GradingCriteria gradingCriteria =
				courseManageService.detailGradingCriteriaByGradingCriteriaRating(searchVO.getGradingCriteriaRating());
		// 성적기준의 상세화면을 보고 수정하기 위해 성적기준 테이블의 기본키가 되는 등급으로
		// 해당 성적 기준의 모든 정보를 가지고 온다.
		
		//Map<String, Object> map = new HashMap<String, Object>();
		// totalGradeResultList() 메소드의 매개변수가 Map 이므로 검색내용을 저장하기 위한 맵 객체 선언
		
		//map.put("gradingCriteriaRating", gradingCriteriaRating);
		// 검색 내용 저장
		
		
		List<Map<String, Object>> gradeReferenceInputScoreList =
				courseAndScoreService.totalGradeResultOneOrList(searchVO);
		// 해당 등급을 참조하는 모든 성적 리스트 가져오기
		
		model.addAttribute("gradingCriteria", gradingCriteria);
		model.addAttribute("gradeReferenceInputScoreList", gradeReferenceInputScoreList);
		model.addAttribute("gradeReferenceInputScoreListSize", gradeReferenceInputScoreList.size());
		
		return "/view/lesson/courseCode/detailGradingCriteria";
	}
	
	
	// 관리자 : 성적기준 수정 처리
	@PostMapping("/updateGradingCriteria")
	public String updateGradingCriteria(GradingCriteria gradingCriteria,
			Model model, RedirectAttributes redirectAttributes) {
		
		String message = courseManageService.updateGradingCriteria(gradingCriteria);
		// 성적기준 수정 처리 후 메세지 반환
		
		System.out.println(message + " <- message   updateGradingCriteria()   CourseManageController.java");
		
		String path = "redirect:/gradingCriteriaList";
		// 성적기준 수정에 성공했을 경우 성적기준 리스트로 이동하게 초기화한다.
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 성적기준 수정에 실패했다는 뜻이다.
			
			System.out.println("성적기준 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("gradingCriteriaRating",
					gradingCriteria.getGradingCriteriaRating());
			// 성적기준 상세 페이지로 리다이렉트하면서 성적기준테이블의 기본키인 등급을 넘겨준다.
			
			path = "redirect:/updateGradingCriteria";
			// 성적기준 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자 : 성적기준 삭제 처리
	@GetMapping("/deleteGradingCriteria")
	public String deleteGradingCriteria(
			@RequestParam(value = "gradingCriteriaRating") String gradingCriteriaRating) {
		System.out.println(gradingCriteriaRating
				+ " <- gradingCriteriaRating   deleteGradingCriteria()   CourseManageController.java");
		
		String message = courseManageService.deleteGradingCriteria(gradingCriteriaRating);
		// 해당 성적기준 삭제 처리 후 메세지 반환
		
		System.out.println(message + " <- message   deleteGradingCriteria()   CourseManageController.java");
		
		return "redirect:/gradingCriteriaList";
		// 성적기준 삭제 처리 후 성적기준 리스트 이동
	}
}
