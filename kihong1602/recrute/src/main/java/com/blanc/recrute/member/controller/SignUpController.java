package com.blanc.recrute.member.controller;

import com.blanc.recrute.member.dto.MemberDTO;
import com.blanc.recrute.member.service.MemberService;
import com.blanc.recrute.member.service.MemberServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signup", value = "/signup")
public class SignUpController extends HttpServlet {

    private static MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "sign/signup-process";
        forward(request, response, path);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("member_id");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String birth = request.getParameter("birth").replace('-', '.');
        String phoneNumber = request.getParameter("phoneNumber");

        MemberDTO memberDto = new MemberDTO.Builder().memberId(memberId).name(name).password(password).email(email).gender(gender).birth(birth).phoneNumber(phoneNumber).build();
        int result = memberService.insertMember(memberDto);

        if (result > 0) {
            request.setAttribute("signUpID", memberId);
        }

        String path = "sign/signup";
        forward(request, response, path);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/" + path + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
