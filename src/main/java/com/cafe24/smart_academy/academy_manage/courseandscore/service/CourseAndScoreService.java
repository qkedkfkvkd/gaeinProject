package com.cafe24.smart_academy.academy_manage.courseandscore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.courseandscore.mapper.CourseAndScoreMapper;

@Service
@Transactional
public class CourseAndScoreService {
	
	@Autowired
	CourseAndScoreMapper courseAndScoreMapper;
	
	 
}
