package com.hossi.recrute.member.controller;

import com.google.gson.Gson;
import com.hossi.recrute.common.response.util.ResponseUtil;
import com.hossi.recrute.member.dto.request.CheckDupReqDto;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "checkDupServlet", value = "/signup/check-duplicate")
public class CheckDupServlet extends HttpServlet {
    private final static Gson gson = new Gson();
    private final static MemberService memberService = new MemberService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckDupReqDto checkDupReqDto = new CheckDupReqDto(request.getParameter("memberId"));
        ResponseUtil.sendJson(SC_OK, gson.toJson(checkDupReqDto), response);
    }
}
