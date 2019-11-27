package com.dream.services;


import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dream.models.Bill;
import com.dream.models.BillProduct;

public interface BillService {
	
	Page<Bill> getBills(long schoolId,String dateFrom, String dateTo,String to, Pageable pageable) throws ParseException;
	
	Bill getBillById(long id);
	
	Bill createBill(Bill Bill);
	
	Bill updateBill(long id, Bill Bill);
	
	void deleteBill(long id);
	
	Page<BillProduct> getBillProducts(long billId, String productNameContaining, int productSize, String studyLevel, Pageable pageable);
	
	BillProduct addBillProduct(long billId, BillProduct billProduct);
	
	BillProduct updateBillProduct(long billProductId, BillProduct billProduct);
	
	void deleteBillProduct(long billProductId);
	
}
