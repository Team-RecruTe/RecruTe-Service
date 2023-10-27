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
import java.time.LocalDate;

@WebServlet(name = "signup", value = "/signup")
public class SignUpController extends HttpServlet {

    private static final MemberService memberService = new MemberServiceImpl();
    private static final ViewResolver viewResolver = new ViewResolver();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/member/register/signup-process";
        request.getRequestDispatcher(viewResolver.viewPath(path)).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("member_id");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        LocalDate birth = LocalDate.parse(request.getParameter("birth"));
        String phoneNumber = request.getParameter("phoneNumber");

        MemberDTO memberDto = new MemberDTO.Builder().memberId(memberId).name(name).password(password).email(email).gender(gender).birth(birth).phoneNumber(phoneNumber).build();
        int result = memberService.insertMember(memberDto);

        if (result > 0) {
            request.setAttribute("signUpID", memberId);
        }

        String path = "member/register/signup";
        request.getRequestDispatcher(viewResolver.viewPath(path)).forward(request, response);
    }
}
