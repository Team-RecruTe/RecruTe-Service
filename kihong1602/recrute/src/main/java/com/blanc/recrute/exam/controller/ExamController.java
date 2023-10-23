package com.blanc.recrute.exam.controller;

import com.blanc.recrute.common.ViewResolver;
import com.blanc.recrute.exam.dto.ExamDTO;
import com.blanc.recrute.exam.service.ExamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "exam", value = "/exams/*/auth")
public class ExamController extends HttpServlet {

  private static ExamService examService = new ExamService();
  
  private static ViewResolver viewResolver = new ViewResolver();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String path = "exam/exam-auth";
    Integer examId = getExamId(request);

    ExamDTO examDTO = examService.getExamContent(examId);
    request.setAttribute("examDTO", examDTO);
    request.getRequestDispatcher(viewResolver.viewPath(path)).forward(request, response);

  }


  private Integer getExamId(HttpServletRequest request) {
    String[] parsingURL = request.getRequestURI().split("/");
    return Integer.parseInt(String.valueOf(parsingURL[parsingURL.length - 2]));
  }
}
