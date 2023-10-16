package com.hossi.recrute.dao;

import com.hossi.recrute.common.MyBatisConnectionFactory;
import com.hossi.recrute.dto.request.SignupDto;
import org.apache.ibatis.session.SqlSession;

public class MemberDao {
    private static SqlSession sqlSession;

    public void register(SignupDto signupDto) {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();

        // 0: false, 1: true
        int result = sqlSession.insert("saveMember", signupDto);

        sqlSession.close();
    }
}
