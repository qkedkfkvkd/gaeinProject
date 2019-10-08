package com.cafe24.smart_academy.academy_manage.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.smart_academy.academy_manage.member.mapper.MemberMapper;
import com.cafe24.smart_academy.academy_manage.member.mapper.TeacherInfoMapper;
import com.cafe24.smart_academy.academy_manage.member.vo.Member;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberLogin;
import com.cafe24.smart_academy.academy_manage.member.vo.MemberSearchVO;
import com.cafe24.smart_academy.academy_manage.member.vo.Teacher;

@Service
@Transactional
public class TeacherInfoService {
	
	@Autowired
	MemberMapper memberMapper;
	// 아이디 및 이메일 중복 확인
	// 로그인 및 회원신상정보 테이블 입력, 수정, 삭제
	// 회원 간단한 정보 가져오기 (아이디, 이름, 생년월일)
	
	@Autowired
	TeacherInfoMapper teacherInfoMapper;
	// 강사 정보 관리 담당 매퍼
	
	
	// 강사 등록 처리
	public String addTeacher(MemberLogin loginInfo, Member memberInfo, Teacher teacher) {
		String memberIdchk = memberMapper.memberLoginInfoById(loginInfo.getMemberId());
		// 중복되는 아이디를 입력하여 가입 시도를 하였는가?
		
		String memberEmailChk = memberMapper.memberByEmail(memberInfo.getMemberEmail());
		// 중복되는 이메일을 입력하여 가입 시도를 하였는가?
		
		String teacherIdChk = teacherInfoMapper.teacherByMemberId(teacher.getMemberId());
		// 중복되는 강사의 아이디를 입력하여 가입 시도를 하였는가?
		
		
		String result = null;
		// 널값이 계속 유지되서 리턴되면 입력 성공했다는 뜻
		
		
		if(memberIdchk != null) { // 이미 존재하는 아이디로 회원가입하려고 했을 경우
			result = "idUsed";		// 아이디 사용 중 메세지
			
		} else if(memberEmailChk != null) { // 유니크값인 이메일을 중복 입력하여 가입시도
			result = "emailUsed";	// 이메일 사용 중 메세지
			
		} else if( teacherIdChk != null) { // 이미 존재하는 아이디로 강사 가입시도
			result = "teacherIdUsed";
			
		} else {
			int loginInfocheck = memberMapper.addMemberLogin(loginInfo);
			// 로그인 테이블 먼저 등록처리한다. (회원 신상정보 테이블의 기본키인 회원 아이디가 로그인 테이블의 회원 아이디를 참조)
			// 로그인 테이블 : 회원 신상정보 테이블 -> 1:1 관계
			
			int memCheck = 0;
			// 회원 신상정보 입력 실패로 초기화
			
			int teacherChk = 0;
			// 강사 입력 실패로 초기화
			
			
			if(loginInfocheck == 1) {  // 로그인 테이블 등록 성공
				memberInfo.setMemberId(loginInfo.getMemberId());
				// 로그인 테이블 객체에서 아이디 값을 꺼내와서 회원 신상정보 테이블의 아이디값에 셋팅
				
				memCheck = memberMapper.addMember(memberInfo);
				// 회원 신상정보 테이블 등록 처리
				
				teacherChk = teacherInfoMapper.addTeacher(teacher);
				// 강사 테이블 등록 처리
			}
			
			if(teacherChk == 0 || loginInfocheck == 0 || memCheck == 0) {
				// 강사 테이블 혹은 로그인 테이블 혹은 회원신상정보 테이블 둘 중 하나라도 입력 실패시
				
				String loginInfoChk = memberMapper.memberLoginInfoById(loginInfo.getMemberId());
				// 강사 테이블과 회원테이블의 기본키가되는 회원 아이디는
				// 모두 로그인 테이블의 회원아이디(기본키)와 1:1대응 관계이다.
				// 즉, 로그인 테이블에 해당 아이디가 없는데
				// 회원신상정보 테이블이나 강사 테이블에 값이 있다는 것은 말이 안된다.
				// --> 참조관계이므로
				
				result = "insertDeleteFail";
				// 입력 삭제 둘 다 실패 메세지
				
				if(loginInfoChk != null) {
					int deleteChk = memberMapper.deleteMemberLogin(loginInfo.getMemberId());
					// 디비가서 있을지도 모르는 해당 레코드 삭제 처리
					
					if(deleteChk == 1) {
						result = "insertFailDeleteSuccess";
						// 입력은 실패했으나 삭제에는 성공했다는 메세지
					}
				}
			}
		}
		
		return result;
	}
	
	
	// 관리자 : 강사 정보 목록 가져오기
	// 아이디, 권한, 이름, 이메일, 휴대폰번호, 담당 강좌코드, 과목명, 강좌명, 강사 등록일
	public List<Map<String, Object>> teacherInfoList(MemberSearchVO memberSearchVO) {
		return teacherInfoMapper.teacherInfoList(memberSearchVO);
	}
	
	
	// 관리자 : 강사 테이블에 강좌 매핑하기
	// -> 강좌 강사 배정 추가처리
	public String updateTeacher(Teacher teacher) {
		
		String resultMessage = "updateTeacherFail";
		// 만약 강좌 강사 배정 수정처리에 실패했다면 이 메세지가 리턴될 것이다.
		
		int result = teacherInfoMapper.updateTeacher(teacher);
		// 강좌 강사 배정 수정 처리
		
		if(result == 1) {  // 강좌 강사 배정 수정에 성공했다면
			resultMessage = null;
			// 리턴 메세지에 널값을 준다
		}
		
		return resultMessage;
	}
	
	
}
