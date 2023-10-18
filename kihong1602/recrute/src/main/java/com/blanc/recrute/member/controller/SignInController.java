package com.blanc.recrute.member.controller;

import com.blanc.recrute.member.dto.InvalidDTO;
import com.blanc.recrute.member.dto.LoginDTO;
import com.blanc.recrute.member.service.MemberService;
import com.blanc.recrute.member.service.MemberServiceImpl;
import com.blanc.recrute.member.util.Authenticater;
import com.blanc.recrute.member.util.JsonUtil;
import com.blanc.recrute.member.view.ViewResolver;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signin", value = "/signin")
public class SignInController extends HttpServlet {
    private static final MemberService memberService = new MemberServiceImpl();
    private static final ViewResolver viewResolver = new ViewResolver();
    private static final Authenticater authenticater = new Authenticater();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authenticater.isAuthenticated(request)) {
            Cookie renewdAuthCookie = authenticater.getAuthCookie();
            response.addCookie(renewdAuthCookie);
            response.setStatus(302);
            response.sendRedirect("/");
        } else {
            String path = "member/login/signin-process";
            request.getRequestDispatcher(viewResolver.viewPath(path)).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String parsingJSON = JsonUtil.jsonParsing(request);


        LoginDTO loginDTO = new Gson().fromJson(parsingJSON, LoginDTO.class);

        boolean check = memberService.loginCheck(loginDTO);
        InvalidDTO invalidDTO;
        String result;
        if (check) {
            invalidDTO = new InvalidDTO("available");

            authenticater.setAuthCookie(request, loginDTO.getMemberId());
            Cookie authCookie = authenticater.getAuthCookie();
            response.addCookie(authCookie);
        } else {
            invalidDTO = new InvalidDTO("unavailable");
        }
        result = new Gson().toJson(invalidDTO);

        JsonUtil.sendJSON(response, result);

    }
}
