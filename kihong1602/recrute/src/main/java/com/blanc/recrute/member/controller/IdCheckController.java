package com.blanc.recrute.member.controller;

import com.blanc.recrute.member.dto.IdCheckDTO;
import com.blanc.recrute.member.dto.InvalidDTO;
import com.blanc.recrute.member.service.MemberService;
import com.blanc.recrute.member.service.MemberServiceImpl;
import com.blanc.recrute.member.util.JsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "check-id", value = "/check-id")
public class IdCheckController extends HttpServlet {
    private static final MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String parsingJSON = JsonUtil.jsonParsing(request);

        IdCheckDTO idCheckDTO = new Gson().fromJson(parsingJSON, IdCheckDTO.class);
        String memberId = idCheckDTO.getMemberId();

        String check = memberService.idCheck(memberId);

        InvalidDTO invalidDTO = null;

        switch (check) {
            case "blank":
                invalidDTO = new InvalidDTO("blank");
                break;
            case "none":
                invalidDTO = new InvalidDTO("available");
                break;
            case "exist":
                invalidDTO = new InvalidDTO("unavailable");
                break;
        }

        String result = new Gson().toJson(invalidDTO);

        JsonUtil.sendJSON(response, result);
    }
}
