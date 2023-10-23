package com.hossi.recrute.member.controller;


import com.hossi.recrute.common.request.Authenticator;
import com.hossi.recrute.common.request.RequestUtil;
import com.hossi.recrute.common.response.AttributeContainer;
import com.hossi.recrute.common.response.CookieContainer;
import com.hossi.recrute.common.response.ResponseUtil;
import com.hossi.recrute.common.response.ViewResolver;
import com.hossi.recrute.member.dto.request.SignupDto;
import com.hossi.recrute.member.service.MemberService;
import com.hossi.recrute.member.vo.GenderVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

import static jakarta.servlet.http.HttpServletResponse.SC_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name="signupServlet", value="/signup")
public class SignupServlet extends HttpServlet {
    private static final CookieContainer cookieContainer = new CookieContainer();
    private static final AttributeContainer attributeContainer = new AttributeContainer();

    private final MemberService memberService = new MemberService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        attributeContainer.set("mainViewPath", ViewResolver.resolveMainViewPath("signup"));
        ResponseUtil.setAttributes(attributeContainer, request);
        ResponseUtil.forward(SC_OK, ViewResolver.getMainViewPath(), request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        GenderVo gender = request.getParameter("gender").equals("male") ? GenderVo.MALE : GenderVo.FEMALE;
        String birthStr = request.getParameter("birth");
        String[] yymmdd = birthStr.split("-");
        int year = Integer.parseInt(yymmdd[0]);
        int month = Integer.parseInt(yymmdd[1]);
        int day = Integer.parseInt(yymmdd[2]);
        LocalDate birth = LocalDate.of(year, month, day);

        SignupDto signupDto = new SignupDto.Builder()
            .memberId(request.getParameter("id"))
            .email(request.getParameter("email"))
            .name(request.getParameter("name"))
            .gender(gender.ordinal())
            .password(request.getParameter("password"))
            .passwordConfirm(request.getParameter("passwordConfirm"))
            .phoneNumber(request.getParameter("phoneNumber"))
            .birth(birth)
            .build();

        Integer id = memberService.signup(signupDto);
        if(id != null) {
            Authenticator authenticator = new Authenticator();
            authenticator.setAuthCookie(RequestUtil.getSession(request), id);
            cookieContainer.setCookies(request);
            cookieContainer.setCookie(authenticator.getAuthCookie(cookieContainer));
            ResponseUtil.setCookies(cookieContainer, response);
            ResponseUtil.sendRedirect(SC_FOUND, "/signup/complete", response);
        }
    }
}
