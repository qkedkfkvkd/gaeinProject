package com.cafe24.smart_academy.academy_manage.courseandscore.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.courseandscore.mapper.CourseAndScoreMapper;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.CourseEnrollee;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.ExaminationDay;

@Service
@Transactional
public class CourseAndScoreService {
	
	@Autowired
	CourseAndScoreMapper courseAndScoreMapper;
	
	
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
	
	
	
	
	
	// 시험 날짜가 오늘보다 이전 / 이후인 모든 시험일자 강좌 리스트를 얻어온다.
	// 시험 날짜가 오늘보다 이전 / 이후인 강사 담당 강좌의 모든 시험일자 강좌 리스트를 얻어온다.
	// 시험 날짜가 오늘보다 이전 / 이후인 시험일자 강좌리스트의 검색 결과를 얻어온다.
	// 시험일자 강좌 상세보기
	public List<Map<String, Object>> testDayCourseOneOrList(CourseRoomSearchVO searchVO) {
		return courseAndScoreMapper.testDayCourseOneOrList(searchVO);
	}
	
	
	// 강사 : 시험일코드 중복 확인
	public String examinationDayByExaminationDayNo(String examinationDayNo) {
		return courseAndScoreMapper.examinationDayByExaminationDayNo(examinationDayNo);
	}
	
	
	// 강사 : 시험일 추가 처리
	public String addExaminationDay(ExaminationDay examinationDay) {

		String existChk = examinationDayByExaminationDayNo(
				examinationDay.getExaminationDayNo());
		// 시험일 테이블에 해당 시험일코드가 존재하는지 확인
		
		String resultMessage = "usedExaminationDayCode";
		// 만약 이미 등록된 시험일코드가 있다면 이 메세지가 리턴될 것이다.
		
		if(existChk == null) {
			// 존재하지 않는 시험일코드 (입력가능한 시험일코드)
			
			int result = courseAndScoreMapper.addExaminationDay(examinationDay);
			// 시험일 추가 처리
			
			if(result == 1) {  // 시험일 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지에 널값을 준다
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자, 강사 : 시험일 수정 처리
	public String updateExaminationDay(ExaminationDay examinationDay) {
		String resultMessage = "updateExaminationDayFail";
		// 만약 시험일 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = courseAndScoreMapper.updateExaminationDay(examinationDay);
		// 시험일 수정 처리
		
		if(result == 1) {  // 시험일 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자, 강사 : 시험일 삭제 처리
	public String deleteExaminationDay(String examinationDayNo) {
		String existChk = examinationDayByExaminationDayNo(examinationDayNo);
		// 삭제하기 전 해당 시험일코드로된 시험일이 존재하는지 확인
		
		String resultMessage = "deleteExaminationDayFail";
		// 시험일삭제 실패로 초기화
		
		if(existChk != null) { // 해당 시험일코드 존재(삭제 가능)
			int result = courseAndScoreMapper.deleteExaminationDay(examinationDayNo);
			// 해당 시험일 삭제 처리
			
			if(result == 1) { // 해당 시험일 삭제 성공
				resultMessage = "deleteExaminationDaySuccess";
				// 시험일 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
	
	
	
	
	
	// 관리자, 강사, 학생
	// 특정 검색 키워드를 입력받아 해당 키워드에 맞는 성적결과 리스트를 리턴한다.
	public List<Map<String, Object>> totalGradeResultOneOrList(CourseRoomSearchVO searchVO) {
		return courseAndScoreMapper.totalGradeResultOneOrList(searchVO);
	}
}
