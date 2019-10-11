package com.cafe24.smart_academy.academy_manage.courseandscore.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.courseandscore.mapper.CourseAndScoreMapper;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.CourseEnrollee;

@Service
@Transactional
public class CourseAndScoreService {
	
	@Autowired
	CourseAndScoreMapper courseAndScoreMapper;
	
	// 관리자, 강사, 학생
	// 특정 검색 키워드를 입력받아 해당 키워드에 맞는 성적결과 리스트를 리턴한다.
	public List<Map<String, Object>> totalGradeResultList(Map<String, Object> map) {
		return courseAndScoreMapper.totalGradeResultList(map);
	}
	
	
	// 관리자 : 전체 수강신청 리스트 가져오기
	public List<Map<String, Object>> courseEnrolleeOneOrList() {
		return courseAndScoreMapper.courseEnrolleeOneOrList();
	}
	
	
	// 관리자 : 수강신청코드 중복확인
	public String courseEnrolleeByCourseEnrolleeNo(String courseEnrolleeNo) {
		return courseAndScoreMapper.courseEnrolleeByCourseEnrolleeNo(courseEnrolleeNo);
	}
	
	
	// 관리자 : 수강신청 등록 처리
	public String addCourseEnrollee(CourseEnrollee courseEnrollee) {
		String existChk =
				courseEnrolleeByCourseEnrolleeNo(courseEnrollee.getCourseEnrolleeNo());
		// 수강신청 테이블에 해당 수강신청코드가 존재하는지 확인
		
		String resultMessage = "usedCourseEnrolleeNo";
		// 만약 이미 등록된 수강신청코드가 있다면 이 메세지가 리턴될 것이다.
		
		if(existChk == null) {
			// 존재하지 않는 수강신청코드 (입력가능한 수강신청코드)
			
			int result = courseAndScoreMapper.addCourseEnrollee(courseEnrollee);
			// 수강신청 추가 처리
			
			if(result == 1) {  // 수강신청 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지에 널값을 준다
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 수강신청 상세보기
	public List<Map<String, Object>> courseEnrolleeOneOrList(CourseRoomSearchVO searchVO) {
		return courseAndScoreMapper.courseEnrolleeOneOrList(searchVO);
	}
	
	
	// 관리자 : 수강신청 수정처리
	public String updateCourseEnrollee(CourseEnrollee courseEnrollee) {
		String resultMessage = "updateCourseEnrolleeFail";
		// 만약 수강신청 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = courseAndScoreMapper.updateCourseEnrollee(courseEnrollee);
		// 수강신청 수정 처리
		
		if(result == 1) {  // 수강신청 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 수강신청 삭제처리
	public String deleteCourseEnrollee(String courseEnrolleeNo) {
		String existChk = courseEnrolleeByCourseEnrolleeNo(courseEnrolleeNo);
		// 삭제하기 전 해당 수강신청코드로된 수강신청이 존재하는지 확인
		
		String resultMessage = "deleteCourseEnrolleeFail";
		// 수강신청삭제 실패로 초기화
		
		if(existChk != null) { // 해당 수강신청코드 존재(삭제 가능)
			int result = courseAndScoreMapper.deleteCourseEnrollee(courseEnrolleeNo);
			// 해당 수강신청 삭제 처리
			
			if(result == 1) { // 해당 수강신청 삭제 성공
				resultMessage = "deleteCourseEnrolleeSuccess";
				// 수강신청 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
}
