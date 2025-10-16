package com.rizwan.entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer enquiryId;
	String stu_Name;
	String  stu_phone_No;
	String courseName;
	String class_Mode;
	String enq_status;
	@CreationTimestamp
	LocalDate  createdDtae;
	@UpdateTimestamp
	LocalDate uptaedDtae;
	@ManyToOne
	@JoinColumn(name = "counciler_id")
	private Counsellor counciler;
	
	public Integer getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}
	public String getStu_Name() {
		return stu_Name;
	}
	public void setStu_Name(String stu_Name) {
		this.stu_Name = stu_Name;
	}
	public String getStu_phone_No() {
		return stu_phone_No;
	}
	public void setStu_phone_No(String stu_phone_No) {
		this.stu_phone_No = stu_phone_No;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getClass_Mode() {
		return class_Mode;
	}
	public void setClass_Mode(String class_Mode) {
		this.class_Mode = class_Mode;
	}
	public String getEnq_status() {
		return enq_status;
	}
	public void setEnq_status(String enq_status) {
		this.enq_status = enq_status;
	}
	public LocalDate getCreatedDtae() {
		return createdDtae;
	}
	public void setCreatedDtae(LocalDate createdDtae) {
		this.createdDtae = createdDtae;
	}
	public LocalDate getUptaedDtae() {
		return uptaedDtae;
	}
	public void setUptaedDtae(LocalDate uptaedDtae) {
		this.uptaedDtae = uptaedDtae;
	}
	public Counsellor getCounciler() {
		return counciler;
	}
	public void setCounciler(Counsellor counciler) {
		this.counciler = counciler;
	}
	
	
}
