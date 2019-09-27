package com.cafe24.smart_academy.academy_manage.course.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.course.manage.vo.AcademyRoom;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.GradingCriteria;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.Subject;

@Mapper
public interface CourseManageMapper {
	
	public List<Subject> listSubject();
	// 관리자 : 과목코드 리스트 가져오기
	
	public String subjectBySubjectNo(String subjectNo);
	// 관리자 : 과목테이블에서 해당 과목코드가 이미 존재하는지 확인
	
	public int addSubject(Subject subject);
	// 관리자 : 과목 테이블에 해당 과목 추가
	
	public Subject detailSubjectBySubjectNo(String subjectNo);
	// 관리자 : 과목 수정을 위해 해당 과목코드의 모든 데이터 가져오기
	
	public int updateSubject(Subject subject);
	// 관리자 : 과목 수정 처리
	
	
	
	public List<AcademyRoom> listAcademyRoom();
	// 관리자 : 강의실 리스트 가져오기
	
	public List<AcademyRoom> listAcademyRoom(AcademyRoom room);
	// 관리자 : 강의실 목록에서 강의실 검색
	
	public List<AcademyRoom> roomUsageListByRoomFloor(String roomFloor);
	// 관리자 : 강의실 리스트에서 층수 선택시 자동으로
	//			해당 층수에 맞는 실용도 나오게 하기
	
	public String academyRoomByRoomNo(String roomNo);
	// 관리자 : 강의실 코드 중복 확인
	
	public int addAcademyRoom(AcademyRoom room);
	// 관리자 : 강의실 테이블에 해당 강의실 추가
	
	public AcademyRoom detailRoomByRoomNo(String roomNo);
	// 관리자 : 강의실 상세보기
	
	public int updateAcademyRoom(AcademyRoom room);
	// 관리자 : 강의실 수정 처리
	
	
	
	public List<GradingCriteria> listGradingCriteria();
	// 관리자 : 성적기준 리스트 가져오기
	
	public List<GradingCriteria> listGradingCriteria(String gradingCriteriaRating);
	// 관리자 : 성적기준을 선택하여 검색하기
	
	public String gradingCriteriaRatingOverlapCheck(String inputGradingCriteriaRating);
	// 관리자 : 성적기준 테이블의 기본키인 등급 중복 확인
	
	public int addGradingCriteria(GradingCriteria criteria);
	// 관리자 : 성적기준 테이블에 해당 성적기준 추가
}
