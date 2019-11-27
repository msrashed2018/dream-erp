package com.dream.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STORE_PRODUCTS_HISTORY")
@Data
@Setter
@Getter
public class StoreProductHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "STORE_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Store store;
	
	@OneToOne
	@JoinColumn(name = "PRODUCT_ID")
	private ProductSize productSize;
	
	@Column(name = "SPECIAL_PRICE")
	private float specialPrice;
	
	@Column(name = "STUDY_LEVEL")
	@Enumerated(EnumType.STRING)
	private StudyLevels studyLevel = StudyLevels.NA;
	
	@Column(name = "AMOUNT")
	private long amount;
	
	@Column(name = "DATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"/* ,timezone = "GMT+2" */)
	private Date registeredDate;
	
	@Column(name = "NOTES")
	@Size(max = 255)
	private String notes;
	
}
