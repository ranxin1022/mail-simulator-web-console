package com.ericsson.jigsaw.simulator.mail.web.model;

public class User {

	private String name;
	private String password;
	
	public User(){}
	
	public User(String name){
		this.password="123456";
		this.name = name;
	}
	
	public User(String name,String password){
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
