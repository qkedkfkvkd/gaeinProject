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

import com.cafe24.smart_academy.academy_manage.course.manage.service.CourseManageService;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.Subject;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.courseandscore.service.CourseAndScoreService;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.CourseEnrollee;
import com.cafe24.smart_academy.academy_manage.member.service.StudentInfoService;
import com.cafe24.smart_academy.academy_manage.member.service.TeacherInfoService;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberSearchVO;

@Controller
public class CourseAndScoreController {
// 수강 및 성적관리 컨트롤러
	
	@Autowired
	CourseManageService courseManageService;
	// 과목, 강의실, 성적기준 관리 서비스
	
	@Autowired
	CourseAndScoreService courseAndScoreService;
	// 수강 및 성적관리 서비스
	
	@Autowired
	StudentInfoService studentInfoService;
	// 학생 정보 관리 서비스
	
	@Autowired
	TeacherInfoService teacherInfoService;
	// 강사 정보 관리 서비스
	
	
	// 관리자가 학생목록에서 해당 학생의 전체 성적 결과를 보고자 할때 사용한다.
	// 로그인 테이블의 회원 아이디 - 수강신청 테이블 회원 아이디,
	// 수강신청 테이블 수강신청코드 - 성적입력 테이블의 수강신청 코드 3개 테이블을 조인
	// 3개 테이블을 조인하여 해당 학생의 전체 수강신청 코드와
	// 전체 성적 정보를 얻어올 수 있다.
	// --- 성적만 가지고 있어서는 안된다. 어느 강좌에서 받은 성적인지, 강사는 누구인지
	// 알아야 할 것이므로 아래의 8개 테이블을 조인해야 할 것이다.
	// --- 조인하는 테이블들
	// 로그인 테이블, 수강신청 테이블, 강좌 강의실 배정 테이블, 강의실 테이블,
	// 강좌 테이블, 과목 테이블, 강사 테이블, 회원신상정보 테이블
	// ----------------------------------------------------------------
	// 로그인 테이블의 회원 아이디 - 수강신청 테이블 회원 아이디,
	// 수강신청 테이블 강좌배정코드 - 강좌 강의실 배정 테이블 강좌배정코드
	// 강좌 강의실 배정 테이블의 강의실 코드 - 강의실 테이블의 강의실 코드
	// 강좌 강의실 배정 테이블의 강좌 코드 - 강좌 테이블의 강좌 코드
	// 강좌 테이블의 과목 코드 - 과목 테이블의 과목 코드
	// 과목 테이블의 과목코드 - 강사 테이블의 과목 코드
	// 강사 테이블의 회원 아이디 - 회원 신상정보 테이블의 회원명
	// 8개 테이블을 조인하여 한 학생의 수강한 강좌의 강좌명, 강의실, 강좌, 강사명을
	// 얻어올 수 있다.
	// ----------------------------------------------------------------
	// 관리자가 특정 학생의 전체 성적 결과 조회
//	@GetMapping("/oneStudentTotalGradeResult")
//	public String oneStudentTotalGradeResult(
//			@RequestParam(value = "memberId")String memberId) {
//		
//		return "";
//	}
	
	
	// 관리자 : 학생 전체 수강신청 리스트
	@GetMapping("/CourseEnrolleeList")
	public String CourseEnrolleeList(Model model) {
		
		List<Map<String, Object>> CourseEnrolleeList =
				courseAndScoreService.courseEnrolleeOneOrList();
		// 학생 전체 수강신청 리스트를 가져온다.
		
		model.addAttribute("CourseEnrolleeList", CourseEnrolleeList);
		// 화면에 보여줄 학생 수강신청 리스트
		
		model.addAttribute("CourseEnrolleeListSize", CourseEnrolleeList.size());
		// 리스트의 사이즈로 학생 수강신청의 존재 여부를 판단한다.
		
		return "/view/lesson/courseAndScore/listCourseEnrollee";
	}
	
	
	// 관리자 : 학생 수강신청 등록 폼 이동
	@GetMapping("/addCourseEnrollee")
	public String addCourseEnrollee(
			 @RequestParam(value = "memberId") String memberId
			,Model model) {
		
		Map<String, Object> studentInfo =
				studentInfoService.detailStudentInfoByMemberId(memberId);
		// 학생 한명의 신상정보 및 학부모 정보 가져오기
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		model.addAttribute("studentInfo", studentInfo);
		// 화면에 보여줄 해당 학생 정보
		
		model.addAttribute("subjectList", subjectList);
		// 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		return "/view/lesson/courseAndScore/addCourseEnrollee";
	}
	
	
	// 관리자 : 수강신청코드 중복확인
	@PostMapping("/courseEnrolleeNoOverlapChk")
	@ResponseBody
	public Map<String, Object> courseEnrolleeNoOverlapChk(@RequestBody String courseEnrolleeNo) {
		System.out.println(courseEnrolleeNo
				+ " <- courseEnrolleeNo   courseEnrolleeNoOverlapChk()   CourseAndScoreController.java");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String result =
				courseAndScoreService.courseEnrolleeByCourseEnrolleeNo(courseEnrolleeNo);
		// 수강신청 테이블에서 해당 수강신청코드가 존재하는지 확인
		
		if(result == null) {	// 결과가 없다 : 해당 수강신청코드가 존재하지 않는다.
			map.put("result", 1);
			// 수강신청 테이블에 해당 수강신청코드값이 존재하지 않음 : 사용가능한 수강신청코드
		} else {
			map.put("result", 0);
			// 수강신청 테이블에 해당 수강신청코드값이 존재함 : 이미 사용 중인 수강신청코드
		}
		
		return map;
	}
	
	
	// 관리자 : 학생 수강신청 등록 처리
	@PostMapping("/addCourseEnrollee")
	public String addCourseEnrollee(
			 CourseEnrollee courseEnrollee
			,RedirectAttributes redirectAttributes
			,Model model) {
		String message = courseAndScoreService.addCourseEnrollee(courseEnrollee);
		// 수강신청 등록 처리 후 메세지를 반환받는다.
		
		String path = "redirect:/addCourseEnrollee";
		// 수강신청 추가에 실패했을 경우
		// 다시 수강신청을 등록하는 폼으로 이동한다.
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 수강신청 추가에 실패했다는 뜻이다.
			
			redirectAttributes.addAttribute("memberId", courseEnrollee.getMemberId());
			// 수강신청 추가 폼으로 이동하면서 해당 학생의 아이디를 넘겨준다.
			
		} else {
			// 리턴받은 메세지가 널이라면 수강신청 추가에 성공했다는 뜻이다.
			path = "redirect:/CourseEnrolleeList";
			// 수강신청 목록으로 이동한다.
		}
		
