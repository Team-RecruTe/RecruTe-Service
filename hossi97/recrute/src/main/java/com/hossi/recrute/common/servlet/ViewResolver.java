package com.hossi.recrute.common.servlet;

public interface ViewResolver {
    String MAIN_VIEW_PATH = "/WEB-INF/views/page.jsp";
    String ERROR_VIEW_PATH = "/WEB-INF/views/error.jsp";
    String EXAM_VIEW_PATH = "/WEB-INF/views/exam-page.jsp";
    static String resolveErrorViewPath(String viewName) {return "./pages/error/" + viewName + ".jsp";}
    static String resolveMainViewPath(String viewName) {
        return "./pages/" + viewName + ".jsp";
    }
    static String resolveExamViewPath(String viewName) {
        return "./pages/" + viewName + ".jsp";
    }
    static String getMainViewPath() {
        return MAIN_VIEW_PATH;
    }
    static String getErrorViewPath() {
        return ERROR_VIEW_PATH;
    }
    static String getExamViewPath() {
        return EXAM_VIEW_PATH;
    }
}

