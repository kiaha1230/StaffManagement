package com.team3.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "STAFF")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "STAFF_CODE")
	private String staffCode;
	@Column(name = "STAFF_NAME")
	private String staffName;
	@Column(name = "DEPART_ID")
	private Integer departId;
	@Column(name = "GENDER")
	private Boolean gender;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDAY")
	private Date birthday;
	@Column(name = "PHOTO")
	private String photo;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHONE")
	private String phoneNumber;
	@Column(name = "STATUS")
	private Boolean status;
	@Column(name = "POSITION_ID")
	private Integer positionId;
	@Transient
	private String departName;
	@Transient
	private Long kiLuat;
	@Transient
	private Long khenThuong;
	@Transient
	private Long diem;
	@Transient
	private MultipartFile photoObj;
	@Transient
	private String positionName;
	

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Transient
	private Pager pager;

	public MultipartFile getPhotoObj() {
		return photoObj;
	}

	public void setPhotoObj(MultipartFile photoObj) {
		this.photoObj = photoObj;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Staff() {

	}

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public Staff(Integer id, String staffCode, String staffName, Integer departId, Boolean gender, Date birthday,
			String photo, String email, String phoneNumber, Boolean status, String departName, Long kiLuat,
			Long khenThuong, Long diem, Pager pager, Integer positionId) {
		super();
		this.id = id;
		this.staffCode = staffCode;
		this.staffName = staffName;
		this.departId = departId;
		this.gender = gender;
		this.birthday = birthday;
		this.photo = photo;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.departName = departName;
		this.kiLuat = kiLuat;
		this.khenThuong = khenThuong;
		this.diem = diem;
		this.pager = pager;
	}

}
