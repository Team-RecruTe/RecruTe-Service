package com.jisun.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jisun.dto.ApplicantDto;
import com.jisun.dto.ExamDto;
import com.jisun.mybatis.MybatisConnectionFactory;

public class ExamDao {
	
	/*
	 * [select] exam테이블의 id, 문제, 4지선다 문항(보기) get 
	 * 
	 * */
	public ExamDto selectExamInfo (ExamDto ExamDto) {
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		ExamDto resultDto = null;
				resultDto = sqlSession.selectOne("selectExamInfo", ExamDto); //exam테이블의 1row(selectOne)의 값 자체
		sqlSession.close();
		return resultDto;
	}
	
	
	
	
	
	/*
	 * [select] examAuth의 view page list
	 * 			exam테이블의 id를 링크에 삽입
	 * 
	 * */
	public List<Integer> selectExamIdList () {
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		List<Integer> resultList = new ArrayList<>(); 
				  	  resultList = sqlSession.selectList("selectExamIdList"); //exam테이블의 多rows의 
		sqlSession.close();
		return resultList;
	}
	
	
	
	
	/*
	 * [select] 시험권한 인증 시 필요한 데이터
	 * 
	 * */
	
	public ExamDto selectExamAuth (int member_id) {
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		ExamDto resultDto = null;
				resultDto = sqlSession.selectOne("selectExamAuth",member_id); //1 row
		sqlSession.close();
		return resultDto;
		
	}
	
	/*
	 * [select] 수험번호 일치 
	 * selectAptIdcheck
	 * */
	public int selectAptIdCheck (ExamDto ExamDto) {
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		int resultInt = 0;
		resultInt = sqlSession.selectOne("selectAptIdcheck",ExamDto); //exam테이블의 1row의 exam_id이 있으면1 없으면0
		sqlSession.close();
		return resultInt;
		
	}
	
	
	


}
