package com.dream.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SCHOOL")
@Data
@Setter
@Getter
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SCHOOL_ID")
	private long id;

	@Column(name = "NAME", nullable = false, unique=true)
	@NotNull(message = "school name must not be null")
	private String name;
	
	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "OWNER")
	private String owner;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "NOTES")
	private String notes;
	
}
