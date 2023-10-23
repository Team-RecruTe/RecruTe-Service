package com.blanc.recrute.exam.dao;

import com.blanc.recrute.exam.dto.ExamInfoDTO;
import com.blanc.recrute.mybatis.MybatisConnectionFactory;
import org.apache.ibatis.session.SqlSession;

public class ExamDAO {

  private static SqlSession sqlSession;

  public ExamInfoDTO getExamContent(Integer examId) {
    connSql();

    ExamInfoDTO examDTO = sqlSession.selectOne("getRecruitTitle", examId);

    sqlSession.close();
    return examDTO;
  }

  private void connSql() {
    sqlSession = MybatisConnectionFactory.getSqlSession();
  }

}
