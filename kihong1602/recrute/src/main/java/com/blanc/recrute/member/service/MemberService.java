package com.blanc.recrute.member.service;

import com.blanc.recrute.member.dto.MemberDTO;

public interface MemberService {

    int insertMember(MemberDTO memberDTO);

    MemberDTO loginCheck(MemberDTO memberDTO);

    boolean idCheck(String id);
}
