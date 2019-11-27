package com.dream.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT_SIZES")
@Data
@Setter
@Getter
public class ProductSize implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_DETAILS_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnore
	private Product product;
	
	@Column(name = "WHOLESALE_PRICE")
	private float wholesalePrice;
	
	@Column(name = "PRICE")
	private float price;
	
	@Column(name = "SIZE")
	private int size;
	
	@Column(name = "NOTES")
	@Size(max = 255)
	private String notes = "";
	
}
