package com.cafe24.smart_academy.academy_manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.smart_academy.academy_manage.courseandscore.service.CourseAndScoreService;

@Controller
public class CourseAndScoreController {
// 수강 및 성적관리 컨트롤러
	
	@Autowired
	CourseAndScoreService courseAndScoreService;
	// 수강 및 성적관리 서비스
	
	
	// 관리자가 학생목록에서 해당 학생의 전체 성적 결과를 보고자 할때 사용한다.
	// 로그인 테이블의 회원 아이디 - 수강신청 테이블 회원 아이디,
	// 수강신청 테이블 수강신청코드 - 성적입력 테이블의 수강신청 코드 3개 테이블을 조인
	// 3개 테이블을 조인하여 해당 학생의 전체 수강신청 코드와
	// 전체 성적 정보를 얻어올 수 있다.
	// --- 성적만 가지고 있어서는 안된다. 어느 강좌에서 받은 성적인지, 강사는 누구인지
	// 알아야 할 것이므로 아래의 8개 테이블을 조인해야 할 것이다.
	// --- 조인하는 테이블들
	// 로그인 테이블, 수강신청 테이블, 강좌 강의실 배정 테이블, 강의실 테이블,
	// 강좌 테이블, 과목 테이블, 강사 테이블, 회원신상정보 테이블
	// ----------------------------------------------------------------
	// 로그인 테이블의 회원 아이디 - 수강신청 테이블 회원 아이디,
	// 수강신청 테이블 강좌배정코드 - 강좌 강의실 배정 테이블 강좌배정코드
	// 강좌 강의실 배정 테이블의 강의실 코드 - 강의실 테이블의 강의실 코드
	// 강좌 강의실 배정 테이블의 강좌 코드 - 강좌 테이블의 강좌 코드
	// 강좌 테이블의 과목 코드 - 과목 테이블의 과목 코드
	// 과목 테이블의 과목코드 - 강사 테이블의 과목 코드
	// 강사 테이블의 회원 아이디 - 회원 신상정보 테이블의 회원명
	// 8개 테이블을 조인하여 한 학생의 수강한 강좌의 강좌명, 강의실, 강좌, 강사명을
	// 얻어올 수 있다.
	// ----------------------------------------------------------------
	// 관리자가 특정 학생의 전체 성적 결과 조회
//	@GetMapping("/oneStudentTotalGradeResult")
//	public String oneStudentTotalGradeResult(
//			@RequestParam(value = "memberId")String memberId) {
//		
//		return "";
//	}
	
	
}
