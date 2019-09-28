package com.cafe24.smart_academy.academy_manage.courseandscore.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.courseandscore.mapper.CourseAndScoreMapper;

@Service
@Transactional
public class CourseAndScoreService {
	
	@Autowired
	CourseAndScoreMapper courseAndScoreMapper;
	
	// 관리자, 강사, 학생
	// 특정 검색 키워드를 입력받아 해당 키워드에 맞는 성적결과 리스트를 리턴한다.
	 public List<Map<String, Object>> totalGradeResultList(Map<String, Object> map) {
		 return courseAndScoreMapper.totalGradeResultList(map);
	 }
}
