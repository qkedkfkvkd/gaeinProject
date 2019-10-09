package com.cafe24.smart_academy.academy_manage.course.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;

@Mapper
public interface ScheduleMapper {
	
	List<Map<String, Object>> scheduleList(CourseRoomSearchVO searchVO);
	// 전체 강좌 시간표 가져오기
	// 관리자 : 강좌 승인요청된 강좌시간표를 가져온다.
}
