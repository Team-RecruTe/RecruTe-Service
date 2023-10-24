package com.hossi.recrute.common.controller;

import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.common.util.http.servlet.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "homeServlet", value = "")
public class HomeServlet extends HttpServlet {
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        servletHandler
            .setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("home"), request)
            .setStatus(SC_OK, response)
            .forward(ViewResolver.getMainViewPath(), request, response);
    }
}
