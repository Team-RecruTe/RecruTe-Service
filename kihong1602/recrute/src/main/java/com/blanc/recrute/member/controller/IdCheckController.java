package com.blanc.recrute.member.controller;

import com.blanc.recrute.member.service.MemberService;
import com.blanc.recrute.member.service.MemberServiceImpl;
import com.blanc.recrute.member.view.ViewResolver;
import com.blanc.recrute.member.vo.IdCheckVO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "check-id", value = "/check-id")
public class IdCheckController extends HttpServlet {
    private static MemberService memberService = new MemberServiceImpl();
    private static ViewResolver viewResolver = new ViewResolver();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader br = request.getReader()) {
            String json;
            while ((json = br.readLine()) != null) {
                jsonBuilder.append(json);
            }
        }

        Gson gson = new Gson();
        IdCheckVO idCheckVO = gson.fromJson(jsonBuilder.toString(), IdCheckVO.class);
        String memberId = idCheckVO.getMemberId();

        boolean check = memberService.idCheck(memberId);

        Map<String, String> resultToMap = new HashMap<>();
        String result;
        if (check) {
            resultToMap.put("data", "available");
        } else {
            resultToMap.put("data", "unavailable");
        }
        result = gson.toJson(resultToMap);


        request.setAttribute("result", result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
}
