package com.iot.dto;

public class SIMDTO {

    private int id;
    private String operatorCode;
    private String countryName;
    private Status status;
    
	public SIMDTO() {
	}

	public SIMDTO(int id, String operatorCode, String countryName, Status status) {
		this.id = id;
		this.operatorCode = operatorCode;
		this.countryName = countryName;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOperatorCode() {
		return operatorCode;
	}
	
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	public String getCountryName() {
		return countryName;
	}
	
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}