package com.iot.dto;

public class DeviceDTO {

	private int id;
	private String name;
	private SIMDTO sim;
    
	public DeviceDTO() {
	}

	public DeviceDTO(int id, String name, SIMDTO sim) {
		this.id = id;
		this.name = name;
		this.sim = sim;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public SIMDTO getSim() {
		return sim;
	}

	public void setSim(SIMDTO sim) {
		this.sim = sim;
	}
}