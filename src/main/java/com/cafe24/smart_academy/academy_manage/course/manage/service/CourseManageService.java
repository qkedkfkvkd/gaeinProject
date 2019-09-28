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
	
	
	// 관리자 : 과목코드 리스트 가져오기
	public List<Subject> listSubject() {
		return courseManageMapper.listSubject();
	}
	
	
	// 관리자 : 과목코드 중복여부 확인
	public String subjectBySubjectNo(String subjectNo) {
		return courseManageMapper.subjectBySubjectNo(subjectNo);
	}
	
	
	// 관리자 : 과목테이블에 과목 추가
	public String addSubject(Subject subject) {
		
		String existChk = courseManageMapper.subjectBySubjectNo(subject.getSubjectNo());
		
		String resultMessage = "usedSubjectNo";
		// 만약 이미 등록된 과목코드가 있다면 이 메세지가 리턴될 것이다.
		
		if(existChk == null) { // 존재하지 않는 과목코드 (입력가능한 과목 코드)
			int result = courseManageMapper.addSubject(subject);
			// 과목 추가 처리
			
			if(result == 1) {  // 과목 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지에 널값을 준다
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 과목 수정을 위해 해당 과목코드의 모든 데이터 가져오기
	public Subject detailSubjectBySubjectNo(String subjectNo) {
		return courseManageMapper.detailSubjectBySubjectNo(subjectNo);
	}
	
	
	// 관리자 : 과목 수정 처리
	public String updateSubject(Subject subject) {
		String resultMessage = "updateSubjectFail";
		// 만약 과목 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = courseManageMapper.updateSubject(subject);
		// 과목 수정 처리
		
		if(result == 1) {  // 과목 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 과목 삭제 처리
	public String deleteSubject(String subjectNo) {
		String existChk = courseManageMapper.subjectBySubjectNo(subjectNo);
		// 삭제하기 전 해당 과목코드로된 과목이 존재하는지 확인
		
		String resultMessage = "deleteSubjectFail";
		// 과목삭제 실패로 초기화
		
		if(existChk != null) { // 해당 과목코드 존재(삭제 가능)
			int result = courseManageMapper.deleteSubject(subjectNo);
			// 해당 과목 삭제 처리
			
			if(result == 1) { // 해당 과목 삭제 성공
				resultMessage = "deleteSubjectSuccess";
				// 과목 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
	
	
	
	// 관리자 : 강의실 리스트 가져오기
	public List<AcademyRoom> listAcademyRoom() {
		return courseManageMapper.listAcademyRoom();
	}
	
	
	// 관리자 : 강의실 리스트에서 층수 선택시 자동으로
	//			해당 층수에 맞는 실용도 나오게 하기
	public List<AcademyRoom> roomUsageListByRoomFloor(String roomFloor) {
		return courseManageMapper.roomUsageListByRoomFloor(roomFloor);
	}
	
	
	// 관리자 : 강의실 목록에서 강의실 검색
	public List<AcademyRoom> listAcademyRoom(AcademyRoom room) {
		return courseManageMapper.listAcademyRoom(room);
	}
	
	
	// 관리자 : 강의실 코드 중복확인
	public String academyRoomByRoomNo(String roomNo) {
		return courseManageMapper.academyRoomByRoomNo(roomNo);
	}
	
	
	// 관리자 : 강의실 추가 처리
	public String addAcademyRoom(AcademyRoom room) {
		String existChk = academyRoomByRoomNo(room.getRoomNo());
		// 강의실 테이블의 기본키인 강의실코드로 추가하려는 강의실의 코드가
		// 중복되는지 확인한다.
		
		String resultMessage = "usedRoomNo";
		
		if(existChk == null) { // 존재하지않는 강의실 코드(사용 가능한 강의실 코드)
			int result = courseManageMapper.addAcademyRoom(room);
			// 강의실 추가 처리
			
			if(result == 1) { // 강의실 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지가 널값을 준다.
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 강의실 상세 보기
	public AcademyRoom detailRoomByRoomNo(String roomNo) {
		return courseManageMapper.detailRoomByRoomNo(roomNo);
	}
	
	
	// 관리자 : 강의실 수정 처리
	public String updateAcademyRoom(AcademyRoom room) {
		String resultMessage = "updateAcademyRoomFail";
		// 만약 강의실 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = courseManageMapper.updateAcademyRoom(room);
		// 강의실 수정 처리
		
		if(result == 1) {  // 강의실 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 강의실 삭제 처리
	public String deleteAcademyRoom(String roomNo) {
		String existChk = courseManageMapper.academyRoomByRoomNo(roomNo);
		// 삭제하기 전 해당 강의실코드로된 강의실이 존재하는지 확인
		
		String resultMessage = "deleteAcademyRoomFail";
		// 강의실 삭제 실패로 초기화
		
		if(existChk != null) { // 해당 강의실코드 존재(삭제 가능)
			int result = courseManageMapper.deleteAcademyRoom(roomNo);
			// 해당 강의실 삭제 처리
			
			if(result == 1) { // 해당 강의실 삭제 성공
				resultMessage = "deleteAcademyRoomSuccess";
				// 강의실 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
	
	
	
	// 관리자 : 성적기준 리스트 가져오기
	public List<GradingCriteria> listGradingCriteria() {
		return courseManageMapper.listGradingCriteria();
	}
	
	
	// 관리자 : 성적기준을 선택하여 검색하기
	public List<GradingCriteria> listGradingCriteria(String gradingCriteriaRating) {
		return courseManageMapper.listGradingCriteria(gradingCriteriaRating);
	}
	
	
	// 관리자 : 성적기준 테이블의 기본키인 등급 중복 확인
	public String gradingCriteriaRatingOverlapCheck(String inputGradingCriteriaRating) {
		return courseManageMapper.gradingCriteriaRatingOverlapCheck(inputGradingCriteriaRating);
	}
	
	
	// 관리자 : 성적기준 추가
	public String addGradingCriteria(GradingCriteria criteria) {
		String existChk =
				gradingCriteriaRatingOverlapCheck(criteria.getGradingCriteriaRating());
		// 성적기준 테이블의 기본키인 등급으로 추가하려는 성적기준의 등급이
		// 중복되는지 확인한다.
		
		String resultMessage = "usedGradingCriteriaRating";
		
		if(existChk == null) { // 존재하지않는 등급(사용 가능한 등급)
			int result = courseManageMapper.addGradingCriteria(criteria);
			// 성적기준 추가 처리
			
			if(result == 1) { // 성적기준 등록에 성공했다면
				resultMessage = null;
				// 리턴 메세지가 널값을 준다.
			}
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 성적기준 상세 보기(수정화면 이동)
	public GradingCriteria detailGradingCriteriaByGradingCriteriaRating(String gradingCriteriaRating) {
		return courseManageMapper.detailGradingCriteriaByGradingCriteriaRating(gradingCriteriaRating);
	}
	
	
	// 관리자 : 성적기준 수정 처리
	public String updateGradingCriteria(GradingCriteria gradingCriteria) {
		String resultMessage = "updateGradingCriteriaFail";
		// 만약 성적기준 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = courseManageMapper.updateGradingCriteria(gradingCriteria);
		// 성적기준 수정 처리
		
		if(result == 1) {  // 성적기준 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
	// 관리자 : 성적기준 삭제 처리
	public String deleteGradingCriteria(String gradingCriteriaRating) {
		String existChk = gradingCriteriaRatingOverlapCheck(gradingCriteriaRating);
		// 삭제하기 전 해당 등급(기본키)으로된 성적기준이 존재하는지 확인
		
		String resultMessage = "deleteGradingCriteriaFail";
		// 성적기준 삭제 실패로 초기화
		
		if(existChk != null) { // 해당 등급 존재(삭제 가능)
			int result = courseManageMapper.deleteGradingCriteria(gradingCriteriaRating);
			// 해당 등급 삭제 처리
			
			if(result == 1) { // 해당 성적기준 삭제 성공
				resultMessage = "deleteGradingCriteriaSuccess";
				// 성적기준 삭제 성공 메세지
			}
		}
		
		return resultMessage;
	}
}
