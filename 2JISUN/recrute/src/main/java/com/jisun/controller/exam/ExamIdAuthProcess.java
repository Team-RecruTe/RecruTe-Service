package com.jisun.controller.exam;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.jisun.dao.ExamDao;
import com.jisun.dto.ExamDto;
import com.jisun.util.EmailManager;
import com.jisun.util.ScriptWriter;

@WebServlet("/exam/idAuth-process") // 클래스 이름 변경한 뒤 xml에서 인식못하는 문제 발생 -> 웹 서블릿으로 import
public class ExamIdAuthProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExamIdAuthProcess() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*From. session*/
		int loggedId = 0;
		HttpSession session = request.getSession();
		String loggedIdStr = String.valueOf(session.getAttribute("loggedId"));
		if(loggedIdStr!=null && !loggedIdStr.isEmpty()) {
			loggedId = Integer.parseInt(loggedIdStr);
		}
		
		/*From. client*/
		int apt_id= 0;
		if(request.getParameter("apt_id")!=null && !request.getParameter("apt_id").isEmpty()) {
			apt_id = Integer.parseInt(request.getParameter("apt_id"));
		}
		
		
		
		ExamDto examDto = new ExamDto();
				examDto.setMember_id(loggedId);
				examDto.setApt_id(apt_id);
				
				
				
		/*로직*/	
		ExamDao examDao = new ExamDao();
		
		int result = examDao.selectAptIdCheck(examDto); //0 or 1 > 잘됨
		ExamDto examInfo = examDao.selectExamInfo(examDto); //exam테이블의 id, 문제, 4지선다형 > 필드명 수정필요
		
		
		
		/*To. client*/
		request.setAttribute("examInfo", examInfo);
		
		
		
		
		if(result>0) {
			ScriptWriter.alertAndNext(response, "시험시작합니다", "../exam/exam");
		} else {
			ScriptWriter.alertAndBack(response, "수험번호를 다시 입력해주세요");
			
		}

	}

}
