package com.cafe24.smart_academy.academy_manage.course.manage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.course.manage.mapper.CourseManageMapper;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.AcademyRoom;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.GradingCriteria;
import com.cafe24.smart_academy.academy_manage.course.manage.vo.Subject;

@Service
@Transactional
public class CourseManageService {
	
	@Autowired
	CourseManageMapper courseManageMapper;
	
	// 관리자 : 과목코드 중복여부 확인
	public String subjectBySubjectNo(String subjectNo) {
		return courseManageMapper.subjectBySubjectNo(subjectNo);
	}
	
	
	// 관리자 : 과목테이블에 과목 추가
	public String addSubjectCode(Subject subject) {
		
		String existChk = courseManageMapper.subjectBySubjectNo(subject.getSubjectNo());
		
		String resultMessage = "usedSubjectNo";
		
		if(existChk == null) { // 존재하지 않는 과목코드 (입력가능한 과목 코드)
			int result = courseManageMapper.addSubjectCode(subject);
			
			if(result == 1) {  // 과목 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지에 널값을 준다
			}
		}
		
		
		
		return resultMessage;
	}
	
	
	// 관리자 : 과목코드 리스트 가져오기
	public List<Subject> listSubjectCode() {
		return courseManageMapper.listSubjectCode();
	}
	
	
	// 관리자 : 강의실 리스트 가져오기
	public List<AcademyRoom> listAcademyRoom() {
		return courseManageMapper.listAcademyRoom();
	}
	
	
	// 관리자 : 강의실 코드 중복확인
	public String academyRoomNoByacademyRoomNo(String roomNo) {
		return courseManageMapper.academyRoomNoByacademyRoomNo(roomNo);
	}
	
	
	// 관리자 : 성적기준 리스트 가져오기
	public List<GradingCriteria> listGradingCriteria() {
		return courseManageMapper.listGradingCriteria();
	}
	
	
	// 관리자 : 성적기준 테이블의 기본키인 등급 중복 확인
	public String gradingCriteriaRatingOverlapCheck(String inputGradingCriteriaRating) {
		return courseManageMapper.gradingCriteriaRatingOverlapCheck(inputGradingCriteriaRating);
	}
	
	
	
}
