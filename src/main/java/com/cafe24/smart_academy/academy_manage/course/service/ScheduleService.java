package com.cafe24.smart_academy.academy_manage.course.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.course.mapper.ScheduleMapper;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;

@Service
@Transactional
public class ScheduleService {
	
	@Autowired
	ScheduleMapper scheduleMapper;
	
	
	// 전체 강좌 시간표 가져오기
	// 관리자 : 강좌 승인요청된 강좌시간표를 가져온다.
	public List<Map<String, Object>> scheduleList(CourseRoomSearchVO searchVO) {
		return scheduleMapper.scheduleList(searchVO);
	}
}
