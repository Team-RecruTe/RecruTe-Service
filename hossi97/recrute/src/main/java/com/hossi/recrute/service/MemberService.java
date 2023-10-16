package com.hossi.recrute.service;

import com.hossi.recrute.dao.MemberDao;
import com.hossi.recrute.dto.request.SignupDto;

public class MemberService {
    private final MemberDao memberDao = new MemberDao();
    public void register(SignupDto signupDto) {
        if(validatePassword(signupDto.getPassword(), signupDto.getPasswordConfirm())) {
            memberDao.register(signupDto);
        }
    }

    private boolean validatePassword(String pw1, String pw2) {
        return pw1.equals(pw2);
    }
}
