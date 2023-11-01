package com.hossi.recrute.member.dao;

import com.hossi.recrute.common.mybatis.MyBatisConnectionManager;
import com.hossi.recrute.common.mybatis.ResultType;
import com.hossi.recrute.member.dto.request.CheckDupReqDto;
import com.hossi.recrute.member.dto.request.SigninReqDto;
import com.hossi.recrute.member.dto.request.SignupReqDto;
import com.hossi.recrute.member.dto.response.CheckDupResDto;
import com.hossi.recrute.member.dto.response.SigninResDto;
import com.hossi.recrute.member.dto.response.SignupResDto;
import org.apache.ibatis.session.SqlSession;

import static com.hossi.recrute.common.mybatis.ResultType.FAILURE;
import static com.hossi.recrute.common.mybatis.ResultType.SUCCESS;

public class MemberDao {

    public SignupResDto saveMember(SignupReqDto signupReqDto) {

        SignupResDto signupResDto = null;
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        try {
            Integer id = sqlSession.insert("insertMember", signupReqDto);
            signupResDto = sqlSession.selectOne("selectIdAndCertificationById", id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSession.close();

        return signupResDto;
    }

    public SigninResDto findIdAndCertification(SigninReqDto signinReqDto) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        SigninResDto signinResDto = sqlSession.selectOne("selectIdAndCertificationByUsernameAndPassword", signinReqDto);
        MyBatisConnectionManager.closeSqlSession(sqlSession);

        return signinResDto;
    }

    public CheckDupResDto getCount(CheckDupReqDto checkDupReqDto) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        Integer count = sqlSession.selectOne("selectCountByUsername", checkDupReqDto);
        CheckDupResDto checkDupResDto = new CheckDupResDto(count > 0);
        MyBatisConnectionManager.closeSqlSession(sqlSession);

        return checkDupResDto;
    }

    public ResultType updateCerification(Integer id) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        int result = sqlSession.update("updateCertificationById", id);
        MyBatisConnectionManager.closeSqlSession(sqlSession);

        return SUCCESS.equals(result) ? SUCCESS : FAILURE;
    }

    public String findEmail(Integer id) {
        SqlSession sqlSession = MyBatisConnectionManager.getSqlSession();
        String email = sqlSession.selectOne("selectEmailById", id);
        MyBatisConnectionManager.closeSqlSession(sqlSession);

        return email;
    }
}
