package com.team3.customModel;

import java.util.Date;

public class StaffCustom {
	private Integer id;

	private String staffCode;

	private String staffName;

	private String departName;

	private Boolean gender;

	private Date birthday;

	private String photo;

	private String email;

	private String phone;

	private Boolean status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Boolean isGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public StaffCustom() {
		super();
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Boolean getGender() {
		return gender;
	}

	public Boolean getStatus() {
		return status;
	}

	public StaffCustom(Integer id, String staffCode, String staffName, String departName, Boolean gender, Date birthday,
			String photo, String email, String phone, Boolean status) {
		super();
		this.id = id;
		this.staffCode = staffCode;
		this.staffName = staffName;
		this.departName = departName;
		this.gender = gender;
		this.birthday = birthday;
		this.photo = photo;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}

}
