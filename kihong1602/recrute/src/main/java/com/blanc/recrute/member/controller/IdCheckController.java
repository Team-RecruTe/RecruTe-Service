package com.blanc.recrute.member.controller;

import com.blanc.recrute.member.dto.IdCheckDTO;
import com.blanc.recrute.member.dto.InvalidDTO;
import com.blanc.recrute.member.service.MemberService;
import com.blanc.recrute.member.service.MemberServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "check-id", value = "/check-id")
public class IdCheckController extends HttpServlet {
    private static MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader br = request.getReader()) {
            String json;
            while ((json = br.readLine()) != null) {
                jsonBuilder.append(json);
            }
        }

        IdCheckDTO idCheckDTO = new Gson().fromJson(jsonBuilder.toString(), IdCheckDTO.class);
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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
}
