package com.hossi.recrute.member.dao;

import com.hossi.recrute.common.data.MyBatisConnectionManager;
import com.hossi.recrute.member.dto.request.CheckDupReqDto;
import com.hossi.recrute.member.dto.request.SigninDto;
import com.hossi.recrute.member.dto.request.SignupDto;
import com.hossi.recrute.member.dto.response.CheckDupResDto;
import org.apache.ibatis.session.SqlSession;

public class MemberDao {
    public Integer register(SignupDto signupDto) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        Integer id = sqlSession.insert("saveMember", signupDto);
        sqlSession.close();

        return id;
    }

    public Integer getIdByMemberId(SigninDto signinDto) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        Integer id = sqlSession.selectOne("selectIdByMemberIdAndPassword", signinDto);
        sqlSession.close();

        return id;
    }

    public CheckDupResDto getCountByMemberId(CheckDupReqDto checkDupReqDto) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        Integer count = sqlSession.selectOne("selectCountByMemberId", checkDupReqDto);
        CheckDupResDto checkDupResDto = new CheckDupResDto(count > 0);
        sqlSession.close();

        return checkDupResDto;
    }

    public void activeAuthStatus(Integer id) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        sqlSession.update("updateAuthStatusById", id);
        sqlSession.close();
    }

    public String getEmailById(Integer id) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        String email = sqlSession.selectOne("selectEmailById", id);
        sqlSession.close();

        return email;
    }
}
