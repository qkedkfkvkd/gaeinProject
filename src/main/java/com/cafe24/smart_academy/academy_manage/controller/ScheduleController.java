package com.cafe24.smart_academy.academy_manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.smart_academy.academy_manage.course.service.ScheduleService;

@Controller
public class ScheduleController {
// 시간표 관리 컨트롤러
	
	@Autowired
	ScheduleService scheduleService;
	
	// 전체 강좌 시간표를 조회한다.
	/*@GetMapping("/scheduleList")
	public String scheduleList(Model model) {
		
		return "";
	}*/
}
