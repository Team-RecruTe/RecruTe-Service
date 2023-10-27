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

        DetailDTO detailDTO = sqlSession.selectOne("selectDetail", recruitDTO);

        sqlSession.close();
        return detailDTO;
    }

    public int applyRecruit(ApplyDTO applyDTO) {
        connSql();

        int result = sqlSession.insert("applyRecruit", applyDTO);

        sqlSession.close();
        return result;
    }

    public Integer searchMemberId(String memberRealId) {
        connSql();

        Integer id = sqlSession.selectOne("searchMemberRealId", memberRealId);

        sqlSession.close();
        return id;
    }

    private void connSql() {
        sqlSession = MybatisConnectionFactory.getSqlSession();
    }
}
