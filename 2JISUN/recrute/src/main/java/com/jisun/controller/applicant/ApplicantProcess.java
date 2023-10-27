package com.jisun.controller.applicant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.jisun.dao.ApplicantDao;
import com.jisun.dao.ExamDao;
import com.jisun.dto.ApplicantDto;
import com.jisun.dto.ExamDto;
import com.jisun.util.EmailManager;
import com.jisun.util.RandomGenerator;
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
		//[from] session
		int loggedId = 0;
		HttpSession session = request.getSession();
		String loggedIdStr = String.valueOf(session.getAttribute("loggedId"));
		if(loggedIdStr!=null && !loggedIdStr.isEmpty()) {
			loggedId = Integer.parseInt(loggedIdStr);
		}
		
		
		
		
		
		String loggedMemberId = request.getParameter("loggedMemberId"); //loggedMemberId
		String loggedEmail = request.getParameter("loggedEmail");
		
		//[from] dao(compRecruitView)
		int id_recruitIndex = 0;
			if(request.getParameter("id_recruitIndex")!=null && !request.getParameter("id_recruitIndex").isEmpty()) {
				id_recruitIndex = Integer.parseInt(request.getParameter("id_recruitIndex"));
			}
		

			
			
			
		// 메일을 보내기 위한 변수 할당
		String hostName = null;
        String[] emailParts = loggedEmail.split("@"); // @를 기준으로 아이디와 호스트를 분리
        if(emailParts.length == 2) {
        	//String emailId = emailParts[0];
        	hostName = emailParts[1].replace(".com", ""); // .com 제거 > naver /google
        }
        String sendEmail = "snm03097@naver.com";
        String password = "Universe96";
        String subjectCompany = request.getParameter("company_name");
        String subjectText = "의 시험응시 페이지로 이동하기";
        String subject = subjectCompany + subjectText;
        
        int apt_id = RandomGenerator.RandomNum(); //랜덤숫자 생성(1000~9999)
        
        
		//ExamDao examDao = new ExamDao();
		//ExamDto examDto = examDao.selectExamId(id_recruitIndex);
		//int examId = examDto.getExamTbl_id(); // 실제 exam_id 변수를 여기에 할당
		String url = "http://localhost:8080/recrute02/exam/idAuth";
		String content = "당신의 수험번호를 아래 링크에 입력하세요 " 
						+ "\n"
						+"수험번호 : " + apt_id 
						+ "\n" 
						+ "시험 보러 가기: " + url;
		
		
			
		//2. db(dao) 연결
		ApplicantDao applicantDao = new ApplicantDao();
		ApplicantDto applicantDto = new ApplicantDto();
					 applicantDto.setApt_id(apt_id); //수험번호 생성
					 applicantDto.setMember_id(loggedId);
					 applicantDto.setRecruitment_id(id_recruitIndex);
					 

		//3. 데이터를 db에 넣기		 	
		int result = applicantDao.insertAppliacnt(applicantDto);
		
		
		//4. 실행
		if (result>0) {
			EmailManager.mailSend(sendEmail, password, hostName, loggedEmail, subject, content);
			ScriptWriter.alertAndBack(response, "지원 성공! 회원님의 이메일로 시험링크가 발송되었습니다. 행운을 빌어요~🍀");
		} else {
			ScriptWriter.alertAndBack(response, "서버 오류 입니다.");
		}
	}
}


