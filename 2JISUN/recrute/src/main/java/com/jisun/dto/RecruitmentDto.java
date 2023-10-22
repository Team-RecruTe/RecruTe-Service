package com.jisun.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public class RecruitmentDto {
	public int id; //auto_increment
	public int company_id;
	public String title; //제목
	public LocalDate start_date; //지원시작날짜
	public LocalDate end_date; //지원종료날짜
	public int salary; //급여
	public String location; //근무지역
	public int work_form; //근무형태
	public String job; //직무
	public String skills; //요구 스킬
	public String career; //요구 경력
	private Timestamp created_date; //default
	private Timestamp modified_date; //default 
	
	@Override
	public String toString() {
		return "RecruitmentDto [id=" + id + ", company_id=" + company_id + ", title=" + title + ", start_date="
				+ start_date + ", end_date=" + end_date + ", salary=" + salary + ", location=" + location
				+ ", work_form=" + work_form + ", job=" + job + ", skills=" + skills + ", career=" + career
				+ ", created_date=" + created_date + ", modified_date=" + modified_date + "]";
	}
	
	public RecruitmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RecruitmentDto(int id, int company_id, String title, LocalDate start_date, LocalDate end_date, int salary,
			String location, int work_form, String job, String skills, String career, Timestamp created_date,
			Timestamp modified_date) {
		super();
		this.id = id;
		this.company_id = company_id;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.salary = salary;
		this.location = location;
		this.work_form = work_form;
		this.job = job;
		this.skills = skills;
		this.career = career;
		this.created_date = created_date;
		this.modified_date = modified_date;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}
	public LocalDate getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getWork_form() {
		return work_form;
	}
	public void setWork_form(int work_form) {
		this.work_form = work_form;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
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

}
