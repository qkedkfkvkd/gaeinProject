package com.cafe24.smart_academy.academy_manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {
// 과목 및 강좌 등록, 성적 관리 등
	
	// 관리자가 학생목록에서 해당 학생의 전체 성적 결과를 보고자 할때 사용한다.
	@GetMapping("/oneStudentTotalGradeResult")
	public String oneStudentTotalGradeResult(@RequestParam(value = "memberId")String memberId) {
		
		
		return "";
	}
}
