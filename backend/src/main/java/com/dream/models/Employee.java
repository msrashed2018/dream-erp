package com.dream.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EMPLOYEE")
@Data
@Setter
@Getter
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPLOYEE_ID")
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "OCCUPATION_ID")
	private Occupation occupation;

	@Column(name = "SALARY")
	private float salary;
	
	@Column(name = "SALARY_TYPE")
	@Enumerated(EnumType.STRING)
	private SalaryType salaryType;
	
	@Column(name = "MOBILE_NUMBER1", nullable = false)
	private String mobileNo1;
	
	@Column(name = "MOBILE_NUMBER2")
	private String mobileNo2;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "email")
	@Email(message = "wrong email")
	private String email;

	@Column(name = "NOTES")
	private String notes;
	
}
