package com.juju.controller.member;

import java.io.IOException;
import java.util.HashMap;
import com.google.gson.Gson;
import com.juju.dao.MemberDao;
import com.juju.dto.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class SignInProcess extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public SignInProcess() {
    super();

  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // response.setContentType("application/x-json; charset=UTF-8");
    // response.setContentType("application/x-json; charset=UTF-8");


    String userId = request.getParameter("userId");
    String pw = request.getParameter("pw");

    // System.out.println("userId===" + userId);

    MemberDto memberDto = new MemberDto();
    memberDto.setMember_id(userId);
    memberDto.setPassword(pw);


    MemberDto loginResult = new MemberDto();


    MemberDao memberDao = new MemberDao();
    loginResult = memberDao.loginMember(memberDto);

    HttpSession session = request.getSession();
    session.setAttribute("loggedID", loginResult.getMember_id());
    String id = loginResult.getMember_id();
    String pw02 = loginResult.getPassword();



    HashMap<String, Object> loginMap = new HashMap<>();
    // loginMap.put("loginResult", loginResult);
    loginMap.put("id", id);
    loginMap.put("pw", pw02);


    Gson gson = new Gson();
    String resultJson = gson.toJson(loginMap);


    request.setAttribute("resultJson", resultJson);


    RequestDispatcher dispatcher =
        request.getRequestDispatcher("/WEB-INF/member/login-complete.jsp");
    dispatcher.forward(request, response);
  }

}