		return path;
	}
	
	
	// 관리자 : 수강신청 상세보기
	@GetMapping("/updateCourseEnrollee")
	public String updateCourseEnrollee(
			 CourseRoomSearchVO searchVO
			,Model model) {
		
		List<Map<String, Object>> oneMap =
				courseAndScoreService.courseEnrolleeOneOrList(searchVO);
		// 수강신청 테이블의 기본키인 수강신청코드로 해당 학생의 수강정보를 얻어오므로
		// 한개의 객체만 리턴될 것이다.
		
		System.out.println(oneMap.toString()
				+ " <- oneMap.toString()   updateCourseEnrollee()   CourseAndScoreController.java");
		System.out.println(oneMap.size()
				+ " <- oneMap.size()   updateCourseEnrollee()   CourseAndScoreController.java");
		
		Map<String, Object> courseEnrolleeInfo = oneMap.get(0);
		// 얻어온 객체가 한개 뿐이므로 그 객체를 다시 맵에 넣어준다.
		
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		// totalGradeResultList() 메소드의 매개변수가 Map 이므로 검색내용을 저장하기 위한 맵 객체 선언
		
		map.put("courseEnrolleeNo", searchVO.getCourseEnrolleeNo());
		// 검색 내용 저장
		
		List<Map<String, Object>> gradeReferenceInputScoreList =
				courseAndScoreService.totalGradeResultList(map);
		// 해당 수강신청코드를 참조하는 모든 성적 리스트 가져오기
		
		
		model.addAttribute("courseEnrolleeInfo", courseEnrolleeInfo);
		// 화면에 보여줄 해당 학생의 수강신청 정보
		
		model.addAttribute("subjectList", subjectList);
		// 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		model.addAttribute("gradeReferenceInputScoreList", gradeReferenceInputScoreList);
		// 화면에 보여줄 성적 리스트
		
		model.addAttribute("gradeReferenceInputScoreListSize", gradeReferenceInputScoreList.size());
		// 리스트의 사이즈로 해당 수강신청코드를 참조하는 성적리스트의 존재여부 판단
		
		return "/view/lesson/courseAndScore/detailCourseEnrollee";
	}
	
	
	// 관리자 : 수강신청 수정처리
	@PostMapping("/updateCourseEnrollee")
	public String updateCourseEnrollee(
			 CourseEnrollee courseEnrollee
			,Model model
			,RedirectAttributes redirectAttributes) {
		String message = courseAndScoreService.updateCourseEnrollee(courseEnrollee);
		// 수강신청 수정 처리 후 메세지를 반환받는다.
		
		String path = "redirect:/CourseEnrolleeList";
		// 수강신청 수정에 성공했을 경우 수강신청 리스트로 이동하게 초기화한다.
		
		if(message != null) {
			// 리턴받은 메세지가 널이 아니라면 수강신청 수정에 실패했다는 뜻이다.
			
			System.out.println("수강신청 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("courseEnrolleeNo", courseEnrollee.getCourseEnrolleeNo());
			// 수강신청 상세 페이지로 리다이렉트하면서 수강신청코드를 넘겨준다.
			
			path = "redirect:/updateCourseEnrollee";
			// 수강신청 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자 : 수강신청 삭제 처리
	@GetMapping("/deleteCourseEnrollee")
	public String deleteCourseEnrollee(
			@RequestParam(value = "courseEnrolleeNo")String courseEnrolleeNo) {
		System.out.println(courseEnrolleeNo
				+ " <- courseEnrolleeNo   deleteCourseEnrollee()   CourseAndScoreController.java");
		
		String message = courseAndScoreService.deleteCourseEnrollee(courseEnrolleeNo);
		// 해당 수강신청 삭제 쿼리 실행 후 메세지 반환
		
		System.out.println(message + " <- message   deleteCourseEnrollee()   CourseAndScoreController.java");
		
		return "redirect:/CourseEnrolleeList";
	}
	
	
	
	
	
	// 강사 : 자신의 강좌에 수강신청한 학생들 리스트 보기
	@GetMapping("/teacherCourseEnrolleeStudentList")
	public String teacherCourseEnrolleeStudentList(
			 @RequestParam(value = "memberId") String memberId
			,Model model) {
		System.out.println(memberId + " <- memberId   teacherCourseEnrolleeStudentList()   CourseAndScoreController.java");
		
		MemberSearchVO searchVO = new MemberSearchVO();
		searchVO.setMemberId(memberId);
		// 특정 강사의 정보만을 가져와야 하므로
		// 검색 키워드에 특정 강사의 아이디를 넣어준다.
		
		List<Map<String, Object>> oneMap = 
				teacherInfoService.teacherInfoOneOrList(searchVO);
		// 리턴타입을 맞춰주기 위해 리스트로 받았을 뿐 로그인 테이블의 기본키인 회원아이디로
		// 검색을 실시하므로 하나의 객체만 리턴될 것이다.
		
		System.out.println(oneMap.toString()
				+ " <- oneMap.toString()   teacherCourseEnrolleeStudentList()   CourseAndScoreController.java");
		System.out.println(oneMap.size()
				+ " <- oneMap.size()   teacherCourseEnrolleeStudentList()   CourseAndScoreController.java");
		
		Map<String, Object> teacherInfo = oneMap.get(0);
		// 하나의 객체 밖에 없으므로 맨 앞에있는 객체를 가져다가 맵에 넣어준다.
		
		CourseRoomSearchVO courseRoomSearchVO = new CourseRoomSearchVO();
		courseRoomSearchVO.setCourseNo((String)teacherInfo.get("courseNo"));
		// 해당 강사가 담당하는 강좌코드를 검색 키워드로 넣어준다.
		
		List<Map<String, Object>> teacherCourseEnrolleeStudentList =
				courseAndScoreService.courseEnrolleeOneOrList(courseRoomSearchVO);
		// 리스트 결과는 특정 강사가 담당하는 강좌에 수강신청한 학생들의 리스트이다.
		
		model.addAttribute("teacherInfo", teacherInfo);
		// 화면에 보여줄 특정 강사에 관한 정보 넣어주기
		
		model.addAttribute("teacherCourseEnrolleeStudentList", teacherCourseEnrolleeStudentList);
		// 화면에 보여줄 특정 강사 담당 강좌에 수강신청한 학생 리스트
		
		model.addAttribute("teacherCourseEnrolleeStudentListSize",
				teacherCourseEnrolleeStudentList.size());
		// 화면에 보여줄 특정 강사 담당 강좌에 수강신청한 학생 리스트
		
		return "/view/lesson/courseAndScore/listTeacherCourseEnrolleeStudent";
	}
}
