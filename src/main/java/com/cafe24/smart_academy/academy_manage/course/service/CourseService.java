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
import com.cafe24.smart_academy.academy_manage.member.mapper.TeacherInfoMapper;
import com.cafe24.smart_academy.academy_manage.member.vo.Teacher;

@Service
@Transactional
public class CourseService {
	
	@Autowired
	CourseMapper courseMapper;
	// 강좌 관리 매퍼
	
	@Autowired
	TeacherInfoMapper teacherInfoMapper;
	// 강사 기본정보 관리 매퍼
	
	
	// 관리자 : 강좌테이블에서 해당 강좌코드 존재 확인
	public String courseByCourseNo(String inputCourseNo) {
		return courseMapper.courseByCourseNo(inputCourseNo);
	}
	
	
	// 관리자 : 강좌 추가 처리
	public String addCourse(Course course) {
		String existChk = courseMapper.courseByCourseNo(course.getCourseNo());
		// 강좌테이블에 해당 강좌코드가 존재하는지 확인
		
		String resultMessage = "usedCourseCode";
		// 만약 이미 등록된 강좌코드가 있다면 이 메세지가 리턴될 것이다.
		
		if(existChk == null) {
			// 존재하지 않는 강좌코드 (입력가능한 강좌코드)
			
			int result = courseMapper.addCourse(course);
			// 강좌 추가 처리
			
			if(result == 1) {  // 강좌 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지에 널값을 준다
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 강좌목록 가져오기
	public List<Map<String, Object>> courseOneOrList() {
		return courseMapper.courseOneOrList();
	}
	
	
	// 관리자 : 강좌 검색결과 목록 가져오기
	// 관리자 : 해당 강좌코드로 강좌 상세정보 가져오기
	public List<Map<String, Object>> courseOneOrList(Map<String, Object> map) {
		return courseMapper.courseOneOrList(map);
	}
	
	
	
	// 관리자 : 강좌 수정 처리
	public int updateCourse(Course course) {
		
		int changeCheck = 0;
		// 수정 사항이 아무 것도 없다는 것으로 초기화
		
		System.out.println(course.getCourseIsChanged()
				+ " <- course.getCourseIsChanged()   updateCourse()   CourseService.java");
		
		if(course.getCourseIsChanged().equals("유")) {
			// 강좌테이블에 변경사항이 존재한다면
			
			changeCheck = courseMapper.updateCourse(course);
			// 강좌 수정 처리
		}
		
		return changeCheck;
	}
	
	
	// 관리자 : 강좌 삭제 처리
	public String deleteCourse(String courseNo) {
		
		String existChk = courseByCourseNo(courseNo);
		// 삭제하기 전 해당 강좌코드로된 강좌가 존재하는지 확인
		
		String resultMessage = "deleteCourseFail";
		// 강좌 삭제 실패로 초기화
		
		if(existChk != null) { // 해당 강좌코드 존재(삭제 가능)
			int result = courseMapper.deleteCourse(courseNo);
			// 해당 강좌 삭제 처리
			
			if(result == 1) { // 해당 강좌 삭제 성공
				resultMessage = "deleteCourseSuccess";
				// 강좌 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
	
	
	
	
	
	
	
	
	// 관리자 : 강좌강의실배정 테이블에서 해당 배정코드 존재 확인
	public String courseRoomAssignByCourseAssignmentNo(String inputCourseAssignNo) {
		return courseMapper.courseRoomAssignByCourseAssignmentNo(inputCourseAssignNo);
	}
	
	
	// 관리자 : 강좌강의실 배정 추가 처리
	public String addCourseAssign(CourseRoomAssignment courseAssign) {
		
		String existChk = courseMapper.courseRoomAssignByCourseAssignmentNo(
												courseAssign.getCourseAssignmentNo());
		// 강좌강의실배정 테이블에 해당 배정코드가 존재하는지 확인
		
		String resultMessage = "usedCourseAssignCode";
		// 만약 이미 등록된 강좌배정코드가 있다면 이 메세지가 리턴될 것이다.
		
		if(existChk == null) {
			// 존재하지 않는 강좌배정코드 (입력가능한 강좌배정코드)
			
			int result = courseMapper.addCourseRoomAssignment(courseAssign);
			// 강좌강의실배정 추가 처리
			
			if(result == 1) {  // 강좌강의실배정 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지에 널값을 준다
			}
		}
		
		return resultMessage;
	}
	
	
	
	// 관리자 : 강좌배정목록 가져오기
	public List<Map<String, Object>> courseAssignmentOneOrList() {
		return courseMapper.courseAssignmentOneOrList();
	}
	
	
	// 관리자 : 선택한 과목코드 혹은 과목코드와 강좌코드로 강좌 리스트 검색
	// 관리자 : 강좌 강의실 배정 및 강사 강좌 배정 상세 데이터 가져오기
	public List<Map<String, Object>> courseAssignmentOneOrList(CourseRoomSearchVO searchVO) {
		return courseMapper.courseAssignmentOneOrList(searchVO);
	}
	
	
	// 관리자 : 강좌 강의실 배정 및 강사 강좌 배정 수정 처리
	public int updateCourse(CourseRoomAssignment assignment, Course course, Teacher teacher) {
		
		int changeCheck = 0;
		// 수정 사항이 아무 것도 없다는 것으로 초기화
		
		System.out.println(assignment.getCourseAssignmentIsChanged()
				+ " <- assignment.getCourseAssignmentIsChanged()   updateCourse()   CourseService.java");
		
		System.out.println(course.getCourseIsChanged()
				+ " <- course.getCourseIsChanged()   updateCourse()   CourseService.java");
		
		System.out.println(teacher.getTeacherIsChanged()
				+ " <- teacher.getTeacherIsChanged()   updateCourse()   CourseService.java");
		
		
		if(assignment.getCourseAssignmentIsChanged().equals("유")) {
			// 강좌 강의실 배정테이블에 변경사항이 존재한다면
			
			int courseAssignmentResult = courseMapper.updateCourseAssignment(assignment);
			// 강좌 강의실 배정 수정 처리
			
			if(courseAssignmentResult == 1) { // 강좌 강의실 배정 수정 성공시
				changeCheck++;
				// 한개 수정되었다.
			}
		}
		
		if(course.getCourseIsChanged().equals("유")) {
			// 강좌테이블에 변경사항이 존재한다면
			
			int courseResult = courseMapper.updateCourse(course);
			// 강좌 수정 처리
			
			if(courseResult == 1) { // 강좌 수정 성공시
				changeCheck++;
				// 한개 수정되었다.
			}
		}
		
		if(teacher.getTeacherIsChanged().equals("유")) {
			// 강사테이블에 변경사항이 존재한다면 (강사 담당 강좌코드가 바뀌었다면)
			
			int teacherResult = teacherInfoMapper.updateTeacher(teacher);
			// 강사 수정 처리
			
			if(teacherResult == 1) { // 강사 수정 성공시
				changeCheck++;
				// 한개 수정되었다.
			}
		}
		
		return changeCheck;
	}
	
	
	
	
	
	
	
	// 관리자 : 과목 리스트에서 과목코드 선택시 자동으로
	//			해당 과목코드에 맞는 강좌 나오게 하기
	public List<Course> courseListBySubjectNo(String subjectNo) {
		return courseMapper.courseListBySubjectNo(subjectNo);
	}
	
	
	// 관리자 : 강사가 배정이 안된 강좌목록 간단히 가져오기
	// 강좌코드, 강좌명, 과목명, 강좌등록일
	public List<Map<String, Object>> courseNotAssignmentTeacherSimpleList() {
		return courseMapper.courseNotAssignmentTeacherSimpleList();
	}
	
	
	// 관리자 : 강사가 배정이 안된 강좌 강의실 배정 목록 상세하게 가져오기
	// 관리자 : 강사가 배정이 안된 강좌의 상세 정보 가져오기
	public List<Map<String, Object>> courseNotAssignTeacherOneOrList(Map<String, Object> map) {
		return courseMapper.courseNotAssignTeacherOneOrList(map);
	}
	
	
	// 관리자 : 강사가 배정이 안된 강좌 수정 처리
	public int updateCourseNotAssignTeacher(CourseRoomAssignment assignment, Course course) {
		
		int changeCheck = 0;
		// 수정 사항이 아무 것도 없다는 것으로 초기화
		
		System.out.println(assignment.getCourseAssignmentIsChanged()
				+ " <- assignment.getCourseAssignmentIsChanged()   updateCourseNotAssignTeacher()   CourseService.java");
		
		System.out.println(course.getCourseIsChanged()
				+ " <- course.getCourseIsChanged()   updateCourseNotAssignTeacher()   CourseService.java");
		
		if(assignment.getCourseAssignmentIsChanged().equals("유")) {
			// 강좌 강의실 배정테이블에 변경사항이 존재한다면
			
			int courseAssignmentResult = courseMapper.updateCourseAssignment(assignment);
			// 강좌 강의실 배정 수정 처리
			
			if(courseAssignmentResult == 1) { // 강좌 강의실 배정 수정 성공시
				changeCheck++;
				// 한개 수정되었다.
			}
		}
		
		if(course.getCourseIsChanged().equals("유")) {
			// 강좌테이블에 변경사항이 존재한다면
			
			int courseResult = courseMapper.updateCourse(course);
			// 강좌 수정 처리
			
			if(courseResult == 1) { // 강좌 수정 성공시
				changeCheck++;
				// 한개 수정되었다.
			}
		}
		
		return changeCheck;
	}
	
	
	// 관리자 : 강사가 배정이 안된 강좌배정만 삭제 처리
	public String deleteCourseAssign(String courseAssignmentNo) {
		
		String existChk = courseRoomAssignByCourseAssignmentNo(courseAssignmentNo);
		// 삭제하기 전 해당 강좌배정코드로된 강좌배정이 존재하는지 확인
		
		String resultMessage = "deleteCourseAssignmentFail";
		// 강좌 삭제 실패로 초기화
		
		if(existChk != null) { // 해당 강좌배정코드 존재(삭제 가능)
			int courseAssignResult =
					courseMapper.deleteCourseRoomAssignment(courseAssignmentNo);
			// 해당 강좌배정 삭제 처리
			
			if(courseAssignResult == 1) { // 해당 강좌 삭제 성공
				resultMessage = "deleteCourseAssignmentSuccess";
				// 강좌배정 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
}
