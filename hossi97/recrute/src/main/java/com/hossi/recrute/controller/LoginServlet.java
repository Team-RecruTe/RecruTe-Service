package com.hossi.recrute.controller;

import java.io.*;

import com.hossi.recrute.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/signin")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("signin"));
        request.getRequestDispatcher(ViewResolver.getViewPath()).forward(request, response);
    }

}