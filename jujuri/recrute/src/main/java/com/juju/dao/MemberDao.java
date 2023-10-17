package com.juju.dao;

import org.apache.ibatis.session.SqlSession;
import com.juju.dto.MemberDto;
import com.juju.mybatis.MybatisConnectionFactory;
import com.juju.service.MemberService;

public class MemberDao implements MemberService {

  @Override
  public int insertMember(MemberDto memberDto) {
    int result = 0;
    SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
    result = sqlSession.insert("insertMember", memberDto);
    return result;
  }

  @Override
  public MemberDto loginMember(MemberDto memberDto) {
    MemberDto loginDto = null;
    SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
    loginDto = sqlSession.selectOne("loginMember", memberDto);
    sqlSession.close();
    return loginDto;
  }
}
