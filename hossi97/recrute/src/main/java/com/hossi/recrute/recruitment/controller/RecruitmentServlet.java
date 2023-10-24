package com.hossi.recrute.recruitment.controller;

import com.hossi.recrute.common.util.auth.AuthCookie;
import com.hossi.recrute.common.util.auth.AuthProcessor;
import com.hossi.recrute.common.util.http.JsonManager;
import com.hossi.recrute.common.util.http.message.Message;
import com.hossi.recrute.common.util.http.message.MessageCreator;
import com.hossi.recrute.common.util.http.message.ResponseData;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.common.util.http.servlet.ViewResolver;
import com.hossi.recrute.common.util.service.ServicePrefix;
import com.hossi.recrute.recruitment.dto.RecruitmentDto;
import com.hossi.recrute.recruitment.service.RecruitmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static com.google.common.net.MediaType.JSON_UTF_8;
import static com.hossi.recrute.common.util.service.ServicePrefix.RCT;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "recruitmentServlet", urlPatterns = "/recruitments/*")
public class RecruitmentServlet extends HttpServlet {
    private static final ServicePrefix prefix = RCT;
    private final RecruitmentService recruitmentService = new RecruitmentService();
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String[] parsedURI = requestURI.split("recruitments/");
        String lastPiece = parsedURI[parsedURI.length - 1];
        if (isNumeric(lastPiece)) {
            Integer rctId = Integer.parseInt(lastPiece);
            RecruitmentDto recruitment = recruitmentService.getRecruitment(rctId);
            servletHandler
                .setAttribute("recruitment", recruitment, request)
                .setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("recruitment"), request)
                .setStatus(SC_OK, response)
                .forward(ViewResolver.getMainViewPath(), request, response);
        } else {
            servletHandler
                .setAttribute("errorViewPath", ViewResolver.resolveErrorViewPath("err404"), request)
                .setStatus(SC_NOT_FOUND, response)
                .forward(ViewResolver.getErrorViewPath(), request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String[] parsedURI = requestURI.split("recruitments/");
        String lastPiece = parsedURI[parsedURI.length - 1];
        if (isNumeric(lastPiece)) {
            AuthCookie authCookie = AuthProcessor.getAuthCookie(servletHandler.getCookies(request));
            Integer rctId = Integer.parseInt(lastPiece);
            Integer id = (Integer) servletHandler.getSession(authCookie.getValue(), request);
            recruitmentService.applyRecruitment(id, rctId);
            ResponseData responseData = new ResponseData.Builder().set("rctId", 1).build();
            Message<Map<String, Object>> message = MessageCreator.create(prefix, "002", true, "Applied", responseData);
            servletHandler
                .setStatus(SC_OK, response)
                .setJson(JSON_UTF_8, JsonManager.toJson(message), response);
        } else {
            servletHandler
                .setAttribute("errorViewPath", ViewResolver.resolveErrorViewPath("err404"), request)
                .setStatus(SC_NOT_FOUND, response)
                .forward(ViewResolver.getErrorViewPath(), request, response);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
