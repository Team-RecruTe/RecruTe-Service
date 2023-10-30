package com.blanc.recrute.member.dao;

import com.blanc.recrute.member.dto.MemberDTO;
import com.blanc.recrute.member.dto.MemberInfoDTO;
import com.blanc.recrute.mybatis.MybatisConnectionFactory;
import org.apache.ibatis.session.SqlSession;

public class MemberDAOImpl implements MemberDAO {

  private static SqlSession sqlSession;

  @Override
  public int insertMember(MemberInfoDTO memberDTO) {
    connSql();
    int result = 0;

    try {

      result = sqlSession.insert("saveMember", memberDTO);

      sqlSession.commit();
    } catch (Exception e) {

      e.printStackTrace();
      sqlSession.rollback();

    } finally {

      if (sqlSession != null) {
        closeSql();
      }

    }

    return result;
  }

  @Override
  public String loginCheck(MemberDTO memberDTO) {
    connSql();
    String memberId = null;

    try {

      memberId = sqlSession.selectOne("loginCheck", memberDTO);

      sqlSession.commit();
    } catch (Exception e) {

      e.printStackTrace();
      sqlSession.rollback();

    } finally {

      if (sqlSession != null) {
        closeSql();
      }

    }
    return memberId;
  }

  @Override
  public int idCheck(String id) {
    connSql();
    MemberDTO memberDTO = new MemberDTO.Builder().memberId(id).build();
    int result = 0;

    try {

      result = sqlSession.selectOne("idCheck", memberDTO);

      sqlSession.commit();
    } catch (Exception e) {

      e.printStackTrace();
      sqlSession.rollback();

    } finally {

      if (sqlSession != null) {
        closeSql();
      }

    }

    return result;
  }

  @Override
  public String searchMember(MemberDTO memberDTO) {
    connSql();
    String findEmail = null;

    try {

      findEmail = sqlSession.selectOne("searchMember", memberDTO);

      sqlSession.commit();
    } catch (Exception e) {

      e.printStackTrace();
      sqlSession.rollback();

    } finally {

      if (sqlSession != null) {
        closeSql();
      }

    }

    return findEmail;
  }

  @Override
  public int authGrantMember(MemberDTO memberDTO) {
    connSql();
    int result = 0;

    try {

      result = sqlSession.update("emailAuth", memberDTO);

      sqlSession.commit();
    } catch (Exception e) {

      e.printStackTrace();
      sqlSession.rollback();

    } finally {
      if (sqlSession != null) {
        closeSql();
      }
    }

    return result;
  }

  private void connSql() {
    sqlSession = MybatisConnectionFactory.getSqlSession();
  }

  private void closeSql() {
    sqlSession.close();
    MybatisConnectionFactory.closeSqlSession();
  }
}
