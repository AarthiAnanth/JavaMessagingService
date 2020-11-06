package com.jms.hospitalmanagement.model;

import java.io.Serializable;

public class Claim implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String doctorName;
	private int hospitlId;
	private String doctorType;
	private String insuranceProvider;
	private String claimAmount;
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public int getHospitlId() {
		return hospitlId;
	}
	public void setHospitlId(int hospitlId) {
		this.hospitlId = hospitlId;
	}
	public String getDoctorType() {
		return doctorType;
	}
	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}
	public String getInsuranceProvider() {
		return insuranceProvider;
	}
	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}
	public String getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}
}
