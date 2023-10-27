package com.jisun.dao;


import org.apache.ibatis.session.SqlSession;

import com.jisun.dto.ApplicantDto;
import com.jisun.mybatis.MybatisConnectionFactory;

public class ApplicantDao {
	
	/*
	 * [insert] 회원이 채용공고 지원하기를 눌렀을 때 지원자 정보를 삽입 
	 * */
	public int insertAppliacnt(ApplicantDto applicantDto) {
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		int result = 0;
			result = sqlSession.insert("insertApplicant",applicantDto); 
		sqlSession.close();
		return result;
	}

}
