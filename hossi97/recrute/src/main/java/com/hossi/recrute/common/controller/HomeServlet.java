package com.hossi.recrute.common.controller;

import com.hossi.recrute.common.response.AttributeContainer;
import com.hossi.recrute.common.response.ResponseUtil;
import com.hossi.recrute.common.response.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "homeServlet", value = "")
public class HomeServlet extends HttpServlet {
    private static final AttributeContainer attributeContainer = new AttributeContainer();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        attributeContainer.set("mainViewPath", ViewResolver.resolveMainViewPath("home"));

        ResponseUtil.setAttributes(attributeContainer, request);
        ResponseUtil.forward(SC_OK, ViewResolver.getMainViewPath(), request, response);
    }
}
