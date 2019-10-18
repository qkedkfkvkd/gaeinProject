package com.cafe24.smart_academy.academy_manage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String index(HttpSession session) {
		
//		String path = "/view/index";
//		// 권한이 학생이거나 로그인 전일 경우의 메인페이지로 초기화
//		
//		if(session.getAttribute("memberLevel") != null) {  // 로그인을 한 경우라면
//			String memberLevel = (String)session.getAttribute("memberLevel");
//			
//			if(memberLevel.equals("관리자")) {
//				path = "/view/adminIndex";
//				// 권한이 관리자일 경우 관리자전용 메인페이지로 향한다.
//				
//			} else if(memberLevel.equals("강사")) {
//				path = "/view/teacherIndex";
//				// 권한이 강사일 경우 강사전용 메인페이지로 향한다.
//			}
//		}
//
//		return path;
		
		return "/view/index";
	}
}
