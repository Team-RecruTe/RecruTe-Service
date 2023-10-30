package com.blanc.recrute.exam.dao;

import com.blanc.recrute.exam.dto.ExaminationDTO;
import com.blanc.recrute.exam.dto.RecruitInfoDTO;
import com.blanc.recrute.exam.dto.answer.AnswerData;
import com.blanc.recrute.mybatis.MybatisConnectionFactory;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class ExamDAO {

  private static SqlSession sqlSession;

  public RecruitInfoDTO getRecruitContent(String aptId) {
    connSql();
    RecruitInfoDTO examDTO = null;
    try {
      examDTO = sqlSession.selectOne("getRecruitTitle", aptId);
      sqlSession.commit();
    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
    } finally {
      if (sqlSession != null) {
        closeSql();
      }
    }

    return examDTO;
  }

  /*  getExamination()
   * 현재는 question 과 answer 컬럼이 exam 테이블에 종속되어있으므로 examId로 recruitId 를 조회하고
   * recruitId를 통해 값을 불러올 것
   * 리팩토링시 테이블 분리 예정이며 해당시점에는 examId를 통해 question 과 answer 조회예정
   */
  public List<ExaminationDTO> getExamination(Integer recruitId) {
    connSql();
    List<ExaminationDTO> examinationDTOList = null;
    try {
      examinationDTOList = sqlSession.selectList("getExamination", recruitId);

      sqlSession.commit();
    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
    } finally {
      if (sqlSession != null) {
        closeSql();
      }
    }

    return examinationDTOList;
  }

  public Integer getRecruitId(Integer examId) {
    connSql();
    Integer recruitId = null;
    try {
      recruitId = sqlSession.selectOne("searchRecruitId", examId);

      sqlSession.commit();
    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
    } finally {
      if (sqlSession != null) {
        closeSql();
      }
    }

    return recruitId;
  }

  public String saveExamination(AnswerData answerData) {
    System.out.println(answerData.toString());
    return answerData.toString();
  }

  private void connSql() {
    sqlSession = MybatisConnectionFactory.getSqlSession();
  }

  private void closeSql() {
    sqlSession.close();
    MybatisConnectionFactory.closeSqlSession();
  }
}
