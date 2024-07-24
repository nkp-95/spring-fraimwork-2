package com.exeample.miniproject;

public class Login {
	private String id;			//아이디
	private String password;	//비밀번호
	private String userName;	//이름
	private int userIdCard;		//주민번호
	private int phone;			//전화번호
	private String address;		//주소
	private String email;		//이메일
	private int serialNum;		//회원 고유번호(회원은 볼수없음)
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserIdCard() {
		return userIdCard;
	}
	public void setUserIdCard(int userIdCard) {
		this.userIdCard = userIdCard;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	
}
