package com.hossi.recrute.exam.dao;

import com.hossi.recrute.common.mybatis.MyBatisConnectionManager;
import com.hossi.recrute.exam.dto.SendExamEmailDto;
import com.hossi.recrute.recruitment.dao.RecruitmentMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EmailDao {

    public List<SendExamEmailDto> findExamInfos(Integer recruitmentId) {
        List<SendExamEmailDto> sendExamEmailDtos = null;

        try(SqlSession sqlSession = MyBatisConnectionManager.getSqlSession()) {
            ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
            sendExamEmailDtos = mapper.selectUserEmailAndAptIdAndCompanyName(recruitmentId);
            sqlSession.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return sendExamEmailDtos;
    }
}
