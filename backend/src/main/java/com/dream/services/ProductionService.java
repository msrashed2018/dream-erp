package com.dream.services;

import org.springframework.data.domain.Pageable;

import java.text.ParseException;

import org.springframework.data.domain.Page;

import com.dream.models.Production;
import com.dream.models.ProductionHistory;

public interface ProductionService {
	
	Page<Production> getProductions(String productName, int size,Pageable pageable);
	
	Production getProductionById(long id);
	
	Production createProduction(Production Production);
	
//	Production updateProduction(long id, Production Production);
	
	void deleteProduction(long id);
	
	Page<ProductionHistory> getProductionHistory(String productName, int size, String dateFrom, String dateTo, Pageable page) throws ParseException;
}
