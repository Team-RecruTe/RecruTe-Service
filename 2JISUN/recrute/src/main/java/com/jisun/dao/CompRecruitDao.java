package com.jisun.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jisun.dto.CompRecruitDto;
import com.jisun.mybatis.MybatisConnectionFactory;

public class CompRecruitDao {
	
	/*
	 * 채용공고List
	 * 메서드 : 쿼리에서 1개의 row를 list로 담는다. 
	 * 		  				 list안의 값들은 [dto, dto, ..]이다 = param
	 * @param : 
	 * @return : 
	 * 
	 * 
	 * */
	public List<CompRecruitDto> selectCompRecruitList(CompRecruitDto compRecruitDto){
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		List<CompRecruitDto> compRecruitList = null;
							 compRecruitList = sqlSession.selectList("selectCompRecruitList", compRecruitDto);
		sqlSession.close();
		return compRecruitList;

	}
	
	/*
	 * 채용공고View
	 * 메서드 : 쿼리에서 1개의 row를 dto로 담는다.
	 * 						 dto
	 * @param : 
	 * @return : 
	 * 
	 * 
	 * */
	
	public CompRecruitDto selectCompRecruitView (int id_recruitIndex) {
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		CompRecruitDto compRecruitView = null;
					   compRecruitView = sqlSession.selectOne("selectCompRecruitView", id_recruitIndex);
		sqlSession.close();
		return compRecruitView;
	}
	
	/*
	 * 채용공고title만 가져오기
	 * 
	 * 
	 * */
	
	public String selectCompRecruitTitle (int loggedId) {
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		String title = null;
		title = sqlSession.selectOne("selectCompRecruitTitle", loggedId);
		sqlSession.close();
		return title;
	}
}
