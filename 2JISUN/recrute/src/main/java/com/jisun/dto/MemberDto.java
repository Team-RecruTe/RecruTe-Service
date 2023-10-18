package com.jisun.dto;

import java.time.LocalDate;

public class MemberDto {
	//id int primary key auto_increment
	private String memberID;
	private String password;
	private String name;
	private LocalDate birth;
	private int gender;
	private String phoneNum;
	private String email;
	//created_date datetime default now()
	//modified_date datetime default now() on update now()
	
	
	public MemberDto(String memberID, String password, String name, LocalDate birth, int gender, String phoneNum,
			String email) {
		this.memberID = memberID;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.phoneNum = phoneNum;
		this.email = email;
	}
	
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "MemberDto [memberID=" + memberID + ", password=" + password + ", name=" + name + ", birth=" + birth
				+ ", gender=" + gender + ", phoneNum=" + phoneNum + ", email=" + email + "]";
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
	
}
