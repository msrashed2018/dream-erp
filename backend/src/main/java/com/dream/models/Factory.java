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
@Table(name = "Factory")
@Data
@Setter
@Getter
public class Factory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FACTORY_ID")
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "MOBILE1")
	private String mobile1;
	
	@Column(name = "MOBILE2")
	private String mobile2;
	
	@Column(name = "NOTES")
	private String notes;
	
}
