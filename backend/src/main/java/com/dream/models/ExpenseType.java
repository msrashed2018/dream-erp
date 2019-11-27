package com.dream.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EXPENSE_TYPE")
@Data
@Setter
@Getter
public class ExpenseType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EXPENSE_TYPE_ID")
	private long id;

	@Column(name = "NAME", unique=true)
	@NotNull(message="name must not be nulls")
	@Size(max=100)
	private String name;
	
	@Column(name = "NOTES")
	@Size(max=120)
	private String notes = "";

}
