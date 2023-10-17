package com.jisun.controller.member;

import java.io.IOException;
import java.time.LocalDate;

import com.jisun.dao.MemberDao;
import com.jisun.dto.MemberDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		String birthStr = request.getParameter("birth");
			String[] yymmdd = birthStr.split("-");
	        int year = Integer.parseInt(yymmdd[0]);
	        int month = Integer.parseInt(yymmdd[1]);
	        int day = Integer.parseInt(yymmdd[2]);
	        LocalDate birth = LocalDate.of(year, month,day);
	        
		int gender = 0;
			if(request.getParameter("gender")!=null && !request.getParameter("gender").isEmpty()) {
				gender = Integer.parseInt(request.getParameter("gender"));
			}
		System.out.println(gender);
			
		String phoneNum = request.getParameter("phoneNum");
		String email = request.getParameter("email");

		
		MemberDto insertMember = new MemberDto();
		insertMember.setMemberID(userId);
		insertMember.setPassword(pw);
		insertMember.setName(username);
		insertMember.setEmail(email);
		insertMember.setBirth(birth);
		insertMember.setGender(gender);
		insertMember.setPhoneNum(phoneNum);
		
		
		MemberDao memberDao = new MemberDao();
		int result = memberDao.insertMember(insertMember); 
		if(result>0) {
			response.sendRedirect("../member/signin");
		} else {
			response.sendRedirect("../member/signup");
		}

	}
	


}
