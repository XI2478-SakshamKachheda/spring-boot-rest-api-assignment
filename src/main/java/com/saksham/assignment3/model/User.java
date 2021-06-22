package com.saksham.assignment3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

	public User() {
	}

	public User(String username, String useremail, String dob, String address, Long phone, String posh_status,
			String xfc_status, String isms_status, String emp_status, String bgv_status) {
		super();
		this.username = username;
		this.useremail = useremail;
		this.dob = dob;
		this.address = address;
		this.phone = phone;
		this.posh_status = posh_status;
		this.xfc_status = xfc_status;
		this.isms_status = isms_status;
		this.emp_status = emp_status;
		this.bgv_status = bgv_status;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "USER_EMAIL")
	private String useremail;

	@Column(name = "DOB")
	private String dob;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "PHONE")
	private Long phone;

	@Column(name = "POSH_STATUS")
	private String posh_status;

	@Column(name = "XFC_STATUS")
	private String xfc_status;

	@Column(name = "ISMS_STATUS")
	private String isms_status;

	@Column(name = "EMP_STATUS")
	private String emp_status;
	
	@Column(name = "BGV_STATUS")
	private String bgv_status;

	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getPosh_status() {
		return posh_status;
	}

	public void setPosh_status(String posh_status) {
		this.posh_status = posh_status;
	}

	public String getXfc_status() {
		return xfc_status;
	}

	public void setXfc_status(String xfc_status) {
		this.xfc_status = xfc_status;
	}

	public String getIsms_status() {
		return isms_status;
	}

	public void setIsms_status(String isms_status) {
		this.isms_status = isms_status;
	}

	public String getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(String emp_status) {
		this.emp_status = emp_status;
	}

	public String getBgv_status() {
		return bgv_status;
	}

	public void setBgv_status(String bgv_status) {
		this.bgv_status = bgv_status;
	}

	

}
