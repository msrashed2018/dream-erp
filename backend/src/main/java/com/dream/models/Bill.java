package com.dream.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BILL")
@Data
@Setter
@Getter
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BILL_ID")
	private Long id;
	
	@Column(name = "DATE", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"/* ,timezone = "GMT+2" */)
	private Date registeredDate;
	
	@OneToOne
	@JoinColumn(name = "SCHOOL_ID")
	private School school;
	
	@Column(name = "TO")
	private String to;
	
//	@OneToMany(mappedBy = "bill"/* , fetch = FetchType.LAZY */)
////	@JsonIgnore
//	private List<BillProduct> billProducts;
	
	@Column(name = "NOTES")
	private String notes;
}
