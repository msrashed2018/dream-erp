package com.dream.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EMPLOYEE_TASKS")
@Data
@Setter
@Getter
public class EmployeeTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TASK_ID")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	private Employee employee;
	
	@OneToOne
	@JoinColumn(name = "TASK_ID")
	private Task task;
	
	@OneToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	@Column(name = "SIZE")
	private int size;
	
	@Column(name = "AMOUNT")
	private long amount;
	
	@Column(name = "DATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"/* ,timezone = "GMT+2" */)
	private Date registeredDate;

	@Column(name = "NOTES")
	private String notes;
}
