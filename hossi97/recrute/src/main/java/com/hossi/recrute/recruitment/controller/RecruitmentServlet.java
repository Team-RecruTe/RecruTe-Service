package com.hossi.recrute.recruitment.controller;

import com.google.gson.Gson;
import com.hossi.recrute.common.auth.Authenticator;
import com.hossi.recrute.common.response.AttributeContainer;
import com.hossi.recrute.common.response.ResponseData;
import com.hossi.recrute.common.response.ResponseUtil;
import com.hossi.recrute.common.response.ViewResolver;
import com.hossi.recrute.recruitment.dto.RecruitmentDto;
import com.hossi.recrute.recruitment.service.RecruitmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "recruitmentServlet", urlPatterns = "/recruitments/*")
public class RecruitmentServlet extends HttpServlet {
    private static final AttributeContainer attributeContainer = new AttributeContainer();
    private static final Gson gson = new Gson();
    private final RecruitmentService recruitmentService = new RecruitmentService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String[] parsedURI = requestURI.split("recruitments/");
        String lastPiece = parsedURI[parsedURI.length - 1];
        if(isNumeric(lastPiece)) {
            Integer rctId = Integer.parseInt(lastPiece);
            RecruitmentDto recruitment = recruitmentService.getRecruitment(rctId);
            request.setAttribute("recruitment", recruitment);
            request.setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("recruitment"));
            request.getRequestDispatcher(ViewResolver.getMainViewPath()).forward(request, response);
        } else {
            request.setAttribute("errorViewPath", ViewResolver.resolveErrorViewPath("err404"));
            request.getRequestDispatcher(ViewResolver.getErrorViewPath()).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String[] parsedURI = requestURI.split("recruitments/");
        String lastPiece = parsedURI[parsedURI.length - 1];
        if(isNumeric(lastPiece)) {
            Integer rctId = Integer.parseInt(lastPiece);
            Authenticator authenticator = new Authenticator();
            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute(authenticator.getAuthCookie(request).getValue());
            recruitmentService.applyRecruitment(id, rctId);
            ResponseData<Integer> responseData = new ResponseData<>("rctId", rctId);
            ResponseUtil.sendJson(SC_OK, gson.toJson(responseData), response);
        } else {
            attributeContainer.set("errorViewPath", ViewResolver.resolveErrorViewPath("err404"));
            ResponseUtil.setAttributes(attributeContainer, request);
            ResponseUtil.forward(SC_NOT_FOUND, ViewResolver.getErrorViewPath(), request, response);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
