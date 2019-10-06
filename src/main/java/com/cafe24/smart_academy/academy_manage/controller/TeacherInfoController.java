package com.cafe24.smart_academy.academy_manage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.smart_academy.academy_manage.course.service.CourseService;
import com.cafe24.smart_academy.academy_manage.member.service.MemberService;
import com.cafe24.smart_academy.academy_manage.member.service.TeacherInfoService;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberSearchVO;
import com.cafe24.smart_academy.academy_manage.member.vo.Teacher;

@Controller
public class TeacherInfoController {
// 강사 정보 관리 컨트롤러
	
	@Autowired
	private MemberService memberService;
	// 아이디 및 이메일 중복 확인
	// 로그인 및 회원신상정보 테이블 입력, 수정, 삭제
	// 회원 간단한 정보 가져오기 (아이디, 이름, 생년월일)
	
	@Autowired
	private TeacherInfoService teacherInfoService;
	// 강사 정보 관리 서비스 객체
	
	@Autowired
	private CourseService courseService;
	// 강좌 정보 관리 서비스 객체
	
	
	// 강사 등록 폼으로 이동
	@GetMapping("/addTeacher")
	public String addTeacher(Model model) {
		
		List<Map<String, Object>> courseList =courseService.courseOneOrList();
		// 담당 강좌를 선택하게 하기 위해서 리스트를 가져온다.
		// 강좌코드, 강좌명, 강좌 등록일, 과목코드, 과목명
		
		model.addAttribute("courseList", courseList);
		// 화면에 보여줄 담당강좌 리스트 객체
		
		return "/view/personnel/addTeacherInfo";
	}
	
	
	// 강사 등록 처리
	// 강좌 강사 매칭
	@PostMapping("/addTeacher")
	public String addTeacher(
			 MemberLogin loginInfo
			,Member memberInfo
			,Teacher teacher
			,Model model
			,RedirectAttributes redirectAttributes) {
		
		String message = teacherInfoService.addTeacher(loginInfo, memberInfo, teacher);
		
		String path = "/view/personnel/addTeacherInfo";
		// 입력 실패했을 경우 강사 입력 폼 페이지로 이동한다.
		
		
		if(message == null) { // 널값이면 입력 성공했다는 뜻이다.
			path = "redirect:/teacherList";
			// 입력 성공 시 강사 목록 페이지로 이동한다.
			
			redirectAttributes.addAttribute("memberLevel", "강사");
			// 권한이 강사인 회원목록을 가져와야하므로 리스트 검색 키워드를 넣어준다.
			
		} else if(message.equals("idUsed")) { // 아이디 중복 메세지가 넘어올 경우
			model.addAttribute("idOverlap", "이미 사용 중인 아이디입니다.");
			
		} else {  // 이메일 중복 메세지가 넘어올 경우
			model.addAttribute("emailOverlap", "이미 사용 중인 이메일입니다.");
		}
		
		return path;
	}
	
	
	// 강사 목록 보기
	@GetMapping("/teacherList")
	public String teacherList(MemberSearchVO memberSearchVO, Model model) {
		System.out.println(memberSearchVO.getMemberLevel()
				+ " <- memberLevel   teacherList()   TeacherInfoController.java");
		
		List<Map<String, Object>> teacherInfoList =
				teacherInfoService.teacherInfoList(memberSearchVO);
		// 강사 정보 목록 가져오기
		// 아이디, 권한, 이름, 이메일, 휴대폰번호, 담당 강좌코드, 과목명, 강좌명, 강사 등록일
		
		model.addAttribute("teacherInfoList", teacherInfoList);
		// 화면에 보여줄 강사 정보 리스트
		
		model.addAttribute("teacherInfoListSize", teacherInfoList.size());
		// 화면에 등록된 강사 목록이 없다는 메세지를 띄워줄 것인지
		// 강사 목록을 띄워줄 것인지 판단한다.
		
		return "/view/personnel/listTeacherInfo";
	}
}
