package com.cafe24.smart_academy.academy_manage.courseandscore.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.CourseEnrollee;

@Mapper
public interface CourseAndScoreMapper {

	List<Map<String, Object>> totalGradeResultList(Map<String, Object> map);
	// 특정 검색 키워드를 입력받아 해당 키워드에 맞는 성적결과 리스트를 리턴한다.
	// 관리자, 강사, 학생
	
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
}
