package com.jisun.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {
	
	//cookie create 생성
	public static void createCookie(HttpServletResponse response, 
									String cookieName, 
									String cookieValue, 
									int time) {
		
		Cookie cookie = new Cookie(cookieName, cookieValue);
			   cookie.setPath("/");
			   cookie.setMaxAge(time);
		
		response.addCookie(cookie);
	}
	
	
	//cookie read 유지 
	public static String readCookie(HttpServletRequest request, 
					 				String cookieName) {
		Cookie cookies [] = request.getCookies();
		String cookieValue = "";
		if(cookies!=null) {
			for(Cookie c : cookies) {
				if(c.getName().equals(cookieName)) {
					cookieValue = c.getValue();
				}
			}
		}
		return cookieValue;
	}
	
	
	
	
	//cookie delete 삭제
	public static void deleteCookie(HttpServletResponse response, 
			   						String cookieName) {
		//time을 0으로 처리하여 쿠키 만료시키기
		createCookie(response, cookieName, "", 0);

		}

}
