package com.dream.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EXPENSE")
@Data
@Setter
@Getter
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EXPENSE_ID")
	private long id;
	
	@OneToOne
	@JoinColumn(name = "EXPENSE_TYPE_ID")
	private ExpenseType expenseType;
	
	@Column(name = "VALUE")
	private double value;
	
	@Column(name = "DATE")
	@JsonFormat(pattern = "yyyy | dd MMM"/* ,timezone = "GMT+2" */)
	private Date registeredDate = new Date();
	
	@Column(name = "NOTES")
	private String notes;
	

}
