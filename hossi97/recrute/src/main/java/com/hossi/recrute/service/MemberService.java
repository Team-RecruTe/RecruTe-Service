package com.hossi.recrute.service;

import com.hossi.recrute.dao.MemberDao;
import com.hossi.recrute.dto.request.CheckDupReqDto;
import com.hossi.recrute.dto.request.SigninDto;
import com.hossi.recrute.dto.request.SignupDto;
import com.hossi.recrute.dto.response.CheckDupResDto;

public class MemberService {
    private final MemberDao memberDao = new MemberDao();
    public boolean signup(SignupDto signupDto) {
        if(validatePassword(signupDto.getPassword(), signupDto.getPasswordConfirm())) {
            return memberDao.register(signupDto);
        }
        return false;
    }

    public Integer signin(SigninDto signinDto) {
        return memberDao.getIdByMemberId(signinDto);
    }

    public CheckDupResDto checkDuplicate(CheckDupReqDto checkDupReqDto) {
        return memberDao.getCountByMemberId(checkDupReqDto);
    }

    private boolean validatePassword(String pw1, String pw2) {
        return pw1.equals(pw2);
    }
}
