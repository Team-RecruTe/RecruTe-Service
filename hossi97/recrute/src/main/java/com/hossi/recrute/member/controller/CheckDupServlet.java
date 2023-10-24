package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.http.JsonManager;
import com.hossi.recrute.common.util.http.message.Message;
import com.hossi.recrute.common.util.http.message.MessageCreator;
import com.hossi.recrute.common.util.http.message.ResponseData;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.common.util.service.ServicePrefix;
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

import static com.google.common.net.MediaType.JSON_UTF_8;
import static com.hossi.recrute.common.util.service.ServicePrefix.MBR;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "checkDupServlet", value = "/signup/check-duplicate")
public class CheckDupServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final ServicePrefix prefix = MBR;
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckDupReqDto checkDupReqDto = new CheckDupReqDto(request.getParameter("memberId"));
        CheckDupResDto checkDupResDto = memberService.checkDuplicate(checkDupReqDto);
        ResponseData responseData = new ResponseData.Builder()
            .set("isDup", checkDupResDto.getIsDup())
            .build();
        Message<Map<String, Object>> message = MessageCreator.create(prefix, "003", true, "Not responseData", responseData);
        servletHandler
            .setStatus(SC_OK, response)
            .setJson(JSON_UTF_8, JsonManager.toJson(message), response);
    }
}
