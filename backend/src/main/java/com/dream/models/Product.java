package com.dream.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Data
@Setter
@Getter
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private long id;

	@Column(name = "NAME", nullable = false, unique = true)
	@NotNull(message = "product name must not be null")
	@Size(max = 150)
	private String name;
	
	@Column(name = "NOTES")
	@Size(max = 255)
	private String notes = "";
	
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy="product")
//	@JsonIgnore
//	private List<ProductSize> productSizes;
	
}
