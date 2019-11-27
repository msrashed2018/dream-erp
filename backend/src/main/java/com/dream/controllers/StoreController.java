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

import com.dream.models.Store;
import com.dream.models.StoreProductHistory;
import com.dream.models.StoreSales;
import com.dream.services.StoreService;

@CrossOrigin(origins = "*")
@RestController
public class StoreController {
	@Autowired
	private StoreService storeService;

	@GetMapping("/stores")
	public Page<Store> retrieveAllStores(
			@RequestParam(name = "name", defaultValue = "", required = false) String nameConatining,
			 Pageable pageable) {
		return storeService.getStores(nameConatining, pageable);
	}

	@GetMapping("/stores/{storeId}")
	public Store retrieveStoreById(@PathVariable long storeId) {
		return storeService.getStoreById(storeId);
	}

	@DeleteMapping("/stores/{storeId}")
	public void deleteStore(@PathVariable long storeId) {
		storeService.deleteStore(storeId);
	}

	@PostMapping("/stores")
	public ResponseEntity<Store> createStore(@Valid @RequestBody Store store) {

		Store createdStore = storeService.createStore(store);
		return new ResponseEntity<Store>(createdStore, HttpStatus.OK);

	}

	@PutMapping("/stores/{storeId}")
	public ResponseEntity<Store> updateStore(@PathVariable long storeId, @Valid @RequestBody Store store) {
		Store updatedStore = storeService.updateStore(storeId, store);
		return new ResponseEntity<Store>(updatedStore, HttpStatus.OK);
	}

	@GetMapping("/stores/{storeId}/products")
	public Page<StoreProductHistory> retrieveStoreProducts(@PathVariable long storeId,
			@RequestParam(name = "productName", defaultValue = "", required = false) String productNameContaining,
			@RequestParam(name = "productSize", required = false) int productSize,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "studyLevel", defaultValue = "", required = false) String studyLevel,
			Pageable pageable) {
		try {
			return storeService.getStoreProducts(storeId, productNameContaining, productSize, dateFrom, dateTo,
					studyLevel, pageable);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@GetMapping("/stores/{storeId}/sales")
	public Page<StoreSales> retrieveStoreSales(@PathVariable long storeId,
			@RequestParam(name = "productName", defaultValue = "", required = false) String productNameContaining,
			@RequestParam(name = "productSize", required = false) int productSize,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "studyLevel", defaultValue = "", required = false) String studyLevel,
			Pageable pageable) {
		try {
			return storeService.getStoreSales(storeId, productNameContaining, productSize, dateFrom, dateTo,
					studyLevel, pageable);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@PostMapping("/stores/{storeId}/products")
	public ResponseEntity<StoreProductHistory> addStoreProduct(@PathVariable long storeId, @Valid @RequestBody StoreProductHistory storeProduct) {

		StoreProductHistory createdStoreProduct = storeService.addStoreProduct(storeId, storeProduct);
		return new ResponseEntity<StoreProductHistory>(createdStoreProduct, HttpStatus.OK);

	}
	
	@PostMapping("/stores/{storeId}/sales")
	public ResponseEntity<StoreSales> addStoreProduct(@PathVariable long storeId, @Valid @RequestBody StoreSales storeSales) {

		StoreSales createdStoreSales = storeService.addStoreSales(storeId, storeSales);
		return new ResponseEntity<StoreSales>(createdStoreSales, HttpStatus.OK);

	}
	
//	@PutMapping("/stores/products/{productId}")
//	public ResponseEntity<StoreProductHistory> updateStoreProduct(@PathVariable long productId, @Valid @RequestBody StoreProductHistory storeProduct) {
//		StoreProductHistory updatedStoreProduct = storeService.updateStoreProduct(productId, storeProduct);
//		return new ResponseEntity<StoreProductHistory>(updatedStoreProduct, HttpStatus.OK);
//	}
//	
//	@PutMapping("/stores/products/{salesId}")
//	public ResponseEntity<StoreSales> updateStoreSales(@PathVariable long salesId, @Valid @RequestBody StoreSales storeSales) {
//		StoreSales updatedStoreSales = storeService.updateStoreSales(salesId, storeSales);
//		return new ResponseEntity<StoreSales>(updatedStoreSales, HttpStatus.OK);
//	}
	
	@DeleteMapping("/stores/products/{productId}")
	public void deleteStoreProduct(@PathVariable long productId) {
		storeService.deleteStoreProduct(productId);
	}
	
	
	@DeleteMapping("/stores/sales/{salesId}")
	public void deleteStoreSales(@PathVariable long salesId) {
		storeService.deleteStoreSales(salesId);
	}

}
