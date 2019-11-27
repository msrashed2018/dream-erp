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
@Table(name = "PRODUCTION_HISTORY")
@Data
@Setter
@Getter
public class ProductionHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@OneToOne
	@JoinColumn(name = "PRODUCT_SIZE_ID")
	private ProductSize productSize;

	@Column(name = "AMOUNT")
	private long amount;
	
	@Column(name = "NOTES")
	private String notes;
	
	@Column(name = "DATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"/* ,timezone = "GMT+2" */)
	private Date registeredDate;
	
	
}
