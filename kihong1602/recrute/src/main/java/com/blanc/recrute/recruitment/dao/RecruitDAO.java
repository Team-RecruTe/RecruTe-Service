package com.blanc.recrute.recruitment.dao;

import com.blanc.recrute.mybatis.MybatisConnectionFactory;
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

    private void connSql() {
        sqlSession = MybatisConnectionFactory.getSqlSession();
    }
}
