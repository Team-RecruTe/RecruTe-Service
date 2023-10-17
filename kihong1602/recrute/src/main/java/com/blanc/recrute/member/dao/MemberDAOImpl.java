package com.blanc.recrute.member.dao;

import com.blanc.recrute.member.dto.MemberDTO;
import com.blanc.recrute.mybatis.MybatisConnectionFactory;
import org.apache.ibatis.session.SqlSession;

public class MemberDAOImpl implements MemberDAO {
    private static SqlSession sqlSession;

    @Override
    public int insertMember(MemberDTO memberDTO) {
        connSql();

        int result = sqlSession.insert("saveMember", memberDTO);

        sqlSession.close();
        return result;
    }

    @Override
    public String loginCheck(MemberDTO memberDTO) {
        connSql();

        String memberId = sqlSession.selectOne("loginCheck", memberDTO);
        sqlSession.close();
        return memberId;
    }

    @Override
    public int idCheck(String id) {
        connSql();
        MemberDTO memberDTO = new MemberDTO.Builder().memberId(id).build();
        int result = sqlSession.selectOne("idCheck", memberDTO);

        sqlSession.close();
        return result;
    }

    private void connSql() {
        sqlSession = MybatisConnectionFactory.getSqlSession();
    }
}
