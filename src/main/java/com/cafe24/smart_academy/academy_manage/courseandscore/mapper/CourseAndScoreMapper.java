package com.cafe24.smart_academy.academy_manage.courseandscore.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.course.vo.CourseRoomSearchVO;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.CourseEnrollee;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.ExaminationDay;
import com.cafe24.smart_academy.academy_manage.courseandscore.vo.ScoreInput;

@Mapper
public interface CourseAndScoreMapper {
	
	public List<Map<String, Object>> courseEnrolleeOneOrList();
	// 관리자 : 전체 수강신청 리스트 가져오기
	
	public String courseEnrolleeByCourseEnrolleeNo(String courseEnrolleeNo);
	// 관리자 : 수강신청코드 중복확인
	
	public int addCourseEnrollee(CourseEnrollee courseEnrollee);
	// 관리자 : 수강신청 추가 처리
	
	public List<Map<String, Object>> courseEnrolleeOneOrList(CourseRoomSearchVO searchVO);
	// 관리자 : 수강신청 상세보기
	// 관리자 : 학생 전체 수강신청 리스트
	// 관리자 : 학생 전체 수강신청 검색결과 리스트
	// 관리자, 강사 : 특정 강좌 수강신청한 학생 리스트
	// 관리자, 학생 : 특정 학생의 수강신청 목록 보기
	
	public int updateCourseEnrollee(CourseEnrollee courseEnrollee);
	// 관리자 : 수강신청 수정처리
	
	public int deleteCourseEnrollee(String courseEnrolleeNo);
	// 관리자 : 수강신청 삭제처리
	
	
	
	public List<Map<String, Object>> testDayCourseOneOrList(CourseRoomSearchVO searchVO);
	// 시험 날짜가 오늘보다 이후인 모든 시험일자 강좌 리스트를 얻어온다.
	// 시험 날짜가 오늘보다 이후인 강사 담당 강좌의 모든 시험일자 강좌 리스트를 얻어온다.
	// 시험 날짜가 오늘보다 이후인 시험일자 강좌리스트의 검색 결과를 얻어온다.
	// 시험 날짜가 오늘보다 이후인 시험일자 강좌 상세보기
	
	public String examinationDayByExaminationDayNo(String examinationDayNo);
	// 강사 : 시험일코드 중복 확인
	
	public int addExaminationDay(ExaminationDay examinationDay);
	// 강사 : 시험일 추가 처리
	
	public int updateExaminationDay(ExaminationDay examinationDay);
	// 관리자, 강사 : 시험일 수정 처리
	
	public int deleteExaminationDay(String examinationDayNo);
	// 관리자, 강사 : 시험일 삭제 처리
	
	
	
	public List<Map<String, Object>> totalGradeResultOneOrList(CourseRoomSearchVO searchVO);
	// 특정 검색 키워드를 입력받아 해당 키워드에 맞는 성적결과 리스트를 리턴한다.
	// 관리자, 강사, 학생
	
	public String scoreInputByScoreInputNo(String scoreInputNo);
	// 관리자, 강사 : 성적입력코드 중복확인
	
	public int addScoreInput(ScoreInput scoreInput);
	// 관리자, 강사 : 해당 학생 성적 추가 처리
	
	public int updateScoreInput(ScoreInput scoreInput);
	// 관리자, 강사 : 시험성적 수정 처리
	
	public int deleteScoreInput(String scoreInputNo);
	// 관리자, 강사 : 시험성적 삭제 처리
}
