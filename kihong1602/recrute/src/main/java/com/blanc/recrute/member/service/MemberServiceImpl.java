package com.blanc.recrute.member.service;

import com.blanc.recrute.member.dao.MemberDAO;
import com.blanc.recrute.member.dao.MemberDAOImpl;
import com.blanc.recrute.member.dto.MemberDTO;

public class MemberServiceImpl implements MemberService {

    MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    public int insertMember(MemberDTO memberDTO) {
        int result = 0;
        if (memberDTO != null) {
            result = memberDAO.insertMember(memberDTO);
        }


        return result;
    }

    @Override
    public MemberDTO loginCheck(MemberDTO memberDTO) {

        String memberId = memberDAO.loginCheck(memberDTO);

        if (memberId == null) {
            return null;
        }

        return new MemberDTO.Builder().memberId(memberId).build();
    }
}
