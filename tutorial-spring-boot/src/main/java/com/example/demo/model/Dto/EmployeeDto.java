package com.example.demo.model.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.example.demo.model.Constants.ValidationApi;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmployeeDto {

    public EmployeeDto() {
        super();
        
    }

    @io.swagger.annotations.ApiModelProperty(notes = "The database generated employee ID")
    private Long id;
    @io.swagger.annotations.ApiModelProperty(notes = "Expedient ID for employee")
    @NotEmpty(message = "Expedient is mandatory")
    private String expedient;
    
    @io.swagger.annotations.ApiModelProperty(notes = "The employee first name")
    @NotEmpty(message = "FirstName is mandatory")
     private String firstName;
    @io.swagger.annotations.ApiModelProperty(notes = "The employee last name")
    @NotEmpty(message = "LastName is mandatory")
    private String lastName;
    @NotEmpty(message = "Email is mandatory")
    @io.swagger.annotations.ApiModelProperty(notes = "The employee email")
    private String email;
    @io.swagger.annotations.ApiModelProperty(notes = "The mobilePhone email")
    private String mobilePhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getExpedient() {
		return expedient;
	}

	public void setExpedient(String expedient) {
		this.expedient = expedient;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

}
