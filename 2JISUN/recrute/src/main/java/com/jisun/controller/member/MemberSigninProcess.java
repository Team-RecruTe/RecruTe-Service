package com.jisun.controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.jisun.dao.MemberDao;
import com.jisun.dto.MemberDto;


public class MemberSigninProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberSigninProcess() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	//로그인 페이지 jquery(ajax{json}) action을 처리합니다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("userId"));

	String userId = request.getParameter("userId"); //signin.jsp 에서 <script>ajax</script>로 params 받아오기
	String pw = request.getParameter("pw");
	System.out.println(userId);
	
	MemberDao memberDao = new MemberDao();
	MemberDto loginMember = new MemberDto();
	loginMember.setMemberID(userId);
	loginMember.setPassword(pw);
	MemberDto loggedMember = memberDao.loginMember(loginMember);
	System.out.println(loggedMember);
	
	
	
		
		
	}

}
