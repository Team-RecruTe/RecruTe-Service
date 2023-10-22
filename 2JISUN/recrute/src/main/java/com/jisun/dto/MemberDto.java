package com.jisun.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public class MemberDto {
	
	private int id; //auto_increment
	private String memberID;
	private String password;
	private String name;
	private LocalDate birth;
	private int gender;
	private String phoneNum;
	private String email;
	private Timestamp created_date; //default
	private Timestamp modified_date; //default 
	 


	public MemberDto(int id, 
					String memberID, 
					String password, 
					String name, 
					LocalDate birth, 
					int gender,
					String phoneNum, 
					String email, 
					Timestamp created_date, 
					Timestamp modified_date) {

		this.id = id;
		this.memberID = memberID;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.phoneNum = phoneNum;
		this.email = email;
		this.created_date = created_date;
		this.modified_date = modified_date;
	}
	
	


	public MemberDto() {
		super();

	}




	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", memberID=" + memberID + ", password=" + password + ", name=" + name
				+ ", birth=" + birth + ", gender=" + gender + ", phoneNum=" + phoneNum + ", email=" + email
				+ ", created_date=" + created_date + ", modified_date=" + modified_date + "]";
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getMemberID() {
		return memberID;
	}



	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public LocalDate getBirth() {
		return birth;
	}



	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}



	public int getGender() {
		return gender;
	}



	public void setGender(int gender) {
		this.gender = gender;
	}



	public String getPhoneNum() {
		return phoneNum;
	}



	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
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
