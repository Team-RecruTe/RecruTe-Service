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


public class MemberIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    public MemberIdCheck() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) throws ServletException, IOException {
		
		//jsp(id속성->ajax)값을 dto에 저장
		String userId = request.getParameter("userId");
		
				  
		//dao(쿼리)를 통해 db와 맵핑하여 int로 저장
		MemberDao memberDao = new MemberDao();
		int count = memberDao.idCheck(userId); //0 or 1
		
		//클라이언트 요청 방식 : json으로 저장
		Gson gson = new Gson();
		Map<String, Integer> map = new HashMap<>();
							 map.put("count", count);
		String resultJson = gson.toJson(map); //{count: 0 or 1}
		//request.setAttribute("클라이언트가 받을 것", resultJson);
		request.setAttribute("resultJson", resultJson);
		
		//페이지 맵핑
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/idCheckJson.jsp");
		dispatcher.forward(request, response);
		
		//성공시 jsp로 돌아가서 ajax의 success처리
		//실패시 jsp로 돌아가서 ajax의 error처리
		
		
	}


	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {

	}

}
