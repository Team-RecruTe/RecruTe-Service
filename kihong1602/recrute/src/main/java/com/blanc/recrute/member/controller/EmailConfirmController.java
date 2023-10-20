package com.blanc.recrute.member.controller;

import com.blanc.recrute.common.CookieManager;
import com.blanc.recrute.common.JsonUtil;
import com.blanc.recrute.common.ViewResolver;
import com.blanc.recrute.member.dto.ConfirmValueDTO;
import com.blanc.recrute.member.dto.InvalidDTO;
import com.blanc.recrute.member.dto.MemberDTO;
import com.blanc.recrute.member.service.MemberService;
import com.blanc.recrute.member.service.MemberServiceImpl;
import com.blanc.recrute.member.service.SendEmailService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "email", value = "/email")
public class EmailConfirmController extends HttpServlet {

    private static MemberService memberService = new MemberServiceImpl();
    private static ViewResolver viewResolver = new ViewResolver();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("email") == null) {
            String path = "member/register/email-confirm";
            request.getRequestDispatcher(viewResolver.viewPath(path)).forward(request, response);
        } else {
            //이메일에 있는 링크 클릭 후 AuthKey 판별해서 Auth-status 카운트하는곳
            String email = request.getParameter("email");
            String authKey = request.getParameter("authKey");
            if (request.getSession().getAttribute("authKey") == authKey) {
                //email의 파라미터로 들어온 authKey가 session에 저장된 authKey와 동일하다면
            } else {
                //동일하지 않다면..
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parsingJson = JsonUtil.jsonParsing(request);
        ConfirmValueDTO confirmValueDTO = new Gson().fromJson(parsingJson, ConfirmValueDTO.class);

        String memberId = confirmValueDTO.getKey();

        if (confirmValueDTO.getKey().equals("sid")) {
            //AuthCookie 의 Key 가 왔을 시
            Cookie AuthCookie = CookieManager.getCookie(request, confirmValueDTO.getKey());
            if (AuthCookie != null) {
                memberId = (String) request.getSession().getAttribute(AuthCookie.getValue());
            }
        }

        MemberDTO memberDTO = memberService.searchMember(memberId);

        String authKey = String.valueOf(UUID.randomUUID());

        //이메일 발송
        boolean check = SendEmailService.mailSend("gmail", "emailAddress", "password", memberDTO.getEmail(), authKey);
        InvalidDTO invalidDTO = check ? new InvalidDTO("available") : new InvalidDTO("unavailable");
        request.getSession().setAttribute("authKey", authKey);

        String result = new Gson().toJson(invalidDTO);
        JsonUtil.sendJSON(response, result);
    }
}
