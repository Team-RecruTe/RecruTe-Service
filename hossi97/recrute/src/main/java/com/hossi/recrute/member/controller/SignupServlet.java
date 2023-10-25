package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.AuthData;
import com.hossi.recrute.common.util.auth.Authenticator;
import com.hossi.recrute.common.util.auth.processor.AuthManager;
import com.hossi.recrute.common.util.auth.util.AuthStream;
import com.hossi.recrute.common.util.code.ErrorMessage;
import com.hossi.recrute.common.util.code.anno.MBR;
import com.hossi.recrute.common.util.http.JsonManager;
import com.hossi.recrute.common.util.http.message.MessageCreator;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.common.util.http.servlet.ViewResolver;
import com.hossi.recrute.member.dto.request.SignupReqDto;
import com.hossi.recrute.member.dto.response.SignupResDto;
import com.hossi.recrute.member.service.MemberService;
import com.hossi.recrute.member.vo.Gender;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

import static com.google.common.net.MediaType.JSON_UTF_8;
import static com.hossi.recrute.common.util.auth.AuthType.COOKIE;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name="signupServlet", value="/signup")
public class SignupServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    @Override @MBR("001")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(AuthManager.renewIfAuth(COOKIE, new AuthStream(request, response))) {
            servletHandler
                .sendRedirect("", response);
        } else {
            servletHandler
                .setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("signup"), request)
                .setStatus(SC_OK, response)
                .forward(ViewResolver.getMainViewPath(), request, response);
        }
    }

    @Override @MBR("002")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Gender gender = request.getParameter("gender").equals("male") ? Gender.MALE : Gender.FEMALE;
        String birthStr = request.getParameter("birth");
        String[] yymmdd = birthStr.split("-");
        int year = Integer.parseInt(yymmdd[0]);
        int month = Integer.parseInt(yymmdd[1]);
        int day = Integer.parseInt(yymmdd[2]);
        LocalDate birth = LocalDate.of(year, month, day);

        SignupReqDto signupReqDto = new SignupReqDto.Builder()
            .username(request.getParameter("id"))
            .email(request.getParameter("email"))
            .name(request.getParameter("name"))
            .gender(gender.ordinal())
            .password(request.getParameter("password"))
            .passwordConfirm(request.getParameter("passwordConfirm"))
            .phoneNumber(request.getParameter("phoneNumber"))
            .birth(birth)
            .build();

        SignupResDto signupResDto = memberService.signup(signupReqDto);
        AuthData authData = new AuthData.Builder()
            .id(signupResDto.getId())
            .certification(signupResDto.getCertification())
            .build();
        if(!authData.isEmpty()) {
            Authenticator authenticator = AuthManager.grantAuth(COOKIE);
            AuthManager.setAuth(authenticator, authData, new AuthStream(request, response));
            servletHandler
                .setStatus(SC_OK, response)
                .sendRedirect("/member/email", response);
        } else {
            ErrorMessage errorMessage = MessageCreator.createErrorMessage();
            servletHandler
                .setStatus(SC_INTERNAL_SERVER_ERROR, response)
                .setJson(JSON_UTF_8, JsonManager.toJson(errorMessage), response);
        }
    }
}
