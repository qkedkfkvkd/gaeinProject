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
	
	
	
	public List<AcademyRoom> listAcademyRoom();
	// 관리자 : 강의실 리스트 가져오기
	
	public String academyRoomByRoomNo(String roomNo);
	// 관리자 : 강의실 코드 중복 확인
	
	public int addAcademyRoom(AcademyRoom room);
	// 관리자 : 강의실 테이블에 해당 강의실 추가
	
	
	
	public List<GradingCriteria> listGradingCriteria();
	// 관리자 : 성적기준 리스트 가져오기
	
	public String gradingCriteriaRatingOverlapCheck(String inputGradingCriteriaRating);
	// 관리자 : 성적기준 테이블의 기본키인 등급 중복 확인
	
	public int addGradingCriteria(GradingCriteria criteria);
	// 관리자 : 성적기준 테이블에 해당 성적기준 추가
}
