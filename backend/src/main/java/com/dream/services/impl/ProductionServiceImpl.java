package com.dream.services.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.models.Production;
import com.dream.models.ProductionHistory;
import com.dream.repositories.ProductionHistoryRepository;
import com.dream.repositories.ProductionRepository;
import com.dream.services.ProductionService;
import com.dream.utils.DateUtils;

@Service
public class ProductionServiceImpl implements ProductionService {

	@Autowired
	private ProductionRepository productionRepository;

	@Autowired
	private ProductionHistoryRepository productionHistoryRepository;

	@Override
	public Page<Production> getProductions(String productName, int productSize, Pageable pageable) {
		if (productSize != 0) {
			return productionRepository.findByProductSizeProductNameContainingAndProductSizeSize(productName,
					productSize, pageable);
		} else {
			return productionRepository.findByProductSizeProductNameContaining(productName, pageable);
		}
	}

	@Override
	public Production getProductionById(long productId) {
		Optional<Production> production = productionRepository.findById(productId);
		if (!production.isPresent()) {
			throw new ResourceNotFoundException("ProductionId " + productId + " not found");
		}
		return production.get();
	}

	@Override
	@Transactional
	public Production createProduction(Production production) {
		Optional<Production> existingProduction = productionRepository.findByProductSizeId(production.getProductSize().getId());

		if (existingProduction.isPresent()) {

			ProductionHistory productionHistory = new ProductionHistory();
			productionHistory.setProductSize(production.getProductSize());
			productionHistory.setNotes(production.getNotes());
			productionHistory.setAmount(production.getAmount());
			productionHistory.setRegisteredDate(new Date());
			productionHistoryRepository.save(productionHistory);

			existingProduction.get().setAmount(existingProduction.get().getAmount() + production.getAmount());
			existingProduction.get().setNotes(production.getNotes());
			
			existingProduction.get().setLastUpdate(new Date());
			return productionRepository.save(existingProduction.get());

		} else {

			ProductionHistory productionHistory = new ProductionHistory();
			productionHistory.setProductSize(production.getProductSize());
			productionHistory.setNotes(production.getNotes());
			productionHistory.setAmount(production.getAmount());
			productionHistory.setRegisteredDate(new Date());
			productionHistoryRepository.save(productionHistory);
			production.setLastUpdate(new Date());
			return productionRepository.save(production);
		}

	}

	@Override
	public void deleteProduction(long id) {
		if (!productionRepository.existsById(id)) {
			throw new ResourceNotFoundException("ProductionId " + id + " not found");
		}
		productionRepository.deleteById(id);
	}

	@Override
	public Page<ProductionHistory> getProductionHistory(String productName, int productSize, String dateFrom,
			String dateTo, Pageable page) throws ParseException {
		if (productSize != 0) {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return productionHistoryRepository
						.findByProductSizeProductNameContainingAndProductSizeSizeAndRegisteredDateBetween(productName,
								productSize, start.getTime(), end.getTime(), page);
			} else {
				return productionHistoryRepository.findByProductSizeSize(productSize, page);
			}
		} else {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);

				return productionHistoryRepository
						.findByProductSizeProductNameContainingAndRegisteredDateBetween(productName,
							 start.getTime(), end.getTime(), page);
				
			} else {
				return productionHistoryRepository.findAll(page);
			}
		}
	}

}
