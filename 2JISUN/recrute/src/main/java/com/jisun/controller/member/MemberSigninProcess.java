package com.jisun.controller.member;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
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

	//jsp값을 dto(loginMember)에 저장
	String userId = request.getParameter("userId"); //signin.jsp > input(name="userId") > userId : $("#userId").val()
	String pw = request.getParameter("pw");
	MemberDto loginMember = new MemberDto();
				loginMember.setMemberID(userId);
				loginMember.setPassword(pw);
	
	//dao( mybatisConn -> 쿼리xml )
	//dto(loginMember)를 dao(쿼리)를 통해 db와 맵핑하여 dto(loggedMember)로 저장
	MemberDao memberDao = new MemberDao();
	MemberDto loggedMember = memberDao.loginMember(loginMember);
	
	System.out.println(loggedMember);
	
	//http 메시지를 json으로 변경시켜서 가져오기
	Map<String, String> idPwMap = new HashMap<>();
					  //idPwMap.put(key, value);
						idPwMap.put("userId", loggedMember.getMemberID());
						idPwMap.put("password", loggedMember.getPassword());
	
	Gson gson = new Gson();
	String resultJson = gson.toJson(idPwMap);
			request.setAttribute("resultJson", resultJson);
	
	//json으로 값 넘기기(resultJson만 보이는 페이지)
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/signinJson.jsp");
	dispatcher.forward(request, response);
	
	//성공시 signin.jsp로 돌아가서 ajax의 success처리
	//실패시 signin.jsp로 돌아가서 ajax의 error처리
	
	}
}
