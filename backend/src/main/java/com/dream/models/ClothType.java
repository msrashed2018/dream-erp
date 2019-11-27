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
@Table(name = "CLOTH_TYPE")
@Data
@Setter
@Getter
public class ClothType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLOTH_TYPE_ID")
	private long id;

	@Column(name = "NAME", nullable = false, unique = true)
	@NotNull(message = "cloth type name must not be null")
	private String name;

	@Column(name = "NOTES")
	private String notes ="";
	
}
