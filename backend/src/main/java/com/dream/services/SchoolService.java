package com.dream.services;

import org.springframework.data.domain.Pageable;

import java.text.ParseException;

import org.springframework.data.domain.Page;

import com.dream.models.School;
import com.dream.models.SchoolProduct;
import com.dream.models.SchoolProductHistory;
import com.dream.models.SchoolSales;

public interface SchoolService {

	Page<School> getSchools(String nameConatining, Pageable pageable);

	School getSchoolById(long id);

	School createSchool(School School);

	School updateSchool(long id, School School);

	void deleteSchool(long id);

	Page<SchoolProductHistory> getSchoolProducts(long schoolId, String productNameContaining, int productSize, String dateFrom,
			String dateTo, String studyLevel, Pageable pageable) throws ParseException;

	Long getSchoolProductsTotalPrices(long schoolId, String productNameContaining, int productSize, String dateFrom,
			String dateTo, String studyLevel) throws ParseException;

	Page<SchoolProduct> getSchoolAvailableProducts(long schoolId, String productNameContaining, Pageable pageable);

	Long getSchoolSalesTotalPrices(long schoolId, String productNameContaining, int productSize, String dateFrom,
			String dateTo, String studyLevel) throws ParseException;

	Page<SchoolSales> getSchoolSales(long schoolId, String productNameContaining, int productSize, String dateFrom,
			String dateTo, String studyLevel, Pageable pageable) throws ParseException;

	SchoolProductHistory addSchoolProduct(long schoolId, SchoolProductHistory schoolProduct);

	SchoolSales addSchoolSales(long schoolId, SchoolSales schoolSales);

//	SchoolProductHistory updateSchoolProduct(long schoolProductId, SchoolProductHistory product);

//	SchoolSales updateSchoolSales(long schoolProductId, SchoolSales schoolSales);

	void deleteSchoolProduct(long schoolProductId);

	void deleteSchoolSales(long schoolSalesId);

}
