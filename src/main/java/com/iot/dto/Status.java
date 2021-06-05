package com.iot.dto;

public enum Status {
    ACTIVE("active"), WAITING_FOR_ACTIVATION("waiting"), BLOCKED("blocked"), DEACTIVATED("deactivated");
    
    private String status;
	
    Status(String status) {
    	this.status = status;
    }
    
	public String getStatus() {
		return status;
    }
	
	public static Status getStatus(String status) {
		if (status == null) throw new IllegalArgumentException("Status cannot be null");
		
		switch (status) {
		case "active": return Status.ACTIVE;
		case "waiting": return Status.WAITING_FOR_ACTIVATION;
		case "blocked": return Status.BLOCKED;
		case "deactivated": return Status.DEACTIVATED;
		
		default: throw new IllegalArgumentException("Status is invalid");
		}
	}
}
