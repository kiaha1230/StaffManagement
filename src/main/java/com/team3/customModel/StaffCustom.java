package com.team3.customModel;

import java.util.Date;

import javax.persistence.Transient;

public class StaffCustom {

	private String departName;
	private String staffName;
	private String staffCode;
	private Long kiLuat;
	private Long khenThuong;
	private Long diem;

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public Long getKiLuat() {
		return kiLuat;
	}

	public void setKiLuat(Long kiLuat) {
		this.kiLuat = kiLuat;
	}

	public Long getKhenThuong() {
		return khenThuong;
	}

	public void setKhenThuong(Long khenThuong) {
		this.khenThuong = khenThuong;
	}

	public Long getDiem() {
		return diem;
	}

	public void setDiem(Long diem) {
		this.diem = diem;
	}

	public StaffCustom() {
		super();
	}

}
