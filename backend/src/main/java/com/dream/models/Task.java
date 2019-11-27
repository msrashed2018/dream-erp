package com.dream.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TASK")
@Data
@Setter
@Getter
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TASK_ID")
	private long id;

	@Column(name = "NAME", nullable = false)
	@Size(max = 2,message = "اسم المهمة لا يجب ان يتعدي 100 حرف")
	private String name;
	
	@Column(name = "PRICE")
	private float price;
	
	@Column(name = "NOTES")
	private String notes;

}
