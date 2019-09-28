package com.cafe24.smart_academy.academy_manage.courseandscore.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseAndScoreMapper {

	List<Map<String, Object>> totalGradeResultList(Map<String, Object> map);
	// 특정 검색 키워드를 입력받아 해당 키워드에 맞는 성적결과 리스트를 리턴한다.
	// 관리자, 강사, 학생
}
