/**
 * 
 */
package com.dream.services.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.models.Bill;
import com.dream.models.BillProduct;
import com.dream.repositories.BillProductRepository;
import com.dream.repositories.BillRepository;
import com.dream.services.BillService;
import com.dream.utils.DateUtils;

/**
 * @author mohamedsalah
 *
 */
@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private BillProductRepository billProductRepository;

	@Override
	public Page<Bill> getBills(long schoolId, String dateFrom, String dateTo, String to, Pageable pageable)
			throws ParseException {

		if (schoolId != 0l && dateFrom != null && dateTo != null) {
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			DateUtils.formatDates(start, end, dateFrom, dateTo);
			return billRepository.findBySchoolIdAndToContainingAndRegisteredDateBetween(schoolId, to, start.getTime(),
					end.getTime(), pageable);
		} else if (schoolId == 0l && dateFrom != null && dateTo != null) {
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			DateUtils.formatDates(start, end, dateFrom, dateTo);
			return billRepository.findByToContainingAndRegisteredDateBetween(to, start.getTime(), end.getTime(),
					pageable);
		} else {
			return billRepository.findByToContaining(to, pageable);
		}
	}

	@Override
	public Bill getBillById(long id) {
		Optional<Bill> bill = billRepository.findById(id);
		if (!bill.isPresent()) {
			throw new ResourceNotFoundException("BillId " + id + " not found");
		}
		return bill.get();
	}

	@Override
	public Bill createBill(Bill bill) {
		bill.setRegisteredDate(new Date());
		return billRepository.save(bill);
	}

	@Override
	public Bill updateBill(long id, Bill bill) {
		Optional<Bill> existingBill = billRepository.findById(id);
		if (!existingBill.isPresent()) {
			throw new ResourceNotFoundException("BillId " + id + " not found");
		}
		existingBill.get().setTo(bill.getTo());
		existingBill.get().setNotes(bill.getNotes());
		existingBill.get().setSchool(bill.getSchool());
		return billRepository.save(existingBill.get());
	}

	@Override
	public void deleteBill(long id) {
		if (!billRepository.existsById(id)) {
			throw new ResourceNotFoundException("BillId " + id + " not found");
		}
		billRepository.deleteById(id);
	}

	@Override
	public Page<BillProduct> getBillProducts(long billId, String productNameContaining, int productSize,
			String studyLevel, Pageable pageable) {

		if (productSize != 0) {
			return billProductRepository.findByBillIdAndProductSizeProductNameContainingAndProductSizeSize(billId, productNameContaining,
					productSize, pageable);
		} else {
			return billProductRepository.findByBillIdAndProductSizeProductNameContaining(billId, productNameContaining, pageable);
		}
	}

	@Override
	public BillProduct addBillProduct(long billId, BillProduct billProduct) {
		Optional<Bill> existingBill = billRepository.findById(billId);
		if (!existingBill.isPresent()) {
			throw new ResourceNotFoundException("BillId " + billId + " not found");
		}

		billProduct.setBill(existingBill.get());

		return billProductRepository.save(billProduct);
	}

	@Override
	public BillProduct updateBillProduct(long billProductId, BillProduct billProduct) {
		Optional<BillProduct> existingBillProduct = billProductRepository.findById(billProductId);
		if (!existingBillProduct.isPresent()) {
			throw new ResourceNotFoundException("billProductId " + billProductId + " not found");
		}
		existingBillProduct.get().setAmount(billProduct.getAmount());
		existingBillProduct.get().setNotes(billProduct.getNotes());
		existingBillProduct.get().setSpecialPrice(billProduct.getSpecialPrice());
		return billProductRepository.save(existingBillProduct.get());
	}

	@Override
	public void deleteBillProduct(long billProductId) {
		if (!billProductRepository.existsById(billProductId)) {
			throw new ResourceNotFoundException("billProductId " + billProductId + " not found");
		}
		billProductRepository.deleteById(billProductId);
	}

}
