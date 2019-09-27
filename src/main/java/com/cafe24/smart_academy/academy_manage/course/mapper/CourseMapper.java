package com.cafe24.smart_academy.academy_manage.course.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.course.vo.Course;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomAssignment;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;

@Mapper
public interface CourseMapper {
	
	public String courseByCourseNo(String inputCourseNo);
	// 관리자 : 강좌테이블에서 해당 강좌코드 존재 확인
	
	public String CourseRoomAssignBycourseAssignmentNo(String courseAssignmentNo);
	// 관리자 : 강좌강의실배정 테이블에서 해당 배정코드 존재여부 확인
	
	public int addCourse(Course course);
	// 관리자 : 강좌 추가 처리
	
	public int addCourseRoomAssignment(CourseRoomAssignment courseAssign);
	// 관리자 : 강좌강의실배정 추가 처리
	
	public List<Map<String, Object>> courseAssignmentList();
	// 관리자 : 강좌배정목록 가져오기
	
	public List<Map<String, Object>> courseAssignmentList(CourseRoomSearchVO searchVO);
	// 관리자 : 선택한 과목코드 혹은 과목코드와 강좌코드로 강좌 리스트 검색
	
	public List<Map<String, Object>> courseList();
	// 관리자 : 강좌목록 가져오기
	
	public List<Course> courseListBySubjectNo(String subjectNo);
	// 관리자 : 과목 리스트에서 과목코드 선택시 자동으로
	//			해당 과목코드에 맞는 강좌 나오게 하기
	
	public List<Map<String, Object>> courseNotAssignmentRoom();
	// 관리자 : 강의실에 배정되지 않은 강좌목록
	
	public List<Map<String, Object>> courseNotAssignmentTeacher();
	// 관리자 : 강사와 매칭되지 않은 강좌목록
}
