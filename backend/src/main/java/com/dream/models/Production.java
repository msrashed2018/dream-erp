package com.dream.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCTION")
@Data
@Setter
@Getter
public class Production implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@OneToOne
	@JoinColumn(name = "PRODUCT_SIZE")
	@NotNull(message = "Product Size must not be null")
	private ProductSize productSize;

	@Column(name = "AMOUNT")
	@NotNull
	private long amount;
	
	@Column(name = "LAST_UPDATE")
	@JsonFormat(pattern = "yyyy | dd MMM"/* ,timezone = "GMT+2" */)
	private Date lastUpdate;
	
	@Column(name = "NOTES")
	private String notes = "";
	
}
