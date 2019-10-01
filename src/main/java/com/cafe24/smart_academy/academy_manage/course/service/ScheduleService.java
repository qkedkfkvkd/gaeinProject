package com.cafe24.smart_academy.academy_manage.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.course.mapper.ScheduleMapper;

@Service
@Transactional
public class ScheduleService {
	
	@Autowired
	ScheduleMapper scheduleMapper;
	
	
}
