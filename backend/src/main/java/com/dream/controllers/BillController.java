package com.dream.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dream.models.Bill;
import com.dream.models.BillProduct;
import com.dream.services.BillService;

@CrossOrigin(origins = "*")
@RestController
public class BillController {
	@Autowired
	private BillService billService;

	@GetMapping("/bills")
	public Page<Bill> retrieveAllBills(@RequestParam(name = "schoolId", defaultValue = "0" ,required = false) long schoolId,
			@RequestParam(name = "dateFrom" ,required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "to", defaultValue = "", required = false) String to, Pageable pageable) {
		try {
			return billService.getBills(schoolId, dateFrom, dateTo, to, pageable);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@GetMapping("/bills/{billId}")
	public Bill retrieveBillById(@PathVariable long billId) {
		return billService.getBillById(billId);
	}

	@DeleteMapping("/bills/{billId}")
	public void deleteBill(@PathVariable long billId) {
		billService.deleteBill(billId);
	}

	@PostMapping("/bills")
	public ResponseEntity<Bill> createBill(@Valid @RequestBody Bill bill) {

		Bill createdBill = billService.createBill(bill);
		return new ResponseEntity<Bill>(createdBill, HttpStatus.OK);

	}

	@PutMapping("/bills/{billId}")
	public ResponseEntity<Bill> updateBill(@PathVariable int billId, @Valid @RequestBody Bill bill) {
		Bill updatedBill = billService.updateBill(billId, bill);
		return new ResponseEntity<Bill>(updatedBill, HttpStatus.OK);
	}

	@GetMapping("/bills/{billId}/products")
	public Page<BillProduct> retreiveBillProducts(@PathVariable int billId,
			@RequestParam(name = "productName", defaultValue = "", required = false) String productName,
			@RequestParam(name = "productSize", defaultValue = "0",required = false) int productSize,
			@RequestParam(name = "studyLevel", defaultValue = "", required = false) String studyLevel,
			Pageable pageable) {
		return billService.getBillProducts(billId, productName, productSize, studyLevel, pageable);
	}

	@PostMapping("/bills/{billId}/products")
	public ResponseEntity<BillProduct> addBillProduct(@PathVariable int billId, @Valid @RequestBody BillProduct billProduct) {
		BillProduct createdBillProduct = billService.addBillProduct(billId, billProduct);
		return new ResponseEntity<BillProduct>(createdBillProduct, HttpStatus.OK);

	}
	
	@PutMapping("/bills/products/{billProductId}")
	public ResponseEntity<BillProduct> updateBillProduct(@PathVariable int billProductId, @Valid @RequestBody BillProduct billProduct) {
		BillProduct updatedBillProduct = billService.updateBillProduct(billProductId, billProduct);
		return new ResponseEntity<BillProduct>(updatedBillProduct, HttpStatus.OK);

	}
	
	@DeleteMapping("/bills/products/{billProductId}")
	public void deleteBillProduct(@PathVariable long billProductId) {
		billService.deleteBillProduct(billProductId);
	}
}
