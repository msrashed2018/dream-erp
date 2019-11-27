package com.dream.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STORE")
@Data
@Setter
@Getter
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STORE_ID")
	private long id;

	@Column(name = "NAME", nullable = false, unique=true)
	private String name;

	@Column(name = "MOBILE_NUMBER")
	private String mobile;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "EMAIL")
	@Email
	private String email;

	@Column(name = "NOTES")
	private String notes;
	
}
