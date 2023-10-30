package com.hossi.recrute.exam.dao;

import com.hossi.recrute.common.mybatis.MyBatisConnectionManager;
import com.hossi.recrute.exam.dto.SendExamEmailDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EmailDao {

    public List<SendExamEmailDto> findExamInfos(Integer recruitmentId) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        List<SendExamEmailDto> sendExamEmailDtos = sqlSession.selectList("selectUserEmailAndAptIdAndCompanyName", recruitmentId);
        sqlSession.close();

        return sendExamEmailDtos;
    }
}
