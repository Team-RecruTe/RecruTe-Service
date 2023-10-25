package com.jisun.controller.member;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import com.jisun.dao.MemberDao;
import com.jisun.dto.MemberDto;
import com.jisun.util.EmailManager;

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
		LocalDate birth = LocalDate.parse(birthStr, DateTimeFormatter.ISO_DATE);
		int gender = 0;
			if(request.getParameter("gender")!=null && !request.getParameter("gender").isEmpty()) {
				gender = Integer.parseInt(request.getParameter("gender"));
			}
		String phoneNum = request.getParameter("phoneNum");
		String email = request.getParameter("email");
		
		
		// 메일을 보내기 위한 변수 할당
		// @를 기준으로 아이디와 호스트를 분리
		String hostName = null;
        String[] emailParts = email.split("@");
        if(emailParts.length == 2) {
        	// String emailId = emailParts[0];
        	hostName = emailParts[1].replace(".com", ""); // .com 제거
        }
        String sendEmail = "snm03097@naver.com";
        String password = "Universe96";
        String subject = "Thank you Sign up to RecruTe!";
		String content = "환영합니다!";
        

		
		MemberDao memberDao = new MemberDao();
		MemberDto insertMember = new MemberDto();
				  insertMember.setMemberID(userId);
				  insertMember.setPassword(pw);
				  insertMember.setName(username);
				  insertMember.setEmail(email);
				  insertMember.setBirth(birth);
				  insertMember.setGender(gender);
				  insertMember.setPhoneNum(phoneNum);
				  
	
		int result = memberDao.insertMember(insertMember); 
		if(result>0) {
			EmailManager.mailSend(sendEmail, password, hostName, email, subject, content);
			response.sendRedirect("../member/signin");
		} else {
			response.sendRedirect("../member/signup");
		}

	}
	


}
