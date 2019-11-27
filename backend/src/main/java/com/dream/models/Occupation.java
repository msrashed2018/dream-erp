package com.dream.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="OCCUPATION")
@Data
@Setter
@Getter
public class Occupation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OCCUPATION_ID")
	private long id;
	
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
}
