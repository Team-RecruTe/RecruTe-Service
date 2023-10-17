package com.blanc.recrute.member.controller;

import com.blanc.recrute.member.dto.LoginDTO;
import com.blanc.recrute.member.service.MemberService;
import com.blanc.recrute.member.service.MemberServiceImpl;
import com.blanc.recrute.member.util.Authenticater;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {
    private static MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Authenticater authenticater = new Authenticater();
        if (authenticater.isAuthenticated(request)) {
            Cookie renewdAuthCookie = authenticater.getAuthCookie();
            response.addCookie(renewdAuthCookie);
            response.setStatus(302);
            response.sendRedirect("/");
        } else {


            StringBuilder jsonBuilder = new StringBuilder();

            try (BufferedReader br = request.getReader()) {
                String json;
                while ((json = br.readLine()) != null) {
                    jsonBuilder.append(json);
                }
            }


            LoginDTO loginDTO = new Gson().fromJson(jsonBuilder.toString(), LoginDTO.class);

            boolean check = memberService.asyncLoginCheck(loginDTO);

            Map<String, String> resultToMap = new HashMap<>();
            String result;
            if (check) {
                resultToMap.put("data", "available");

                authenticater.setAuthCookie(request, loginDTO.getMemberId());
                Cookie authCookie = authenticater.getAuthCookie();
                response.addCookie(authCookie);
            } else {
                resultToMap.put("data", "unavailable");
            }
            result = new Gson().toJson(resultToMap);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
        }
    }
}
