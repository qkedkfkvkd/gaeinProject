package com.cafe24.smart_academy.academy_manage.course.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleMapper {
	
	List<Map<String, Object>> scheduleList();
	// 전체 강좌 시간표 가져오기
}
