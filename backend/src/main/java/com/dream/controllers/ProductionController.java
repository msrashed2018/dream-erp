package com.dream.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
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

import com.dream.models.Production;
import com.dream.models.ProductionHistory;
import com.dream.services.ProductionService;

@CrossOrigin(origins = "*")
@RestController
public class ProductionController {
	@Autowired
	private ProductionService productionService;
	
	@GetMapping("/productions")
	public Page<Production> retrieveAllProductions(
			@RequestParam(name = "productName", defaultValue = "", required = false) String productName,
			@RequestParam(name = "productSize", defaultValue = "0", required = false) int productSize,
			Pageable pageable) {
		return productionService.getProductions(productName, productSize,pageable);
	}

	@GetMapping("/productions/{productionId}")
	public Production retrieveProductionById(@PathVariable long productionId) {
		return productionService.getProductionById(productionId);
	}

	@DeleteMapping("/productions/{productionId}")
	public void deleteProduction(@PathVariable long productionId) {
		productionService.deleteProduction(productionId);
	}

	@PostMapping("/productions")
	public ResponseEntity<Production> createProduction(@Valid @RequestBody Production production) {

		Production createdProduction = productionService.createProduction(production);
		return new ResponseEntity<Production>(createdProduction, HttpStatus.OK);

	}
	
	
	@GetMapping("/productions/history")
	public Page<ProductionHistory> retrieveProductionsHistory(
			@RequestParam(name = "productName", defaultValue = "", required = false) String productName,
			@RequestParam(name = "productSize", defaultValue = "0", required = false) int productSize,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			Pageable pageable) {
		try {
			return productionService.getProductionHistory(productName, productSize, dateFrom, dateTo, pageable);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
}
