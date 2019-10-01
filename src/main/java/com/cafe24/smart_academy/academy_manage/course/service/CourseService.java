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
	public String CourseRoomAssignByCourseAssignmentNo(String inputCourseAssignNo) {
		return courseMapper.CourseRoomAssignByCourseAssignmentNo(inputCourseAssignNo);
	}
	
	
	// 관리자 : 강좌 추가 처리
	public String addCourse(Course course, CourseRoomAssignment courseAssign) {
		String courseExistChk = courseMapper.courseByCourseNo(course.getCourseNo());
		// 강좌테이블에 해당 강좌코드가 존재하는지 확인
		
		String courseAssignExistChk =
				courseMapper.CourseRoomAssignByCourseAssignmentNo(courseAssign.getCourseAssignmentNo());
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
	
	
	// 관리자 : 강사가 배정이 안된 강좌목록 간단히 가져오기
	// 강좌코드, 강좌명, 과목명, 강좌등록일
	public List<Map<String, Object>> courseNotAssignmentTeacherSimple() {
		return courseMapper.courseNotAssignmentTeacherSimple();
	}
	
	
	// 관리자 : 강사가 배정이 안된 강좌의 상세 정보 가져오기
	// 관리자 : 강좌 상세정보 가져오기
	public Course detailCourseByCourseNo(String courseNo) {
		return courseMapper.detailCourseByCourseNo(courseNo);
	}
	
	
	// 관리자 : 강사가 배정이 안된 강좌 강의실 배정 목록 가져오기
	public List<Map<String, Object>> courseNotAssignTeacherList(String courseNo) {
		return courseMapper.courseNotAssignTeacherList(courseNo);
	}
	
	
	// 관리자 : 강좌 수정 처리
	public String updateCourse(Course course) {
		String resultMessage = "updateCourseFail";
		// 만약 강좌 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = courseMapper.updateCourse(course);
		// 강좌 수정 처리
		
		if(result == 1) {  // 강좌 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 강좌 삭제 처리
	public String deleteCourse(String courseNo) {
		String existChk = courseByCourseNo(courseNo);
		// 삭제하기 전 해당 강좌코드로된 강좌가 존재하는지 확인
		
		String resultMessage = "deleteCourseFail";
		// 강좌 삭제 실패로 초기화
		
		if(existChk != null) { // 해당 강좌코드 존재(삭제 가능)
			int courseAssignResult =
					courseMapper.deleteCourseRoomAssignmentByCourseNo(courseNo);
			// 강좌 강의실 배정 테이블에서 해당 강좌코드를 참조하는 모든 레코드 삭제 처리
			
			int courseResult = courseMapper.deleteCourse(courseNo);
			// 해당 강좌 삭제 처리
			
			if(courseAssignResult == 1 & courseResult == 1) { // 해당 강좌 삭제 성공
				resultMessage = "deleteCourseSuccess";
				// 강좌 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
}
