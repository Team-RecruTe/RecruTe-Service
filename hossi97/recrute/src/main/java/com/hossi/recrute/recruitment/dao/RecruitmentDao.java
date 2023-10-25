package com.hossi.recrute.recruitment.dao;

import com.hossi.recrute.common.util.mybatis.MyBatisConnectionManager;
import com.hossi.recrute.recruitment.dto.ApplicantDto;
import com.hossi.recrute.recruitment.dto.RecruitmentDto;
import org.apache.ibatis.session.SqlSession;

public class RecruitmentDao {
    public RecruitmentDto findRecruitment(Integer rctId) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        RecruitmentDto recruitmentDto = sqlSession.selectOne("selectRecruitmentByRctId", rctId);
        sqlSession.close();

        return recruitmentDto;
    }

    public void saveRecruitment(ApplicantDto applicantDto) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        sqlSession.insert("insertApplicant", applicantDto);
        sqlSession.close();
    }
}
