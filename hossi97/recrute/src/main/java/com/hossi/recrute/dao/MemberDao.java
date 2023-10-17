package com.hossi.recrute.dao;

import com.hossi.recrute.common.MyBatisConnectionFactory;
import com.hossi.recrute.dto.request.SigninDto;
import com.hossi.recrute.dto.request.SignupDto;
import org.apache.ibatis.session.SqlSession;

public class MemberDao {
    public boolean register(SignupDto signupDto) {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
        // 0: false, 1: true
        int result = sqlSession.insert("saveMember", signupDto);
        sqlSession.close();

        return result == 1;
    }

    public Integer findById(SigninDto signinDto) {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
        Integer id = sqlSession.selectOne("findMember", signinDto);
        sqlSession.close();

        return id;
    }
}
