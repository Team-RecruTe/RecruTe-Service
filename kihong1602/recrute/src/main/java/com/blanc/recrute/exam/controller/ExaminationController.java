package com.blanc.recrute.exam.controller;

import com.blanc.recrute.common.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "exam/*", value = "/exam/*")
public class ExaminationController extends HttpServlet {

  //시험 응시 페이지 뷰, 시험응시 제출
  private static ViewResolver viewResolver = new ViewResolver();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
