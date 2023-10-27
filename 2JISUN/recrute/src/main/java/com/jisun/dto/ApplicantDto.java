package com.jisun.dto;

import java.sql.Timestamp;

public class ApplicantDto {
	
	public int id; //지원자정보의 id auto_increment
	private int apt_id; //수험번호 > landom으로 생성
	private Timestamp created_date;  
	private Timestamp modified_date; 
	private int member_id; //멤버테이블의 id
	private int recruitment_id; //채용공고테이블의 id
	
	@Override
	public String toString() {
		return "ApplicantDto [id=" + id + ", apt_id=" + apt_id + ", created_date=" + created_date + ", modified_date="
				+ modified_date + ", member_id=" + member_id + ", recruitment_id=" + recruitment_id + "]";
	}
	public ApplicantDto(int id, int apt_id, Timestamp created_date, Timestamp modified_date, int member_id,
			int recruitment_id) {
		super();
		this.id = id;
		this.apt_id = apt_id;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.member_id = member_id;
		this.recruitment_id = recruitment_id;
	}
	public ApplicantDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApt_id() {
		return apt_id;
	}
	public void setApt_id(int apt_id) {
		this.apt_id = apt_id;
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
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getRecruitment_id() {
		return recruitment_id;
	}
	public void setRecruitment_id(int recruitment_id) {
		this.recruitment_id = recruitment_id;
	}
	

}
