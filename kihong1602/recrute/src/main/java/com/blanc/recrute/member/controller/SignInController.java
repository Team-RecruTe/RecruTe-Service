package com.blanc.recrute.member.controller;

import com.blanc.recrute.member.service.MemberService;
import com.blanc.recrute.member.service.MemberServiceImpl;
import com.blanc.recrute.member.view.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signin", value = "/signin")
public class SignInController extends HttpServlet {

    private static MemberService memberService = new MemberServiceImpl();
    private static ViewResolver viewResolver = new ViewResolver();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "sign/signup";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
