package com.cafe24.smart_academy.academy_manage.course.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.smart_academy.academy_manage.course.vo.Course;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomAssignment;
import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;

@Mapper
public interface CourseMapper {
	
	public String courseByCourseNo(String inputCourseNo);
	// 관리자 : 강좌테이블에서 해당 강좌코드 존재 확인
	
	public int addCourse(Course course);
	// 관리자 : 강좌 추가 처리
	
	public List<Map<String, Object>> courseOneOrList();
	// 관리자 : 강좌목록 가져오기
	
	public List<Map<String, Object>> courseOneOrList(Map<String, Object> map);
	// 관리자 : 강좌 검색결과 목록 가져오기
	// 관리자 : 해당 강좌코드로 강좌 상세정보 가져오기
	
	public List<Course> courseListBySubjectNo(String subjectNo);
	// 관리자 : 과목 리스트에서 과목코드 선택시 자동으로
	//			해당 과목코드에 맞는 강좌 나오게 하기
	
	public int updateCourse(Course course);
	// 관리자 : 강좌 수정 처리
	
	public int deleteCourse(String courseNo);
	// 관리자 : 해당 강좌 삭제 처리
	
	
	
	
	
	public List<Map<String, Object>> courseNotAssignmentTeacherSimpleList();
	// 관리자 : 강사와 매칭되지 않은 강좌목록 간단히 가져오기
	// 강좌코드, 강좌명, 과목명, 변경여부, 강좌등록일
	
	public List<Map<String, Object>> courseNotAssignmentTeacherSimpleList(Course course);
	// 관리자 : 강사와 매칭되지 않은 강좌목록 검색결과 가져오기
	// 강좌코드, 강좌명, 과목명, 변경여부, 강좌등록일
	
	public List<Map<String, Object>> courseAssignTeacherList();
	// 관리자 : 강사가 배정된 강좌리스트 가져오기
	
	public List<Map<String, Object>> courseAssignTeacherList(Course course);
	// 강사가 배정된 강좌 검색결과 리스트
	
	
	
	
	
	
	
	public String courseRoomAssignByCourseAssignmentNo(String courseAssignmentNo);
	// 관리자 : 강좌강의실배정 테이블에서 해당 배정코드 존재여부 확인
	
	public int addCourseRoomAssignment(CourseRoomAssignment courseAssign);
	// 관리자 : 강좌강의실배정 추가 처리
	
	public List<Map<String, Object>> courseNotAssignTeacherOneOrList(Map<String, Object> map);
	// 관리자 : 강사가 배정이 안된 강좌 강의실 배정 목록 상세히 가져오기
	// 관리자 : 강사가 배정이 안된 강좌의 상세 정보 가져오기
	
	public List<Map<String, Object>> courseAssignmentOneOrList();
	// 관리자 : 강좌배정목록 가져오기
	
	public List<Map<String, Object>> courseAssignmentOneOrList(CourseRoomSearchVO searchVO);
	// 관리자 : 선택한 과목코드 혹은 과목코드와 강좌코드로 강좌 리스트 검색
	// 관리자 : 강좌 강의실 배정 및 강사 강좌 배정 상세 데이터 가져오기
	
	public int updateCourseAssignment(CourseRoomAssignment assignment);
	// 관리자 : 강좌강의실배정 수정처리
	
	public int deleteCourseRoomAssignment(String courseAssignmentNo);
	// 관리자 : 해당 강좌배정 삭제처리
	
	
	
	//public int deleteCourseRoomAssignmentByCourseNo(String courseNo);
	// 관리자 : 강좌 강의실 배정 테이블에서 해당 강좌코드를 참조하는 모든 레코드 삭제 처리
	
}
