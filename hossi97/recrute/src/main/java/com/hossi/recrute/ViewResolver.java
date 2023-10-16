package com.hossi.recrute;

public class ViewResolver {
    private static String viewPath = "/WEB-INF/views/page.jsp";
    public static String resolveMainViewPath(String viewName) {
        return "./pages/" + viewName + ".jsp";
    }

    public static String getViewPath() {
        return viewPath;
    }
}
