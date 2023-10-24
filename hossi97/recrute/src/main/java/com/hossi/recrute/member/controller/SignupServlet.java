package com.hossi.recrute.member.controller;


import com.hossi.recrute.common.util.auth.AuthCookie;
import com.hossi.recrute.common.util.auth.AuthProcessor;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.common.util.http.servlet.ViewResolver;
import com.hossi.recrute.member.dto.request.SignupDto;
import com.hossi.recrute.member.service.MemberService;
import com.hossi.recrute.member.vo.Gender;
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
    private final MemberService memberService = new MemberService();
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        servletHandler
            .setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("signup"), request)
            .setStatus(SC_OK, response)
            .forward(ViewResolver.getMainViewPath(), request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Gender gender = request.getParameter("gender").equals("male") ? Gender.MALE : Gender.FEMALE;
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
            AuthCookie authCookie = AuthProcessor.getAuthCookie(servletHandler.getCookies(request));
            servletHandler
                .setSession(authCookie.getValue(), id, request)
                .setCookie(authCookie.getCookie(), response)
                .setStatus(SC_FOUND, response)
                .sendRedirect("/signup/complete", response);
        }
    }
}
