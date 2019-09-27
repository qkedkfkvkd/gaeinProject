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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.smart_academy.academy_manage.course.manage.service.CourseManageService;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.AcademyRoom;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.Subject;
import com.cafe24.smart_academy.academy_manage.course.service.CourseService;
import com.cafe24.smart_academy.academy_manage.course.vo.Course;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomAssignment;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;

@Controller
public class CourseController {
// 강의를 하는 것에 관한 컨트롤러
// 강좌, 강좌 강의실 배정, 강의 계획서, 강의 시간표 등
	
	@Autowired
	CourseManageService courseManageService;
	// 과목과 강의실에 관한 정보를 가져오기위한 서비스 객체 선언
	
	@Autowired
	CourseService courseService;
	
	
	// 관리자 : 강좌 추가 폼 이동
	@GetMapping("/addCourseInfo")
	public String addCourseInfo(Model model) {
		List<Subject> subjectList = courseManageService.listSubject();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.listAcademyRoom();
		// 전체 강의실 리스트를 가져온다.
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("roomList", roomList);
		
		return "/view/lesson/course/addCourse";
	}
	
	
	// 관리자 : 강좌코드 중복 확인
	@PostMapping("/courseNoOverlapChk")
	@ResponseBody
	public Map<String, Object> courseNoOverlapChk(@RequestBody String inputCourseNo) {
		System.out.println(inputCourseNo
				+ " <- inputCourseNo   courseNoOverlapChk()   CourseController.java");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String result = courseService.courseByCourseNo(inputCourseNo);
		// 강좌 테이블에서 해당 강좌코드가 존재하는지 확인
		
		if(result == null) {	// 결과가 없다 : 해당 강좌코드가 존재하지 않는다.
			map.put("result", 1);
			// 강좌 테이블에 해당 강좌코드값이 존재하지 않음 : 사용가능한 강좌코드
		} else {
			map.put("result", 0);
			// 강좌 테이블에 해당 강좌코드값이 존재함 : 이미 사용 중인 강좌코드
		}
		
		return map;
	}
	
	
	// 관리자 : 강좌강의실배정코드 중복 확인
	@PostMapping("/courseAssignmentNoOverlapChk")
	@ResponseBody
	public Map<String, Object> courseAssignmentNoOverlapChk(@RequestBody String inputCourseAssignNo) {
		System.out.println(inputCourseAssignNo
				+ " <- inputCourseAssignNo   courseAssignmentNoOverlapChk()   CourseController.java");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String result = courseService.CourseRoomAssignBycourseAssignmentNo(inputCourseAssignNo);
		// 강좌강의실배정 테이블에서 해당 배정코드가 존재하는지 확인
		
		if(result == null) {	// 결과가 없다 : 해당 배정코드가 존재하지 않는다.
			map.put("result", 1);
			// 강좌강의실배정 테이블에 해당 배정코드값이 존재하지 않음 : 사용가능한 배정코드
		} else {
			map.put("result", 0);
			// 강좌강의실배정 테이블에 해당 배정코드값이 존재함 : 이미 사용 중인 배정코드
		}
		
		return map;
	}
	
	
	// 관리자 : 강좌코드 추가처리
	@PostMapping("/addCourse")
	public String addCourse(Course course,
					CourseRoomAssignment courseAssign, Model model) {
		String message = courseService.addCourse(course, courseAssign);
		
		String path = "/view/lesson/course/addCourse";
		// 강좌추가에 실패했을 경우 다시 강좌를 추가하는 폼으로 이동하게 초기화한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 강좌추가에 성공했다는 뜻이다.
			path = "redirect:/listSubject";
			// 과목 리스트로 이동한다.
		}
		
