package com.cafe24.smart_academy.academy_manage.course.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.course.mapper.CourseMapper;
import com.cafe24.smart_academy.academy_manage.course.vo.Course;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomAssignment;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;

@Service
@Transactional
public class CourseService {
	
	@Autowired
	CourseMapper courseMapper;
	
	// 관리자 : 강좌테이블에서 해당 강좌코드 존재 확인
	public String courseByCourseNo(String inputCourseNo) {
		return courseMapper.courseByCourseNo(inputCourseNo);
	}
	
	
	// 관리자 : 강좌강의실배정 테이블에서 해당 배정코드 존재 확인
	public String CourseRoomAssignBycourseAssignmentNo(String inputCourseAssignNo) {
		return courseMapper.CourseRoomAssignBycourseAssignmentNo(inputCourseAssignNo);
	}
	
	
	// 관리자 : 강좌 추가 처리
	public String addCourse(Course course, CourseRoomAssignment courseAssign) {
		String courseExistChk = courseMapper.courseByCourseNo(course.getCourseNo());
		// 강좌테이블에 해당 강좌코드가 존재하는지 확인
		
		String courseAssignExistChk =
				courseMapper.CourseRoomAssignBycourseAssignmentNo(courseAssign.getCourseAssignmentNo());
		// 강좌강의실배정 테이블에 해당 배정코드가 존재하는지 확인
		
		String resultMessage = "usedCourseCode";
		// 만약 이미 등록된 강좌코드가 있다면 이 메세지가 리턴될 것이다.
		
		if(courseExistChk == null &
		   courseAssignExistChk == null) {
			// 존재하지 않는 강좌코드와 배정코드 (입력가능한 강좌코드, 배정코드)
			int courseResult = courseMapper.addCourse(course);
			// 강좌 추가 처리
			
			int courseAssignResult = courseMapper.addCourseRoomAssignment(courseAssign);
			// 강좌강의실배정 추가 처리
			
			if(courseResult == 1 &
			   courseAssignResult == 1) {  // 강좌와 강좌강의실배정 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지에 널값을 준다
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 강좌배정목록 가져오기
	public List<Map<String, Object>> courseAssignmentList() {
		return courseMapper.courseAssignmentList();
	}
	
	
	// 관리자 : 선택한 과목코드 혹은 과목코드와 강좌코드로 강좌 리스트 검색
	public List<Map<String, Object>> courseAssignmentList(CourseRoomSearchVO searchVO) {
		return courseMapper.courseAssignmentList(searchVO);
	}
	
	
	// 관리자 : 강좌목록 가져오기
	public List<Map<String, Object>> courseList() {
		return courseMapper.courseList();
	}
	
	
	// 관리자 : 과목 리스트에서 과목코드 선택시 자동으로
	//			해당 과목코드에 맞는 강좌 나오게 하기
	public List<Course> courseListBySubjectNo(String subjectNo) {
		return courseMapper.courseListBySubjectNo(subjectNo);
	}
	
	
	// 관리자 : 강의실 혹은 강좌가 배정이 안된 강좌목록
	public List<Map<String, Object>> courseNotAssignment() {
		return courseMapper.courseNotAssignmentRoom();
	}
	
	
	// 관리자 : 강의실 혹은 강좌가 배정이 안된 강좌목록
	public Map<String, Object> courseNotAssignment123() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		
		List<Map<String, Object>> courseNotAssignmentRoom =
				courseMapper.courseNotAssignmentRoom();
		// 강의실에 배정되지 않은 강좌 목록
		
		List<Map<String, Object>> courseNotAssignmentTeacher =
				courseMapper.courseNotAssignmentTeacher();
		// 강사와 매칭되지 않은 강좌 목록
		
		return map;
	}
}
