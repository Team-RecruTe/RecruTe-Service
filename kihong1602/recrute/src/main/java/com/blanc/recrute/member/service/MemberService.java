package com.blanc.recrute.member.service;

import com.blanc.recrute.member.dto.LoginDTO;
import com.blanc.recrute.member.dto.MemberDTO;

public interface MemberService {

    int insertMember(MemberDTO memberDTO);

    String idCheck(String id);

    boolean loginCheck(LoginDTO loginDTO);

    MemberDTO searchMember(String memberId);
}
