package com.cafe24.smart_academy.academy_manage.course.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseSchedule;

@Mapper
public interface ScheduleMapper {
	
	public List<Map<String, Object>> scheduleOneOrList();
	// 강사 : 시간표 추가 폼에서 관리자의 승인 여부 상관없이
	// 데이터베이스에 있는 모든 시간표 가져오기
	
	public List<Map<String, Object>> scheduleOneOrList(CourseRoomSearchVO searchVO);
	// 전체 강좌 시간표 가져오기
	// 관리자 : 강좌 승인요청된 강좌시간표를 가져온다.
	
	public String CourseScheduleByscheduleNo(String scheduleNo);
	// 강사 : 강좌 시간표코드 중복 확인
	
	public int addSchedule(CourseSchedule schedule);
	// 강사 : 강좌 시간표 추가 처리
	
	public int updateSchedule(CourseSchedule schedule);
	// 관리자, 강사 : 시간표 수정처리
	
	public int deleteSchedule(String scheduleNo);
	// 관리자, 강사 : 시간표 삭제 처리
}
