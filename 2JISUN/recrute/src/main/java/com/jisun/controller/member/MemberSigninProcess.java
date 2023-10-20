package com.jisun.controller.member;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.jisun.dao.MemberDao;
import com.jisun.dto.MemberDto;
import com.jisun.dto.ModalDto;
import com.jisun.dto.ModalState;
import com.jisun.util.CookieManager;
import com.jisun.util.ScriptWriter;


public class MemberSigninProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberSigninProcess() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	//로그인 페이지 jquery(ajax{json}) action을 처리합니다.
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {

		//jsp값을 dto(loginMember)에 저장
		String userId = request.getParameter("userId"); //id속성
		String pw = request.getParameter("pw"); //id속성
		String saveId = request.getParameter("saveId"); //name속성
	
		MemberDto loginMember = new MemberDto();
					loginMember.setMemberID(userId);
					loginMember.setPassword(pw);
					
		
		//dao( mybatisConn -> 쿼리xml )
		//dto(loginMember)를 dao(쿼리)를 통해 db와 맵핑하여 dto(loggedMember)로 저장
		MemberDao memberDao = new MemberDao();
		MemberDto loggedMember = memberDao.loginMember(loginMember);
		
		
		
		//데이터가 존재하면 로그인 없으면 알림창
		if(loggedMember!=null) {
			//세션으로 데이터를 저장 > 로그인상태 유지를 위해 세션 추가
			HttpSession session = request.getSession();
						session.setAttribute("loggedId", loggedMember.getMemberID());
						session.setAttribute("loggedName", loggedMember.getName());
			
			//쿠키설정
			//input > type(checkbox) > value속성의 값과 일치할 경우 쿠키를 생성한다.
			if(saveId!=null){
			//페이지 방문 경험이 있고 로그인을 해본경우
				if(saveId.equals("rememberMe")) { //saveId value="rememberMe"일 경우
					//토글 on
					CookieManager.createCookie(response, "saveIdCookie", userId, 60*60*24*365); //
				} else { 
					//토글 off
					CookieManager.deleteCookie(response, "saveIdCookie");
				}
			} else {
			//페이지 방문경험이 없음
				CookieManager.deleteCookie(response, "saveIdCookie");
			}
	
			
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
}
