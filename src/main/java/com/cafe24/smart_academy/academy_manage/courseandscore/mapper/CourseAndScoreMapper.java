package com.cafe24.smart_academy.academy_manage.courseandscore.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.CourseEnrollee;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.ExaminationDay;

@Mapper
public interface CourseAndScoreMapper {
	
	List<Map<String, Object>> courseEnrolleeOneOrList();
	// 관리자 : 전체 수강신청 리스트 가져오기
	
	String courseEnrolleeByCourseEnrolleeNo(String courseEnrolleeNo);
	// 관리자 : 수강신청코드 중복확인
	
	int addCourseEnrollee(CourseEnrollee courseEnrollee);
	// 관리자 : 수강신청 추가 처리
	
	List<Map<String, Object>> courseEnrolleeOneOrList(CourseRoomSearchVO searchVO);
	// 관리자 : 수강신청 상세보기
	
	int updateCourseEnrollee(CourseEnrollee courseEnrollee);
	// 관리자 : 수강신청 수정처리
	
	int deleteCourseEnrollee(String courseEnrolleeNo);
	// 관리자 : 수강신청 삭제처리
	
	
	
	List<Map<String, Object>> testDayCourseOneOrList(CourseRoomSearchVO searchVO);
	// 시험 날짜가 오늘보다 이후인 모든 시험일자 강좌 리스트를 얻어온다.
	// 시험 날짜가 오늘보다 이후인 강사 담당 강좌의 모든 시험일자 강좌 리스트를 얻어온다.
	// 시험 날짜가 오늘보다 이후인 시험일자 강좌리스트의 검색 결과를 얻어온다.
	// 시험 날짜가 오늘보다 이후인 시험일자 강좌 상세보기
	
	String examinationDayByExaminationDayNo(String examinationDayNo);
	// 강사 : 시험일코드 중복 확인
	
	int addExaminationDay(ExaminationDay examinationDay);
	// 강사 : 시험일 추가 처리
	
	int updateExaminationDay(ExaminationDay examinationDay);
	// 관리자, 강사 : 시험일 수정 처리
	
	int deleteExaminationDay(String examinationDayNo);
	// 관리자, 강사 : 시험일 삭제 처리
	
	
	
	List<Map<String, Object>> totalGradeResultOneOrList(CourseRoomSearchVO searchVO);
	// 특정 검색 키워드를 입력받아 해당 키워드에 맞는 성적결과 리스트를 리턴한다.
	// 관리자, 강사, 학생
}
