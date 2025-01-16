package com.funfit.model;

public class Participant {

	 private int participantId;
	    private String name;
	    private String email;
	    private String phoneNumber;
	    private int batchId;
	    
	    public Participant() {
	    	super();
	    }
	
	  public Participant(int participantId, String name, String email, String phoneNumber, int batchId) {
	        this.participantId = participantId;
	        this.name = name;
	        this.email = email;
	        this.phoneNumber = phoneNumber;
	        this.batchId = batchId;
	    }

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	@Override
	public String toString() {
		return "Participant [participantId=" + participantId + ", name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", batchId=" + batchId + "]";
	}

}
