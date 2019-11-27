package com.dream.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BILL_PRODUCTS")
@Data
@Setter
@Getter
public class BillProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BILL_PRODUCTS_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILL_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Bill bill;
	
	@OneToOne
	@JoinColumn(name = "PRODUCT_SIZE_ID")
	private ProductSize productSize;
	
	@Column(name = "SPECIAL_PRICE")
	private float specialPrice;
	
	@Column(name = "AMOUNT")
	private long amount;
	
	@Column(name = "NOTES")
	@Size(max = 255)
	private String notes;
	
}
