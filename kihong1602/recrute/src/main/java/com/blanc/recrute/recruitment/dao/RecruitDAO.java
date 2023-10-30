package com.blanc.recrute.recruitment.dao;

import com.blanc.recrute.mybatis.MybatisConnectionFactory;
import com.blanc.recrute.recruitment.dto.ApplyDTO;
import com.blanc.recrute.recruitment.dto.DetailDTO;
import com.blanc.recrute.recruitment.dto.RecruitDTO;
import org.apache.ibatis.session.SqlSession;

public class RecruitDAO {

  private static SqlSession sqlSession;

  public DetailDTO selectDetail(RecruitDTO recruitDTO) {
    connSql();
    DetailDTO detailDTO = null;

    try {
      detailDTO = sqlSession.selectOne("selectDetail", recruitDTO);

      sqlSession.commit();
    } catch (Exception e) {

      e.printStackTrace();
      sqlSession.rollback();

    } finally {
      if (sqlSession != null) {
        closeSql();
      }
    }

    return detailDTO;
  }

  public int applyRecruit(ApplyDTO applyDTO) {
    connSql();
    int result = 0;
    try {
      result = sqlSession.insert("applyRecruit", applyDTO);

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

  public Integer searchMemberId(String memberRealId) {
    connSql();
    Integer id = null;
    try {
      id = sqlSession.selectOne("searchMemberRealId", memberRealId);
      sqlSession.commit();
    } catch (Exception e) {

      e.printStackTrace();
      sqlSession.rollback();

    } finally {
      if (sqlSession != null) {
        closeSql();
      }
    }

    return id;
  }

  private void connSql() {
    sqlSession = MybatisConnectionFactory.getSqlSession();
  }

  private void closeSql() {
    sqlSession.close();
    MybatisConnectionFactory.closeSqlSession();
  }
}
