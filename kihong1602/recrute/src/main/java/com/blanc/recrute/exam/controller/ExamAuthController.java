package com.blanc.recrute.exam.controller;

import com.blanc.recrute.common.CookieManager;
import com.blanc.recrute.common.ViewResolver;
import com.blanc.recrute.exam.dto.ExamInfoDTO;
import com.blanc.recrute.exam.service.ExamService;
import com.blanc.recrute.member.dto.InvalidDTO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "exam", value = "/exam/auth/*")
public class ExamAuthController extends HttpServlet {

  //시험 인증 관련
  private static ExamService examService = new ExamService();
  private static ViewResolver viewResolver = new ViewResolver();
  private static final Gson GSON = new Gson();
  private static final String COOKIE_NAME = "examAuth";
  private static InvalidDTO invalidDTO;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //파라미터는 recruitment_id와 apt_id가 들어오는걸로
    String aptId = request.getParameter("aptId");
    Integer recruitId = Integer.parseInt(request.getParameter("recruitmentId"));
    ExamInfoDTO examDTO = examService.getExamContent(recruitId);

    sendExamAuth(request, response, aptId);
    request.setAttribute("examDTO", examDTO);

    String path = "exam/exam-auth";
    request.getRequestDispatcher(viewResolver.viewPath(path)).forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //post로 들어온 수험번호가, session에 저장된 aptId와 동일하다면
    //applicant의 recruitID로 exam을 조회 -> exam question을 출력

  }


  private void sendExamAuth(HttpServletRequest request, HttpServletResponse response,
      String aptId) {

    String cookieValue = String.valueOf(UUID.randomUUID());
    request.getSession().setAttribute(cookieValue, aptId);

    Cookie examAuthCookie = CookieManager.createCookie(ExamAuthController.COOKIE_NAME, cookieValue,
        60 * 60);
    response.addCookie(examAuthCookie);

  }
}


