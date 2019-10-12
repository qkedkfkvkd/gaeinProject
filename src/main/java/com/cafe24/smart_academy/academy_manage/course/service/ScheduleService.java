package com.cafe24.smart_academy.academy_manage.course.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.course.mapper.ScheduleMapper;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseSchedule;

@Service
@Transactional
public class ScheduleService {
	
	@Autowired
	ScheduleMapper scheduleMapper;
	// 시간표 관리 매퍼
	
	// 강사 : 시간표 추가 폼에서 관리자의 승인 여부 상관없이
	// 데이터베이스에 있는 모든 시간표 가져오기
	public List<Map<String, Object>> scheduleOneOrList() {
		return scheduleMapper.scheduleOneOrList();
	}
	
	
	// 전체 강좌 시간표 가져오기
	// 관리자 : 강좌 승인요청된 강좌시간표를 가져온다.
	public List<Map<String, Object>> scheduleOneOrList(CourseRoomSearchVO searchVO) {
		return scheduleMapper.scheduleOneOrList(searchVO);
	}
	
	
	// 강사 : 강좌 시간표코드 중복 확인
	public String CourseScheduleByscheduleNo(String scheduleNo) {
		return scheduleMapper.CourseScheduleByscheduleNo(scheduleNo);
	}
	
	
	// 강사 : 강좌 시간표 추가 처리
	public String addSchedule(CourseSchedule schedule) {
		String existChk = CourseScheduleByscheduleNo(schedule.getScheduleNo());
		// 강좌 시간표테이블에 해당 시간표코드가 존재하는지 확인
		
		String resultMessage = "usedScheduleCode";
		// 만약 이미 등록된 시간표코드가 있다면 이 메세지가 리턴될 것이다.
		
		if(existChk == null) {
			// 존재하지 않는 시간표코드 (입력가능한 시간표코드)
			
			int result = scheduleMapper.addSchedule(schedule);
			// 강좌시간표 추가 처리
			
			if(result == 1) {  // 강좌시간표 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지에 널값을 준다
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자, 강사 : 시간표 수정처리
	public String updateSchedule(CourseSchedule schedule) {
		String resultMessage = "updateScheduleFail";
		// 만약 시간표 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = scheduleMapper.updateSchedule(schedule);
		// 시간표 수정 처리
		
		if(result == 1) {  // 시간표 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자, 강사 : 시간표 삭제 처리
	public String deleteSchedule(String scheduleNo) {
		
		String existChk = CourseScheduleByscheduleNo(scheduleNo);
		// 삭제하기 전 해당 시간표코드로된 강좌시간표가 존재하는지 확인
		
		String resultMessage = "deleteScheduleFail";
		// 시간표삭제 실패로 초기화
		
		if(existChk != null) { // 해당 시간표코드 존재(삭제 가능)
			int result = scheduleMapper.deleteSchedule(scheduleNo);
			// 해당 시간표 삭제 처리
			
			if(result == 1) { // 해당 시간표 삭제 성공
				resultMessage = "deleteScheduleSuccess";
				// 시간표 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
}
