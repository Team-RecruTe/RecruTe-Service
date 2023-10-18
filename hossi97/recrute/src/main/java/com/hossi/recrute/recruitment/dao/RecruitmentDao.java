package com.hossi.recrute.recruitment.dao;

import com.hossi.recrute.common.MyBatisConnectionFactory;
import com.hossi.recrute.recruitment.dto.RecruitmentDto;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class RecruitmentDao {
    public RecruitmentDto getRecruitmentByRctId(Integer rctId) {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();

        RecruitmentDto recruitmentDto = sqlSession.selectOne("selectRecruitmentByRctId", rctId);

        sqlSession.close();

        return recruitmentDto;
    }
}
