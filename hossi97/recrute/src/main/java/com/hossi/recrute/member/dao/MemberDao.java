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

        try(SqlSession sqlSession = MyBatisConnectionManager.getSqlSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            Integer id = mapper.insertMember(signupReqDto);
            signupResDto = mapper.selectIdAndCertificationById(id);
            sqlSession.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return signupResDto;
    }

    public SigninResDto findIdAndCertification(SigninReqDto signinReqDto) {
        SigninResDto signinResDto = null;

        try(SqlSession sqlSession = MyBatisConnectionManager.getSqlSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            signinResDto = mapper.selectIdAndCertificationByUsernameAndPassword(signinReqDto);
            sqlSession.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return signinResDto;
    }

    public CheckDupResDto getCount(CheckDupReqDto checkDupReqDto) {
        Integer count = 0;
        try (SqlSession sqlSession = MyBatisConnectionManager.getSqlSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            count = mapper.selectCountByUsername(checkDupReqDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new CheckDupResDto(count > 0);
    }

    public ResultType updateCerification(Integer id) {
        int result = 0;

        try (SqlSession sqlSession = MyBatisConnectionManager.getSqlSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            result = mapper.updateCertificationById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return SUCCESS.equals(result) ? SUCCESS : FAILURE;
    }

    public String findEmail(Integer id) {
        String email = null;

        try (SqlSession sqlSession = MyBatisConnectionManager.getSqlSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            email = mapper.selectEmailById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return email;
    }
}
