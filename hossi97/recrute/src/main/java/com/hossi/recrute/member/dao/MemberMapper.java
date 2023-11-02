package com.hossi.recrute.member.dao;

import com.hossi.recrute.member.dto.request.CheckDupReqDto;
import com.hossi.recrute.member.dto.request.SignupReqDto;
import com.hossi.recrute.member.dto.response.SignupResDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Integer insertMember(SignupReqDto signupReqDto);
    SignupResDto selectIdAndCertificationById(Integer id);
    Integer selectCountByUsername(CheckDupReqDto checkDupReqDto);
}
