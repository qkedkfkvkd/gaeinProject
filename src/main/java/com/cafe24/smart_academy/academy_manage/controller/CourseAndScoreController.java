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
import com.cafe24.smart_academy.academy_manage.course.manage.vo.GradingCriteria;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.Subject;
import com.cafe24.smart_academy.academy_manage.course.service.ScheduleService;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.courseandscore.service.CourseAndScoreService;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.CourseEnrollee;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.ExaminationDay;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.ScoreInput;
import com.cafe24.smart_academy.academy_manage.member.service.MemberService;
import com.cafe24.smart_academy.academy_manage.member.service.StudentInfoService;
import com.cafe24.smart_academy.academy_manage.member.service.TeacherInfoService;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
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
	ScheduleService scheduleService;
	// 강좌 시간표 관리 서비스
	
	@Autowired
	MemberService memberService;
	// 회원 정보 관리 서비스 객체
	
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
	// 관리자 : 학생 전체 수강신청 검색결과 리스트
	// 관리자, 강사 : 특정 강좌 수강신청한 학생 리스트
	// 관리자, 학생 : 특정 학생의 수강신청 목록 보기
	@GetMapping("/courseEnrolleeList")
	public String courseEnrolleeList(
			 CourseRoomSearchVO searchVO
			,Model model) {
		
		List<Map<String, Object>> courseEnrolleeList =
				courseAndScoreService.courseEnrolleeOneOrList(searchVO);
		// 학생 전체 수강신청 리스트를 가져온다.
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 샐렉트박스에 넣어줄 과목 리스트를 가져온다.
		
		
		model.addAttribute("subjectList", subjectList);
		// 샐렉트박스에 보여줄 과목 리스트
		
		model.addAttribute("courseEnrolleeList", courseEnrolleeList);
		// 화면에 보여줄 학생 수강신청 리스트
		
		model.addAttribute("courseEnrolleeListSize", courseEnrolleeList.size());
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
			path = "redirect:/courseEnrolleeList";
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
		
		
		//Map<String, Object> map = new HashMap<String, Object>();
		// totalGradeResultList() 메소드의 매개변수가 Map 이므로 검색내용을 저장하기 위한 맵 객체 선언
		
		//map.put("courseEnrolleeNo", searchVO.getCourseEnrolleeNo());
		// 검색 내용 저장
		
		List<Map<String, Object>> gradeReferenceInputScoreList =
				courseAndScoreService.totalGradeResultOneOrList(searchVO);
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
		
		String path = "redirect:/courseEnrolleeList";
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
		
		return "redirect:/courseEnrolleeList";
	}
	
	
	
	
	
	// 강사 : 자신의 강좌에 수강신청한 학생들 리스트 보기
	@GetMapping("/teacherCourseEnrolleeStudentList")
	public String teacherCourseEnrolleeStudentList(
			CourseRoomSearchVO courseRoomSearchVO
			,Model model) {
		System.out.println(courseRoomSearchVO.getMemberId() + " <- memberId   teacherCourseEnrolleeStudentList()   CourseAndScoreController.java");
		
		MemberSearchVO searchVO = new MemberSearchVO();
		searchVO.setMemberId(courseRoomSearchVO.getMemberId());
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
		
		
		courseRoomSearchVO.setMemberId(null);
		// 강사 아이디가 저장된 변수에 널값을 준다.
		// 학생의 수강신청 리스트를 검색하는데 학생아이디 = 강사아이디 ? 는 말이 안된다.
		// 제대로 값이 나오질 않는다.
		
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
	
	
	
	
	
	// 관리자, 강사, 학생 : 아직 시험을 치르지 않은 시험일 조회
	// 관리자, 강사, 학생 : 시험완료된 강좌 리스트
	@GetMapping("/examinationDayList")
	public String examinationDayList(
			 CourseRoomSearchVO searchVO
			,@RequestParam(value = "memberLevel") String memberLevel
			,Model model) {
		
		System.out.println(memberLevel
				+ " <- memberLevel   examinationDayList()   CourseAndScoreController.java");
		// 회원 권한 보기
		
		
		if(memberLevel.equals("관리자")) {
			// 관리자 회원이 시험일 리스트를 조회하는 것이라면 모든 강사의 아이디를 조회해야한다.
			searchVO.setMemberId(null);
			// 시험일은 강사만이 등록할 수 있는데 관리자 아이디로 등록된 시험일 리스트를 뽑으면 제대로 조회가 안될 것이다.
			// 따라서 관리자 아이디가 들어있는 변수에 널값을 넣어줘야 값이 제대로 나온다.
			
		} else if(memberLevel.equals("학생")) {
			// 학생 권한으로 시험일 리스트 조회 시
			
			List<Map<String, Object>> courseEnrolleeList =
					courseAndScoreService.courseEnrolleeOneOrList(searchVO);
			// 학생의 아이디로 수강신청내역을 검색하여 리스트를 가져온다.
			
			List<String> courseList = new ArrayList<String>();
			// 수강신청한 강좌를 저장할 리스트 객체
			
			for(int i=0; i<courseEnrolleeList.size(); i++) {
				System.out.println((String)courseEnrolleeList.get(i).get("courseNo")
						+ " <- courseEnrolleeList.get(i).get('courseNo')   "
						+ "examinationDayList()   CourseAndScoreController.java");
				
				String courseNo = (String)courseEnrolleeList.get(i).get("courseNo");
				// 수강신청한 강좌의 강좌코드를 한개씩 꺼내온다.
				
				courseList.add(courseNo);
				// 꺼내온 수강신청한 강좌코드를 넣어준다.
			}
			
			System.out.println(courseList.toString() + " <- courseList.toString()   examinationDayList()   CourseAndScoreController.java");
			System.out.println(courseList.size() + " <- courseList.size()   examinationDayList()   CourseAndScoreController.java");
			
			
			searchVO.setMemberId(null);
			// 학생이 등록한 시험일은 없으므로 쿼리 검색 키워드에서 강사아이디 부분을 없애준다.
			
			searchVO.setCourseList(courseList);
			// 수강신청한 강좌로 쿼리를 돌리기위해 검색 키워드에 넣어준다.
		}
		
		List<Map<String, Object>> testDayCourseList =
				courseAndScoreService.testDayCourseOneOrList(searchVO);
		// 관리자 접근시 : 시험 날짜가 오늘보다 이후인 모든 시험일자 강좌 리스트를 얻어온다.
		// 강사 접근시 : 시험 날짜가 오늘보다 이후인 강사 담당 강좌의 모든 시험일자 강좌 리스트를 얻어온다.
		// 학생 접근시 : 시험 날짜가 오늘보다 이후인 수강신청한 강좌의 모든 시험일자 강좌 리스트를 얻어온다.
		
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		List<Map<String, Object>> teacherList = teacherInfoService.teacherInfoOneOrList();
		// 전체 강사 리스트를 가져온다.
		
		
		String testStandard = searchVO.getTestStandardDay();
		String testStandardDay = "";
		
		if(testStandard.equals("notTest")) {
			// 미시험일 리스트를 보여주는 것이라면
			
			testStandardDay = "아직 치르지 않은 ";
			// 제목의 앞부분에 붙여줄 글자를 넣어준다.
			// '아직 치르지 않은 '시험일 목록
		} else {
			// 완료된 시험일 리스트를 보여주는 것이라면
			
			testStandardDay = "완료된 ";
			// 제목의 앞부분에 붙여줄 글자를 넣어준다.
			// '완료된 '시험일 목록
		}
		
		model.addAttribute("testStandardDay", testStandardDay);
		// 제목의 앞부분에 붙여줄 글자를 넣어준다.
		
		model.addAttribute("testDayCourseList", testDayCourseList);
		// 화면에 보여줄 아직 시험을 치르지 않은 시험 강좌 리스트
		// 화면에 보여줄 완료된 시험 강좌 리스트
		
		model.addAttribute("testDayCourseListSize", testDayCourseList.size());
		// 리스트의 사이즈를 보고 시험일 목록의 존재 여부 판단
		
		model.addAttribute("testStandard", testStandard);
		// 위 변수에 담긴 값으로 미시험일 리스트인지, 완료된시험 리스트인지 판단한다.
		
		
		model.addAttribute("subjectList", subjectList);
		// 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		model.addAttribute("roomList", roomList);
		// 샐랙트박스에 넣어줄 전체 강의실 리스트
		
		model.addAttribute("teacherList", teacherList);
		// 샐랙트박스에 넣어줄 전체 강사 리스트
		
		return "/view/lesson/courseAndScore/listExaminationDay";
	}
	
	
	// 강사 : 시험일 추가 폼 이동
	@GetMapping("/addExaminationDay")
	public String addExaminationDay(MemberSearchVO memberSearchVO, Model model) {
		System.out.println(memberSearchVO.getMemberId() + " <- memberId   addExaminationDay()   CourseAndScoreController.java");
		// 특정 강사의 정보만을 가져와야 하므로 검색 키워드에 특정 강사의 아이디를 넣어준다.
		
		List<Map<String, Object>> oneMap = 
				teacherInfoService.teacherInfoOneOrList(memberSearchVO);
		// 리턴타입을 맞춰주기 위해 리스트로 받았을 뿐 로그인 테이블의 기본키인 회원아이디로
		// 검색을 실시하므로 하나의 객체만 리턴될 것이다.
		
		System.out.println(oneMap.toString()
				+ " <- oneMap.toString()   addExaminationDay()   CourseAndScoreController.java");
		System.out.println(oneMap.size()
				+ " <- oneMap.size()   addExaminationDay()   CourseAndScoreController.java");
		
		Map<String, Object> teacherInfo = oneMap.get(0);
		// 하나의 객체 밖에 없으므로 맨 앞에있는 객체를 가져다가 맵에 넣어준다.
		
		
		CourseRoomSearchVO searchVO = new CourseRoomSearchVO();
		searchVO.setMemberId(memberSearchVO.getMemberId());
		// 해당 강사의 아이디를 검색 키워드로 넣어준다.
		
		List<Map<String, Object>> courseScheduleList =
				scheduleService.scheduleOneOrList(searchVO);
		// 해당 강사 담당 강좌의 시간표를 얻어온다.
		
		model.addAttribute("teacherInfo", teacherInfo);
		// 화면에 보여줄 특정 강사에 관한 정보 넣어주기
		
		model.addAttribute("courseScheduleList", courseScheduleList);
		// 샐랙트박스에 넣어줄 해당강좌시간표 리스트
		
		return "/view/lesson/courseAndScore/addExaminationDay";
	}
	
	
	// 강사 : 시험일코드 중복 확인
	@PostMapping("/examinationDayNoOverlapChk")
	@ResponseBody
	public Map<String, Object> examinationDayNoOverlapChk(@RequestBody String examinationDayNo) {
		System.out.println(examinationDayNo
				+ " <- examinationDayNo   examinationDayNoOverlapChk()   CourseAndScoreController.java");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String result = courseAndScoreService.examinationDayByExaminationDayNo(examinationDayNo);
		// 시험일 테이블에서 해당 시험일코드가 존재하는지 확인
		
		if(result == null) {	// 결과가 없다 : 해당 시험일코드가 존재하지 않는다.
			map.put("result", 1);
			// 시험일 테이블에 해당 시험일코드값이 존재하지 않음 : 사용가능한 시험일코드
		} else {
			map.put("result", 0);
			// 시험일 테이블에 해당 시험일코드값이 존재함 : 이미 사용 중인 시험일코드
		}
		
		return map;
	}
	
	
	// 강사 : 시험일 추가 처리
	@PostMapping("/addExaminationDay")
	public String addExaminationDay(
			 ExaminationDay examinationDay
			,@RequestParam(value = "memberId") String memberId
			,RedirectAttributes redirectAttributes) {
		String message = courseAndScoreService.addExaminationDay(examinationDay);
		// 시험일 추가 처리 후 메세지를 리턴받는다.
		
		String path = "/view/lesson/courseAndScore/addExaminationDay";
		// 시험일 추가에 실패했을 경우 다시 시험일을 추가하는 폼으로 이동한다.
		
		if(message == null) { // 리턴받은 메세지가 널이라면 시험일 추가에 성공했다는 뜻이다.
			
			path = "redirect:/examinationDayList";
			// 시험일 리스트로 이동
			
			redirectAttributes.addAttribute("testStandardDay", "notTest");
			// 쿼리문 실행시 아직 시험을 치르지 않은 날짜 리스트를 구해오기위한 검색 키워드
			
			redirectAttributes.addAttribute("memberLevel", "강사");
			// 시험일 등록은 강사만이 할 수 있으므로 시험일 등록 후
			// 해당 강사의 담당강좌 시험일 리스트로 이동한다.
		}
		
		redirectAttributes.addAttribute("memberId", memberId);
		// 추가 성공 시 자신의 강좌 시험일 리스트를 가져와야하므로 강사의 아이디를 넣어준다.
		// 추가 실패 시 시험일 추가 폼으로 이동할 때 자신의 정보와 강좌에 관련된 정보를
		// 가져와야 하므로 강사의 아이디를 넣어준다.
		
		return path;
	}
	
	
	// 관리자, 강사 : 시험일 상세 화면 이동
	@GetMapping("/updateExaminationDay")
	public String updateExaminationDay(
			 CourseRoomSearchVO searchVO
			,Model model) {
		
		List<Map<String, Object>> oneMap =
				courseAndScoreService.testDayCourseOneOrList(searchVO);
		// 시험일 테이블의 기본키인 시험일코드로 값을 가지고 오므로 한개의 행만 나온다.
		
		System.out.println(oneMap.toString()
				+ " <- oneMap.toString()   updateExaminationDay()   CourseAndScoreController.java");
		System.out.println(oneMap.size()
				+ " <- oneMap.size()   updateExaminationDay()   CourseAndScoreController.java");
		
		Map<String, Object> examInfo = oneMap.get(0);
		// 가지고 온 한개의 객체를 맵에 담아준다.
		
		
		searchVO.setMemberId((String)examInfo.get("memberId"));
		// 해당 강사의 아이디를 검색 키워드로 넣어준다.
		
		List<Map<String, Object>> courseScheduleList =
				scheduleService.scheduleOneOrList(searchVO);
		// 해당 강사 담당 강좌의 시간표를 얻어온다.
		
		searchVO.setMemberId(null);
		// 특정 강사의 강좌시험일로 검색이 되면 안되고
		// 특정 시험일의 강좌시험일이 모두 출력되야 하므로 강사 아이디값을 제거한다. 
		
		List<Map<String, Object>> courseScoreList =
				courseAndScoreService.totalGradeResultOneOrList(searchVO);
		// 시험일 상세화면에서 해당 시험일코드를 참조하는 강좌시험성적 리스트 가져오기
		
		
		model.addAttribute("examInfo", examInfo);
		// 화면에 뿌려줄 시험일 상세 정보
		
		model.addAttribute("courseScheduleList", courseScheduleList);
		// 샐랙트박스에 넣어줄 해당강좌시간표 리스트
		
		model.addAttribute("courseScoreList", courseScoreList);
		// 화면에 뿌려줄 해당 시험일의 학생 성적 목록
		
		model.addAttribute("courseScoreListSize", courseScoreList.size());
		// 리스트의 사이즈를 보고 해당 학생 성적목록 존재 여부 판단
		
		return "/view/lesson/courseAndScore/detailExaminationDay";
	}
	
	
	// 관리자, 강사 : 시험일 수정 처리
	@PostMapping("/updateExaminationDay")
	public String updateExaminationDay(
			 ExaminationDay examinationDay
			,@RequestParam(value = "memberId", required = false) String memberId
			,RedirectAttributes redirectAttributes) {
		String message = courseAndScoreService.updateExaminationDay(examinationDay);
		// 시험일 수정 처리 후 메세지를 반환받는다.
		
		String path = "redirect:/examinationDayList";
		// 시험일 수정에 성공했을 경우 시험일리스트로 이동하게 초기화한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 시험일 수정에 성공했다는 뜻이다.
			
			redirectAttributes.addAttribute("testStandardDay", "notTest");
			// 아직 시험을 치르지 않은 리스트로 이동하므로 쿼리문 검색 키워드를 넣어준다.
			
			if(memberId != null) { // 강사 회원이 수정처리를 한 것이라면
				redirectAttributes.addAttribute("memberId", memberId);
				// 자신의 강좌 리스트가 보여져야 하므로 강사 아이디를 넣어준다.
			}
			
		} else {
			// 리턴받은 메세지가 널이 아니라면 시험일 수정에 실패했다는 뜻이다.
			
			System.out.println("시험일 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("examinationDayNo",
					examinationDay.getExaminationDayNo());
			// 시험일 상세 페이지로 리다이렉트하면서 시험일코드를 넘겨준다.
			
			path = "redirect:/updateExaminationDay";
			// 시험일 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자, 강사 : 시험일 삭제 처리
	@GetMapping("/deleteExaminationDay")
	public String deleteExaminationDay(
			 @RequestParam(value = "examinationDayNo") String examinationDayNo
			,@RequestParam(value = "memberId", required = false) String memberId
			,RedirectAttributes redirectAttributes) {
		
		System.out.println(examinationDayNo
				+ " <- examinationDayNo   deleteExaminationDay()   CourseAndScoreController.java");
		
		String message = courseAndScoreService.deleteExaminationDay(examinationDayNo);
		// 해당 시험일 삭제 쿼리 실행 후 메세지 반환
		
		System.out.println(message + " <- message   deleteExaminationDay()   CourseAndScoreController.java");
		
		if(memberId != null) { // 강사 회원이 삭제처리를 한 것이라면
			redirectAttributes.addAttribute("memberId", memberId);
			// 자신의 강좌 리스트가 보여져야 하므로 강사 아이디를 넣어준다.
		}
		
		redirectAttributes.addAttribute("testStandardDay", "notTest");
		// 아직 시험을 치르지 않은 리스트로 이동하므로 쿼리문 검색 키워드를 넣어준다.
		
		return "redirect:/examinationDayList";
	}
	
	
	
	
	
	// 관리자, 강사 : 학생 시험 성적 입력폼 이동
	@GetMapping("/addScoreInput")
	public String addScoreInput(CourseRoomSearchVO searchVO, Model model) {
		
		List<Map<String, Object>> oneMap =
				courseAndScoreService.courseEnrolleeOneOrList(searchVO);
		// 수강신청코드를 이용하여 객체를 가져오기에 하나의 객체만 나올 것이다.
		
		System.out.println(oneMap.toString()
				+ " <- oneMap.toString()   addScoreInput()   CourseAndScoreController.java");
		System.out.println(oneMap.size()
				+ " <- oneMap.size()   addScoreInput()   CourseAndScoreController.java");
		
		Map<String, Object> courseEnrolleeInfo = oneMap.get(0);
		// 가져온 한개의 객체를 맵에 넣어준다.
		
		
		System.out.println((String)courseEnrolleeInfo.get("courseNo")
				+ " <- courseNo   addScoreInput()   CourseAndScoreController.java");
		
		searchVO.setCourseNo((String)courseEnrolleeInfo.get("courseNo"));
		// 필요한 강좌코드를 얻어온다.
		
		searchVO.setTestStandardDay("testComplete");
		// 성적을 입력하는 것이다. 즉, 시험이 완료된 시험강좌 리스트를 가져와야한다.
		// 시험 완료된 시험강좌 리스트를 가져오기위해 쿼리 검색 키워드를 넣어준다.
		
		List<Map<String, Object>> examinationDayList =
				courseAndScoreService.testDayCourseOneOrList(searchVO);
		// 강좌코드와 시험완료 검색 키워드를 넣어서 시험완료된 시험강좌 리스트를 가져온다.
		
		
		List<GradingCriteria> GradingCriteriaList =
				courseManageService.gradingCriteriaList();
		// 전체 성적기준 리스트를 가져온다.
		
		
		model.addAttribute("courseEnrolleeInfo", courseEnrolleeInfo);
		// 학생의 수강신청 정보를 넣어준다.
		
		model.addAttribute("examinationDayList", examinationDayList);
		// 샐랙트 박스에 넣어줄 시험강좌 리스트
		
		model.addAttribute("GradingCriteriaList", GradingCriteriaList);
		// 샐랙트 박스에 넣어줄 성적평가기준 리스트
		
		return "/view/lesson/courseAndScore/addScoreInput";
	}
	
	
	// 관리자, 강사 : 성적입력코드 중복확인
	@PostMapping("/scoreInputNoOverlapChk")
	@ResponseBody
	public Map<String, Object> scoreInputNoOverlapChk(@RequestBody String scoreInputNo) {
		System.out.println(scoreInputNo
				+ " <- scoreInputNo   scoreInputNoOverlapChk()   CourseAndScoreController.java");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String result = courseAndScoreService.scoreInputByScoreInputNo(scoreInputNo);
		// 성적입력 테이블에서 해당 성적입력코드가 존재하는지 확인
		
		if(result == null) {	// 결과가 없다 : 해당 성적입력코드가 존재하지 않는다.
			map.put("result", 1);
			// 성적입력 테이블에 해당 성적입력코드값이 존재하지 않음 : 사용가능한 성적입력코드
		} else {
			map.put("result", 0);
			// 성적입력 테이블에 해당 성적입력코드값이 존재함 : 이미 사용 중인 성적입력코드
		}
		
		return map;
	}
	
	
	// 강사 : 학생 시험성적 입력 처리
	@PostMapping("/addScoreInput")
	public String addScoreInput(
			 ScoreInput scoreInput
			,@RequestParam(value = "memberId") String memberId
			// 강사가 학생의 점수를 입력처리했을 경우
			,RedirectAttributes redirectAttributes) {
		System.out.println(memberId + " <- memberId   addScoreInput()   CourseAndScoreController.java");
		
		String message = courseAndScoreService.addScoreInput(scoreInput);
		// 학생시험성적 입력 처리 후 메세지를 반환받는다.
		
		String path = "redirect:/addScoreInput";
		// 학생시험성적 추가에 실패했을 경우 다시 성적을 추가하는 폼으로 이동한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 학생시험성적 추가에 성공했다는 뜻이다.
			
			path = "redirect:/teacherCourseEnrolleeStudentList";
			// 자신의 강좌에 수강신청한 학생들 목록으로 이동
			
			redirectAttributes.addAttribute("memberId", memberId);
			//해당 강사의 아이디를 넣어준다.
		} else {
			// 리턴받은 메세지가 널이 아니라면 학생시험성적 추가에 실패했다는 뜻이다.
			
			redirectAttributes.addAttribute("courseEnrolleeNo",
					scoreInput.getCourseEnrolleeNo());
			// 점수 입력에 실패하여 성적 입력폼으로 이동할 경우 학생의 수강신청코드를 넣어준다.
		}
		
		return path;	
	}
	
	
	// 관리자, 강사, 학생 : 시험성적순위 리스트
	@GetMapping("/scoreRankInCourseList")
	public String scoreRankInCourseList(CourseRoomSearchVO searchVO, Model model) {
		
		List<Map<String, Object>> scoreRankList =
				courseAndScoreService.totalGradeResultOneOrList(searchVO);
		// 시험일코드를 이용하여 해당 시험을 치룬 학생들의 리스트를 가져온다.
		
		
		model.addAttribute("scoreRankList", scoreRankList);
		// 화면에 보여줄 시험을 치룬 학생들의 성적 리스트
		
		model.addAttribute("scoreRankListSize", scoreRankList.size());
		// 리스트의 사이즈를 보고 해당 학생 성적 목록의 존재 여부 판단
		
		return "/view/lesson/courseAndScore/listScoreRankInCourse";
	}
	
	
	// 관리자, 강사 : 시험성적 상세 폼 이동
	@GetMapping("/updateScoreInput")
	public String updateScoreInput(CourseRoomSearchVO searchVO, Model model) {
		// CourseRoomSearchVO : 성적입력코드와 시험일코드가 들어있다.
		
		List<Map<String, Object>> oneMap =
				courseAndScoreService.totalGradeResultOneOrList(searchVO);
		// 성적입력코드로 값을 가져올 것이기에 값이 한개만 나올것이다.
		
		System.out.println(oneMap.toString()
				+ " <- oneMap.toString()   updateScoreInput()   CourseAndScoreController.java");
		System.out.println(oneMap.size()
				+ " <- oneMap.size()   updateScoreInput()   CourseAndScoreController.java");
		
		Map<String, Object> scoreInputInfo = oneMap.get(0);
		// 가져온 한개의 객체를 맵에 넣어준다.
		
		
		searchVO.setScoreInputNo(null);
		// 해당 시험을 본 학생 순위리스트를 가져올 것이기에 성적입력코드를 없애준다.
		
		List<Map<String, Object>> scoreStudentRankList =
				courseAndScoreService.totalGradeResultOneOrList(searchVO);
		// 시험일 코드를 이용하여 해당 시험을 본 순위리스트를 가져온다.
		
		
		List<GradingCriteria> GradingCriteriaList =
				courseManageService.gradingCriteriaList();
		// 전체 성적기준 리스트를 가져온다.
		
		
		
		model.addAttribute("scoreInputInfo", scoreInputInfo);
		// 화면에 뿌려줄 학생성적 상세 정보
		
		model.addAttribute("scoreStudentRankList", scoreStudentRankList);
		// 화면에 뿌려줄 해당 시험을 본 학생 성적 리스트
		
		model.addAttribute("scoreStudentRankListSize", scoreStudentRankList.size());
		// 리스트의 사이즈를 보고 해당 학생 성적 목록의 존재 여부 판단
		
		
		model.addAttribute("GradingCriteriaList", GradingCriteriaList);
		// 샐랙트 박스에 넣어줄 성적평가기준 리스트
		
		return "/view/lesson/courseAndScore/detailScoreInput";
	}
	
	
	// 관리자, 강사 : 시험성적 수정 처리
	@PostMapping("/updateScoreInput")
	public String updateScoreInput(
			 ScoreInput scoreInput
			,RedirectAttributes redirectAttributes) {
		
		String message = courseAndScoreService.updateScoreInput(scoreInput);
		
		String path = "redirect:/scoreRankInCourseList";
		// 시험성적 수정에 성공했을 경우 시험성적 순위리스트로 이동하게 초기화한다.
		
		if(message == null) {
			// 리턴받은 메세지가 널이라면 시험성적 수정에 성공했다는 뜻이다.
			
			redirectAttributes.addAttribute("examinationDayNo",
					scoreInput.getExaminationDayNo());
			// 어떤 시험의 순위 리스트인지 검색할 쿼리문장의 값을 넣어준다.
			
		} else {
			// 리턴받은 메세지가 널이 아니라면 시험성적 수정에 실패했다는 뜻이다.
			
			System.out.println("시험성적 수정 실패!!!!!!!!!!!!");
			
			redirectAttributes.addAttribute("scoreInputNo", scoreInput.getScoreInputNo());
			// 시험성적 상세 페이지로 리다이렉트하면서 성적입력코드를 넘겨준다.
			
			path = "redirect:/updateScoreInput";
			// 시험성적 상세 페이지로 이동
		}
		
		return path;
	}
	
	
	// 관리자, 강사 : 시험성적 삭제 처리
	@GetMapping("/deleteScoreInput")
	public String deleteScoreInput(
			 @RequestParam(value = "scoreInputNo") String scoreInputNo
			,@RequestParam(value = "examinationDayNo") String examinationDayNo
			,RedirectAttributes redirectAttributes) {
		System.out.println(scoreInputNo + " <- scoreInputNo   deleteScoreInput()   CourseAndScoreController.java");
		System.out.println(examinationDayNo + " <- examinationDayNo   deleteScoreInput()   CourseAndScoreController.java");
		
		String message = courseAndScoreService.deleteScoreInput(scoreInputNo);
		// 해당 시험성적 삭제 쿼리 실행 후 메세지 반환
		
		System.out.println(message + " <- message   deleteScoreInput()   CourseAndScoreController.java");
		
		redirectAttributes.addAttribute("examinationDayNo", examinationDayNo);
		// 성적 삭제 처리후 어떤 시험의 순위리스트로 이동할 것인지 쿼리 검색키워드를 넣어준다.
		
		return "redirect:/scoreRankInCourseList";
		// 성적 삭제 처리 후 시험성적 순위리스트로 이동한다.
	}
	
	
	// 관리자, 학생 : 특정 학생의 성적 결과 리스트 가져오기
	@GetMapping("/oneStudentTotalGradeResultList")
	public String oneStudentTotalGradeResultList(
			 CourseRoomSearchVO searchVO
			,Model model) {
		
		Member student = memberService.memberSimpleInfo(searchVO.getMemberId());
		// 회원의 간단한 정보를 가져온다. (아이디, 이름, 생년월일)
		
		List<Map<String, Object>> gradeResultList =
				courseAndScoreService.totalGradeResultOneOrList(searchVO);
		// 특정 학생의 회원 아이디를 이용하여 해당 학생의 성적 결과를 가져온다.
		
		List<Subject> subjectList = courseManageService.subjectList();
		// 전체 과목 리스트를 가져온다.
		
		List<AcademyRoom> roomList = courseManageService.academyRoomList();
		// 전체 강의실 리스트를 가져온다.
		
		
		model.addAttribute("student", student);
		// 화면의 제목 부분에 보여줄 학생의 간단한 정보
		
		model.addAttribute("gradeResultList", gradeResultList);
		// 화면에 보여줄 특정 학생의 성적결과 리스트
		
		model.addAttribute("gradeResultListSize", gradeResultList.size());
		// 리스트의 사이즈를 보고 해당 학생의 성적결과 리스트 존재여부 판단
		
		
		model.addAttribute("subjectList", subjectList);
		// 샐랙트 박스에 넣어줄 전체 과목 리스트
		
		model.addAttribute("roomList", roomList);
		// 샐랙트박스에 넣어줄 전체 강의실 리스트
		
		return "/view/lesson/courseAndScore/listOneStudentTotalGradeResult";
	}
}
