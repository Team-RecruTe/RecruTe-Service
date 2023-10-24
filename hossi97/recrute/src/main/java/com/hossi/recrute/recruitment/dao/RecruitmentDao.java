package com.hossi.recrute.recruitment.dao;

import com.hossi.recrute.common.util.mybatis.MyBatisConnectionManager;
import com.hossi.recrute.recruitment.dto.ApplicantDto;
import com.hossi.recrute.recruitment.dto.RecruitmentDto;
import org.apache.ibatis.session.SqlSession;

public class RecruitmentDao {
    public RecruitmentDto getRecruitmentByRctId(Integer rctId) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        RecruitmentDto recruitmentDto = sqlSession.selectOne("selectRecruitmentByRctId", rctId);
        sqlSession.close();

        return recruitmentDto;
    }

    public void setRecruitment(ApplicantDto applicantDto) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        sqlSession.insert("saveApplicant", applicantDto);
        sqlSession.close();
    }
}
