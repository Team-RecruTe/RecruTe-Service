package com.hossi.recrute.member.dao;

import com.hossi.recrute.member.dto.request.CheckDupReqDto;
import com.hossi.recrute.member.dto.request.SigninReqDto;
import com.hossi.recrute.member.dto.request.SignupReqDto;
import com.hossi.recrute.member.dto.response.SigninResDto;
import com.hossi.recrute.member.dto.response.SignupResDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Integer insertMember(SignupReqDto signupReqDto);
    SignupResDto selectIdAndCertificationById(Integer id);
    SigninResDto selectIdAndCertificationByUsernameAndPassword(SigninReqDto signupReqDto);
    Integer selectCountByUsername(CheckDupReqDto checkDupReqDto);
    String selectEmailById(Integer id);
    Integer updateCertificationById(Integer id);
}
