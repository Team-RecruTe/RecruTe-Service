package com.jisun.dto;

import java.sql.Timestamp;

public class CompanyDto {
	public int id; //auto_increment
	public String company_name;
	private Timestamp created_date; //default
	private Timestamp modified_date; //default 
	
	@Override
	public String toString() {
		return "CompanyDto [id=" + id + ", company_name=" + company_name + ", created_date=" + created_date
				+ ", modified_date=" + modified_date + "]";
	}
	
	public CompanyDto(int id, String company_name, Timestamp created_date, Timestamp modified_date) {
		super();
		this.id = id;
		this.company_name = company_name;
		this.created_date = created_date;
		this.modified_date = modified_date;
	}
	
	public CompanyDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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
