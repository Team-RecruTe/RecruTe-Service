package com.hossi.recrute.common.controller;

import com.hossi.recrute.common.util.http.AttributeContainer;
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
    private final AttributeContainer attributeContainer = new AttributeContainer();
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        attributeContainer.set("mainViewPath", ViewResolver.resolveMainViewPath("home"));
        servletHandler.setAttributes(attributeContainer, request);
        servletHandler.forward(SC_OK, ViewResolver.getMainViewPath(), request, response);
    }
}
