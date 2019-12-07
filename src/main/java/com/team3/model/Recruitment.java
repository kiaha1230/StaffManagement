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
	@Column(name = "RECRUITMENT_CODE")
	private String recruitmentCode;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "STATUS")
	private Boolean status;
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Transient
	private Pager pager;
	@Transient
	private String staffCode;
	@Transient
	private String staffName;

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Recruitment(Integer id, String recruitmentCode, String title, String description, Boolean status) {
		super();
		this.id = id;
		this.recruitmentCode = recruitmentCode;
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
