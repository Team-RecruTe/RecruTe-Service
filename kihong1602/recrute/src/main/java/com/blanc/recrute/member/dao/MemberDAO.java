package com.blanc.recrute.member.dao;

import com.blanc.recrute.member.dto.MemberDTO;

public interface MemberDAO {
    int insertMember(MemberDTO memberDTO);

    String loginCheck(MemberDTO memberDTO);

    int idCheck(String id);

    String searchMember(MemberDTO memberDTO);

    int authGrantMember(MemberDTO memberDTO);
}
