package com.cafe24.smart_academy.academy_manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.smart_academy.academy_manage.courseandscore.service.CourseAndScoreService;

@Controller
public class CourseAndScoreController {
// 수강 및 성적관리 컨트롤러
	
	@Autowired
	CourseAndScoreService courseAndScoreService;
	// 수강 및 성적관리 서비스
	
	// 관리자가 특정 학생의 전체 성적 결과 조회
//	@GetMapping("/oneStudentTotalGradeResult")
//	public String oneStudentTotalGradeResult(
//			@RequestParam(value = "memberId")String memberId) {
//		
//		return "";
//	}
}
