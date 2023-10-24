package com.jisun.controller.applicant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.jisun.dao.ApplicantDao;
import com.jisun.dto.ApplicantDto;
import com.jisun.util.ScriptWriter;


public class ApplicantProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ApplicantProcess() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 데이터 가져오기
		//jsp
		//from session ->멤버테이블 인덱스
		int loggedId = 0;
		if(request.getParameter("loggedId")!=null && !request.getParameter("loggedId").isEmpty()) {
			loggedId = Integer.parseInt(request.getParameter("loggedId"));
		}
		
		//from 리크루트먼트테이블->  리크루트먼트테이블 인덱스
		int id_recruitIndex = 0;
			if(request.getParameter("id_recruitIndex")!=null && !request.getParameter("id_recruitIndex").isEmpty()) {
				id_recruitIndex = Integer.parseInt(request.getParameter("id_recruitIndex"));
			}
		//from session -> loggedMemberId
		String loggedMemberId = request.getParameter("loggedMemberId");
			
			
		//2. db(dao) 연결
		ApplicantDao applicantDao = new ApplicantDao();
		ApplicantDto applicantDto = new ApplicantDto();
					 applicantDto.setMember_id(loggedId);
					 applicantDto.setRecruitment_id(id_recruitIndex);

		//3. 데이터를 db에 넣기		 	
		int result = applicantDao.insertAppliacnt(applicantDto);
		
		//4. 실행
		if (result>0) {
			ScriptWriter.alertAndBack(response, "지원 성공! 행운을 빌어요~🍀");
		} else {
			ScriptWriter.alertAndBack(response, "서버 오류 입니다.");
		}
		
		/*
		 * if(loggedId != 0) { } else { ScriptWriter.alertAndBack(response,
		 * "로그인 후 지원가능합니다."); }
		 */
		
		/*
		 * if (loggedMemberId!=null && !loggedMemberId.isEmpty()) {
		 * ScriptWriter.alertAndNext(response, "로그인 후 지원하실 수 있습니다.",
		 * "../member/signin"); }
		 */
	}
}


