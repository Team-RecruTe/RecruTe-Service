package com.jisun.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ExamDto {

	//exam table
	public int examTbl_id; // id AS examtbl_id
	public int recruitmenttbl_id; //recruitment_id AS recruitmenttbl_id
	public String question; // 질문
	public int answer; // 정답
	public Timestamp created_date; //default
	public Timestamp modified_date; //default 
	
	//applicant table
	public int applicantTbl_id; //지원자T id as applicantTbl_id
	public int member_id; //멤버T id
	public int apt_id; // 수험번호
	
	
	
	
	@Override
	public String toString() {
		return "ExamDto [examTbl_id=" + examTbl_id + ", recruitmenttbl_id=" + recruitmenttbl_id + ", question="
				+ question + ", answer=" + answer + ", created_date=" + created_date + ", modified_date="
				+ modified_date + ", applicantTbl_id=" + applicantTbl_id + ", member_id=" + member_id + ", apt_id="
				+ apt_id + "]";
	}
	
	
	
	
	public ExamDto(int examTbl_id, int recruitmenttbl_id, String question, int answer, Timestamp created_date,
			Timestamp modified_date, int applicantTbl_id, int member_id, int apt_id) {
		super();
		this.examTbl_id = examTbl_id;
		this.recruitmenttbl_id = recruitmenttbl_id;
		this.question = question;
		this.answer = answer;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.applicantTbl_id = applicantTbl_id;
		this.member_id = member_id;
		this.apt_id = apt_id;
	}
	
	
	
	public ExamDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getExamTbl_id() {
		return examTbl_id;
	}
	public void setExamTbl_id(int examTbl_id) {
		this.examTbl_id = examTbl_id;
	}
	public int getRecruitmenttbl_id() {
		return recruitmenttbl_id;
	}
	public void setRecruitmenttbl_id(int recruitmenttbl_id) {
		this.recruitmenttbl_id = recruitmenttbl_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	public Timestamp getModified_date() {
		return modified_date;
	}
	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}
	public int getApplicantTbl_id() {
		return applicantTbl_id;
	}
	public void setApplicantTbl_id(int applicantTbl_id) {
		this.applicantTbl_id = applicantTbl_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getApt_id() {
		return apt_id;
	}
	public void setApt_id(int apt_id) {
		this.apt_id = apt_id;
	}

	
}
