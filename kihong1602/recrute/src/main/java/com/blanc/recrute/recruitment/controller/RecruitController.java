package com.blanc.recrute.recruitment.controller;

import com.blanc.recrute.common.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "recruitments/*", value = "/recruitments/*")
public class RecruitController extends HttpServlet {
    private static final ViewResolver ViewResolver = new ViewResolver();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "recruitment/rct-detail";
        request.getRequestDispatcher(ViewResolver.viewPath(path)).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //비동기 처리 apt_id 는 회사ID+회사Name+응시순서+@로 입력
        //외래키 삽입을 위해 MemberDTO 사용해야함
    }
}
