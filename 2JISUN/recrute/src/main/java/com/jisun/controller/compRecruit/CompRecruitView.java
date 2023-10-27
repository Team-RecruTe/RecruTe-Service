package com.jisun.controller.compRecruit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.jisun.dao.CompRecruitDao;
import com.jisun.dto.CompRecruitDto;

@WebServlet("/compRecruit/view") // 클래스 이름 변경한 뒤 xml에서 인식못하는 문제 발생 -> 웹 서블릿으로 import
public class CompRecruitView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CompRecruitView() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp에서 데이터 가져오기
		String strId_recruitIndex = request.getParameter("id_recruitIndex"); //얘는 url 에서 오는건지???
		int id_recruitIndex = 0;
		if (strId_recruitIndex!=null && !strId_recruitIndex.isEmpty()) {
			id_recruitIndex = Integer.parseInt(strId_recruitIndex);
		}
		
		//db에서 데이터 가져오기
		//dao(sql열기)->xml->dao->어떻게가져올건지=dto(데이터형식)
		CompRecruitDao compRecruitDao = new CompRecruitDao();
		
		CompRecruitDto compRecruitDto = compRecruitDao.selectCompRecruitView(id_recruitIndex);
		
		request.setAttribute("compRecruitView", compRecruitDto);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/compRecruit/view.jsp");
		dispatcher.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
