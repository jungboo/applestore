package com.javaweb.apple.model.vo;

import java.sql.Timestamp;

public class MemberVO {
	private int id; // <pk>
	private String name; // 이름
	private String login; // 아이디 유니코드
	private String password; // 비밀번호
	private String birthday; // 생년월일 ex) 901111
	private String email; //
	private String phone; // ex)01011112222
	private String zipcode;
	private String address; // 일반주소 ex) 서울시 강남구 테헤란로~
	private String addressDetail; // 상세주소 ex) 아파트 301동 105호
	private Timestamp joinDay; // 가입일

	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String name, String login, String password, String birthday, String email, String phone) {
		this(0, name, login, password, birthday, email, phone, null, null, null, null);
	}

	public MemberVO(int id, String name, String login, String password, String birthday, String email, String phone,
			String zipcode, String address, String addressDetail, Timestamp joinDay) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.zipcode = zipcode;
		this.address = address;
		this.addressDetail = addressDetail;
		this.joinDay = joinDay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Timestamp getJoinDay() {
		return joinDay;
	}

	public void setJoinDay(Timestamp joinDay) {
		this.joinDay = joinDay;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + ", birthday="
				+ birthday + ", email=" + email + ", phone=" + phone + ", zipcode=" + zipcode + ", address=" + address
				+ ", addressDetail=" + addressDetail + ", joinDay=" + joinDay + "]";
	}

}
