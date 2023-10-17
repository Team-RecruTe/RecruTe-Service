package com.blanc.recrute.member.controller;

import com.blanc.recrute.member.dto.MemberDTO;
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
        String path = "member/login/signin-process";
        request.getRequestDispatcher(viewResolver.viewPath(path)).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "member/login/signin";
        String memberId = request.getParameter("member_id");
        String password = request.getParameter("password");

        MemberDTO memberDTO = new MemberDTO.Builder().memberId(memberId).password(password).build();

        MemberDTO memberResponseDTO = memberService.loginCheck(memberDTO);
        boolean check = memberResponseDTO != null;
        request.setAttribute("loginCheck", check);
        request.setAttribute("loginMember", memberResponseDTO);
        request.getRequestDispatcher(viewResolver.viewPath(path)).forward(request, response);
    }
}
