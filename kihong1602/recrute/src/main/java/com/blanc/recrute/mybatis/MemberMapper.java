package com.blanc.recrute.mybatis;

import com.blanc.recrute.member.dto.MemberDTO;
import com.blanc.recrute.member.dto.MemberInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

  int saveMember(MemberInfoDTO memberInfoDTO);

  Integer duplicateId(MemberDTO memberDTO);

  String findEmailById(MemberDTO memberDTO);

  String loginCheck(MemberDTO memberDTO);

  int emailAuth(MemberDTO memberDTO);
}
