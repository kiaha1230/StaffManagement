package com.team3.customModel;

import java.util.Date;

import javax.persistence.Transient;

public class StaffCustom {

	private String departName;
	private String staffName;
	private String staffCode;
	private Long totalDiscipline;
	private Long totalAchievement;
	private Long totalMark;

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

	public StaffCustom() {
		super();
	}

	public Long getTotalDiscipline() {
		return totalDiscipline;
	}

	public void setTotalDiscipline(Long totalDiscipline) {
		this.totalDiscipline = totalDiscipline;
	}

	public Long getTotalAchievement() {
		return totalAchievement;
	}

	public void setTotalAchievement(Long totalAchievement) {
		this.totalAchievement = totalAchievement;
	}

	public Long getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(Long totalMark) {
		this.totalMark = totalMark;
	}

}
