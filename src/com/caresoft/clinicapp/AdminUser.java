package com.caresoft.clinicapp;
import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantAdmin, HIPAACompliantUser {
	//Members
	private Integer employeeID;
	private String role;
	private ArrayList<String> securityIncidents = new ArrayList<String>();
	
	//Constructors
	public AdminUser(Integer id) {
		super(id);
	}
		
	public AdminUser(Integer id, String role) {
		super(id);
		this.role = role;
	}
		
    // TO DO: Setters & Getters
    public Integer getEmployeeID() {
    	return employeeID;
    }
    public void setEmployeeID(Integer employeeID) {
    	this.employeeID = employeeID;
    }
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
			this.role = role;
		}
	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}
	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}
		
	
	    public void newIncident(String notes) {
	        String report = String.format(
	            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
	            new Date(), this.id, notes
	        );
	        securityIncidents.add(report);
	    }
	    public void authIncident() {
	        String report = String.format(
	            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
	            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
	        );
	        securityIncidents.add(report);
	    }
	    
	@Override
	public boolean assignPin(int pin) {
		// TODO Auto-generated method stub
		if(String.valueOf(pin).length() == 6) {
			this.pin = pin;
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		// TODO Auto-generated method stub
		if(confirmedAuthID == this.id) {
			return true;
		}
		authIncident();
		return false;
	}
	
	@Override
	public void printSecurityIncidents() {
		HIPAACompliantAdmin.super.printSecurityIncidents();
	}

//	@Override
//	public boolean adminQATest(ArrayList<String>)expectedIncidents) {
//		HIPAACompliantAdmin.super.adminQATest(expectedIncidents);
//	}
	
	@Override
	public ArrayList<String> reportSecurityIncidents() {
		// TODO Auto-generated method stub
		return this.securityIncidents;
	}

}
