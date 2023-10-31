package com.juju.controller.exam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.juju.dao.ExamDao;
import com.juju.dto.ExamDto;
import com.juju.util.ScriptWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExamServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ExamServlet() {
    super();

  }

  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String[] url = request.getRequestURI().split("/");
    // ex) /recrute/exam/1/auth ==> [, recrute, exam, 1, auth]

    if (url.length > 3) {
      if (url[3].equals("auth")) {
        RequestDispatcher dispatcher =
            request.getRequestDispatcher("/WEB-INF/exam/authorization.jsp");
        dispatcher.forward(request, response);
      }
    } else {
      String aptId = request.getParameter("aptId");

      ExamDao examDao = new ExamDao();
      List<ExamDto> examList = new ArrayList<>();

      examList = examDao.selectProblems(aptId);
      System.out.println(examList);

      if (!examList.isEmpty()) {
        request.setAttribute("examList", examList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/exam/exam-view.jsp");
        dispatcher.forward(request, response);

      } else {
        ScriptWriter.alertAndBack(response, "error");
      }
    }


  }



}
