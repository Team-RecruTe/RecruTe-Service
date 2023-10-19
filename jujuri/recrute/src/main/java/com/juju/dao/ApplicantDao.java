package com.juju.dao;

import org.apache.ibatis.session.SqlSession;
import com.juju.dto.ApplicantDto;
import com.juju.dto.MemberDto;
import com.juju.mybatis.MybatisConnectionFactory;
import com.juju.service.ApplicantService;

public class ApplicantDao implements ApplicantService {

  @Override
  public MemberDto findMember(String userId) {
    MemberDto findDto = null;
    SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
    findDto = sqlSession.selectOne("findMember", userId);
    sqlSession.close();
    return findDto;
  }

  @Override
  public int insertApplicant(ApplicantDto applicantDto) {
    int result = 0;
    SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
    result = sqlSession.insert("insertApplicant", applicantDto);
    return result;
  }

}
