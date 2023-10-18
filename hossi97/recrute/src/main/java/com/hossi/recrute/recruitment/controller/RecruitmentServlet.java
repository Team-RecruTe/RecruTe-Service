package com.hossi.recrute.recruitment.controller;

import com.hossi.recrute.common.ViewResolver;
import com.hossi.recrute.recruitment.dto.RecruitmentDto;
import com.hossi.recrute.recruitment.service.RecruitmentService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "recruitmentServlet", urlPatterns = "/recruitments/*")
public class RecruitmentServlet extends HttpServlet {
    private final RecruitmentService recruitmentService = new RecruitmentService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String[] parsedURI = requestURI.split("/");
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

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
