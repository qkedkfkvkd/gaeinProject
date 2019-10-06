package com.cafe24.smart_academy.academy_manage.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.smart_academy.academy_manage.member.vo.MemberSearchVO;
import com.cafe24.smart_academy.academy_manage.member.vo.Teacher;

@Mapper
public interface TeacherInfoMapper {
	
	public String teacherByMemberId(String memberId);
	// 관리자 : 강사 아이디 중복 확인 (로그인 테이블의 회원아이디 참조 1:1 대응)
	
	public int addTeacher(Teacher teacher);
	// 관리자 : 강사 테이블에 강사 등록 처리
	
	public List<Map<String, Object>> teacherInfoList(MemberSearchVO memberSearchVO);
	// 관리자 : 강사 정보 목록 가져오기
	// 강사 아이디, 이름, 휴대폰번호, 이메일, 담당 강좌코드, 강좌명
	
	public int updateTeacher(Teacher teacher);
	// 관리자 : 강사 담당 강좌코드 수정
}
