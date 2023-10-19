package com.jisun.dto;

public class ModalDto {
	private String msg;
	private String title;
	private String state;
	
	
	public ModalDto(String msg, String title, String state) {
		super();
		this.msg = msg;
		this.title = title;
		this.state = state;
	}
	
	
	public ModalDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
