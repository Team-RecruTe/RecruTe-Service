package com.blanc.recrute.common;

public class ViewResolver {

    public String viewPath(String viewName) {
        return "WEB-INF/" + viewName + ".jsp";
    }
}
