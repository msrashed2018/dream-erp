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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STORE_PRODUCTS")
@Data
@Setter
@Getter
public class StoreProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "SCHOOL_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private School school;
	
	@OneToOne
	@JoinColumn(name = "PRODUCT_SIZE_ID")
	private ProductSize productSize;


	@Column(name = "AMOUNT")
	private long amount;
	
	@Column(name = "PRICE")
	private int price;
	
}
