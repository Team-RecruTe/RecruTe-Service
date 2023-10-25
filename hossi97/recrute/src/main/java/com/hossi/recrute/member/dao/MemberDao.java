package com.hossi.recrute.member.dao;

import com.hossi.recrute.common.util.mybatis.MyBatisConnectionManager;
import com.hossi.recrute.member.dto.request.CheckDupReqDto;
import com.hossi.recrute.member.dto.request.SigninReqDto;
import com.hossi.recrute.member.dto.request.SignupReqDto;
import com.hossi.recrute.member.dto.response.CheckDupResDto;
import com.hossi.recrute.member.dto.response.SigninResDto;
import com.hossi.recrute.member.dto.response.SignupResDto;
import org.apache.ibatis.session.SqlSession;

public class MemberDao {
    public SignupResDto saveMember(SignupReqDto signupReqDto) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        SignupResDto signupResDto = sqlSession.insert("insertMember", signupReqDto);
        sqlSession.close();

        return signupResDto;
    }

    public SigninResDto findIdAndCertification(SigninReqDto signinReqDto) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        SigninResDto signinResDto = sqlSession.selectOne("selectIdAndCertificationByUsernameAndPassword", signinReqDto);
        sqlSession.close();

        return signinResDto;
    }

    public CheckDupResDto getCount(CheckDupReqDto checkDupReqDto) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        Integer count = sqlSession.selectOne("selectCountByUsername", checkDupReqDto);
        CheckDupResDto checkDupResDto = new CheckDupResDto(count > 0);
        sqlSession.close();

        return checkDupResDto;
    }

    public void updateCerification(Integer id) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        sqlSession.update("updateCertificationById", id);
        sqlSession.close();
    }

    public String findEmail(Integer id) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        String email = sqlSession.selectOne("selectEmailById", id);
        sqlSession.close();

        return email;
    }
}
