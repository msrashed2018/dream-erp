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
@Table(name = "CLOTH")
@Data
@Setter
@Getter
public class Cloth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLOTH_ID")
	private long id;

	@OneToOne
	@JoinColumn(name = "CLOTH_TYPE_ID", nullable = false)
	private ClothType clothType;
	
	@OneToOne
	@JoinColumn(name = "COLOR_ID", nullable = false)
	private Color color;

	@Column(name = "AMOUNT")
	private double amount;
	
	@Column(name = "REGISTERED_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"/* ,timezone = "GMT+2" */)
	private Date registeredDate;
	
	@Column(name = "NOTES")
	private String notes = "";
	
}
