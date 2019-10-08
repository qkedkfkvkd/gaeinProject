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
import com.cafe24.smart_academy.academy_manage.course.vo.Course;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomAssignment;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.member.service.MemberService;
import com.cafe24.smart_academy.academy_manage.member.service.TeacherInfoService;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberSearchVO;
import com.cafe24.smart_academy.academy_manage.member.vo.Teacher;

@Controller
public class CourseController {
// 강의를 하는 것에 관한 컨트롤러
// 강좌, 강좌 강의실 배정, 강의 계획서, 강의 시간표 등
	
	@Autowired
	CourseManageService courseManageService;
	// 과목과 강의실에 관한 정보를 가져오기위한 서비스 객체 선언
	
	@Autowired
	CourseService courseService;
	// 강좌 및 강좌강의실 배정 정보 관리
	
	@Autowired
	MemberService memberService;
	// 학생 및 강사의 공통된 정보를 관리
	
	@Autowired
	TeacherInfoService teacherInfoService;
	// 강사 정보 관리 
	
	
	// 관리자 : 강좌 추가 폼 이동
	@GetMapping("/addCourse")
	public String addCourse(Model model) {
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
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
	
	
	// 관리자 : 강좌코드 추가처리
	@PostMapping("/addCourse")
	public String addCourse(Course course, Model model) {
		String message = courseService.addCourse(course);
		
		String path = "/view/lesson/course/addCourse";
		// 강좌추가에 실패했을 경우 다시 강좌를 추가하는 폼으로 이동하게 초기화한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 강좌추가에 성공했다는 뜻이다.
			path = "redirect:/courseList";
			// 강좌 목록으로 이동한다.
		}
		
		return path;
	}
	
	
	// 관리자 : 강좌 목록 보기
	@GetMapping("/courseList")
	public String courseList(Model model) {
		
		List<Map<String, Object>> courseList = courseService.courseOneOrList();
		// 모든 강좌 목록을 가져온다.
		
		model.addAttribute("courseList", courseList);
		// 화면에 보여줄 강좌목록을 모델에 넣는다.
		
		model.addAttribute("courseListSize", courseList.size());
		
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
	
	
	// 관리자 : 강좌 목록에서 과목명 혹은 강좌명으로 검색
	@GetMapping("/searchCourseList")
	public String searchCourseList(Course course, Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subjectNo", course.getSubjectNo());
		map.put("courseName", course.getCourseName());
		// 강좌리스트의 검색 키워드값을 넣어준다.
		
		List<Map<String, Object>> courseList = courseService.courseOneOrList(map);
		// 선택한 과목코드 혹은 강좌명으로 검색된 강좌 리스트 가져오기
		
		model.addAttribute("courseList", courseList);
		// 화면에 보여줄 강좌목록을 모델에 넣는다.
		
		return "/view/lesson/course/listCourse";
	}
	
	
	// 관리자 : 강좌 상세 화면 이동
	@GetMapping("/updateCourse")
	public String updateCourse(
			Course course, Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseNo", course.getCourseNo());
		// 강좌 테이블의 검색 키워드를 넣어준다.
		// 해당 강좌코드를 참조하는 강좌 강의실목록 상세정보 리스트의 검색 키워드를 넣어준다.
		
		List<Map<String, Object>> oneMap = courseService.courseOneOrList(map);
		// 해당 강좌코드로 강좌 상세정보 가져오기
		// 리턴형태를 맞춰주기위해 리스트로 받았지만 강좌테이블의 기본키인 강좌코드로
		// 검색해서 가져오므로 한개의 객체만 나올 것이다.
		
		
		System.out.println(oneMap.toString()
				+ " <- oneMap.toString()   updateCourse()   CourseController.java");
		
		System.out.println(oneMap.size()
				+ " <- oneMap.size()   updateCourse()   CourseController.java");
		
		
		Map<String, Object> courseInfo = oneMap.get(0);
		// 한개의 객체밖에 없으므로 제일 처음에 위치한 객체만 가져오면 된다.
		
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 샐렉트박스에 넣어줄 과목 리스트를 가져온다.
		
		
		List<Map<String, Object>> courseAssignList = 
				courseService.courseNotAssignTeacherOneOrList(map);
		// 검색키워드를 매개변수로 넣어서 해당 강좌코드를 참조하는 강좌강의실 배정목록을 가져온다.
		
		System.out.println(courseAssignList.toString()
				+ " <- courseAssignList.toString()   updateCourse()   CourseController.java");
		
		List<Map<String, Object>> courseTeacherList =
				courseService.courseAssignTeacherList(course);
		// 해당 강좌 코드를 참조하는 강좌 강사 리스트 가져오기
		
		
		model.addAttribute("course", courseInfo);
		// 화면에 보여줄 해당 강좌 상세정보
		
		model.addAttribute("subjectList", subjectList);
		// 샐렉트박스에 보여줄 과목 리스트
		
		model.addAttribute("courseAssignList", courseAssignList);
		// 화면에 보여줄 강좌 강의실 배정 리스트
		
		model.addAttribute("courseAssignListSize", courseAssignList.size());
		// 리스트의 존재 여부를 판단할 강좌강의실 배정 리스트 사이즈
		
		model.addAttribute("courseTeacherList", courseTeacherList);
		// 화면에 보여줄 강좌 강사 리스트
		
		model.addAttribute("courseTeacherListSize", courseTeacherList.size());
		// 리스트의 존재 여부를 판단할 강좌 강사 리스트 사이즈
		
		return "/view/lesson/course/detailCourse";
	}
	
	
	// 관리자 : 강좌 수정 처리
	@PostMapping("/updateCourse")
	public String updateCourse(
			 Course course
			,Model model
			,RedirectAttributes redirectAttributes) {
		int result = courseService.updateCourse(course);
		// 해당 강좌 수정 처리 후 수정 처리된 숫자
		
		String path = "redirect:/courseList";
		// 강좌 수정에 성공했을 경우 강좌 리스트로 이동한다.
		
		if(course.getCourseIsChanged().equals("유") && result == 0) {
			// 강좌의 변경사항이 있으면
			// 반환되는 result값은 1이 나와야한다.
			// 만약 강좌 변경사항이 있음에도
			// 반환된 result값이 0이라면 강좌 수정에 실패했다는 뜻이다.
			
			System.out.println("강좌 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("courseNo", course.getCourseNo());
			// 강좌 상세 페이지로 리다이렉트하면서 강좌코드를 넘겨준다.
			
			path = "redirect:/updateCourse";
			// 강좌 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자 : 강좌 삭제 처리
	@GetMapping("/deleteCourse")
	public String deleteCourse(
			 @RequestParam(value = "courseNo") String courseNo) {
		System.out.println(courseNo + " <- courseNo   deleteCourse()   CourseController.java");
		String message = courseService.deleteCourse(courseNo);
		// 해당 강좌 삭제 처리 후 메세지 반환
		
		System.out.println(message + " <- message   deleteCourse()   CourseController.java");
		
		return "redirect:/courseList";
	}
	
	
	
	
	
	// 관리자 : 강사가 배정안된 강좌 리스트
	@GetMapping("/courseNotAssignTeacherList")
	public String courseNotAssignTeacherList(Model model) {
		
		List<Map<String, Object>> courseNotAssignList =
				courseService.courseNotAssignmentTeacherSimpleList();
		// 강좌 테이블과 강사 테이블을 서로 조인하여
		// 강사가 배정되지 않은 강좌 목록을 가지고 온다. (간단한 정보만 가져온다.)
		// 강좌코드, 강좌명, 과목명, 변경여부, 강좌등록일
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 샐렉트박스에 넣어줄 과목 리스트를 가져온다.
		
		
		model.addAttribute("courseNotAssignList", courseNotAssignList);
		// 강사가 배정되지않은 강좌 리스트
		
		model.addAttribute("courseNotAssignListSize", courseNotAssignList.size());
		// 강사가 배정되지않은 강좌의 존재여부를 판단할 리스트 사이즈
		
		model.addAttribute("subjectList", subjectList);
		// 샐랙트박스에 넣어줄 과목리스트
		
		return "/view/lesson/course/listCourseNotAssignTeacher";
	}
	
	
	// 강사가 배정안된 강좌 검색결과 리스트
	@GetMapping("/searchCourseNotAssignTeacherList")
	public String searchCourseNotAssignTeacherList(Course course, Model model) {
		
		System.out.println(course.getSubjectNo()
				+ " <- course.getSubjectNo()   searchCourseNotAssignTeacherList()   CourseController.java");
		
		System.out.println(course.getCourseName()
				+ " <- course.getCourseName()   searchCourseNotAssignTeacherList()   CourseController.java");
		
		List<Map<String, Object>> courseNotAssignList =
				courseService.courseNotAssignmentTeacherSimpleList(course);
		// 강좌 테이블과 강사 테이블을 서로 조인하여
		// 강사가 배정되지 않은 강좌 목록을 가지고 온다. (간단한 정보만 가져온다.)
		// 강좌코드, 강좌명, 과목명, 변경여부, 강좌등록일
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 샐렉트박스에 넣어줄 과목 리스트를 가져온다.
		
		
		model.addAttribute("courseNotAssignList", courseNotAssignList);
		// 강사가 배정되지않은 강좌 리스트
		
		model.addAttribute("courseNotAssignListSize", courseNotAssignList.size());
		// 강사가 배정되지않은 강좌의 존재여부를 판단할 리스트 사이즈
		
		model.addAttribute("subjectList", subjectList);
		// 샐랙트박스에 넣어줄 과목리스트
		
		return "/view/lesson/course/listCourseNotAssignTeacher";
	}
	
	
	// 관리자 : 강사 배정안된 강좌 강사 배정폼 이동
	@GetMapping("/updateCourseAssignTeacher")
	public String updateCourseAssignTeacher(
			 Teacher teacher
			,MemberSearchVO memberSearchVO
			,Model model) {
		
		List<Map<String, Object>> courseList = courseService.courseOneOrList();
		// 전체 강좌 리스트를 가져온다.
		
		List<Map<String, Object>> teacherList = memberService.memberInfoList(memberSearchVO);
		// 샐랙트박스에 넣어줄 강사 리스트
		
		model.addAttribute("courseList", courseList);
		// 샐랙트박스에 넣어줄 강좌 리스트를 넣어준다.
		
		model.addAttribute("teacherList", teacherList);
		// 샐랙트박스에 넣어줄 강사 리스트를 넣어준다.
		
		if(teacher.getCourseNo() != null) {
			// 강사 배정안된 강좌리스트에서 강사배정버튼을 눌러 이동한 것이라면
			
			model.addAttribute("courseNo", teacher.getCourseNo());
			// 모델객체에 강좌코드를 넣어준다.
		}
		
		return "/view/lesson/course/addCourseAssignTeacher";
	}
	
	
	// 강좌 강사 배정 처리
	@PostMapping("/updateCourseAssignTeacher")
	public String updateCourseAssignTeacher(
			 Teacher teacher
			,Model model
			,RedirectAttributes redirectAttributes) {
		
		String message = teacherInfoService.updateTeacher(teacher);
		// 강사 테이블에 강좌 강사 추가처리 후 메세지 반환
		
		String path = "/view/lesson/course/addCourseAssignTeacher";
		// 강사테이블에 강좌 강사 배정 추가에 실패했을 경우
		// 다시 강좌 강사 배정 처리하는 폼으로 이동하게 초기화한다.
		
		if(message != null) {
			// 리턴받은 메세지에 값이 있다면 강좌 강사 배정추가에 실패했다는 뜻이다.
			
			redirectAttributes.addAttribute("memberLevel", "강사");
			// 회원 로그인 테이블에서 권한이 강사인 사람들만 가져오기 위해서 검색 키워드를 넣어준다.
			
		} else {
			// 리턴받은 메세지가 널이라면 강좌 강사 배정추가에 성공했다는 뜻이다.
			path = "redirect:/courseAssignTeacherList";
			// 강사가 배정된 강좌 리스트로 이동한다.
		} 
		
		return path;
	}
	
	
	
	
	
	// 관리자 : 강사가 배정된 강좌 리스트
	@GetMapping("/courseAssignTeacherList")
	public String courseAssignTeacherList(Model model) {
		
		List<Map<String, Object>> courseTeacherList = 
				courseService.courseAssignTeacherList();
		// 강사가 배정된 강좌리스트 가져오기
		
		model.addAttribute("courseTeacherList", courseTeacherList);
		// 화면에 뿌려줄 강좌 강사 배정 리스트 
		
		return "/view/lesson/course/listCourseTeacher";
	}
	
	
	// 관리자 : 강사가 배정된 강좌 검색결과 리스트
	@GetMapping("/searchCourseAssignTeacherList")
	public String searchCourseAssignTeacherList(Course course, Model model) {
		
		System.out.println(course.getSubjectNo()
				+ " <- course.getSubjectNo()   searchCourseAssignTeacherList   CourseController.java");
		
		System.out.println(course.getCourseName()
				+ " <- course.getCourseName()   searchCourseAssignTeacherList   CourseController.java");
		
		
		List<Map<String, Object>> searchCourseTeacherList =
				courseService.courseAssignTeacherList(course);
		// 입력한 검색 키워드로 강좌 검색결과리스트 가져오기
		
		model.addAttribute("courseTeacherList", searchCourseTeacherList);
		
		return "/view/lesson/course/listCourseTeacher";
	}
	
	
	
	
	
	
	// 관리자 : 강좌강의실 배정 추가 폼 이동
	@GetMapping("/addCourseAssign")
	public String addCourseAssign(Model model) {
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("roomList", roomList);
		
		return "/view/lesson/courseAssign/addCourseAssign";
	}
	
	
	// 관리자 : 강좌강의실배정코드 중복 확인
	@PostMapping("/courseAssignmentNoOverlapChk")
	@ResponseBody
	public Map<String, Object> courseAssignmentNoOverlapChk(@RequestBody String inputCourseAssignNo) {
		System.out.println(inputCourseAssignNo
				+ " <- inputCourseAssignNo   courseAssignmentNoOverlapChk()   CourseController.java");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String result = courseService.courseRoomAssignByCourseAssignmentNo(inputCourseAssignNo);
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
	
	
	// 관리자 : 강좌강의실 배정 추가처리
	@PostMapping("/addCourseAssign")
	public String addCourseAssign(CourseRoomAssignment courseAssign, Model model) {
		String message = courseService.addCourseAssign(courseAssign);
		
		String path = "/view/lesson/courseAssign/addCourseAssign";
		// 강좌강의실 배정코드 추가에 실패했을 경우 다시 강좌를 추가하는 폼으로 이동한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 강좌강의실 배정 추가에 성공했다는 뜻이다.
			
			path = "redirect:/courseAssignList";
			// 강사가 배정된 강좌강의실 리스트로 이동
		}
		
		return path;
	}
	
	
	
	
	
	// 관리자 : 강사가 배정이 안된 강좌강의실 배정 리스트
	@GetMapping("/notTeacherCourseAssignList")
	public String notTeacherCourseAssignList(Model model) {
		
		List<Map<String, Object>> courseNotAssignList =
				courseService.courseNotAssignmentTeacherSimpleList();
		// 강좌 테이블과 강사 테이블을 서로 조인하여
		// 강사가 배정되지 않은 강좌 목록을 가지고 온다. (간단한 정보만 가져온다.)
		// 강좌코드, 강좌명, 과목명, 강좌등록일
		
		List<String> courseNoList = new ArrayList<String>();
		// 강사가 배정되지 않은 강좌의 상세 정보를 가지고 오기 위해서
		// 해당 강좌의 기본키인 강좌코드를 담을 리스트 선언
		
		// 가지고온 강좌리스트를 넣어서 강좌 코드만 얻어내는 반복문
		for(int i=0; i<courseNotAssignList.size(); i++) {
			String courseNo = (String)courseNotAssignList.get(i).get("courseNo");
			// 맵에 들어있는 객체들 중 강좌 코드만 가져온다.
			
			System.out.println(courseNo + " <- courseNo courseNotAssignmentTeacherList() for   CourseController.java");
			
			courseNoList.add(courseNo);
			// 가져온강좌 코드를 강좌코드 리스트에 넣어준다.
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseNoList", courseNoList);
		// 강사가 배정이 안된 강좌목록 상세정보 리스트의 검색 키워드와 값을 넣어준다.
		
		List<Map<String, Object>> courseNotAssignmentTeacherList = 
				courseService.courseNotAssignTeacherOneOrList(map);
		// 검색키워드를 매개변수로 넣어서 강사가 배정이 안된 강좌목록을 가져온다.
		
		System.out.println(courseNotAssignmentTeacherList.toString()
				+ " <- courseNotAssignmentTeacherList.toString()   "
				+ "courseNotAssignmentTeacherList()   CourseController.java");
		
		System.out.println(courseNotAssignmentTeacherList.size()
				+ " <- courseNotAssignmentTeacherList.size()   "
				+ "courseNotAssignmentTeacherList()   CourseController.java");
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 샐렉트박스에 넣어줄 과목 리스트를 가져온다.
		
		model.addAttribute("courseNotAssignmentTeacherList",
				courseNotAssignmentTeacherList);
		model.addAttribute("courseNotAssignmentTeacherListSize",
				courseNotAssignmentTeacherList.size());
		// 강좌 리스트의 사이즈를 보고 강좌를 뿌려줄 것인지,
		// 강사가 배정되지 않은 강좌 목록이 없다는 메세지를 뿌려줄 것인지 판단한다.
		
		model.addAttribute("subjectList", subjectList);
		
		return "/view/lesson/courseAssign/listNotTeacherCourseAssign";
	}
	
	
	// 관리자 : 강사가 배정이 안된 강좌배정에서 과목코드나 강좌명으로 검색한 결과 리스트
	@GetMapping("/searchNotTeacherCourseAssignList")
	public String searchNotTeacherCourseAssignList(Course course, Model model) {
				  
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subjectNo", course.getSubjectNo());
		map.put("courseName", course.getCourseName());
		// 강사가 배정이 안된 강좌 리스트의 검색 키워드와 값을 넣어준다.
		
		List<Map<String, Object>> courseNotAssignmentTeacherList = 
				courseService.courseNotAssignTeacherOneOrList(map);
		// 검색키워드를 매개변수로 넣어서 강사가 배정이 안된 강좌목록을 가져온다.
		
		System.out.println(courseNotAssignmentTeacherList.toString()
				+ " <- courseNotAssignmentTeacherList.toString()   "
				+ "courseNotAssignmentTeacherList()   CourseController.java");
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 샐렉트박스에 넣어줄 과목 리스트를 가져온다.
		
		model.addAttribute("courseNotAssignmentTeacherList",
				courseNotAssignmentTeacherList);
		model.addAttribute("courseNotAssignmentTeacherListSize",
				courseNotAssignmentTeacherList.size());
		// 강좌 리스트의 사이즈를 보고 강좌를 뿌려줄 것인지,
		// 강사가 배정되지 않은 강좌 목록이 없다는 메세지를 뿌려줄 것인지 판단한다.
		
		model.addAttribute("subjectList", subjectList);
		// 검색하기위해 사용될 과목리스트를 넣어준다.
		
		return "/view/lesson/courseAssign/listNotTeacherCourseAssign";
	}
	
	
	// 관리자 : 강사가 배정이 안된 강좌 상세보기
	@GetMapping("/updateCourseNotAssignTeacher")
	public String updateCourseNotAssignTeacher(
			 @RequestParam(value = "courseAssignmentNo") String courseAssignmentNo
			,@RequestParam(value = "courseNo") String courseNo
			,Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseAssignmentNo", courseAssignmentNo);
		
		List<Map<String, Object>> oneMap =
				courseService.courseNotAssignTeacherOneOrList(map);
		// 강좌 강의실 배정 코드로 강사가 배정이 안된 강좌의 상세정보 가져온다.
		// 한개의 객체만 나온다.
		
		
		System.out.println(oneMap.toString() + " <- oneMap.toString()   updateCourseNotAssignTeacher()   CourseController.java");
		System.out.println(oneMap.size() + " <- oneMap.size()   updateCourseNotAssignTeacher()   CourseController.java");
		
		
		Map<String, Object> course = oneMap.get(0);
		// 한개의 객체만 나올 것이므로 그 객체를 넣어준다.
		
		map = new HashMap<String, Object>();
		// 원래의 주소값을 날리고 새로이 셋팅한다. (이전값이 없어진다.)
		
		map.put("courseNo", courseNo);
		// 해당 강좌코드로 등록된 강좌의 목록을 가져오기 위해 검색 키워드를 넣어준다.
		
		List<Map<String, Object>> courseAssignList = 
				courseService.courseNotAssignTeacherOneOrList(map);
		// 강좌코드로 강사가 배정이 안된 강좌 강의실 배정목록 가져오기
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 샐렉트박스에 넣어줄 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		
		model.addAttribute("course", course);
		model.addAttribute("courseAssignList", courseAssignList);
		model.addAttribute("courseAssignListSize", courseAssignList.size());
		// 강좌 리스트의 사이즈를 보고 강좌를 뿌려줄 것인지,
		// 강좌 목록이 없다는 메세지를 뿌려줄 것인지 판단한다.
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("roomList", roomList);
		
		return "/view/lesson/courseAssign/detailCourseNotAssignTeacher";
	}
	
	
	// 관리자 : 강사가 배정이 안된 강좌 수정 처리
	@PostMapping("/updateCourseNotAssignTeacher")
	public String updateCourseNotAssignTeacher(
			 CourseRoomAssignment assignment
			,Course course, Model model
			,RedirectAttributes redirectAttributes) {
		int result = courseService.updateCourseNotAssignTeacher(assignment, course);
		// 해당 강좌 수정 처리 후 수정 처리된 숫자
		
		String path = "redirect:/notTeacherCourseAssignList";
		// 강좌 수정에 성공했을 경우 강사가 배정이 안된 강좌목록 리스트로 이동한다.
		
		if((assignment.getCourseAssignmentIsChanged().equals("유") || course.getCourseIsChanged().equals("유"))
				&& result == 0) {
			// 강좌강의실 배정 변경사항이 있거나 강좌의 변경사항이 있으면
			// 반환되는 result값은 1이상이 나와야한다.
			// 만약 강좌강의실 배정 변경사항이 있거나 강좌 변경사항이 있음에도
			// 반환된 result값이 0이라면 강좌강의실배정 혹은 강좌 수정에 실패했다는 뜻이다.
			
			System.out.println("강좌 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("courseAssignmentNo", assignment.getCourseAssignmentNo());
			// 강사가 배정이 안된 강좌 상세 페이지로 리다이렉트하면서 강좌강의실배정코드를 넘겨준다.
			
			redirectAttributes.addAttribute("courseNo", course.getCourseNo());
			// 강사가 배정이 안된 강좌 상세 페이지로 리다이렉트하면서 강좌코드를 넘겨준다.
			
			path = "redirect:/updateCourseNotAssignTeacher";
			// 강사가 배정이 안된 강좌 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자 : 강좌배정 삭제 처리
	@GetMapping("/deleteCourseAssign")
	public String deleteCourseAssign(
			 @RequestParam(value = "courseAssignmentNo") String courseAssignmentNo) {
		System.out.println(courseAssignmentNo + " <- courseAssignmentNo   deleteCourseAssign()   CourseController.java");
		String message = courseService.deleteCourseAssign(courseAssignmentNo);
		// 해당 강좌 삭제 처리 후 메세지 반환
		
		System.out.println(message + " <- message   deleteCourseNotAssignTeacher()   CourseController.java");
		
		return "redirect:/courseAssignList";
		// 강의실과 강사가 배정된 강좌 리스트로 이동한다.
	}
	
	
	
	
	
	// 관리자 : 강의실과 강사가 배정된 강좌 리스트 보기 
	@GetMapping("/courseAssignList")
	public String courseAssignList(Model model) {
		
		List<Map<String, Object>> courseAssignmentList =
					courseService.courseAssignmentOneOrList();
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
		
		
		List<Subject> subjectList = courseManageService.subjectList();
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
		
		return "/view/lesson/courseAssign/listCourseAssignTeacher";
	}
	
	
	// 관리자 : 선택한 과목코드 혹은 과목코드와 강좌코드로 강좌강의실배정 리스트 검색
	@PostMapping("/searchCourseAssignList")
	public String searchCourseAssignList(CourseRoomSearchVO searchVO, Model model) {
		
		List<Map<String, Object>> courseAssignmentList =
				courseService.courseAssignmentOneOrList(searchVO);
		// 선택한 과목코드 혹은 과목코드와 강좌코드로 강좌 리스트 검색
		// 강좌 테이블에서 강좌코드와 강좌명,
		// 과목 테이블에서 과목코드와 과목명,
		// 강의실 테이블에서 강의실코드와 호실번호, 층수,
		// 강사 테이블에서 강사 아이디(회원 아이디)
		// 회원 신상정보 테이블에서 강사명(회원명)을 가지고 온다.
		// 여러 개의 테이블을 조인하므로 맵 형태의 리스트로 가져온다.
		
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 샐렉트박스에 넣어줄 과목 리스트를 가져온다.
		
		
		model.addAttribute("courseAssignmentList", courseAssignmentList);
		model.addAttribute("courseAssignmentListSize", courseAssignmentList.size());
		// 강좌강의실 배정리스트의 사이즈를 보고 강좌를 뿌려줄 것인지,
		// '등록된 강좌배정목록이 없습니다.'메세지를 뿌려줄 것인지 판단한다.
		
		model.addAttribute("subjectList", subjectList);
		
		return "/view/lesson/courseAssign/listCourseAssign";
	}
	
	
	// 관리자 : 강좌 강의실 배정 및 강사 강좌 배정 수정 상세화면 (강좌상세화면)
	@GetMapping("/updateCourseAssign")
	public String updateCourseAssign(CourseRoomSearchVO courseRoomSearchVO, Model model) {
		
		System.out.println(courseRoomSearchVO.getCourseAssignmentNo() + " <- updateCourse()   CourseController.java");
		System.out.println(courseRoomSearchVO.getMemberId() + " <- updateCourse()   CourseController.java");
		
		List<Map<String, Object>> oneMap = courseService.courseAssignmentOneOrList(courseRoomSearchVO);
		// 강좌 강의실 배정 테이블의 기본키인 강좌배정코드로 값을 가져올 것이기에
		// 하나의 객체만 나올 것이다.
		
		System.out.println(oneMap.toString() + " <- oneMap.toString()   updateCourse()   CourseController.java");
		System.out.println(oneMap.size() + " <- oneMap.size()   updateCourse()   CourseController.java");
		
		Map<String, Object> courseInfo = oneMap.get(0);
		// 강좌 강의실 배정 및 강사 강좌 배정 상세 데이터 가져오기
		
		CourseRoomSearchVO searchVO = new CourseRoomSearchVO();
		searchVO.setCourseNo((String)courseInfo.get("courseNo"));
		// 강좌강의실 리스트에서 해당 강좌코드를 참조하는 리스트만을 가져오기위해서
		// 검색키워드로 강좌코드를 넣어준다.
		
		List<Map<String, Object>> courseAssignmentList =
				courseService.courseAssignmentOneOrList(searchVO);
		// 해당 강좌코드를 참조하는 모든 강좌강의실 강사 배정목록을 가져온다.
		
		List<Map<String, Object>> courseList = courseService.courseOneOrList();
		// 수정화면에서 강사의 담당 강좌를 바꿀 수 있도록 강좌목록을 넣어준다.
		// 샐렉트박스에 넣어줄 강좌 리스트를 가져온다.
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseNo", (String)courseInfo.get("courseNo"));
		
		oneMap = courseService.courseOneOrList(map);
		// 샐렉트박스에 넣어줄 과목 정보를 가져온다.
		
		System.out.println(oneMap.toString() + " <- oneMap.toString()   updateCourse()   CourseController.java");
		System.out.println(oneMap.size() + " <- oneMap.size()   updateCourse()   CourseController.java");
		
		Map<String, Object> courseSubject = oneMap.get(0);
		// 한개의 객체만 존재하므로 맨 앞에 위치한 것만 가져오면 된다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 샐랙트박스에 넣어줄 전체 강의실 리스트를 가져온다.
		
		
		model.addAttribute("course", courseInfo);
		// 해당 강좌에 관한 상세정보를 넣어준다.
		
		model.addAttribute("courseAssignList", courseAssignmentList);
		// 해당 강좌코드를 참조하는 강좌강의실 목록을 넣어준다.
		
		model.addAttribute("courseList", courseList);
		// 강좌 리스트를 넣어준다.
		
		model.addAttribute("courseSubject", courseSubject);
		// 과목 리스트를 넣어준다.
		
		model.addAttribute("roomList", roomList);
		// 강의실 리스트를 넣어준다.
		
		return "/view/lesson/courseAssign/detailCourseAssign";
	}
	
	
	// 관리자 : 해당 강좌코드로 강좌정보 가져오기
	@PostMapping("/subjectSelect")
	@ResponseBody
	public Map<String, Object> subjectSelect(@RequestBody String courseNo) {
		System.out.println(courseNo + "<- courseNo   subjectSelect()   CourseController.java");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseNo", courseNo);
		
		List<Map<String, Object>> oneMap =
				courseService.courseOneOrList(map);
		// 해당 강좌코드로 강좌정보 가져오기
		
		System.out.println(oneMap.toString() + " <- oneMap.toString()   subjectSelect()   CourseController.java");
		System.out.println(oneMap.size() + " <- oneMap.size()   subjectSelect()   CourseController.java");
		
		Map<String, Object> courseInfo = oneMap.get(0);
		
		return courseInfo;
	}
	
	
	// 관리자 : 강좌 강의실 배정 및 강사 강좌 배정 수정 처리
	@PostMapping("updateCourseAssign")
	public String updateCourseAssign(
			 CourseRoomAssignment assignment
			,Course course
			,Teacher teacher
			,Model model
			,RedirectAttributes redirectAttributes) {
		int result = courseService.updateCourse(assignment, course, teacher);
		// 해당 강좌 수정 처리 후 수정 처리된 숫자
		
		String path = "redirect:/courseAssignList";
		// 강좌 수정에 성공했을 경우 강좌배정 및 강사 매칭 강좌 리스트로 이동한다.
		
		if((assignment.getCourseAssignmentIsChanged().equals("유") ||
			course.getCourseIsChanged().equals("유") ||
			teacher.getTeacherIsChanged().equals("유"))
				&& result == 0) {
			// 강좌강의실 배정 변경사항 or 강좌의 변경사항 or 강사 담당 강좌 변경사항이 있을 경우
			// 반환되는 result값은 1이상이 나와야한다.
			// 만약 강좌강의실 배정 변경사항이 있거나 강좌 변경사항이 있거나 강사 담당 강좌 변경사항이 있음에도
			// 반환된 result값이 0이라면 강좌강의실배정 or 강좌 or 강사 담당 강좌 수정에 실패했다는 뜻이다.
			
			System.out.println("강좌 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("courseAssignmentNo", assignment.getCourseAssignmentNo());
			// 강좌 상세 페이지로 리다이렉트하면서 강좌강의실배정코드를 넘겨준다.
			
			redirectAttributes.addAttribute("memberId", teacher.getMemberId());
			// 강좌 상세 페이지로 리다이렉트하면서 강사 아이디를 넘겨준다.
			
			path = "redirect:/updateCourseAssign";
			// 강좌 상세 페이지로 이동
		}
		
		return path;
	}
}
