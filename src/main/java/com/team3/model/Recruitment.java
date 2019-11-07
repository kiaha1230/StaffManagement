package com.team3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "RECRUITMENT")
public class Recruitment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Column(name = "RECRUITMENT_CODE")
	private String recruitmentCode;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "DESCRIPTION")
	private String description;
	@Transient
	private String staffName;

	public Recruitment(Integer id, Integer staffId, String recruitmentCode, String title, String description) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.recruitmentCode = recruitmentCode;
		this.title = title;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getRecruitmentCode() {
		return recruitmentCode;
	}

	public void setRecruitmentCode(String recruitmentCode) {
		this.recruitmentCode = recruitmentCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Recruitment() {
		super();
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Recruitment(Integer id, Integer staffId, String recruitmentCode, String title, String description,
			String staffName) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.recruitmentCode = recruitmentCode;
		this.title = title;
		this.description = description;
		this.staffName = staffName;
	}

}
