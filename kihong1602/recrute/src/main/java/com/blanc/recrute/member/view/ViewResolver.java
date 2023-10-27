package com.blanc.recrute.member.view;

public class ViewResolver {

    public String viewPath(String viewName) {
        return "WEB-INF/" + viewName + ".jsp";
    }
}