		return path;
	}
	
	
	// 관리자 : 강좌배정목록 및 강좌목록 보기
	@GetMapping("/courseList")
	public String courseList(Model model) {
		
		List<Map<String, Object>> courseAssignmentList =
					courseService.courseAssignmentList();
		// 강좌 테이블에서 강좌코드와 강좌명,
		// 과목 테이블에서 과목코드와 과목명,
		// 강의실 테이블에서 강의실코드와 호실번호, 층수,
		// 강사 테이블에서 강사 아이디(회원 아이디)
		// 회원 신상정보 테이블에서 강사명(회원명)을 가지고 온다.
		// 여러 개의 테이블을 조인하므로 맵 형태의 리스트로 가져온다.
		
		
		//List<Map<String, Object>> courseList = courseService.courseList();
		// 강좌 테이블에서 강좌코드와 강좌명, 등록일,
		// 과목 테이블에서 과목코드와 과목명을 가지고 온다
		// 하나 이상의 테이블을 조인하므로 맵 형태의 리스트로 가져온다.
		
		
		List<Subject> subjectList = courseManageService.listSubject();
		// 샐렉트박스에 넣어줄 과목 리스트를 가져온다.
		
		
		model.addAttribute("courseAssignmentList", courseAssignmentList);
		model.addAttribute("courseAssignmentListSize", courseAssignmentList.size());
		// 강좌강의실 배정리스트의 사이즈를 보고 강좌를 뿌려줄 것인지,
		// '등록된 강좌배정목록이 없습니다.'메세지를 뿌려줄 것인지 판단한다.
		
		//model.addAttribute("courseList", courseList);
		//model.addAttribute("courseListSize", courseList.size());
		// 강좌리스트의 사이즈를 보고 강좌를 뿌려줄 것인지,
		// '등록된 강좌목록이 없습니다.'메세지를 뿌려줄 것인지 판단한다.
		
		model.addAttribute("subjectList", subjectList);
		
		return "/view/lesson/course/listCourse";
	}
	
	
	// 관리자 : 과목 리스트에서 과목코드 선택시 자동으로
	//			해당 과목코드에 맞는 강좌 나오게 하기
	@PostMapping("/courseSelect")
	@ResponseBody
	public List<Course> courseSelect(@RequestBody String subjectNo) {
		System.out.println(subjectNo + "<- subjectNo   courseSelect()   CourseController.java");
		
		//Map<Object, Object> map = new HashMap<Object, Object>();
		
		List<Course> courseList =
				courseService.courseListBySubjectNo(subjectNo);
		// 해당 과목코드로 강좌 리스트 가져오기
		
		return courseList;
	}
	
	
	// 관리자 : 선택한 과목코드 혹은 과목코드와 강좌코드로 강좌강의실배정 리스트 검색
	@PostMapping("/searchCourseList")
	public String searchCourseList(CourseRoomSearchVO searchVO, Model model) {
		
		List<Map<String, Object>> courseAssignmentList =
				courseService.courseAssignmentList(searchVO);
		// 선택한 과목코드 혹은 과목코드와 강좌코드로 강좌 리스트 검색
		// 강좌 테이블에서 강좌코드와 강좌명,
		// 과목 테이블에서 과목코드와 과목명,
		// 강의실 테이블에서 강의실코드와 호실번호, 층수,
		// 강사 테이블에서 강사 아이디(회원 아이디)
		// 회원 신상정보 테이블에서 강사명(회원명)을 가지고 온다.
		// 여러 개의 테이블을 조인하므로 맵 형태의 리스트로 가져온다.
		
		
		List<Subject> subjectList = courseManageService.listSubject();
		// 샐렉트박스에 넣어줄 과목 리스트를 가져온다.
		
		
		model.addAttribute("courseAssignmentList", courseAssignmentList);
		model.addAttribute("courseAssignmentListSize", courseAssignmentList.size());
		// 강좌강의실 배정리스트의 사이즈를 보고 강좌를 뿌려줄 것인지,
		// '등록된 강좌배정목록이 없습니다.'메세지를 뿌려줄 것인지 판단한다.
		
		model.addAttribute("subjectList", subjectList);
		
		return "/view/lesson/course/listCourse";
	}
	
	
	// 관리자 : 강의실 혹은 강사가 배정이 안된 강좌목록
	@GetMapping("/courseNotAssignment")
	public String courseNotAssignment(Model model) {
		
		List<Map<String, Object>> courseNotAssignment =
				courseService.courseNotAssignment();
		
		return "/view/lesson/course/listCourseNotAssignment";
	}
	
	
	
	
	
	
	
	
	
	
}
