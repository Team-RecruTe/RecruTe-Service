package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.processor.AuthManager;
import com.hossi.recrute.common.util.auth.util.AuthStream;
import com.hossi.recrute.common.util.code.anno.MBR;
import com.hossi.recrute.common.util.http.JsonManager;
import com.hossi.recrute.common.util.http.message.Message;
import com.hossi.recrute.common.util.http.message.MessageCreator;
import com.hossi.recrute.common.util.http.message.ResponseData;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.member.dto.request.CheckDupReqDto;
import com.hossi.recrute.member.dto.response.CheckDupResDto;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static com.google.common.net.MediaType.JSON_UTF_8;
import static com.hossi.recrute.common.util.auth.AuthType.COOKIE;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "checkDupServlet", value = "/signup/check-duplicate")
public class CheckDupServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    @Override @MBR("003")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(AuthManager.renewIfAuth(COOKIE, new AuthStream(request, response))) {
            servletHandler
                .sendRedirect("", response);
        } else {
            CheckDupReqDto checkDupReqDto = new CheckDupReqDto(request.getParameter("username"));
            CheckDupResDto checkDupResDto = memberService.checkDuplicate(checkDupReqDto);
            ResponseData responseData = new ResponseData.Builder()
                .set("isDup", checkDupResDto.getIsDup())
                .build();
            Message<Map<String, Object>> message = MessageCreator.create(responseData);
            servletHandler
                .setStatus(SC_OK, response)
                .setJson(JSON_UTF_8, JsonManager.toJson(message), response);
        }
    }
}
