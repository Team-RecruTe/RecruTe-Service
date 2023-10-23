package com.hossi.recrute.member.controller;

import com.google.gson.Gson;
import com.hossi.recrute.common.response.data.Message;
import com.hossi.recrute.common.response.data.MessageCreator;
import com.hossi.recrute.common.response.data.ResponseData;
import com.hossi.recrute.common.response.service.ServicePrefix;
import com.hossi.recrute.common.response.util.ResponseUtil;
import com.hossi.recrute.member.dto.request.CheckDupReqDto;
import com.hossi.recrute.member.dto.response.CheckDupResDto;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static com.hossi.recrute.common.response.service.ServicePrefix.MBR;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "checkDupServlet", value = "/signup/check-duplicate")
public class CheckDupServlet extends HttpServlet {
    private final static Gson gson = new Gson();
    private final static MemberService memberService = new MemberService();
    private final static MessageCreator messageCreator = MessageCreator.getInstance();
    private final static ServicePrefix prefix = MBR;
    private final static ResponseData responseData = new ResponseData();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckDupReqDto checkDupReqDto = new CheckDupReqDto(request.getParameter("memberId"));
        CheckDupResDto checkDupResDto = memberService.checkDuplicate(checkDupReqDto);
        responseData.set("isDup", checkDupResDto.getIsDup());
        Message<Map<String, Object>> message = messageCreator.create(prefix, "003", true, "Not Duplicated", responseData);
        ResponseUtil.sendJson(SC_OK, gson.toJson(message), response);
    }
}
