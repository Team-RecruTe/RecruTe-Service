package com.jisun.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MemberSignupProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberSignupProcess() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	//회원가입페이지 form action을 처리합니다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String pw = request.getParameter("pw");
		String username = request.getParameter("username");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String phoneNum = request.getParameter("phoneNum");
		String email = request.getParameter("email");
		
		
		
	}

}
