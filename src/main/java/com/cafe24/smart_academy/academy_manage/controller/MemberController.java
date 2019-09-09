package com.cafe24.smart_academy.academy_manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.smart_academy.academy_manage.member.service.MemberService;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.Member_login;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	// 로그인 버튼 클릭시 로그인 폼으로 이동
	@GetMapping("/loginMember")
	public String memberLogin() {
		return "/view/login/login";
	}
	
	
	// 로그인 처리
	@PostMapping("/loginMember")
	public String memberLogin(Model model, HttpSession session, Member_login login) {
		System.out.println(login.getMember_id() + " <- member_id   memberLogin()   MemberController.java");
		System.out.println(login.getMember_pw() + " <- member_pw   memberLogin()   MemberController.java");
		
		Map<String, Object> map = memberService.memberLogin(login);
		// 아이디와 비밀번호를 가지고 로그인 서비스 실행
		
		String path = "";
		// 페이지 이동할 경로
		
		if(map.get("result") == null) { // result 키값의 객체가 없다 -> 로그인 성공했다.
			System.out.println(map.get("member_id") + "<- map.member_id   memberLogin()   MemberController.java");
			System.out.println(map.get("member_level") + "<- map.member_level   memberLogin()   MemberController.java");
			System.out.println(map.get("member_name") + "<- map.member_name   memberLogin()   MemberController.java");
			
			session.setAttribute("member_id", map.get("member_id"));
			session.setAttribute("member_level", map.get("member_level"));
			session.setAttribute("member_name", map.get("member_name"));
			
			String level = (String)map.get("member_level");
			if(level.equals("관리자")) {
				path = "/view/admin_index";
			} else if(level.equals("강사")) {
				path = "/view/teacher_index";
			} else {
				path = "/view/index";
			}
			
		} else {
			System.out.println(map.get("result") + "<- map.result   memberLogin()   MemberController.java");
			
			path = "/view/login/login";
			// 로그인 실패했으므로 로그인 페이지로 이동한다.
			
			model.addAttribute("result", map.get("result"));
			// 로그인 실패 관련 메세지를 넣어준다(아이디 불일치 혹은 비밀번호 불일치 메세지)
		}
		return path;
	}
	
	
	// 로그아웃
	@GetMapping("/logoutMember")
	public String memberLoginOut(HttpSession session) {
		session.invalidate();
		// 세션 무효화
		
		return "redirect:/";
	}
	
	
	// 관리자의 회원 등록폼 이동하기
	@GetMapping("/addMember")
	public String addMember() {
		return "/view/academyRegister/memberInfo/memberInfo";
	}
	
	
	// 회원 등록 폼에서 아이디 중복버튼 눌렀을 경우
	@PostMapping("/memberIdOverlapChk")
	@ResponseBody
	public Map<Object, Object> memberIdOverlapChk(@RequestBody String member_id) {
		System.out.println(member_id + "<- member_id   memberIdOverlapChk()   MemberController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String member_id_result = memberService.memberLoginById(member_id);
		// 서비스의 아이디 중복확인 메소드 호출
		
		if(member_id_result == null) {
			map.put("result", 1);
			// 로그인 테이블에 해당 아이디값이 존재하지 않음 : 사용 가능한 아이디
			
			//result = "{\"result\":\"1\"}"; // {"result":"1"} -> 사용 가능한 아이디
		} else {
			map.put("result", 0);
			// 로그인 테이블에 해당 아이디값이 존재함 : 사용 중인 아이디
			
			//result = "{\"result\":\"0\"}"; // {"result":"0"} -> 사용 중인 아이디
		}
		
		return map;
	}
	
	
	// 회원 등록 폼에서 이메일 중복버튼 눌렀을 경우
	@PostMapping("/memberEmailOverlapChk")
	@ResponseBody
	public Map<Object, Object> memberEmailOverlapChk(@RequestBody String member_email) {
		System.out.println(member_email + "<- member_email   memberEmailOverlapChk()   MemberController.java");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String member_email_result = memberService.memberByEmail(member_email);
		// 서비스의 이메일 중복확인 메소드 호출
		
		if(member_email_result == null) {
			map.put("result", 1);
			// 회원신상정보 테이블에 해당 이메일 값이 존재하지 않음 : 사용 가능한 이메일
			
		} else {
			map.put("result", 0);
			// 회원신상정보 테이블에 해당 이메일 값이 존재함 : 사용 중인 이메일
		}
		
		return map;
	}
	
	// 회원 추가 처리 메소드
	@PostMapping("/addMember")
	public String addMember(Member_login loginInfo, Member memberInfo) {
		String message = memberService.addMember(loginInfo, memberInfo);
		
		String path = "/view/academyRegister/memberInfo/memberInfo";
		// 입력 실패했을 경우 회원 등록 폼으로 이동한다.
		
		if(message == null) { // 널값이면 입력 성공했다는 뜻이다.
			path = "redirect:/";
			// 관리자 인덱스 페이지로 이동
		}
		
		return path;
	}
}
