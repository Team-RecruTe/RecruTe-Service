package com.hossi.recrute.recruitment.dao;

import com.hossi.recrute.common.mybatis.MyBatisConnectionManager;
import com.hossi.recrute.common.mybatis.ResultType;
import com.hossi.recrute.member.dao.MemberMapper;
import com.hossi.recrute.recruitment.dto.ApplicantReqDto;
import com.hossi.recrute.recruitment.dto.RecruitmentDto;
import org.apache.ibatis.session.SqlSession;

import static com.hossi.recrute.common.mybatis.ResultType.FAILURE;
import static com.hossi.recrute.common.mybatis.ResultType.SUCCESS;

public class RecruitmentDao {
    public RecruitmentDto findRecruitment(Integer rctId) {
        RecruitmentDto recruitmentDto = null;

        try(SqlSession sqlSession = MyBatisConnectionManager.getSqlSession()) {
            RecruitmentMapper mapper = sqlSession.getMapper(RecruitmentMapper.class);
            recruitmentDto = mapper.selectRecruitmentByRctId(rctId);
            sqlSession.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return recruitmentDto;
    }

    public ResultType saveRecruitment(ApplicantReqDto applicantReqDto) {
        int result = 0;
        try(SqlSession sqlSession = MyBatisConnectionManager.getSqlSession()) {
            RecruitmentMapper mapper = sqlSession.getMapper(RecruitmentMapper.class);
            result = mapper.insertApplicant(applicantReqDto);
            sqlSession.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return SUCCESS.equals(result) ? SUCCESS : FAILURE;
    }
}
