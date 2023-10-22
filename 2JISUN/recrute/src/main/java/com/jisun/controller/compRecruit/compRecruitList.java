package com.jisun.controller.compRecruit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.jisun.dao.CompRecruitDao;
import com.jisun.dto.CompRecruitDto;

public class compRecruitList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

    public compRecruitList() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp에서 데이터 가져오기
		//없음
		
		//db에서 데이터를 가져오기
		CompRecruitDao compRecruitDao = new CompRecruitDao();
		CompRecruitDto CompRecruitDto = new CompRecruitDto();	   
		List<CompRecruitDto> compRecruitList = compRecruitDao.selectCompRecruitList(CompRecruitDto);
		
		request.setAttribute("compRecruitList", compRecruitList);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/compRecruit/list.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
