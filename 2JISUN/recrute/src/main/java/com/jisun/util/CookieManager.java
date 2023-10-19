package com.jisun.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {
	
	//cookie create 생성
	public static void createCookie(HttpServletResponse response, 
									String cookieName, //쿠키의 이름 또는 식별자를 나타냅니다. = id???
									String cookieValue, //쿠키에 저장된 데이터 또는 값 자체를 나타냅니다.
									int time) {
		// 새로운 쿠키 객체를 생성합니다.
		Cookie cookie = new Cookie(cookieName, cookieValue);
		 		// 쿠키의 경로를 루트로 설정합니다.	
			   cookie.setPath("/");
			   // 쿠키의 수명을 설정합니다 (시간 단위).
			   cookie.setMaxAge(time);
			   
		// 응답에 쿠키를 추가합니다.
		response.addCookie(cookie);
	}
	
	
	//cookie read 유지 
	public static String readCookie(HttpServletRequest request, 
					 				String cookieName) {
		// 현재 요청에서 모든 쿠키를 가져옵니다.
		Cookie cookies [] = request.getCookies();
		String cookieValue = "";
		if(cookies!=null) {
			// 모든 쿠키를 반복하여 원하는 쿠키를 찾습니다.
			for(Cookie c : cookies) {
				if(c.getName().equals(cookieName)) {
					// 원하는 쿠키의 값을 저장합니다.
					cookieValue = c.getValue();
				}
			}
		}
		// 찾은 쿠키의 값을 반환합니다.
		return cookieValue;
	}
	
	
	
	
	//cookie delete 삭제
	public static void deleteCookie(HttpServletResponse response, 
			   						String cookieName) {
		//time을 0으로 처리하여 쿠키 만료시키기
		createCookie(response, cookieName, "", 0);

		}

}
